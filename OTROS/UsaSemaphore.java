package com.uca;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class UsaSemaphore implements Runnable {
	Semaphore semaphore = new Semaphore(1);
	RecursoSemaphore r = new RecursoSemaphore();

	@Override
	public void run() {
		while (r.valor() < r.max()) {
			try {
				semaphore.acquire();
				r.aumentar();
				System.out.println(r.valor());
			} catch (InterruptedException e) {
			} finally {
				semaphore.release();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int n = Runtime.getRuntime().availableProcessors();
		UsaSemaphore usa = new UsaSemaphore();
		ArrayList<Thread> misHIlos = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			misHIlos.add(new Thread(usa));
			misHIlos.get(i).start();
			misHIlos.get(i).join();
		}
		


		

	}

}
