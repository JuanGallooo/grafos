package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelOpcionesDark extends JPanel implements ActionListener{
	public static final String SIGUIENTE="SIGUIENTE";
	public static final String ANTERIOR="ANTERIOR";
	public static final String ORIGINAL="ORIGINAL";
	public static final String AHORRATIVO="AHORRATIVO";
	public static final String SENDINGMAIL="SENDINGMAIL";
	
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private JButton btnOriginal;
	private JButton btnAhorrativo;
	private JButton btnSending;
	
	private VentanaPrincipal principal;
	
	public PanelOpcionesDark(VentanaPrincipal p) {
		principal= p;
		TitledBorder borde= new TitledBorder("Opciones");
		setLayout(new GridLayout(1, 5));
	    setBorder(borde);
	    
	    btnSiguiente= new JButton("Siguiente");
	    btnSiguiente.addActionListener(this);
	    btnSiguiente.setActionCommand(SIGUIENTE);
	    btnAnterior= new JButton("Anterior");
	    btnAnterior.addActionListener(this);
	    btnAnterior.setActionCommand(ANTERIOR);
	    btnOriginal= new JButton("Caminos Originales");
	    btnOriginal.addActionListener(this);
	    btnOriginal.setActionCommand(ORIGINAL);
	    btnAhorrativo= new JButton("Caminos Ahorrativos");
	    btnAhorrativo.addActionListener(this);
	    btnAhorrativo.setActionCommand(AHORRATIVO);
	    btnSending= new JButton("Problema Sending E-mail");
	    btnSending.addActionListener(this);
	    btnSending.setActionCommand(SENDINGMAIL);
	    
	    add(btnAnterior);
	    add(btnOriginal);
	    add(btnAhorrativo);
	    add(btnSending);
	    add(btnSiguiente);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accion= e.getActionCommand();
		if(accion.equals(ANTERIOR)) principal.problemaAnterior1();
		else if(accion.equals(ORIGINAL)) principal.problemaOriginal1();
		else if( accion.equals(AHORRATIVO)) principal.problemaAhorrativo1();
		else if( accion.equals(SIGUIENTE)) principal.problemaSiguiente1();
		else if( accion.equals(SENDINGMAIL)) principal.reiniciar();
	}

}
