import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur Rectangles (fichier associés : Rectangles, RectangleSimulator)
public class TestRectangleSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		int guiHeight = 500;
		int guiWidth = 500;
		GUISimulator gui = new GUISimulator(guiWidth, guiHeight, Color.BLACK);
		LinkedList<Point> liste = new LinkedList<Point>();
		//hauteur et largeur des rectangles :
		int sizeHeight =30;
		int sizeWidth =30;
		RectangleSimulator newRectSim=new RectangleSimulator(gui,sizeHeight,sizeWidth);
		//MODE MANUEL : on choisit les rectangle qu'on veut mettre à 1 :
		/*
		newRectSim.setState1(0,0);
		newRectSim.setState1(1,0);
		newRectSim.setState1(0,1);
		newRectSim.setState1(1,2);
		*/

		//OU

		//MODE ALEATOIRE : initialisation aléatoire des rectangles :
		for(int i=0;i<guiHeight/sizeHeight;i++){
			for(int j=0;j<guiWidth/sizeWidth;j++){
				if(2*Math.random()>1){newRectSim.setState1(i,j);;}
			}
		}


//SIMULATION
		gui.setSimulable(newRectSim) ;
		}
	}
