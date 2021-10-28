import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestRectangleImGameSimulator{
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		LinkedList<Point> liste = new LinkedList<Point>();
		//hauteur et largeur des rectangles :
		int sizeHeight =30;
		int sizeWidth =30;
		int nbStates = 3;
		RectanglesImGameSimulator newRectImSim=new RectanglesImGameSimulator(gui,sizeHeight,sizeWidth,nbStates);
		//on choisit les rectangle dont on veut changer l'état initial
		for(int l=0;l<newRectImSim.r.getcurrentState().length;l++){
			for(int c=0;c<newRectImSim.r.getcurrentState()[0].length;c++){
				newRectImSim.setState(l,c,(int)(Math.random()*nbStates));
			}
		}
		//on lance la simulation :
		gui.setSimulable(newRectImSim) ;

		}
	}
