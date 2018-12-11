//Ejemplo de clase que toma argumentos desde la linea de comandos
//Uso:   java lin_comando n siendo n un numero mayor que cero

public class lin_comando
{ //metodo interno de la clase; calcula el factorial recursivamente
  static int factorial (int n)
  { if(n==1) return 1;
    else return n*factorial (n-1);
  }
  public static void main (String[] args)
  {int Auxiliar;
   if(args.length!=1){
     System.err.println ("Uso java Factorial_n numero_entero");
     System.exit(-1);
    }
   Auxiliar=Integer.valueOf(args[0]).intValue(); 
   System.out.println("Resultado es: "+factorial(Auxiliar));
  }
}
