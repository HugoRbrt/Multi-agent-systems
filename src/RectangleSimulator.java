import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;

//RectangleSimulator : gestion graphique de la simulation des rectangles pour le jeu de la vie de Conway
public class RectangleSimulator implements Simulable {
//ATTRIBUTS
	private Rectangles r;//partie calculatoire de la simulation
	private GUISimulator gui;//espace d'affichage de la simulation
	//Taille des rectangles :
	private int HeightSize;
	private int WidthSize;
//CONSTRUCTEUR
	public RectangleSimulator(GUISimulator Gui,int HeightSize, int WidthSize){//constructeur : associe un GUISimulator, ajoute le nombre de points en conséquence
		this.gui = Gui;
		this.HeightSize = HeightSize;
		this.WidthSize = WidthSize;
		this.r = new Rectangles(HeightSize,WidthSize,this.gui.getPanelHeight(),this.gui.getPanelWidth());
		this.affichage();
	}
//METHODES
	public void affichage(){//affiche l'état courant de l'ensemble des rectangle
	int c=0;
	int l=0;
	for(Point n:this.r.getrectangle()){
		if(this.r.getcurrentState(l,c)==1){//si le nouvel état est vivant on affiche en blanc
			gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),Color.WHITE,Color.WHITE,HeightSize,WidthSize));
		}
		else{//si le nouvel état est mort, on affiche en noir
			gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),Color.BLACK,Color.BLACK,HeightSize,WidthSize));
		}
		c++;
		if(c==this.r.getcurrentStateColonnesLength()){c=0;l++;}
	}
}
	public void setState1(int l, int c){//modifie l'état du rectangle ligne l colonne c pour le metter à 1 puis affiche la modification
		this.r.setState(l,c,1);
		this.affichage();
	}
	@Override
	public void next() {//calcul et affiche l'état suivant de la simulation
    this.r.newState();
		this.affichage();
	}
	@Override
	public void restart(){//charge et affiche l'état initial de la simulation
		this.r.reInit();
		this.affichage();
	}
}
