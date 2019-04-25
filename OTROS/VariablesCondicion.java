package com.uca;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class VariablesCondicion {
	private volatile int n = 0;
	ReentrantLock cerrojo = new ReentrantLock();
	Condition noLleno = cerrojo.newCondition();
	Condition noVacio = cerrojo.newCondition();
	
	public void incrementar() {
		cerrojo.lock();
		try {
			while(n == max())
				noLleno.await();
			n = n + 1;
			noVacio.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrojo.unlock();
		}
	}
	

	public void decrementar() {
		cerrojo.lock();
		try {
			while(n == 0)
				noVacio.await();
			n = n - 1;
			noLleno.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
