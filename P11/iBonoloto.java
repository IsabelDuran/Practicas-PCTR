//package bonoloto;

import java.rmi.*;

public interface iBonoloto extends Remote {

	  public boolean compApuesta(int[] apuesta)  throws RemoteException;
}
