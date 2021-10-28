import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;

class RectanglesImGameSimulator implements Simulable{
  //ATTRIBUTS
  	public RectanglesImGame r;
  	private GUISimulator gui;
  	//Taille des rectangles :
  	private int HeightSize;
  	private int WidthSize;
  //CONSTRUCTEUR
  	public RectanglesImGameSimulator(GUISimulator Gui,int HeightSize, int WidthSize, int nbEtats){
      //associe un GUISimulator, ajoute le nombre de points en conséquence
  		this.gui = Gui;
  		this.HeightSize = HeightSize;
  		this.WidthSize = WidthSize;
  		int l = this.gui.getPanelHeight()/HeightSize;
  		int c = this.gui.getPanelWidth()/WidthSize;
  		this.r = new RectanglesImGame(l,c,nbEtats);
  		for(int k=0;k<l-1;k++){
  			for(int i=0;i<c;i++){
  				this.r.add(new Point(HeightSize*i+HeightSize/2,WidthSize*k+WidthSize/2));
  			}
  		}
  		this.affichage();
  	}
  //METHODES
  	public void setState(int l, int c, int n){
  		this.r.modifyState(l,c,n);
  		this.affichage();
  	}
  	@Override
  	public void next() {
      this.r.newState();
  		this.affichage();
  	}
  	public void affichage(){
  		int c=0;
  		int l=0;
      Color couleur = Color.WHITE;
      for(Point n:this.r.getrectangle()){
        int nuance = 255-this.r.getcurrentState()[l][c]*255/this.r.getnbStates();
        gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),new Color(nuance,nuance,nuance),new Color(nuance,nuance,nuance),HeightSize,WidthSize));
  			c++;
  			if(c==this.r.getcurrentState()[l].length){c=0;l++;}
  		}
  	}

  	@Override
  	public void restart()
  	{
  		this.r.reInit();
  		this.affichage();
  	}
}
