package interfaz;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import mundo.Controlador;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{

	private PanelVer panelVer;
	private PanelOpciones panelOpciones;
	private Controlador mundo;
	
	public VentanaPrincipal() {
		setSize(800, 500);
		setTitle("Problemas UVA");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(true);
		try {
		mundo= new Controlador();
		panelVer=new PanelVer(this);
		add(panelVer,BorderLayout.CENTER);
		pack();
		HiloBombilla hilo= new HiloBombilla(this);
		hilo.start();
		} catch (Exception e) {
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
}
