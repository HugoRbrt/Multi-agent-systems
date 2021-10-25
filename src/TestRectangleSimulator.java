import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestRectangleSimulator{
	public static void main(String[] args) {
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
		//on lance la simulation :
		gui.setSimulable(newRectSim) ;

		}
	}
