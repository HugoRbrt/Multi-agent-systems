import gui.GUISimulator ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Color;


public class TestRectanglesSchellingSimulator{
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		//hauteur et largeur des rectangles :
		int sizeHeight =30;
		int sizeWidth =30;
		int nbStates = 7;
		int k = 5;
		RectanglesSchellingSimulator newRectImSim=new RectanglesSchellingSimulator(gui,sizeHeight,sizeWidth,nbStates,k);
		//on choisit les rectangle dont on veut changer l'état initial
		for(int l=0;l<newRectImSim.r.getcurrentState().length;l++){
			for(int c=0;c<newRectImSim.r.getcurrentState()[0].length;c++){
				if(1<Math.random()*7){//environ la moitié des cases sont vides
					newRectImSim.setState(l,c,(int)(1+Math.random()*(newRectImSim.r.getnbColor())));//
				}
				else{
					newRectImSim.setState(l,c,0);
				}
			}
		}
		//on lance la simulation :
		gui.setSimulable(newRectImSim) ;

		}
	}
