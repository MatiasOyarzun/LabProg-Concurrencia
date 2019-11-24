package JuegoDeLaVida;

/**
 *
 * @author MatiasOyarzun - FAI-1950
 */
public class CellID {
    // Representan ids X e Y
    public int idX;
    public int idY;
    
    // Constructor de la celula con ids
    public CellID(int x, int y){
        this.idX = x;
        this.idY = y;
    }
    
    // Obtener id X
    public int getX(){
        return this.idX;
    }
    
    // Obtener id Y
    public int getY(){
        return this.idY;
    }
}
