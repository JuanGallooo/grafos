package interfaz;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mundo.Controlador;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{

	private PanelVerDark panelVerDark;
	private PanelVerSending panelVerSending;
	private PanelOpcionesDark panelOpciones;
	private PanelOpcionesSending panelOpcionesDos;
	private Controlador mundo;
	private int indice;
	
	public VentanaPrincipal() {
		
		int confirm= JOptionPane.showOptionDialog(this, "A que porblema desea entrar?", "Uva solutions", JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Problema DarkRoads","Problema Sending E-Mail","Salir"}, "Problema DarkRoads");
		if( confirm==0) {
			setSize(1100, 700);
			setTitle("Problemas UVA");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setResizable(true);
			try {
	        mundo= new Controlador(true);
			panelVerDark=new PanelVerDark(this);
			panelVerDark.actualizar(0, true);
			panelOpciones= new PanelOpcionesDark(this);
			indice=0;
			HiloBombilla hilo= new HiloBombilla(this);
			hilo.start();
			add(panelVerDark,BorderLayout.CENTER);
			add(panelOpciones, BorderLayout.SOUTH);
			
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al leer los archivos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(confirm==1) {
			setSize(1200, 1000);
			setTitle("Problemas UVA");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setResizable(true);
			try {
				mundo= new Controlador(false);
				panelVerSending= new PanelVerSending(this);
				panelVerSending.actualizar(0,true);
				panelOpcionesDos= new PanelOpcionesSending(this);
				HiloEmail hilo= new HiloEmail(this);
				hilo.start();
				add(panelVerSending,BorderLayout.CENTER);
				add(panelOpcionesDos, BorderLayout.SOUTH);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al leer los archivos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else System.exit(0);
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
	public void repintarPanelVerDark() {
		panelVerDark.repaint();
	}
	public void problemaAnterior1() {
		indice--;
		if(indice<0)indice=mundo.darkRoads().getGrafos().size()-1;
		panelVerDark.actualizar(indice, true);
	}
	public void problemaOriginal1() {
		panelVerDark.actualizar(indice, true);
	}
	public void problemaAhorrativo1() {
		panelVerDark.actualizar(indice, false);
	}
	public void problemaSiguiente1() {
		indice++;
		if((indice==mundo.darkRoads().getGrafos().size())) {
			indice=0;
		}
		panelVerDark.actualizar(indice, true);
	}
	public void reiniciar() {
		this.setVisible(false);
		this.dispose();
		main(null);		
	}

	public void problemaAnterior2() {
		indice--;
		if(indice<0)indice=mundo.getSending().getGrafos().size()-1;
		panelVerSending.actualizar(indice, true);
	}
	public void problemaSiguiente2() {
		indice++;
		if((indice==mundo.getSending().getGrafos().size())) {
			indice=0;
		}
		panelVerSending.actualizar(indice, true);
	}

	public void repintarPanelVerSending() {
		panelVerSending.repaint();
	}
}
