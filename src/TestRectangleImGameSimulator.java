import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur RectanglesImGame (fichier associés : RectanglesImGame, RectanglesImGameSimulator)
public class TestRectangleImGameSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		//hauteur et largeur des rectangles :
		int sizeHeight =30;
		int sizeWidth =30;
		int nbStates = 3;
		RectanglesImGameSimulator newRectImSim=new RectanglesImGameSimulator(gui,sizeHeight,sizeWidth,nbStates);
		//on choisit les rectangle dont on veut changer l'état initial
		for(int l=0;l<newRectImSim.r.getcurrentStateLength();l++){
			for(int c=0;c<newRectImSim.r.getcurrentStateColonnesLength();c++){
				newRectImSim.setState(l,c,(int)(Math.random()*nbStates));//ici la simulation est aléatoire
			}
		}
//SIMULATION
		gui.setSimulable(newRectImSim) ;

		}
	}
