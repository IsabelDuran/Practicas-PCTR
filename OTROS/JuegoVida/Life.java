import java.util.List;

public class Life {
	public static synchronized void nextGen(int filaInicio) {
		if(celLife.celulasVivas >= 0) {
		for (int i = 0; i < celLife.tam; i++) {
			int nCeldasVivas = 0;
			List<Integer> matrizAdyacente = celLife.celdasAdyacentes(filaInicio, i);
			
			nCeldasVivas = (int) matrizAdyacente.stream().filter((value) -> value == 1).count();
			
			if(celLife.mundo[filaInicio][i] == 1 && nCeldasVivas < 2 || nCeldasVivas > 3 && celLife.celulasVivas > 0) {
				celLife.mundo[filaInicio][i] = 0;
				celLife.celulasVivas--;
			}else if(celLife.mundo[filaInicio][i] == 0 && nCeldasVivas == 3) {
				celLife.mundo[filaInicio][i] = 1;
				celLife.celulasVivas++;
			}
		}}
	}

	public static synchronized void status() {
		System.out.println("Soy el hilo " + Thread.currentThread().getName() + ", mi prioridad es "
				+ Thread.currentThread().getPriority() + " y el estatus actual es: " + celLife.celulasVivas
				+ " células vivas\n");
	}
}
