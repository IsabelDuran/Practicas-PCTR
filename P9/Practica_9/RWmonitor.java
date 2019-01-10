/**
 * @(#)RWmonitor.java
 * @author M. Ben-Ari (adaptado)
 * @version 1.00 2006
 */

class RWMonitor {
  volatile int readers = 0;
  volatile boolean writing = false;

  synchronized void StartRead() {
    while (writing)
      try {
         wait();
      } catch (InterruptedException e) {}
    readers = readers + 1;
    System.out.println("Lector inicia lectura...");
    notifyAll();
  }

  synchronized void EndRead() {
    readers = readers - 1;
    if (readers == 0) notifyAll();
    System.out.println("Lector finaliza lectura...");
  }
  synchronized void StartWrite() {
    while (writing || (readers != 0))
      try {
         wait();
      } catch (InterruptedException e) {}
    writing = true;
    System.out.println("Escritor inicia escritura...");
  }
  synchronized void EndWrite() {
    writing = false;
    notifyAll();
    System.out.println("Escritor finaliza escritura...");
  }
}