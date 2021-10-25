import java.awt.Color;
import gui.* ;
import java.awt.Point;


public class BallsSimulator implements Simulable {
//ATTRIBUTS
	private Balls b;
	public GUISimulator gui;
//METHODES
	public BallsSimulator(GUISimulator Gui){
		this.b=new Balls(); //Constructeur ajouté
		this.gui=Gui;
		for(Point n:this.b.point){ // on crée un élement graohique pour chaque point de l'ensemble des balles
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
		}
	}
	@Override
	public void next() {
    b.translate(10,10);
		//b.reset();// on efface les formes affichées aux anciennes coordonnées
		for(Point n:this.b.point){
			gui.addGraphicalElement(new Oval((int)n.getX(),(int)n.getY(),Color.WHITE,Color.BLACK,10));
			}
		}
	@Override
	public void restart()
	{
    b.reInit();
		//b.reset(); // on efface les formes à l'écran
		for(Point n:this.b.point)
		{
			gui.addGraphicalElement(new Oval(0,0,Color.WHITE,Color.BLACK,10));
		}
	}
}
