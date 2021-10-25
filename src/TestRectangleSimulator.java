import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestRectangleSimulator{
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		LinkedList<Point> liste = new LinkedList<Point>();
		//liste.add(new Point(0,0));liste.add(new Point(100,0));liste.add(new Point(0,100));
		RectangleSimulator newRectSim=new RectangleSimulator(gui);
		// newRectSim.r.modifyState(5,5,1);
		gui.setSimulable(newRectSim) ;

		}
	}
