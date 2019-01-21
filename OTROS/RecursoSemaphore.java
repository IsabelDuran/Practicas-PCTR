package com.uca;

import java.util.concurrent.Semaphore;

public class RecursoSemaphore {
	private volatile int n = 0;
	private final int max = 100;
	
	public void aumentar(){
		n = n + 1;
	}
	
	public int valor() {
		return n;
	}
	
	public int max() {
		return max;
	}
	
}
