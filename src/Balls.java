import java.awt.Point;
import java.util.LinkedList;

//Balls : ensemble de points pouvaznt être translaté et réinitialisé à leurs position d'origine
class Balls{
//ATTRIBUTS
	private LinkedList<Point> point;//ensemble des points
	private LinkedList<Point> pointInit;//mémoire de l'état initial des points
//CONSTRUCTEUR
	public Balls() { //constructeur : création de liste vide de point
		this.point = new LinkedList<Point>();
		this.pointInit = new LinkedList<Point>();
	}
//METHODES
	public LinkedList<Point> getpoint(){//renvoie la liste des points contenu dans Balls
		return this.point;
	}
	public void add(Point p) {//ajoute un point à la liste et enregistre sa position initiale
		this.point.add(p);
		this.pointInit.add(new Point((int)this.point.getLast().getX(),(int)this.point.getLast().getY()));
	}
	public void translate(int dx, int dy) {//translate l'ensemble des points de la liste en ajoutant dx selon x et dy selon y
		for(Point b : point) {
			b.setLocation(b.getX()+dx, b.getY()+dy);
		}
	}
	public void reInit() {//réinitialisation des paramètres des points de la liste
		point.clear();//on vide la liste
		for(Point b : pointInit){//on clone l'ensemble de la liste
			point.add(new Point((int)b.getX(),(int)b.getY()));
		}
	}
	@Override
	public String toString() {
		String a = "";
		for(Point b : point) {
			a += "x : "+ b.getX();
			a += "; y : "+ b.getY() + "\n";
		}
		return a;
	}

}
