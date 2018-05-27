package interfaz;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.ActionMapUIResource;

import mundo.Controlador;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{

	private PanelVer panelVer;
	private PanelOpciones panelOpciones;
	private Controlador mundo;
	private int indice;
	private JPanel panelNuevo;
	
	public VentanaPrincipal() {
		setSize(1550, 1400);
		setTitle("Problemas UVA");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(true);
		try {
		panelNuevo= new JPanel();
		
			
        mundo= new Controlador();
		
		//Dark Roads
		panelVer=new PanelVer(this);
		panelVer.actualizar(0, true);
		panelOpciones= new PanelOpciones(this);
		indice=0;
		panelNuevo.add(panelVer,BorderLayout.CENTER);
		panelNuevo.add(panelOpciones, BorderLayout.SOUTH);
		HiloBombilla hilo= new HiloBombilla(this);
		hilo.start();
		add(panelVer,BorderLayout.CENTER);
		add(panelOpciones, BorderLayout.SOUTH);
//		add(panelNuevo,BorderLayout.CENTER);
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al leer los archivos", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		VentanaPrincipal ventana= new VentanaPrincipal();
		ventana.setVisible(true);
	}
	public Controlador getMundo() {
		return mundo;
	}
	public void setMundo(Controlador mundo) {
		this.mundo = mundo;
	}
	public void repintarPanelVer() {
		panelVer.repaint();
	}
	public void problemaAnterior1() {
		indice--;
		if(indice<0)indice=mundo.darkRoads().getGrafos().size()-1;
		panelVer.actualizar(indice, true);
	}
	public void problemaOriginal1() {
		panelVer.actualizar(indice, true);
	}
	public void problemaAhorrativo1() {
		panelVer.actualizar(indice, false);
	}
	public void problemaSiguiente1() {
		indice++;
		if((indice==mundo.darkRoads().getGrafos().size())) {
			indice=0;
		}
		panelVer.actualizar(indice, true);
	}
}
