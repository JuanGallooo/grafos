package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import grafosLista.Arista;
import grafosLista.AristaNoDirigida;
import grafosLista.Vertice;

@SuppressWarnings("serial")
public class PanelVer extends JPanel{
	
	private ImageIcon icono;
	private HashMap<Vertice<String>, String> duplas;
	private VentanaPrincipal principal;
	private ArrayList<Vertice<String>> todas;
	private ArrayList<AristaNoDirigida<String>> todasAristas;
	private ArrayList<Color> colores;
	private boolean todo;
	
	public PanelVer(VentanaPrincipal p) {
		principal=p;
		colores= new ArrayList<Color>();
		todas= principal.getMundo().darkRoads().getGrafo().getVerticesList();
		todasAristas= principal.getMundo().darkRoads().getGrafo().getAristasList();
		duplas= new HashMap<Vertice<String>, String>();
		Random aleatorio = new Random(System.currentTimeMillis());
		icono=new ImageIcon("./ArchivosDarkRoads/imagenes/bombilla.gif"); 
		todo=true;
		for (int i = 0; i < todas.size(); i++) {
			int x= aleatorio.nextInt(1000)+100;
			int y= aleatorio.nextInt(1000)+100;
			String dupla= x+" "+y;
			duplas.put(todas.get(i), dupla);
		}
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(todo){
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
			    g.setFont( new Font( "Tahoma", Font.BOLD, 40 ) );
			    g.drawString(todasAristas.get(j).getPeso()+"", (xa+xd)/2,(ya+yd)/2);
			}
			for (int i = 0; i < todas.size(); i++) {
				String dupla= duplas.get(todas.get(i));
				String[] posiciones= dupla.split(" ");
				g.drawImage(icono.getImage(),Integer.parseInt(posiciones[0]),Integer.parseInt(posiciones[1]), null);
			}
		}
		else {
		}
		setOpaque(false);
		revalidate();
	}

}
