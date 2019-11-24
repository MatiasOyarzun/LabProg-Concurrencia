package JuegoDeLaVida;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MatiasOyarzun - FAI-1950
 * 
 * Clase para la creacion de patrones en el juego de la vida
 * 
 */
public class Patterns {

    private static final int SIZEMINIBOARD = 3;
    private static int size;
    private static Scanner sc = new Scanner(System.in);

    public static int selectPattern(LinkedList<CellID> cells) {
        int option;
        boolean repeat = true;

        System.out.println("Choose pattern: ");
        System.out.println("1. Custom");
        System.out.println("2. Glider");
        System.out.println("3. Creator");
        System.out.println("4. Infinite Growth");
        System.out.println("5. AK47 Reaction");
        System.out.println("6. Replicator");
        System.out.println("7. Ants");
        System.out.println("8. Centinal");
        System.out.println("9. Random");

        while (repeat) {
            option = sc.nextInt();
            switch (option) {
                case 1:
                    custom(cells);
                    repeat = false;
                    break;
                case 2:
                    glider(cells);
                    repeat = false;
                    break;
                case 3:
                    creator(cells);
                    repeat = false;
                    break;
                case 4:
                    infiniteGrowth(cells);
                    repeat = false;
                    break;
                case 5:
                    ak47Reaction(cells);
                    repeat = false;
                    break;
                case 6:
                    replicator(cells);
                    repeat = false;
                    break;
                case 7:
                    ants(cells);
                    repeat = false;
                    break;
                case 8: 
                    centinal(cells);
                    repeat = false;
                    break;
                case 9:
                    random(cells);
                    repeat = false;
                    break;
                default:
                    System.out.println("Incorrect option");
                    break;
            }
        }

        return size;
    }

    private static int glider(LinkedList<CellID> cells) {
        cells.clear();

        size = 2;

        cells.add(new CellID(0, 0));
        cells.add(new CellID(0, 1));
        cells.add(new CellID(0, 2));
        cells.add(new CellID(1, 0));
        cells.add(new CellID(2, 1));

        return size;
    }

    private static int custom(LinkedList<CellID> cells) {
        cells.clear();
        boolean repeat = true, wrongNumber, response;
        int y = 0, x = 0, option, count = 0;
        
        while (repeat) {
            wrongNumber = true;
            while (wrongNumber) {
                System.out.println("Enter row (number >= 1):");
                y = sc.nextInt();
                System.out.println("Enter collumn (number >= 1):");
                x = sc.nextInt();
                if (y >= 1 && x >= 1) {
                    wrongNumber = false;
                }else{
                    System.out.println("The number you choose must be greater than 1");
                }
            }
            y--;
            x--;
            cells.add(new CellID(y, x));
            if (count < y) {
                count = y;
            }else{
                if (count < x) {
                    count = x;
                }
            }
            response = true;
            while (response) {    
                System.out.println("Do you want to continue creating life?");
                System.out.println("1. Yes\n"
                        + "2. No");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        response = false;
                        break;
                    case 2:
                        response = false;
                        repeat = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }    
        }
        
        int res = count / SIZEMINIBOARD;
        int mod = count % SIZEMINIBOARD;
        
        if (mod != 0) {
            size = res + 1;
        }else{
            size = res;
        }

        return size;
    }

    private static int random(LinkedList<CellID> cells) {
        Random random = new Random();
        size = 6;
        int tam = size * SIZEMINIBOARD;
        boolean alive;

        cells.clear();

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                alive = random.nextBoolean() && random.nextBoolean() && random.nextBoolean();
                if (alive) {
                    cells.add(new CellID(i, j));
                }
            }
        }

        return size;
    }
    
    private static int centinal(LinkedList<CellID> cells){
        cells.clear();
        
        size = 20;
        
        cells.add(new CellID(2, 2));
        cells.add(new CellID(2, 3));
        cells.add(new CellID(3, 3));
        cells.add(new CellID(4, 3));
        cells.add(new CellID(5, 4));
        cells.add(new CellID(5, 5));
        cells.add(new CellID(4, 5));
        
        cells.add(new CellID(18, 2));
        cells.add(new CellID(18, 3));
        cells.add(new CellID(17, 3));
        cells.add(new CellID(16, 3));
        cells.add(new CellID(15, 4));
        cells.add(new CellID(15, 5));
        cells.add(new CellID(16, 5));
        
        cells.add(new CellID(5, 14));
        cells.add(new CellID(6, 14));
        cells.add(new CellID(6, 13));
        cells.add(new CellID(7, 12));
        cells.add(new CellID(7, 13));
        cells.add(new CellID(8, 13));
        cells.add(new CellID(8, 14));
        cells.add(new CellID(8, 17));
        cells.add(new CellID(8, 18));
        
        cells.add(new CellID(12, 14));
        cells.add(new CellID(12, 13));
        cells.add(new CellID(12, 17));
        cells.add(new CellID(12, 18));
        cells.add(new CellID(13, 13));
        cells.add(new CellID(14, 13));
        cells.add(new CellID(13, 12));
        cells.add(new CellID(14, 14));
        cells.add(new CellID(15, 14));
        
        cells.add(new CellID(4, 27));
        cells.add(new CellID(4, 28));
        cells.add(new CellID(5, 27));
        cells.add(new CellID(5, 28));
        
        cells.add(new CellID(15, 27));
        cells.add(new CellID(15, 28));
        cells.add(new CellID(16, 27));
        cells.add(new CellID(16, 28));
        
        
        cells.add(new CellID(5, 41));
        cells.add(new CellID(5, 42));
        cells.add(new CellID(6, 41));
        cells.add(new CellID(6, 43));
        cells.add(new CellID(7, 43));
        cells.add(new CellID(8, 43));
        cells.add(new CellID(8, 42));
        cells.add(new CellID(8, 41));
        
        cells.add(new CellID(12, 41));
        cells.add(new CellID(12, 42));
        cells.add(new CellID(12, 43));
        cells.add(new CellID(13, 43));
        cells.add(new CellID(14, 43));
        cells.add(new CellID(14, 41));
        cells.add(new CellID(15, 41));
        cells.add(new CellID(15, 42));
        
        cells.add(new CellID(5, 50));
        cells.add(new CellID(4, 50));
        cells.add(new CellID(5, 51));
        cells.add(new CellID(4, 52));
        cells.add(new CellID(3, 52));
        cells.add(new CellID(2, 52));
        cells.add(new CellID(2, 53));
        
        cells.add(new CellID(15, 50));
        cells.add(new CellID(15, 51));
        cells.add(new CellID(16, 50));
        cells.add(new CellID(16, 52));
        cells.add(new CellID(17, 52));
        cells.add(new CellID(18, 52));
        cells.add(new CellID(18, 53));

        return size;
    }
    
    private static int infiniteGrowth(LinkedList<CellID> cells) {
        cells.clear();
        
        size = 6;
        
        cells.add(new CellID(6, 1));
        cells.add(new CellID(5, 3));
        cells.add(new CellID(6, 3));
        cells.add(new CellID(4, 5));
        cells.add(new CellID(3, 5));
        cells.add(new CellID(2, 5));
        cells.add(new CellID(3, 7));
        cells.add(new CellID(2, 7));
        cells.add(new CellID(1, 7));
        cells.add(new CellID(2, 8));
        
        return size;
    }
    
    private static int ak47Reaction(LinkedList<CellID> cells){
        cells.clear();
        
        size = 6;
        
        cells.add(new CellID(1, 6));
        cells.add(new CellID(2, 5));
        cells.add(new CellID(2, 7));
        cells.add(new CellID(3, 4));
        cells.add(new CellID(4, 4));
        cells.add(new CellID(5, 4));
        cells.add(new CellID(3, 8));
        cells.add(new CellID(4, 8));
        cells.add(new CellID(5, 8));
        cells.add(new CellID(6, 7));
        cells.add(new CellID(6, 5));
        cells.add(new CellID(7, 6));
        cells.add(new CellID(9, 4));
        cells.add(new CellID(9, 3));
        cells.add(new CellID(10, 4));
        cells.add(new CellID(11, 3));
        cells.add(new CellID(11, 2));
        cells.add(new CellID(11, 1));
        cells.add(new CellID(12, 1));
        cells.add(new CellID(12, 1));
        cells.add(new CellID(11, 9));
        cells.add(new CellID(12, 9));
        cells.add(new CellID(11, 10));
        cells.add(new CellID(12, 10));
        
        return size;
    }
    
    private static int ants(LinkedList<CellID> cells) {
        cells.clear();
       
        size = 5;
        
        cells.add(new CellID(2, 2));
        cells.add(new CellID(2, 3));
        cells.add(new CellID(3, 4));
        cells.add(new CellID(3, 5));
        cells.add(new CellID(4, 5));
        cells.add(new CellID(4, 4));
        cells.add(new CellID(5, 3));
        cells.add(new CellID(5, 2));
        
        cells.add(new CellID(2, 7));
        cells.add(new CellID(2, 8));
        cells.add(new CellID(3, 9));
        cells.add(new CellID(3, 10));
        cells.add(new CellID(4, 10));
        cells.add(new CellID(4, 9));
        cells.add(new CellID(5, 7));
        cells.add(new CellID(5, 8));
        
        return size;
    }

    private static int creator(LinkedList<CellID> cells) {
        cells.clear();

        size = 14;

        cells.add(new CellID(5, 2));
        cells.add(new CellID(6, 2));
        cells.add(new CellID(5, 3));
        cells.add(new CellID(5, 12));
        cells.add(new CellID(6, 12));
        cells.add(new CellID(7, 12));
        cells.add(new CellID(4, 13));
        cells.add(new CellID(8, 13));
        cells.add(new CellID(3, 14));
        cells.add(new CellID(9, 14));
        cells.add(new CellID(3, 15));
        cells.add(new CellID(9, 15));
        cells.add(new CellID(6, 16));
        cells.add(new CellID(4, 17));
        cells.add(new CellID(8, 17));
        cells.add(new CellID(5, 18));
        cells.add(new CellID(6, 18));
        cells.add(new CellID(7, 18));
        cells.add(new CellID(6, 19));
        cells.add(new CellID(5, 22));
        cells.add(new CellID(4, 22));
        cells.add(new CellID(3, 22));
        cells.add(new CellID(3, 23));
        cells.add(new CellID(4, 23));
        cells.add(new CellID(5, 23));
        cells.add(new CellID(2, 24));
        cells.add(new CellID(6, 24));
        cells.add(new CellID(1, 26));
        cells.add(new CellID(2, 26));
        cells.add(new CellID(6, 26));
        cells.add(new CellID(7, 26));
        cells.add(new CellID(3, 36));
        cells.add(new CellID(4, 36));
        cells.add(new CellID(3, 37));
        cells.add(new CellID(4, 37));

        return size;
    }

    private static int replicator(LinkedList<CellID> cells) {
        cells.clear();
        
        size = 5;
        
        cells.add(new CellID(2, 4));
        cells.add(new CellID(2, 5));
        cells.add(new CellID(2, 6));
        cells.add(new CellID(3, 6));
        cells.add(new CellID(4, 6));
        cells.add(new CellID(3, 3));
        cells.add(new CellID(4, 2));
        cells.add(new CellID(5, 2));
        cells.add(new CellID(6, 2));
        cells.add(new CellID(6, 3));
        cells.add(new CellID(6, 4));
        cells.add(new CellID(5, 5));
        
        return size;
    }

}
