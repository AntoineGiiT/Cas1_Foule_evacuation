
public class Principale {
	
	// Méthodes
	public static int[][] createGrid(int ligne, int colonne) {
		int[][] matrice = new int[ligne][colonne];
		return matrice;
	}

	public static void displayGrid(int[][] matrice) {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[i].length; j++) {
				System.out.print(matrice[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public static void borderWall(int[][] matrice) {
		// On remplace tous les coeffs en bordure de la grille par des -1 correpondants
		// à des murs
		int ligne = matrice.length;
		int colonne = matrice[0].length;
		for (int i = 0; i < ligne; i++) {
			matrice[i][0] = -1;
			matrice[i][colonne - 1] = -1;
		}
		for (int j = 1; j < colonne - 1; j++) {
			// On part de j=1 car on a déjà affecter la valeur -1 au début de la colonne
			// Même raisonnement pour l'arrêt à colonne-1
			matrice[0][j] = -1;
			matrice[ligne - 1][j] = -1;
		}
	}

	public static void insertObstacle(int[][] matrice, int ligne, int colonne) {
		// Ligne colonne correspond à l'endroit où l'on souhaite mettre un obstacle
		matrice[ligne][colonne] = -1;
	}

	public static void insertDoor(int[][] matrice, int ligne, int colonne, int id_porte) {
		// Ligne colonne correspond à l'endroit où l'on souhaite mettre une porte
		// L'id d'une porte est : entrante(10), sortante(01) ou entrante et sortante(11)
		matrice[ligne][colonne] = id_porte;
	}

	public static void insertStair(int[][] matrice, int ligne, int colonne, int id_escalier) {
		// Ligne colonne correspond à l'endroit où l'on souhaite mettre un escalier
		// L'id d'un escalier est : entrant(20), sortant(02) ou entrant et sortant(22)
		matrice[ligne][colonne] = id_escalier;
	}

	public static void main(String[] args) {
		// On créer notre objet fenêtre
		Fenetre fenetrePrincipale = new Fenetre();
		// Par défaut la fenêtre ne s'affiche pas on la rend visible
		fenetrePrincipale.setVisible(true);
	}
}
