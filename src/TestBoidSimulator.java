import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur graphique Boids (fichier associés : Boid, BoidsSimulator)
public class TestBoidSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		int sizeHeight =500;
		int sizeWidth =500;
		int nbFlock = 2;//nombre de groupe de boids
		int nbBoids = 20;//nombre de boids par groupe
		GUISimulator gui = new GUISimulator(sizeHeight, sizeWidth, Color.BLACK);
		//hauteur et largeur de la fenetre d'affichage :
		BoidSimulator BoidSim = new BoidSimulator(gui,sizeHeight,sizeWidth,nbFlock,nbBoids);
//SIMULATION
		gui.setSimulable(BoidSim) ;

		}
	}
