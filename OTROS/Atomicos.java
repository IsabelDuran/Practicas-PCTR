package com.uca;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomicos implements Runnable {
	static AtomicInteger at;
	static AtomicBoolean ab;
	
	Atomicos(int n){
		at = new AtomicInteger(n);
		ab = new AtomicBoolean(false);
	}
	
	@Override
	public void run() {
//		while(!at.compareAndSet(100, at.get()))
			at.incrementAndGet();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		int n = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(n);
		
		Atomicos atomicos = new Atomicos(0);
		
		for(int i = 0; i < 100; i++)
			executor.execute(atomicos);
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Resultado final: " + at.get());
	}

}
