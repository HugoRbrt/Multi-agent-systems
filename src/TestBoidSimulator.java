import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur graphique Boids (fichier associés : Boid, BoidsSimulator)
public class TestBoidSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		int sizeHeight =700;
		int sizeWidth =700;
		int nbFlock = 3;//nombre de groupe de boids
		int nbBoids = 8;//nombre de boids par groupe
		GUISimulator gui = new GUISimulator(sizeHeight, sizeWidth, Color.BLACK);
		//hauteur et largeur de la fenetre d'affichage :
		BoidSimulator BoidSim = new BoidSimulator(gui,sizeHeight,sizeWidth,nbFlock,nbBoids);
//SIMULATION
		gui.setSimulable(BoidSim) ;

		}
	}
