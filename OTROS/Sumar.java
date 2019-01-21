package com.uca;

import java.util.concurrent.Callable;

public class Sumar implements Callable<Integer>{

	private int x;
	private int y;
	
	
	public Sumar(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	@Override
	public Integer call() throws Exception {
		Thread.sleep(10000);
		return x;
	}

}