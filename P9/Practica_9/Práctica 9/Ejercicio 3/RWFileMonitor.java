
import java.util.*;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.*; //añadir para las excepciones de fichero

class RWFileMonitor {
  volatile int readers = 0;
  volatile boolean writing = false;

  static File f = new File("fichero.txt");
  static RandomAccessFile fichero;

  RWFileMonitor() 
  {
    try
    {
      fichero = new RandomAccessFile(f,"rw");     
    } catch(FileNotFoundException e)
      {
        System.out.println("ERROR. Fichero no encontrado");
      }

  }

  synchronized void StartRead() {
    String cadena= " ";
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
    
  }

  synchronized void EndWrite() {
    writing = false;
    notifyAll();
    System.out.println("Escritor finaliza escritura...");
  }
}