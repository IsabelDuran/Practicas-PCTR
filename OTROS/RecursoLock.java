package com.uca;

import java.util.concurrent.locks.ReentrantLock;

public class RecursoLock {
	private volatile int n = 0;
	ReentrantLock cerrojo = new ReentrantLock();
	
	public void incrementar() {
		cerrojo.lock();
		try {
			n = n + 1;
		} finally {
			cerrojo.unlock();
		}
	}
	
	public int valor() {
		int val = -4;
		cerrojo.lock();
		try {
			val = n;
		} finally {
			cerrojo.unlock();
			return val;
		}
	}
	
	public int max() {
		return 100;
	}
	
}
