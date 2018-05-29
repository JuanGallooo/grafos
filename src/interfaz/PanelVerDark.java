package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import grafosLista.AristaNoDirigida;
import grafosLista.Vertice;

@SuppressWarnings("serial")
public class PanelVerDark extends JPanel{
	
	private ImageIcon icono;
	private HashMap<Vertice<String>, String> duplas;
	private VentanaPrincipal principal;
	private ArrayList<Vertice<String>> todas;
	private ArrayList<AristaNoDirigida<String>> todasAristas;
	private ArrayList<Color> colores;
	private boolean todo;
	int indiceSolucion=0;
	
	public PanelVerDark(VentanaPrincipal p) {
		setSize(1550, 1400);
		principal=p;
		indiceSolucion=-1;
		colores= new ArrayList<Color>();
	}
	
	public void actualizar(int indiceSolucion, boolean todo) {
		if( indiceSolucion!= this.indiceSolucion) {
		this.indiceSolucion=indiceSolucion;
		todas= principal.getMundo().darkRoads().getGrafos().get(indiceSolucion).getVerticesList();
		todasAristas= principal.getMundo().darkRoads().getGrafos().get(indiceSolucion).getAristasList();
		Random aleatorio = new Random(System.currentTimeMillis());
		icono=new ImageIcon("./ArchivosDarkRoads/imagenes/bombilla.gif"); 
		duplas= new HashMap<Vertice<String>, String>();
		for (int i = 0; i < todas.size(); i++) {
				int x= aleatorio.nextInt(1000)+100;
				int y= aleatorio.nextInt(1000)+100;
				String dupla= x+" "+y;
				duplas.put(todas.get(i), dupla);
		}
		colores= new ArrayList<Color>();
		}
		this.todo=todo;
		if(todo)todasAristas= principal.getMundo().darkRoads().getGrafos().get(indiceSolucion).getAristasList();
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(!todo) todasAristas= principal.getMundo().darkRoads().getAristasSoluciones().get(indiceSolucion);
			Random rand = new Random();
			for (int j = 0; j < todasAristas.size(); j++) {
				Vertice<String> referencia= todasAristas.get(j).getReferencia();
				Vertice<String> destino= todasAristas.get(j).getDestino();
				
				String dupla1= duplas.get(referencia);
				String[] posiciones1= dupla1.split(" ");
				
				String dupla2= duplas.get(destino);
				String[] posiciones2= dupla2.split(" ");
				int xa= Integer.parseInt(posiciones1[0])+45;
				int ya=Integer.parseInt(posiciones1[1])+45;
				int xd=Integer.parseInt(posiciones2[0])+45;
				int yd=Integer.parseInt(posiciones2[1])+45;
				if( colores.size()<todasAristas.size()) {
					float r = rand.nextFloat();
					float ga = rand.nextFloat();
					float b = rand.nextFloat();
					Color randomColor = new Color(r, ga, b);
					colores.add(randomColor);
				}
				g2d.setColor(colores.get(j));
				g2d.setStroke(new BasicStroke(5));
			    g2d.drawLine(xa, ya, xd, yd);
			    g2d.setColor(Color.BLACK);
			    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
			    g.drawString(todasAristas.get(j).getPeso()+"", (xa+xd)/2,(ya+yd)/2);
			}
			for (int i = 0; i < todas.size(); i++) {
				String dupla= duplas.get(todas.get(i));
				String[] posiciones= dupla.split(" ");
				g.drawImage(icono.getImage(),Integer.parseInt(posiciones[0]),Integer.parseInt(posiciones[1]), null);
			}
			 g.drawString(principal.getMundo().darkRoads().getSoluciones().get(indiceSolucion), 50,50);
			 super.paintComponent(g);
		setOpaque(false);
		revalidate();
	}
	public boolean isTodo() {
		return todo;
	}
	public void setTodo(boolean todo) {
		this.todo = todo;
	}
	
}
