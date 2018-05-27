package interfaz;

public class HiloBombilla extends Thread{
	private VentanaPrincipal interfazJuego;
	public HiloBombilla(VentanaPrincipal interfazJuego){
		this.interfazJuego= interfazJuego;
	}
	@Override
	public void run(){
		while(true){
			interfazJuego.repintarPanelVer();
		}
	}
}
