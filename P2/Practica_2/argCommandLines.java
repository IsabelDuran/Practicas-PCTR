
public class argCommandLines {
    
    public static void main(String[] args) {
        System.out.println("Leyendo la linea de comandos... ");
        
        // Comprobamos que nos dan EXACTAMENTE los argumentos necesarios
        if(args.length != 2){
            System.out.println("Se nesitan dos argumentos numericos!");
            System.exit(0);}
        
        // Mostramos la suma de los argumentos...
        int int1 = Integer.parseInt(args[0]);
        int int2 = Integer.parseInt(args[1]);
        int sum = int1 + int2;
        System.out.println("Suma = " + sum);   
    }
}