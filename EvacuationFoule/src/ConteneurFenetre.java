import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ConteneurFenetre extends JPanel{
	
	private JLabel etiquette;
	
	Color wall = new Color(153,153,102);
	Color door = new Color(128,43,0);
	Color floor = new Color(230,115,0);
	Color stairs = new Color(51,102,204);
	Color rose = new Color(255,0,255);
	
	public ConteneurFenetre() {
		super();
		this.proprietesConteneur();
	}
	
	private void proprietesConteneur() {
		this.setLayout(null);
		this.proprietesEtiquette();
	}
	
	private void proprietesEtiquette() {
		etiquette = new JLabel();
		this.etiquette.setBounds(600, 50, 350, 50);
		this.etiquette.setText("Légende :" + "\t"
				+ " magenta : vide"
				+ " marron clair : sol"
				+ " marron foncé : porte"
				+ " gris : mur");
		this.add(etiquette);
	}
	
	public void paintComponent(Graphics g) {
		Salle grille1 = new Salle(9,9);
		Salle grille2 = new Salle(9,9);
		grille1.borderWall();
		grille2.borderWall();
		grille1.insertDoor(0, 4);
		grille2.insertDoor(8, 7);
		Salle grille = Salle.joinSallesDessous(grille1,grille2,0,4,8,7);
		int ligne = grille.getLigne();
		int colonne = grille.getColonne();
		for(int i = 0; i<colonne; i++) {
			for(int j = 0; j<ligne; j++) {
				if(grille.getCellule(j, i).getType().equals("    ")) {
					g.setColor(rose);
				}
				if(grille.getCellule(j, i).getType().equals("void")) {
					g.setColor(floor);
				}
				if(grille.getCellule(j, i).getType().equals("door")){
					g.setColor(door);
				}
				if(grille.getCellule(j, i).getType().equals("stai")){
					g.setColor(stairs);
				}
				if(grille.getCellule(j, i).getType().equals("wall")) {
					g.setColor(wall);
				}
				g.fillRect(i*30, j*30, 30, 30);
			}
		}
		
	}
	

}
