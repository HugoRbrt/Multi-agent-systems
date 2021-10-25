import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;


public class BallsSimulator implements Simulable {
//ATTRIBUTS
	private Balls b;
	public GUISimulator gui;
//METHODES
	public BallsSimulator(GUISimulator Gui, LinkedList<Point> p){
		this.b=new Balls(); //Constructeur ajouté
		this.gui=Gui;
		for(Point n:p){ // on crée un élement graohique pour chaque point de l'ensemble des balles
			this.b.add(n);
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
		}
	}
	@Override
	public void next() {
		for(Point n:this.b.point){// on efface les formes affichées aux anciennes coordonnées
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.BLACK,Color.BLACK,12));
			}
    b.translate(10,10);
		//b.reset();
		for(Point n:this.b.point){// on crée un élement graohique pour chaque point de l'ensemble des balles aux nouvelles coordonnées
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
			}
		}
	@Override
	public void restart()
	{
		// on efface les formes à l'écran
		gui.addGraphicalElement(new Rectangle(gui.getPanelWidth()/2,gui.getPanelHeight()/2, Color.BLACK, Color.BLACK, 2*gui.getPanelWidth(), 2*gui.getPanelHeight()));
    b.reInit();
		//b.reset(); // on efface les formes à l'écran
		for(Point n:this.b.point)
		{
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
		}
	}
}
