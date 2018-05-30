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

import grafosMatriz.GrafoMatrizNoDirigido;

@SuppressWarnings("serial")
public class PanelVerSending extends JPanel{
	private ImageIcon icono;
	private HashMap<Integer, String> duplas;
	private VentanaPrincipal principal;
	private int[][] matriz;
	private ArrayList<Color> colores;
	private String sYt;
	private boolean todo;
	int indiceSolucion=0;
	
	public PanelVerSending(VentanaPrincipal p) {
		setSize(1550, 1400);
		principal=p;
		indiceSolucion=-1;
		colores= new ArrayList<Color>();
	}
	
	public void actualizar(int indiceSolucion, boolean todo) {
		if( indiceSolucion!= this.indiceSolucion) {
		this.indiceSolucion=indiceSolucion;
		
		GrafoMatrizNoDirigido<Integer> grafo= principal.getMundo().getSending().getGrafos().get(indiceSolucion);
		matriz= grafo.getMatrizPeso();
		sYt= principal.getMundo().getSending().getsYt().get(indiceSolucion);
		Random aleatorio = new Random(System.currentTimeMillis());
		icono=new ImageIcon("./ArchivosSendingEmails/imagenes/email2.gif"); 
		duplas= new HashMap<Integer, String>();
		
		for (int i = 0; i < matriz.length; i++) {
				int x= aleatorio.nextInt(1000)+100;
				int y= aleatorio.nextInt(1000)+100;
				String dupla= x+" "+y;
				duplas.put(i, dupla);
		}
		colores= new ArrayList<Color>();
		}
		this.todo=todo;
		if(todo) {
			
		}
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
			Random rand = new Random();
			for (int j = 0; j < matriz.length; j++) {
				for (int i = 0; i < matriz.length; i++) {
					String dupla1= duplas.get(i);
					String[] posiciones1= dupla1.split(" ");
					
					String dupla2= duplas.get(j);
					String[] posiciones2= dupla2.split(" ");
					int xa= Integer.parseInt(posiciones1[0])+150;
					int ya=Integer.parseInt(posiciones1[1])+100;
					int xd=Integer.parseInt(posiciones2[0])+150;
					int yd=Integer.parseInt(posiciones2[1])+100;
					if( colores.size()<matriz.length) {
						float r = rand.nextFloat();
						float ga = rand.nextFloat();
						float b = rand.nextFloat();
						Color randomColor = new Color(r, ga, b);
						colores.add(randomColor);
					}
					g2d.setColor(colores.get(i));
					g2d.setStroke(new BasicStroke(5));
					if( matriz[j][i]!= GrafoMatrizNoDirigido.INF && i!=j) {
					g2d.drawLine(xa, ya, xd, yd);
				    g2d.setColor(Color.RED);
				    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
				    g.drawString(matriz[j][i]+"", (xa+xd)/2,(ya+yd)/2);
					}
				}
			}
		    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
			g.setColor(Color.red);
			for (int i = 0; i < matriz.length; i++) {
				String dupla= duplas.get(i);
				String[] posiciones= dupla.split(" ");
				g.drawImage(icono.getImage(),Integer.parseInt(posiciones[0]),Integer.parseInt(posiciones[1]), null);
				String[] syta= sYt.split(" ");
				if(i== Integer.parseInt(syta[0])) {
					String dupla1= duplas.get(i);
					String[] posiciones1= dupla1.split(" ");
					int xa= Integer.parseInt(posiciones1[0])+100;
					int ya=Integer.parseInt(posiciones1[1]);
					g.drawString("S", xa, ya);
				}
				else if(i==Integer.parseInt(syta[1])) {
						String dupla1= duplas.get(i);
						String[] posiciones1= dupla1.split(" ");
						int xa= Integer.parseInt(posiciones1[0])+100;
						int ya=Integer.parseInt(posiciones1[1]);
						g.drawString("T", xa, ya);
				}
			}
			g.setColor(Color.BLACK);
			g.drawString(principal.getMundo().getSending().getSoluciones().get(indiceSolucion), 100,100);
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
