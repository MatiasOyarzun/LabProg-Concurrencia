package JuegoDeLaVida;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MatiasOyarzun - FAI-1950
 */
public class LifeGame {

    // Matriz completa (siempre es cuadrada y multiplo del tamaño de cada tablero controlador)
    private final Cell[][] world;
    // Arreglo de los tableros controladores (cada tarea que controlara sus celulas)
    private final MiniBoardController[][] boardControllers;
    // Tamaño de cada tablero controlador (Ej. si es 3, sera una matriz de 3x3)
    private static final int SIZEMINIBOARD = 3;
    /** Barrera para permitir que el controlador de turnos y tableros controladores 
        esperen a que todos terminen de evolucionar celulas **/
    private final CyclicBarrier passTurns;
    /** Barrera para permitir que el controlador de turnos y tableros controladores 
        esperen a que todos terminen de preparar la evolucion de las celulas **/
    private final CyclicBarrier prepareTurns;
    
    /*
    // Executor que controlara los hilos de tableros controladores
    private final ExecutorService boardsExecutor;
    // Executor que controlara el controlador de turnos
    private final ExecutorService timeExecutor;
    */
    
    // Factor utilizado para luego crear el turnManager y determinar la velocidad de cada turno
    private final double fct;

    // Constructor del juego de la vida
    public LifeGame(int size, LinkedList<CellID> cells, double fct) {
        this.world = new Cell[size * SIZEMINIBOARD][size * SIZEMINIBOARD];
        this.boardControllers = new MiniBoardController[size][size];
        initializeBoards(size);
        initializeGame(size * SIZEMINIBOARD, cells);
        this.passTurns = new CyclicBarrier((size*size) + 1);
        this.prepareTurns = new CyclicBarrier((size*size) + 1);
        /*this.boardsExecutor = Executors.newFixedThreadPool(size*size);
        this.timeExecutor = Executors.newSingleThreadExecutor();*/
        this.fct = fct;
    }

    /** Inicializador del juego, creo celulas en cada lugar de la matriz completa 
        y luego revivo aquellas que me indica la lista de celulas ID **/
    private void initializeGame(int size, LinkedList<CellID> cells) {
        int divisionResRow, divisionResCollumn, i, j;
        MiniBoardController board;
        
        for (i = 0; i < size; i++) {
            divisionResRow = i / SIZEMINIBOARD;
            for (j = 0; j < size; j++) {
                divisionResCollumn = j / SIZEMINIBOARD;
                board = this.boardControllers[divisionResRow][divisionResCollumn];
                this.world[i][j] = new Cell(i, j, false, board);
            }
        }
        
        for (CellID cell : cells) {
            i = cell.getX();
            j = cell.getY();
            
            this.world[i][j].revive();
        }
    }

    /** Inicializador de cada tablero controlador con la posicion que debera verificar, 
        la matriz completa y el juego de la vida **/
    private void initializeBoards(int size) {
        for (int i = 0; i < size; i++) {
            int x = i * SIZEMINIBOARD;
            for (int j = 0; j < size; j++) {
                int y = j * SIZEMINIBOARD;
                this.boardControllers[i][j] = new MiniBoardController(x, y, this.world, this);
            }
        }
    }

    // Obtener matriz completa
    public Cell[][] getWorld() {
        return this.world;
    }

    /** Metodo invocado por el controlador de turnos y por cada tablero controladro, para 
        que esperen hasta que todas las celulas se hayan preparado para evolucionar **/ 
    public void prepareTurn() {
        try {
            this.prepareTurns.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(LifeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Metodo invocado por el controlador de turnos y por cada tablero controlador, para
        que esperen hasta que todas las celulas hayan evolucionado **/
    public void passTurn() {
        try {
            this.passTurns.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(LifeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para que comience el juego (agrega tareas a executors) 
    public void startGame() {
        int size = this.boardControllers.length;
        Thread timer, boards;
        //this.timeExecutor.execute(new TurnManager(this, this.fct));

        timer = new Thread(new TurnManager(this, this.fct));
        timer.start();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boards = new Thread(this.boardControllers[i][j]);
                boards.start();
                //this.boardsExecutor.execute(this.boardControllers[i][j]);
            }
        }

        /*this.timeExecutor.shutdown();
        this.boardsExecutor.shutdown();*/

    }

    @Override
    public String toString() {
        int tam = this.world.length;
        String res = "";
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                res += "    " + this.world[i][j].toString();
            }
            res += "\n\n";
        }
        return res;
    }
}
