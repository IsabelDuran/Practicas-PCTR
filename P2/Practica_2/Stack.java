import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * La clase Stack modela una implementación de pila LIFO de objetos de clase Integer, porque Juanma G. Hutchison me la ha pedido. 
 * @author  A.T. Sinonimia: Ilota Docente
 * @version 1.0
 * @since October 30, 2017
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html">Ver tambien Clase Stack Predefinida </a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">Ver tambien Clase ArrayList </a>
 * 
 * NOTA: Esta implementacion no es segura frente a hebras concurrentes. 
 */
public class Stack 
{   
	//este comentario no generara informacion via javadoc, pero podria ilustrar el uso de "elements"
	private ArrayList<Integer> elements = new ArrayList<Integer>();
	
		
	/**
	 * Inserta un elementos sobre el tope de la pila. 
	 * @param   item   el elemento a insertar.
	 */
	public void push(Integer item){
	  this.elements.add(item); 
	}

	/**
	 * Elimina el tope de la pila y lo devuelve como un objeto de clase Integer. 
	 * @return  El objeto en el tope de la pila.
	 * @exception  NoSuchElementException  si la pila esta vacia.
	 */
	public Integer pop() throws NoSuchElementException{	
		int length = this.elements.size();
		if (length == 0) throw new NoSuchElementException();
		return this.elements.remove(length - 1);
	}

	/**
	 * Devuelve el tope de la pila pero no lo elimina. 
	 * @return  el objeto situado en el tope de la pla. 
	 * @exception  NoSuchElementException  si la pila esta vacia.
	 */
	public Integer peek() throws NoSuchElementException
	{	int length = this.elements.size();
		if (length == 0) throw new NoSuchElementException();
		return this.elements.get(length - 1);
	}

	/**
	 * Comprueba si la pila esta vacia.
	 * @return  true si la pila esta vacia, y falso en otro caso.
	 */
	public boolean isEmpty() {	
	 return this.elements.isEmpty();
	}
	
	/**
	 * Comprueba si la pila esta vacia. Al estar derogado, filtra la informacion parametrica y el modo de uso.
	 * @param data este parametro no vale para nada
	 * @return  1 si la pila esta vacia, y 0 en otro caso.
	 * @deprecated este metodo esta derogado
	 */
	 public int isEmpty(int data) {	
	  if (this.elements.isEmpty()) return 1;
	    else return 0;
	}

}