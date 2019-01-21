package com.uca;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UsaMonitor implements Runnable {
	Monitor miMonitor = new Monitor();

	@Override
	public void run() {
		while (true) {
			miMonitor.leer();
			System.out.println(miMonitor.getNlectores());
			miMonitor.dejarDeLeer();
			System.out.println(miMonitor.getNlectores());
			miMonitor.escribir();
			System.out.println(miMonitor.getNlectores());
			miMonitor.dejarEscribir();
			System.out.println(miMonitor.getNlectores());
		}
	}

	public static void main(String[] args) {
		ArrayList<Thread> misHilos = new ArrayList<>();
		int n = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(n, n, 10000, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(n));

		for (int i = 0; i < n; i++)
			pool.execute(new UsaMonitor());

	}
}
