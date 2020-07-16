package arbre;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dao.FamilleDAO;
import dao.entities.Famille;



public class Arbre extends Applet {

	private static final long serialVersionUID = 1L;
	private Famille famille ;
	private FamilleDAO famDAO;
	public void init(){
		setBackground(Color.WHITE);
		setSize(700,700);
		famille = new Famille();
		famDAO = new FamilleDAO();
		
		
	}
	
	public void paint(Graphics g){
//		 g.setColor(Color.RED);
//		 g.drawRect(30, 30, 100, 200);
//		 symbolMereFille(100, 200,  g,"Fatima");
//		 symbolPereFils(100, 100, g, "Ali");
//		 symbolFoetus(100, 150, g);
//		 symbolHommeDecede(100, 250, g, "Mohamed");
//		 symbolFemmeDecede(100, 300,  g, "Amina");
//		 symbolGrossesse(100, 350,  g);
//		 traitHorizontal(100, 400, 20,g);
//		 traitVertical(100, 450,20,  g);
//		 symbolFemmeMalade(100, 500, g, "Sarra");
//		 symbolHommeMalade(100, 550, g, "Said");
		
	famille=famDAO.trouverFamilleById(452);
	famille.dessin(this, g, getWidth()/2, 100);
	
	}
	
	public void symbolMereFille(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.drawOval(x, y, 20, 20);
		g.setColor(Color.green);
		
		g.setFont(new Font("Arrial", Font.BOLD, 10)); 
		g.drawString(prenom, x-15, y-3);
	}
	public void symbolPereFils(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 20, 20);
		g.setColor(Color.green);
		g.setFont(new Font("Arrial", Font.BOLD, 10)); 
		g.drawString(prenom, x-15, y-3);
	}
	public void symbolFoetus(int x, int y, Graphics g){
		g.setColor(Color.blue);
		g.drawLine(x+10, y, x, y+10);
		g.drawLine(x+10, y, x+20, y+10);
		g.drawLine(x, y+10, x+10, y+20);
		g.drawLine(x+10, y+20, x+20, y+10);
		g.drawString("?", x+8, y+14);
		
	}
	public void symbolHommeDecede(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 20, 20);
		g.drawLine(x, y+20, x+20, y);
		g.setColor(Color.green);
		g.drawString(prenom, x, y-5);
	}
	public void symbolFemmeDecede(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.drawOval(x, y, 20, 20);
		g.drawLine(x, y+20, x+20, y);
		g.setColor(Color.green);
		g.drawString(prenom, x, y-5);
	}
	public void symbolHommeDecedeMalade(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.red);
		g.drawLine(x, y+20, x+20, y);
		g.setColor(Color.green);
	
		g.drawString(prenom, x, y-5);
	}
	public void symbolFemmeDecedeMalade(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 20, 20);
		g.setColor(Color.red);
		g.drawLine(x, y+20, x+20, y);
		g.setColor(Color.green);

		g.drawString(prenom, x, y-5);
	}
	public void symbolGrossesse(int x, int y, Graphics g){
		g.setColor(Color.blue);
		g.drawLine(x+10, y, x, y+10);
		g.drawLine(x+10, y, x+20, y+10);
		g.drawLine(x, y+10, x+10, y+20);
		g.drawLine(x+10, y+20, x+20, y+10);
		
		
	}
	public void traitHorizontal(int x, int y,int l, Graphics g){
		g.setColor(Color.BLUE);
		g.drawLine(x, y, x+l, y);
	}
	
	public void traitVertical(int x, int y,int h, Graphics g){
		g.setColor(Color.BLUE);
		g.drawLine(x, y, x, y+h);
	}
	public void symbolFemmeMalade(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 20, 20);
		g.setColor(Color.red);
		g.drawString(prenom, x, y-5);
	}
	public void symbolHommeMalade(int x, int y, Graphics g, String prenom){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.red);
		g.drawString(prenom, x, y-5);
	}
}
