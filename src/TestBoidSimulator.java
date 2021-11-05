import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestBoidSimulator{
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		//hauteur et largeur de la fenetre d'affichage :
		int sizeHeight =30;
		int sizeWidth =30;
		BoidSimulator BoidSim = new BoidSimulator(gui,sizeHeight,sizeWidth);
		//on choisit les rectangle dont on veut changer l'état initial
		//on lance la simulation :
		gui.setSimulable(BoidSim) ;

		}
	}
