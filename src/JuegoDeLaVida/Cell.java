package JuegoDeLaVida;

/**
 * 
 * @author MatiasOyarzun - FAI-1950
 */

public class Cell {
    // Representa estado actual de la celula
    private boolean alive;
    // Representa el estado siguiente de la celula, para luego evolucionar a este
    private boolean nextState;
    // Representan posiciones X e Y dentro de su tablero controlador
    private final int posX;
    private final int posY;
    // Tablero en donde se encuentra la celula
    private final MiniBoardController board;
    
    // Constructor de celulas con su posicion, indicador de vida y tablero controlador
    public Cell(int posX, int posY, boolean alive, MiniBoardController board){
        this.posX = posX;
        this.posY = posY;
        this.alive = alive;
        this.nextState = alive;
        this.board = board;
    }
    
    // Metodo para verificar si esta con vida
    public boolean isAlive(){
        return this.alive;
    }
    
    // Metodo para obtener coordenada X en la matriz
    public int getX(){
        return this.posX;
    }
    
    // Metodo para obtener coordenada Y en la matriz
    public int getY(){
        return this.posY;
    }
    
    // Metodo para evolucionar a la celula
    public void evolve(){
        if (this.alive != this.nextState) {
            this.alive = this.nextState;
        }
    }
    
    // Metodo para preparar la evolucion de la celula, segun el tablero en que se encuentra
    public void prepareToEvolve(){
        int cantidad = this.board.searchNeighborsCells(this.posX, this.posY);
        if (this.alive) {
            this.nextState = Rules.canLive(cantidad);
        }else{
            this.nextState = Rules.canReborn(cantidad);
        }
    }
    
    // Metodo para revivir celula (Uso en patrones)
    public void revive(){
        this.alive = true;
    }
    
    @Override
    public String toString(){
        String res;
        if (this.alive) {
            // Muestra "O" de color verde
            res = "\u001B[32m"+"O";
        } else {
            // Muestra "." de color blanco
            res = "\u001B[37m"+".";
        }
        return res;
    }
}