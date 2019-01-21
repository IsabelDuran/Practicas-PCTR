/**
*
*	Ventana para la pantalla de carga del cliente.
*
*	@author José Miguel Aragón Jurado
*	@version 1.0
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaCarga extends JFrame{

	/**
	*	Constructor de la clase.
	*	@param titulo Titulo a mostrar en la pantalla.
	*	@param texto Texto a mostrar en la pantalla.
	*/
	public VentanaCarga(String titulo,String texto){
		super(titulo);
		setLayout(new FlowLayout());
		JLabel noedittext = new JLabel(texto);
		this.add(noedittext);
		this.setSize(300,100);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
}