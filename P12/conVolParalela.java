import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class conVolParalela implements Runnable {
	public static final int LIM_MAX = 20;
	public static final int LIM_INF = -20;
    public static int[][] matriz = new int[10000][10000];
    public static int[][] matConvolucionadaSobel = new int[10000][10000];
    public static int[][] matConvolucionadaSharpen = new int[10000][10000];
    
    private int inicio;
    private int fin;
    
    public conVolParalela(int i, int f) {
		inicio = i;
		fin = f;
	}
    
	@Override
	public void run() {
    	int[][] sobel = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    	int[][] sharpen = {{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}};
    	
    	aplicarFiltro(matriz, matConvolucionadaSobel, sobel, inicio, fin);
    	aplicarFiltro(matriz, matConvolucionadaSharpen, sharpen, inicio, fin);
		
	}
    
    static Random r = new Random();
    
    /**
     * @param matriz
     */
    static void inicializarMatriz(){
    	for(int i = 0; i < 10000; i++)
    		for(int j = 0; j < 10000; j++)
    			matriz[i][j] = -20 + r.nextInt(40);
    		
    }
    
    /**
     * @param matriz
     */
    static void imprimirMatriz() {
    	for(int i = 0; i < 10000; i++)
    		for(int j = 0; j < 10000; j++)
    			System.out.println(matriz[i][j]);
    }
    
    /**
     * @param matriz
     * @param matOut
     * @param fila
     * @param columna
     */
    static void celdasAdyacentes(int[][] matOut, int fila, int columna){	
    	for (int i = 0; i < matOut.length; i++)
    		for (int j = 0; j < matOut.length; j++) 
    			matOut[i][j] = 0;
    	
    	for(int i = -1; i <= 1; i++)
    		for(int j = -1; j <= 1; j++) {
    			if((fila + i) >= 0 && (fila + i) < 10000 && (columna + j) >= 0 && (columna + j) < 10000)
    				matOut[i + 1][j + 1] = matriz[fila + i][columna + j];
    		}		
    }
    
    /**
     * @param matrizAdyacentes
     * @param filtro
     * @return
     */
    static int sumarAdyacentes(int[][] matrizAdyacentes, int[][] filtro) {
    	int resultado = 0;
    	for(int i = 0; i < matrizAdyacentes.length; i++)
			for(int j = 0; j < matrizAdyacentes.length; j++)
				resultado += matrizAdyacentes[i][j] * filtro[i][j];
    	
    	if(resultado > LIM_MAX)
    		resultado = LIM_MAX;
    	if(resultado < LIM_INF)
    		resultado = LIM_INF;
    	
    	return resultado;
				
    }
    
    /**
     * @param matriz
     * @param matOut
     * @param filtro
     */
    static void aplicarFiltro(int[][] matriz, int[][] matOut, int[][] filtro, int inicio, int fin) {
    	int[][] matrizAdyacentes = new int[3][3];
    	
    	for(int i = inicio; i < fin; i++)
    		for(int j = 0; j < matriz.length; j++) {
    			celdasAdyacentes(matrizAdyacentes, i, j);
    			matOut[i][j] = sumarAdyacentes(matrizAdyacentes, filtro);
    			
    		}
    }
    
    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        int nHilos = Runtime.getRuntime().availableProcessors();
        ArrayList<Thread> hilos = new ArrayList<>();
        inicializarMatriz();
        
        int inicio = 0;
        int tamTarea = 10000/nHilos;
        
        double tInicio;
        double tFin;
        
        tInicio = System.currentTimeMillis();
        for(int i = 0; i < nHilos; i++)
        {
        	hilos.add(new Thread(new conVolParalela(inicio, inicio + tamTarea)));
        	hilos.get(i).start();
        	inicio = inicio + tamTarea;
        }
        
        for(int i = 0; i < nHilos; i++)
        	hilos.get(i).join();
        tFin = System.currentTimeMillis();
        
//        imprimirMatriz();
        System.out.println(tFin - tInicio);
        

	}
}
