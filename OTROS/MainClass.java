package com.uca;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainClass   {
	
	public static void main(String[] args) throws Exception {
		Sumar s = new Sumar(4, 8);
		ExecutorService executor = Executors.newFixedThreadPool(12);
		Future<Integer> r = executor.submit(s);
		
		while(!r.isDone())
		{
			System.out.println("no esta");

		}
		
		System.out.println(r.get());
	}
}