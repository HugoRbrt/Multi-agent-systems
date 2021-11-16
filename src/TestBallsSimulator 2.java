import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


//Test du simulateur graphique Balls (fichier associés : Balls, BallsSimulator)
public class TestBallsSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		//création d'une fenêtre graphique de taille 500*500 noir
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		//on choisit les coordonnées initiales des balles à afficher: (ici (0,0) ; (100,0) ; (0,100))
		LinkedList<Point> liste = new LinkedList<Point>();
		liste.add(new Point(0,0));
		liste.add(new Point(100,0));
		liste.add(new Point(0,100));
		BallsSimulator newBallSim=new BallsSimulator(gui,liste);

//SIMULATION
		gui.setSimulable(newBallSim) ;

		}
	}
