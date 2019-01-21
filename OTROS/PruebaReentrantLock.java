package com.uca;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class PruebaReentrantLock implements Runnable {
	RecursoLock r = new RecursoLock();
	ReentrantLock cerrojo = new ReentrantLock();

	@Override
	public void run() {
		try {
			cerrojo.lock();
			if (r.valor() < r.max()) {
				r.incrementar();
				System.out.println(r.valor());
			}
		} catch (Exception e) {
		} finally {
			cerrojo.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int n = Runtime.getRuntime().availableProcessors();
		
		//Número de hilos
		ExecutorService ejecutor = Executors.newFixedThreadPool(n);

		PruebaReentrantLock usa = new PruebaReentrantLock();

		
		//Número de tareas
		for (int i = 0; i < 100; i++)
			ejecutor.execute(new Thread(usa));
		ejecutor.shutdown();
	}

}
