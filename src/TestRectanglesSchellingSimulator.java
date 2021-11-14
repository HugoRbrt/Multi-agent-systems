import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;

//Test du simulateur RectanglesShelling (fichier associés : RectanglesSchelling, RectanglesSchellingSimulator)
public class TestRectanglesSchellingSimulator{
	public static void main(String[] args) {
//INITIALISATION DES VARIABLES
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		//hauteur et largeur des rectangles :
		int sizeHeight =50;
		int sizeWidth =50;
		int nbStates = 7;
		int k = 6;
		RectanglesSchellingSimulator newRectImSim=new RectanglesSchellingSimulator(gui,sizeHeight,sizeWidth,nbStates,k);
		//on choisit les rectangle dont on veut changer l'état initial
		for(int l=0;l<newRectImSim.r.getcurrentStateLength();l++){
			for(int c=0;c<newRectImSim.r.getcurrentStateColonnesLength();c++){
				if(1<Math.random()*10){//environ 10% des cases sont vides
					newRectImSim.setState(l,c,(int)(1+Math.random()*(newRectImSim.r.getnbColor())));//ici la simulation est aléatoire
				}
				else{
					newRectImSim.setState(l,c,0);
				}
			}
		}
//SIMULATION
		gui.setSimulable(newRectImSim) ;

		}
	}
