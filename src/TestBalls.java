import java.awt.Point;
class TestBalls{
	public static void main(String[] args) {
		//initialisation des variables
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,1);
		Point p3 = new Point(2,2);
		Balls b = new Balls();
		b.add(p1);
		b.add(p2);b.add(p3);
		//affichage
		System.out.println(b.toString());
		//translate de 0 en x et -1 en y
		b.translate(0, -1);
		System.out.println("y-=1 pour tous");
		System.out.println(b.toString());
		//replace toutes les balles au centre
		b.reInit();
		System.out.println("y=0 x=0 pour tous");
		System.out.println(b.toString());
	}
}
