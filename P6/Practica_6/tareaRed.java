/*tareaRed.java
 @author A.Tomeu
*/
import java.util.*;
import java.io.*;
import java.net.*;

public class tareaRed implements Runnable{
   
  private String dir;
  private String datos;
  private RandomAccessFile volcado;
  private URL url;
  private Integer j;
   	
  public tareaRed(String d, int i){dir=d; j=new Integer(i);}
  
  public void run()
    {
      try{url =  new URL(dir);} catch(MalformedURLException e){}	  
      System.out.println("Contactando con "+dir);
      try{String name = j.toString()+".html";
      	  volcado = new RandomAccessFile(name, "rw");
          BufferedReader lector = new BufferedReader(
	      new InputStreamReader(url.openStream()));		      		     
      do{
         datos = lector.readLine();
         if(datos!=null)volcado.writeChars(datos);
      //System.out.println("escribiendo...");   
      }while(datos!=null);
      volcado.close();
     }catch(IOException e){}  
  }
 }
