
public class Salle extends Cellule{
	// Variable d'intance
	private Cellule[][] cellule;
	
	// Constructeurs
	public Salle(int nbLignes, int nbColonnes) {
		cellule = new Cellule[nbLignes][nbColonnes];
		for(int i = 0; i < nbLignes; i++) {
			for(int j = 0; j < nbColonnes; j++) {
				Cellule c = new Cellule();
				this.cellule[i][j] = c;
			}
		}
	}
	public Salle(Cellule[][] cellule) {
		this.cellule = cellule;
	}
	
	
	// Setters
	public void setSalle(Cellule[][] cellule) {
		this.cellule = cellule;
	}
	public void setCellule(int i, int j, Cellule cellule) {
		this.cellule[i][j] = cellule; 
	}
	
	public void setLength(int i, int j) {
		this.cellule = new Cellule[i][j];
	}
	
	// Getters
	public Cellule[][] getSalle() {
		return this.cellule;
	}
	public int getLigne() {
		return this.cellule.length;
	}
	public int getColonne() {
		return this.cellule[0].length;
	}
	
	public Cellule getCellule(int i, int j) {
		return this.cellule[i][j];
	}
	
	// Méthodes
	public void displaySalle() {
		for(int i = 0; i < this.getLigne(); i++) {
			for(int j = 0; j < this.getColonne(); j++) {
				String contenu = this.getCellule(i, j).getType();
				System.out.print(contenu + " ");
			}
			System.out.print("\n");
		}
	}
	public void borderWall() {
		int ligne  = this.getLigne();
		int colonne = this.getColonne();
		for(int i = 0; i < this.getLigne(); i++) {
			for(int j = 1; j < this.getColonne(); j++) {
				this.cellule[i][0].setType("wall");
				this.cellule[i][colonne-1].setType("wall");
				this.cellule[0][j].setType("wall");
				this.cellule[ligne-1][j].setType("wall");
			}
		}
		for(int i = 0; i < this.getLigne(); i++) {
			for(int j = 1; j < this.getColonne(); j++) {
				if(this.cellule[i][j].getType().equals("\t")) {
					this.cellule[i][j].setType("void");
				}
			}
		}
	}
	public void insertObstacle(int ligne, int colonne) {
		this.cellule[ligne][colonne].setType("wall");
	}
	public void insertDoor(int ligne, int colonne) {
		this.cellule[ligne][colonne].setType("door");
	}
	public void insertStairs(int ligne, int colonne) {
		this.cellule[ligne][colonne].setType("stairs");
	}
	public void cleanCellule(int ligne, int colonne) {
		this.cellule[ligne][colonne].setType("void");
	}
	public boolean isInBorder(Salle a, int posLigne, int posColonne) {
		int ligne = a.getLigne();
		int colonne = a.getColonne();
		if(((posColonne == 0) || (posColonne == colonne-1)) || ((posLigne == 0) || (posLigne == ligne-1))) {
			return true;
		}
		return false;
	}
	public Salle joinSalles(Salle a, Salle b, int posXDoorA, int posYDoorA, int posXDoorB, int posYDoorB) {
		// On récupère les données utiles
		int ligneA = a.getLigne();
		int ligneB = b.getLigne();
		int colonneA = a.getColonne();
		int colonneB = b.getColonne();
		
		// On vérifie que les deux portes soient bien en bordure de salle
		boolean test1 = isInBorder(a, posXDoorA, posYDoorA);
		boolean test2 = isInBorder(b, posXDoorB, posYDoorB);
		if(!(test1 && test2)) {
			System.out.println("Jointure impossible : Au moins une des deux portes n'est pas en bordure de la salle");
			Salle erreur = new Salle(0,0);
			return erreur;
		}
		
		// On vérifie que les deux cellules soient bien des portes
		if(!(a.cellule[posXDoorA][posYDoorA].getType().equals("door") && b.cellule[posXDoorB][posYDoorB].getType().equals("door"))) {
			System.out.println("Jointure impossible : il n'y a pas de porte sur au moins une des poistions rensignée");
			Salle erreur = new Salle(0,0);
			return erreur;
		}
		
		// On vérifie que les deux portes coïncident  : portes sur les bordures 'opposées'
		boolean testA = (posXDoorA == 0 && posXDoorB == ligneB-1);
		boolean testB = (posXDoorA == ligneA-1 && posXDoorB == 0);
		boolean testC = (posYDoorA == 0 && posYDoorB == colonneB-1);
		boolean testD = (posYDoorA == colonneA-1 && posYDoorB == 0);
		if(!(testA || testB || testC || testD)) {
			System.out.println("Jointure impossible : les portes sont mal placées, elles ne coïncident pas");
			System.out.println("Les portes ne sont pas sur des murs 'opposés'");
			Salle erreur = new Salle(0,0);
			return erreur;
		}
		
		
		// On joint les salles
		Salle c = new Salle(ligneA*ligneB,colonneA*colonneB);
		if(testA) {
			int offset = Math.abs(posYDoorA-posYDoorB);
			if(offset < posYDoorB) {
				for(int i = 0; i < ligneB; i++) {
					for(int j=0; j < colonneB; j++) {
						c.setCellule(i, j, b.getCellule(i, j));
					}
				}
				for(int i = ligneB; i < ligneA + ligneB; i++) {
					for(int j = offset; j < ligneA + offset; j++) {
						c.setCellule(i,j,a.getCellule(i - ligneB,j - offset));
					}
				}
			} else {
				for(int i = 0; i < ligneB; i++) {
					for(int j = offset; j < colonneB + offset; j++) {
						c.setCellule(i, j, b.getCellule(i, j - offset));
					}
				}	
				for(int i = ligneB; i < ligneA + ligneB; i++) {
					for(int j = 0; j < ligneA; j++) {
						c.setCellule(i,j,a.getCellule(i - ligneB, j));
					}
				}
			}
			
		}
		return c;
	}
	
	
	// Main
	public static void main(String[] args) {
		Salle a = new Salle(5,5);
		a.borderWall();
		a.insertDoor(0, 3);
		a.displaySalle();
		System.out.println("");
		Salle b = new Salle (3,3);
		b.borderWall();
		b.insertDoor(2,0);
		b.displaySalle();
		System.out.println("");
		Salle c = new Salle(0,0);
		c.joinSalles(a, b, 0, 3, 2, 0);// Test pour exécuter joinSalles mais c reste Salle(0,0)
		Salle d = displaySalle();// Test pour executer joinSalles mais erreur java
	}
	
}
