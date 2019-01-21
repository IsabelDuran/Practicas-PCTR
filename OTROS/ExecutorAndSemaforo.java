package com.uca;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExecutorAndSemaforo implements Runnable {
	static int n = 0;
	static int max = 100;
	Semaphore s = new Semaphore(1);
	
	@Override
	public void run() {
		try {
			s.acquire();
			n = n + 1;
			System.out.println(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			s.release();
		}
		
	}
	
	public static void main(String[] args) {
		int n = Runtime.getRuntime().availableProcessors();
		Executor executor = Executors.newFixedThreadPool(n);
		
		ExecutorAndSemaforo hilo = new ExecutorAndSemaforo();
		
		for(int i = 0; i < max; i++)
			executor.execute(hilo);
		
	}

}
