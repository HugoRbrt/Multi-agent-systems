import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;
import java.util.Random;


//Test du simulateur graphique Balls (fichier associés : Balls, BallsSimulator)
public class TestBallsSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		//création d'une fenêtre graphique de taille 500*500 noir
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		//on positionne 3 balles à des coordonnées aléatoire dans le simulateur
		LinkedList<Point> liste = new LinkedList<Point>();
		liste.add(new Point((int)(500*Math.random()),(int)(500*Math.random())));
		liste.add(new Point((int)(500*Math.random()),(int)(500*Math.random())));
		liste.add(new Point((int)(500*Math.random()),(int)(500*Math.random())));
		BallsSimulator newBallSim=new BallsSimulator(gui,liste);

//SIMULATION
		gui.setSimulable(newBallSim) ;

		}
	}
