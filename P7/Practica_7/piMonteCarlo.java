public class piMonteCarlo
{
   public static void main(String[] args){
      int intentos = 6000000;
      int puntos   = 0;
      for(int i=0; i<intentos; i++){
        double cx = Math.random();
        double cy = Math.random();
        if(Math.pow(cx, 2)+Math.pow(cy, 2)<=1)puntos++;
      }	      
      System.out.println(4.0*puntos/intentos);
      
    }
}
