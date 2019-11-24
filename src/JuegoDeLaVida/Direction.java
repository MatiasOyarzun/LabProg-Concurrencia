package JuegoDeLaVida;

/**
 * Enumerado utilizado para representar los distintos puntos cartesianos
 * en los que se debe fijar una celula, para actualizar su estado
 * 
 * @author MatiasOyarzun - FAI-1950
 */
public enum Direction {
    // Defino constantes para cada punto cartesiano
    NORTHWEST(-1, -1),
    NORTH(0, -1),
    NORTHEAST(1, -1),
    EAST(1, 0),
    SOUTHEAST(1, 1),
    SOUTH(0, 1),
    SOUTHWEST(-1, 1),
    WEST(-1, 0);
    
    // Representan posiciones X e Y
    public final int dirX;
    public final int dirY;
    
    // Constructor de las direcciones
    private Direction(int posX, int posY){
        this.dirX = posX;
        this.dirY = posY;
    }
}
