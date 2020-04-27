import javax.swing.JFrame;

public class Fenetre extends JFrame {
	ConteneurFenetre pan;
	public Fenetre() {
		super();
		attributes();
	}
	private void attributes() {
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//Par défaut le programme continue de tourner apès la fermerture, on l'arrete 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = new ConteneurFenetre();
		this.setContentPane(pan);
	}
}