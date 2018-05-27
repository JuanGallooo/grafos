package mundo;

import java.util.ArrayList;

public class Controlador {
	
	private String solucionProblema1;
	private String solucionProblema2;
	
	private DarkRoads darkRoads;
	
	public Controlador() throws Exception{
		darkRoads= new DarkRoads();
		darkRoads.crearGrafo("./ArchivosDarkRoads/TextPruebas/prueba1"+".txt");
		solucionProblema1= darkRoads.getSoluciones().get(0);
	}
	public ArrayList<String> darSoluciones(){
		return darkRoads.getSoluciones();
	}

	public String getSolucionProblema1() {
		return solucionProblema1;
	}

	public void setSolucionProblema1(String solucionProblema1) {
		this.solucionProblema1 = solucionProblema1;
	}

	public String getSolucionProblema2() {
		return solucionProblema2;
	}

	public void setSolucionProblema2(String solucionProblema2) {
		this.solucionProblema2 = solucionProblema2;
	}

	public DarkRoads darkRoads() {
		return darkRoads;
	}

	public void darkRoads(DarkRoads darkRoads) {
		this.darkRoads = darkRoads;
	}
}
