package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelOpcionesSending extends JPanel implements ActionListener{
	public static final String SIGUIENTE="SIGUIENTE";
	public static final String ANTERIOR="ANTERIOR";
	public static final String SENDINGMAIL="SENDINGMAIL";
	
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private JButton btnSending;
	
	private VentanaPrincipal principal;
	
	public PanelOpcionesSending(VentanaPrincipal p) {
		principal= p;
		TitledBorder borde= new TitledBorder("Opciones");
		setLayout(new GridLayout(1, 3));
	    setBorder(borde);
	    
	    btnSiguiente= new JButton("Siguiente");
	    btnSiguiente.addActionListener(this);
	    btnSiguiente.setActionCommand(SIGUIENTE);
	    btnAnterior= new JButton("Anterior");
	    btnAnterior.addActionListener(this);
	    btnAnterior.setActionCommand(ANTERIOR);
	    btnSending= new JButton("Problema DarkRoads");
	    btnSending.addActionListener(this);
	    btnSending.setActionCommand(SENDINGMAIL);
	    
	    add(btnAnterior);
	    add(btnSending);
	    add(btnSiguiente);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accion= e.getActionCommand();
		if(accion.equals(ANTERIOR)) principal.problemaAnterior2();
		else if( accion.equals(SIGUIENTE)) principal.problemaSiguiente2();
		else if( accion.equals(SENDINGMAIL)) principal.reiniciar();
	}

}
