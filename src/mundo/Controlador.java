package mundo;


public class Controlador {
	
	private DarkRoads darkRoads;
	private SendingMail sending;

	public Controlador(boolean problema) throws Exception{
		if(problema==true) {
			darkRoads= new DarkRoads();
			darkRoads.crearGrafo("./ArchivosDarkRoads/TextPruebas/prueba2"+".txt");	
		}
		else {
			sending= new SendingMail();
			sending.crearGrafo("./ArchivosSendingEmails/TextPruebas/Sending_Emails_prueba1-Entrada"+".txt");
		}
	}
	public DarkRoads darkRoads() {
		return darkRoads;
	}
	public void darkRoads(DarkRoads darkRoads) {
		this.darkRoads = darkRoads;
	}
	public SendingMail getSending() {
		return sending;
	}
	public void setSending(SendingMail sending) {
		this.sending = sending;
	}
}
