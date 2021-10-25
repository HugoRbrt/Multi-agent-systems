import java.awt.Point;
import java.util.LinkedList;

class Rectangles{
//ATTRIBUTS
	public LinkedList<Point> rectangle;//liste des rectangles
	public int[][] currentState;//état actuel des rectangles
	public int[][] pastState;//ancien état des rectangles
	public int[][] initState;// état initial des rectangles
//CONSTRUCTEUR
	public Rectangles(int ligne, int col) {//création de col*ligne rectangle
		this.rectangle = new LinkedList<Point>();
		this.currentState = new int[ligne][];
		this.pastState = new int[ligne][];
		this.initState = new int[ligne][];
		for(int i=0;i<this.currentState.length;i++){
			currentState[i]= new int[col];
			pastState[i]   = new int[col];
			initState[i]   = new int[col];
		}
	}
//METHODES
	public void add(Point p) {
		this.rectangle.add(p);
		int numLigne = this.rectangle.size()/currentState[0].length;
		int numCol   = this.rectangle.size()-(numLigne*currentState[0].length);
		//l'état du rectangle est mort de base
		this.initState[numLigne][numCol]   =0;
		this.pastState[numLigne][numCol]   =0;
		this.currentState[numLigne][numCol]=0;
	}
	//modifier l'état d'un rectangle :
	public void modifyState(int l, int c, int state){
		currentState[l][c] = state;
		pastState[l][c] = state;
		initState[l][c] = state;
	}


	public void newState() {
		for(int l=0;l<currentState.length;l++) {
			for(int c=0;c<currentState[0].length;c++){
				//calcul nb voisin vivant :
				int lsuiv = l+1; if(lsuiv==currentState.length){lsuiv=0;}
				int lprec = l-1; if(lprec==-1){lprec=currentState.length-1;}
 				int csuiv = c+1; if(csuiv==currentState[0].length){csuiv=0;}
				int cprec = c-1; if(cprec==-1){cprec=currentState[0].length-1;}
				int nbVoisinsVivant = pastState[lsuiv][csuiv]+pastState[lsuiv][c]+pastState[lsuiv][cprec]   +   pastState[l][csuiv]+pastState[l][cprec]   +  pastState[lprec][csuiv]+pastState[lprec][c]+pastState[lprec][cprec];
				//on effectue les chgmts necessaire :
				if(pastState[l][c]==0 && (nbVoisinsVivant==3)){//cas cellule morte qui va naitre
					currentState[l][c]=1;
				}
				if(pastState[l][c]==1 && (nbVoisinsVivant>3 || nbVoisinsVivant<2)){//cas cellule vivante qui va mourir
					currentState[l][c]=0;
				}
			}
		}
		//le nouvel état calculé devient alors l'ancien état pour la mise en mémoire :
		for(int l=0;l<currentState.length;l++) {
			for(int c=0;c<currentState[0].length;c++){
				pastState[l][c]=currentState[l][c];
			}
		}

	}
	public void reInit() {
		//On remet les coordonnées de tous les points à leurs valeurs initiales stocké dans pointInit
		for(int i=0;i<currentState.length;i++){
			for(int k=0;k<currentState[0].length;k++){
				this.currentState[i][k]=this.initState[i][k];
				this.pastState[i][k]=this.initState[i][k];
			}
		}
	}



	@Override
	public String toString() {
		String a = "";
		for(Point b : rectangle) {
			a += "x : "+ b.getX();
			a += "; y : "+ b.getY() + "\n";
		}
		return a;
	}

}
