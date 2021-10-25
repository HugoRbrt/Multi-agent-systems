import java.awt.Point;
class TestBalls{
	public static void main(String[] args) {
		//initialisation des variables
		Balls b = new Balls();
		b.add(new Point(1,1));b.add(new Point(2,1));b.add(new Point(2,2));
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
