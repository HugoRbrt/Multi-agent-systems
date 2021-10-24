import java.awt.Point;
import java.util.LinkedList;

class Balls{
//ATTRIBUTS
	public LinkedList<Point> point;
//CONSTRUCTEUR
	public Balls() {
		this.point = new LinkedList<Point>();
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,1);
		Point p3 = new Point(2,2);
		point.add(p1);
		point.add(p2);
		point.add(p3);
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
