import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;


public class RectangleSimulator implements Simulable {
//ATTRIBUTS
	private Rectangles r;
	private GUISimulator gui;
	//Taille des rectangles :
	private int HeightSize;
	private int WidthSize;
//CONSTRUCTEUR
	public RectangleSimulator(GUISimulator Gui,int HeightSize, int WidthSize){//associe un GUISimulator, ajoute le nombre de points en conséquence
		this.gui = Gui;
		this.HeightSize = HeightSize;
		this.WidthSize = WidthSize;
		int l = this.gui.getPanelHeight()/HeightSize;
		int c = this.gui.getPanelWidth()/WidthSize;
		this.r = new Rectangles(l,c);
		for(int k=0;k<l-1;k++){
			for(int i=0;i<c;i++){
				this.r.add(new Point(HeightSize*i+HeightSize/2,WidthSize*k+WidthSize/2));
			}
		}
		this.affichage();
	}
//METHODES
	public void setState1(int l, int c){
		this.r.modifyState(l,c,1);
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
		for(Point n:this.r.getrectangle()){
			if(this.r.getcurrentState()[l][c]==1){//si le nouvel état est vivant on affiche en blanc
				gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),Color.WHITE,Color.WHITE,HeightSize,WidthSize));
			}
			else{//si le nouvel état est mort, on affiche en noir
				gui.addGraphicalElement(new Rectangle((int)n.getX(),(int)n.getY(),Color.BLACK,Color.BLACK,HeightSize,WidthSize));
			}
			c++;
			if(c==this.r.getcurrentState()[l].length){c=0;l++;}
		}
	}
	@Override
	public void restart(){
		this.r.reInit();
		this.affichage();
	}
}
