import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;

//RectangleImGameSimulator : gestion graphique de la simulation des rectangles pour le jeu de l'immigration
class RectanglesImGameSimulator implements Simulable{
//ATTRIBUTS
  	public RectanglesImGame r;
  	private GUISimulator gui;
  	//Taille des rectangles :
  	private int HeightSize;
  	private int WidthSize;
//CONSTRUCTEUR
  	public RectanglesImGameSimulator(GUISimulator Gui,int HeightSize, int WidthSize, int nbEtats){//constructeur : associe un GUISimulator, ajoute le nombre de points en conséquence et initialise le nb d'états que peut prendre un rectangle lors de la simulation
      //associe un GUISimulator, ajoute le nombre de points en conséquence
  		this.gui = Gui;
  		this.HeightSize = HeightSize;
  		this.WidthSize = WidthSize;
  		this.r = new RectanglesImGame(HeightSize,WidthSize,this.gui.getPanelHeight(),this.gui.getPanelWidth(),nbEtats);
  		this.affichage();
  	}
//METHODES
  	public void setState(int l, int c, int n){//modifie l'état du rectangle ligne l colonbne c pour le metter à n
  		this.r.setState(l,c,n);
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
    public void affichage(){//affiche l'état courant de l'ensemble des rectangle
  		int c=0;
  		int l=0;
      for(Point n:this.r.getrectangle()){
        int nuance = 255-this.r.getcurrentState(l,c)*255/this.r.getnbStates();//calcul d'une nuance de gris unique à chaque état
        gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),new Color(nuance,nuance,nuance),new Color(nuance,nuance,nuance),HeightSize,WidthSize));
  			c++;//passage à la colonne suivante
  			if(c==this.r.getcurrentStateColonnesLength()){c=0;l++;}//passage a la ligne suivante lorsqu'on à rempli le derniere colonne
  		}
  	}

}
