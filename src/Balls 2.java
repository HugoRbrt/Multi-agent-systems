import java.awt.Point;
import java.util.LinkedList;
import java.util.Iterator;


//Balls : ensemble de points pouvaznt être translaté avec leurs sens de translation (pour d'éventuels rebonds) associés et réinitialisé à leurs position d'origine
class Balls{
//ATTRIBUTS
	private LinkedList<Point> point;//ensemble des points
	private LinkedList<Point> sens;//sens pour chaques points (si sens.x=1 alors cela signifie qu'on va dans le sens inverse car il y a eu un nb de rebond impaire selon x, idem pour y)
	private LinkedList<Point> pointInit;//mémoire de l'état initial des points
//CONSTRUCTEUR
	public Balls() { //constructeur : création de liste vide de point
		this.point = new LinkedList<Point>();
		this.sens = new LinkedList<Point>();//permet de prendre en compte l'orientation de la balle lorsqu'elle rebondi contre une parois
		this.pointInit = new LinkedList<Point>();
		//Le 1er point est ainsi localisé aux valeurs contenues dans la première case de la liste point, initialisé dans la première case de pointInit et se déplace dans le sens indiqué dans la première case de la liste Sens
		//idem pour le point 2 dans la deuxieme case de chaque liste etc.
	}
//METHODES
	public LinkedList<Point> getpoint(){//renvoie la liste des positions points contenu dans Balls
		return this.point;
	}
	public void add(Point p) {//ajoute un point à la liste avec un sens par défaut positif et enregistre sa position initiale
		this.point.add(p);
		this.pointInit.add(new Point((int)this.point.getLast().getX(),(int)this.point.getLast().getY()));
		this.sens.add(new Point(1,1));
	}
	public void translate(int dx, int dy, int xmax, int ymax) {//translate l'ensemble des points de la liste en ajoutant dx selon x et dy selon y e rebondissant si on sort de cadre (il faut x dans [0,xmax] et y dans [0,ymax])
	Iterator<Point> itPoint = point.iterator();
	Iterator<Point> itSens = sens.iterator();
		while(itPoint.hasNext()) {
			Point b = itPoint.next();
			Point s = itSens.next();
			if(b.getX()+dx*s.getX()<0 || b.getX()+dx*s.getX()>xmax){s.setLocation(-1*s.getX(),s.getY());}//si on sort du cadre selon x, alors on inverse le sens de translation de la balle selon x
			if(b.getY()+dx*s.getY()<0 || b.getY()+dx*s.getY()>ymax){s.setLocation(s.getX(),-1*s.getY());}//idem pour y
			b.setLocation(b.getX()+dx*s.getX(), b.getY()+dy*s.getY());//on fait la translation maintenant que les rebonds ont été pris en compte
		}
	}
	public void reInit() {//réinitialisation des paramètres des points de la liste
		point.clear();//on vide la liste des points et des sens de chaques points
		sens.clear();
		for(Point b : pointInit){//on clone l'ensemble de la liste
			point.add(new Point((int)b.getX(),(int)b.getY()));
			sens.add(new Point(1,1));//par défaut chaque point va dans le sens positif de chaque axe
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
