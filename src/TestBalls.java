import java.awt.Point;


//Test de la partie calculatoire de la simulation (fichier associé : Balls)
class TestBalls{
	public static void main(String[] args) {
		//initialisation des variables avec 3 points aux coordonnées (1,1) ; (2,1) ; (2,2)
		Balls b = new Balls();
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,1);
		Point p3 = new Point(1,2);
		b.add(p1);b.add(p2);b.add(p3);

		//affichage
		System.out.println("*** Position initiale des balles ***");
		System.out.println(b.toString());

		//translate de 0 en x et -1 en y
		//xmax et ymax sont définit en 1000 pour s'assurer qu'il n'y ait pas de rebond
		b.translate(0, -1,1000,1000);
		System.out.println("*** y-=1 pour tous ***");
		System.out.println(b.toString());

		//translate de 2 en x et 0 en y
		b.translate(2, 0,1000,1000);
		System.out.println("*** x+=2 pour tous ***");
		System.out.println(b.toString());
		//replace toutes les balles au centre
		b.reInit();
		System.out.println("*** retour à la position initiale des balles ***");
		System.out.println(b.toString());
	}
}
