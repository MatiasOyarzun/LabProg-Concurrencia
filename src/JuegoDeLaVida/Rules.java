package JuegoDeLaVida;
public class Rules {
    // Arreglo para representar regla que si una celula viva puede seguir viva
    public static final int[] LIVEAMOUNT = {2, 3};
    // Constante para representar regla que si una celula muerta puede revivir
    public static final int REBORNAMOUNT = 3;
    
    /** 
     * @author MatiasOyarzun - FAI-1950
     * @param cantidad
     * @return 
     */
    
    
    // Permite verificar si puede seguir viviendo o no
    public static boolean canLive(int amount){
        boolean res = false;
        int size = LIVEAMOUNT.length;
        
        for (int i = 0; i < size && !res; i++) {
            if (amount == LIVEAMOUNT[i]) {
                res = true;
            }
        }
        
        return res;
    }
    
    // Permite verificar si puede renacer o no
    public static boolean canReborn(int amount){
        boolean res = false;
        if (amount == REBORNAMOUNT) {
            res = true;
        }
        return res;
    }
    
}
