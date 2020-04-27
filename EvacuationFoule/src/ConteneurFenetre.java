import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ConteneurFenetre extends JPanel{
	int[][] grille = Principale.createGrid(10,10);
	Principale.borderWall(grille);
	
	Color wall = new Color(153,153,102);
	Color door = new Color(128,43,0);
	Color floor = new Color(230,115,0);
	Color stairs = new Color(51,102,204);
	
	public ConteneurFenetre() {
		super();
	}
	public void paintComponent(Graphics g) {
		for(int i = 0; i<grille.length; i++) {
			for(int j = 0; j<grille[0].length; j++) {
				if(grille[i][j]==0) {
					g.setColor(floor);
				}
				if((grille[i][j]==1)||(grille[i][j]==11)||(grille[i][j]==10)){
					g.setColor(door);
				}
				if((grille[i][j]==2)||(grille[i][j]==22)||(grille[i][j]==20)){
					g.setColor(stairs);
				}
				if(grille[i][j]==-1) {
					g.setColor(wall);
				}
				g.fillRect(i*20, j*20, 20, 20);
			}
		}
		
	}

}
