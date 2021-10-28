import java.awt.Point;
import java.util.LinkedList;

class Balls{
//ATTRIBUTS
	private LinkedList<Point> point;
	private LinkedList<Point> pointInit;
//CONSTRUCTEUR
	public Balls() {
		this.point = new LinkedList<Point>();
		this.pointInit = new LinkedList<Point>();
	}
//METHODES
	public LinkedList<Point> getpoint(){
		return this.point;
	}
	public void add(Point p) {
		this.point.add(p);
		this.pointInit.add(new Point((int)this.point.getLast().getX(),(int)this.point.getLast().getY()));
	}
	public void translate(int dx, int dy) {
		for(Point b : point) {
			b.setLocation(b.getX()+dx, b.getY()+dy);
		}
	}
	public void reInit() {
		//On remet les coordonnées de tous les points à leurs valeurs initiales stocké dans pointInit
		point.clear();
		for(Point b : pointInit){
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
