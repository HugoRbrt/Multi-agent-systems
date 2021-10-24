import java.awt.Point;
import java.util.LinkedList;

class Balls{
//ATTRIBUTS
	private LinkedList<Point> point;
//CONSTRUCTEUR
	public Balls() {
		this.point = new LinkedList<Point>();
	}
//METHODES
	public void add(Point p) {
		this.point.add(p);
	}
	public void translate(int dx, int dy) {
		for(Point b : point) {
			b.setLocation(b.getX()+dx, b.getY()+dy);
		}
	}
	public void reInit() {
		for(Point b : point) {
			b.setLocation(0,0);
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
