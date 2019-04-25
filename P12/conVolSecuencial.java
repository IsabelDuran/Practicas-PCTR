import java.util.Random;

/**
 * @author isa
 *
 */
public class conVolSecuencial {
	static final int LIM_MAX = 20;
    static final int LIM_INF = -20;
    
    static Random r = new Random();
    
    /**
     * @param matriz
     */
    static void inicializarMatriz(int[][] matriz){
    	for(int i = 0; i < 10000; i++)
    		for(int j = 0; j < 10000; j++)
    			matriz[i][j] = -20 + r.nextInt(40);
    		
    }
    
    /**
     * @param matriz
     */
    static void imprimirMatriz(int[][] matriz) {
    	for(int i = 0; i < 10000; i++)
    		for(int j = 0; j < 10000; j++)
    			System.out.println(matriz[i][j]);
    }
    
    /**
     * @param matIn
     * @param matOut
     * @param fila
     * @param columna
     */
    static void celdasAdyacentes(int[][] matIn, int[][] matOut, int fila, int columna){	
    	for (int i = 0; i < matOut.length; i++)
    		for (int j = 0; j < matOut.length; j++) 
    			matOut[i][j] = 0;
    	
    	for(int i = -1; i <= 1; i++)
    		for(int j = -1; j <= 1; j++) {
    			if((fila + i) >= 0 && (fila + i) < 10000 && (columna + j) >= 0 && (columna + j) < 10000)
    				matOut[i + 1][j + 1] = matIn[fila + i][columna + j];
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
     * @param matInicial
     * @param matOut
     * @param filtro
     */
    static void aplicarFiltro(int[][] matInicial, int[][] matOut, int[][] filtro) {
    	int[][] matrizAdyacentes = new int[3][3];
    	
    	for(int i = 0; i < matInicial.length; i++)
    		for(int j = 0; j < matInicial.length; j++) {
    			celdasAdyacentes(matInicial, matrizAdyacentes, i, j);
    			matOut[i][j] = sumarAdyacentes(matrizAdyacentes, filtro);
    			
    		}
    }
    
    /**
     * @param matInicial
     * @param matConvolucionadaSobel
     * @param matConvolucionadaSharpen
     */
    static void convolucionarMatriz(int[][] matInicial, int[][] matConvolucionadaSobel, int[][] matConvolucionadaSharpen) {
    	int[][] sobel = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    	int[][] sharpen = {{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}};
    	
    	aplicarFiltro(matInicial, matConvolucionadaSobel, sobel);
    	aplicarFiltro(matInicial, matConvolucionadaSharpen, sharpen);
    	
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
    	int[][] matriz = new int[10000][10000];
        int[][] matConvolucionadaSobel = new int[10000][10000];
        int[][] matConvolucionadaSharpen = new int[10000][10000];
        
        inicializarMatriz(matriz);
        
        double tInicio;
        double tFin;
        tInicio = System.currentTimeMillis();
        convolucionarMatriz(matriz, matConvolucionadaSobel, matConvolucionadaSharpen);
        tFin = System.currentTimeMillis();
        System.out.println(tFin - tInicio);

	}
}
