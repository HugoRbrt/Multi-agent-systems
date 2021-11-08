import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;


//BallsSimulator : gestion graphique de la simulation des balles
public class BallsSimulator implements Simulable {
//ATTRIBUTS
	private Balls b;//partie calculatoire de la simulation
	private GUISimulator gui;//espace d'affichage de la simulation
//METHODES
	public BallsSimulator(GUISimulator Gui, LinkedList<Point> p){//création d'une simulation à partir d'un simulateur Gui et d'une liste de points à simuler
		this.b=new Balls(); //Constructeur ajouté
		this.gui=Gui;
		for(Point n:p){ // on crée un élement graohique pour chaque point de l'ensemble des balles
			this.b.add(n);
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
		}
	}
	@Override
	public void next() {//on avance la simulation d'1 pas de temps
	int dx = 10;
	int dy = 10;
		for(Point n:this.b.getpoint()){// on efface les formes correspondant à chaques points affichées aux anciennes coordonnées
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.BLACK,Color.BLACK,12));
			}
    b.translate(dx,dy,gui.getPanelWidth(),gui.getPanelHeight());//On avance dans la simulation (on délègue la partie calculatoire à la classe Balls)
		for(Point n:this.b.getpoint()){// on affiche les formes correspondant à chaques points affichées aux nouvelles coordonnées
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
			}
		}
	@Override
	public void restart(){//on revient à l'état initial de la simulation
	gui.addGraphicalElement(new Rectangle(gui.getPanelWidth()/2,gui.getPanelHeight()/2, Color.BLACK, Color.BLACK, 2*gui.getPanelWidth(), 2*gui.getPanelHeight()));// on efface les formes à l'écran
    b.reInit();//on revient a l'initialisation de la simulation (partie calcul délégué à la classe Balls)
		for(Point n:this.b.getpoint()){//on affiche les formes correspondant à l'initialisation de la simulation
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
		}
	}
}
