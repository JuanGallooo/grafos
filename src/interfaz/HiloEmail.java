package interfaz;

public class HiloEmail extends Thread{
	private VentanaPrincipal interfazJuego;
	public HiloEmail(VentanaPrincipal interfazJuego){
		this.interfazJuego= interfazJuego;
	}
	@Override
	public void run(){
		while(true){
			interfazJuego.repintarPanelVerSending();
		}
	}
}
