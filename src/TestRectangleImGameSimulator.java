import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur RectanglesImGame (fichier associés : RectanglesImGame, RectanglesImGameSimulator)
public class TestRectangleImGameSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		int guiHeight = 500;
		int guiWidth = 500;
		GUISimulator gui = new GUISimulator(guiWidth, guiHeight, Color.BLACK);
		//hauteur et largeur des rectangles :
		int sizeHeight =50;
		int sizeWidth =50;
		int nbStates = 3;
		RectanglesImGameSimulator newRectImSim=new RectanglesImGameSimulator(gui,sizeHeight,sizeWidth,nbStates);
		//on choisit les rectangle dont on veut changer l'état initial
		//MODE AUTONMATIQUE : 
		for(int l=0;l<newRectImSim.r.getcurrentStateLength();l++){
			for(int c=0;c<newRectImSim.r.getcurrentStateColonnesLength();c++){
				newRectImSim.setState(l,c,(int)(Math.random()*nbStates));//ici la simulation est aléatoire
			}
		}
//SIMULATION
		gui.setSimulable(newRectImSim) ;

		}
	}
