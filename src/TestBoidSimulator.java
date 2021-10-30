import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestBoidSimulator{
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		LinkedList<Point> liste = new LinkedList<Point>();
		//hauteur et largeur des rectangles :
		int sizeHeight =30;
		int sizeWidth =30;
		int nbStates = 3;
		BoidSimulator BoidSim=new BoidSImulator(gui,sizeHeight,sizeWidth);
		//on choisit les rectangle dont on veut changer l'état initial
		//on lance la simulation :
		gui.setSimulable(BoidSim) ;

		}
	}
