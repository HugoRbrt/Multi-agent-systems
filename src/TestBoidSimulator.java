import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestBoidSimulator{
	public static void main(String[] args) {
		int sizeHeight =500;
		int sizeWidth =500;
		GUISimulator gui = new GUISimulator(sizeHeight, sizeWidth, Color.BLACK);
		//hauteur et largeur de la fenetre d'affichage :
		BoidSimulator BoidSim = new BoidSimulator(gui,sizeHeight,sizeWidth,2,8);
		//on choisit les rectangle dont on veut changer l'état initial
		//on lance la simulation :
		gui.setSimulable(BoidSim) ;

		}
	}
