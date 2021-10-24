import java.awt.Color;
import gui.* ;
import java.awt.Point;


public class BallsSimulator implements Simulable {
//ATTRIBUTS
	Balls b;
//METHODES
	public BallsSimulator(){
		this.b=new Balls(); //Constructeur ajouté
		for(Point n:this.b.point){ // on crée un élement graohique pour chaque point de l'ensemble des balles
			(GUISimulator)this.addGraphicalElement(new Oval(n.getX(),n.getY(),Color.WHITE,Color.BLACK,10));
		}
	}
	@Override
	public void next() {
    b.translate(10,10);
		//b.reset();// on efface les formes affichées aux anciennes coordonnées
		for(Point n:this.b.point){
			(GUISimulator)this.addGraphicalElement(new Oval(n.getX(),n.getY(),Color.WHITE,Color.BLACK));
			}
		}
	@Override
	public void restart()
	{
    this.reInit();
		//b.reset(); // on efface les formes à l'écran
		for(Point n:this.b.point)
		{
			this.addGraphicalElement(new Oval(0,0,Color.WHITE,Color.BLACK,10));
		}
	}
}
