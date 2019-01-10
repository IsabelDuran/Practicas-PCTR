/*volcadoRedSecuencial.java
 *@author A.T.
*/
import java.util.*;
import java.io.*;
import java.net.*;

public class volcadoRedSecuencial{
   
  public static void navegarURL(String d, int i){
       
    String dir=d;
    String datos;
    RandomAccessFile volcado;
    URL url;
    Integer j=new Integer(i);
    
    try{
        url =  new URL(dir);
        System.out.println("Contactando con "+dir);
        
        String name = j.toString()+".html";
        volcado = new RandomAccessFile(name, "rw");
        BufferedReader lector = new BufferedReader(
	     new InputStreamReader(url.openStream()));		      		     
        do{
           datos = lector.readLine();
           if(datos!=null)volcado.writeChars(datos);   
        }while(datos!=null);
        volcado.close();
      }catch(IOException e){}  
      
  }
  
  
  
  public static void main(String[] args){
    int cont=0;
    long iniTiempo = System.nanoTime();	  
    try {
    	 String linea=" ";      
    	 RandomAccessFile direcciones = new RandomAccessFile("direccionesRed.txt","r");
         while(linea!=null){
           linea =(String)direcciones.readLine();
           if(linea!=null)navegarURL(linea, cont);
           cont++;
         }
         direcciones.close();
        }catch (FileNotFoundException e) {}
         catch (IOException e) {}
    long finTiempo = System.nanoTime();
    System.out.println("Tiempo Total (segundos): "+(finTiempo-iniTiempo)/1.0e9);
  }  
}
