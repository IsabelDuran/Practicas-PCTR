package com.uca;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ArrayListAndSemaphore implements Runnable {
	static int n = 0;
	static int max = 100;
	Semaphore s = new Semaphore(1);

	@Override
	public void run() {
		while (n < max) {
			try {
				s.acquire();
				if(n < max) n = n + 1;
				System.out.println(n);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				s.release();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		int nHilos = Runtime.getRuntime().availableProcessors();
		ArrayList<Thread> hilos = new ArrayList<>();

		ArrayListAndSemaphore hilo = new ArrayListAndSemaphore();

		for (int i = 0; i < nHilos; i++) {
			hilos.add(new Thread(hilo));
			hilos.get(i).start();
		}
		
		for(int i = 0; i < nHilos; i++)
			hilos.get(i).join();

	}
	
}
