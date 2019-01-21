/**
*
*	Ventana para la interfaz gráfica del cliente
*
*	@author José Miguel Aragón Jurado
*	@version 1.0
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaCliente extends JFrame{

	/**	Constructor de la clase.
	*	@param titulo Titulo a mostrar en la pantalla.
	*	@param texto Texto a mostrar en la pantalla.
	*/
	public VentanaCliente(String titulo,String texto){
		super(titulo);
		setLayout(new FlowLayout());
		JLabel noedittext = new JLabel(texto);
		this.add(noedittext);
	}
}