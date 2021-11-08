import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur Rectangles (fichier associés : Rectangles, RectangleSimulator)
public class TestRectangleSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		LinkedList<Point> liste = new LinkedList<Point>();
		//hauteur et largeur des rectangles :
		int sizeHeight =30;
		int sizeWidth =30;
		RectangleSimulator newRectSim=new RectangleSimulator(gui,sizeHeight,sizeWidth);
		//on choisit les rectangle qu'on veut mettre à 1 :
		newRectSim.setState1(0,0);
		newRectSim.setState1(1,0);
		newRectSim.setState1(0,1);
		newRectSim.setState1(1,2);
//SIMULATION
		gui.setSimulable(newRectSim) ;
		}
	}
