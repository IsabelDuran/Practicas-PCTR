package com.uca;

public class Monitor {
	private volatile int nLectores = 0;
	private volatile boolean estanEscribiendo = false;

	synchronized public void leer() {
		while (estanEscribiendo) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		nLectores = nLectores + 1;
		notifyAll();
	}

	synchronized public void dejarDeLeer() {
		while (nLectores == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}
		nLectores = nLectores - 1;
		notifyAll();
	}

	synchronized public void escribir() {
		while (estanEscribiendo || nLectores != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		estanEscribiendo = true;
	}

	synchronized public void dejarEscribir() {
		estanEscribiendo = false;
		notifyAll();
	}

	synchronized public int getNlectores() {
		return nLectores;
	}

}
