import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;

//RectangleSchellingSimulator : gestion graphique de la simulation des rectangles pour le modèle de shelling
class RectanglesSchellingSimulator implements Simulable{
//ATTRIBUTS
  	public RectanglesSchelling r;
  	private GUISimulator gui;
  	//Taille des rectangles :
  	private int HeightSize;
  	private int WidthSize;
//CONSTRUCTEUR
  	public RectanglesSchellingSimulator(GUISimulator Gui,int HeightSize, int WidthSize, int nbEtats, int k){//constructeur : associe un GUISimulator, ajoute le nombre de points en conséquence et initialise le nb d'états que peut prendre un rectangle lors de la simulation et le seuil k
      //associe un GUISimulator, ajoute le nombre de points en conséquence
  		this.gui = Gui;
  		this.HeightSize = HeightSize;
  		this.WidthSize = WidthSize;
  		int l = this.gui.getPanelHeight()/HeightSize;
  		int c = this.gui.getPanelWidth()/WidthSize;
  		this.r = new RectanglesSchelling(HeightSize,WidthSize,this.gui.getPanelHeight(),this.gui.getPanelWidth(),nbEtats,k);
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
  	public void affichage(){//affiche l'état courant de l'ensemble des rectangle
  		int c=0;
  		int l=0;
      for(Point n:this.r.getrectangle()){
        int nuance = 255-this.r.getcurrentState(l,c)*255/(1+this.r.getnbColor());//les cases vacantes sont blanche
        gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),new Color(nuance,nuance,nuance),new Color(nuance,nuance,nuance),HeightSize,WidthSize));
  			c++;
  			if(c==this.r.getcurrentStateColonnesLength()){c=0;l++;}
  		}
  	}

  	@Override
  	public void restart(){//charge et affiche l'état initial de la simulation
  		this.r.reInit();
  		this.affichage();
  	}
}
