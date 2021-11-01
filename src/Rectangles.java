import java.awt.Point;
import java.util.LinkedList;

class Rectangles{
//ATTRIBUTS
	private LinkedList<Point> rectangle;//liste des rectangles de longeur correspondant au nombre de rectangle
	private int[][] currentState;//currentState[k][i] : état actuel au rectangle ligne k colonne i
	private int[][] pastState;//ancien état des rectangles
	private int[][] initState;// état initial des rectangles
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
	public int[][] getcurrentState(){
		return this.currentState;
	}
//UNE DES MODIFS A FAIRE SERAIT DE FAIRE UN getpastState	(l,c) POUYR EVITER DE RENVOYER TOUT LE TABLEAU A CHAQUE FOIS
	public int[][] getpastState(){
		return this.pastState;
	}
	public int[][] getinitState(){
		return this.initState;
	}
	public LinkedList<Point> getrectangle(){
		return this.rectangle;
	}
	public void add(Point p) {//permet d'ajouter la coordonnée d'un rectangle p à la liste des rectangles deja existant et de mettre, par défaut, l'état à 0
		this.rectangle.add(p);
		int numLigne = this.rectangle.size()/currentState[0].length;
		int numCol   = this.rectangle.size()-(numLigne*currentState[0].length);
		//l'état du rectangle est mort par défaut
		this.initState[numLigne][numCol]   =0;
		this.pastState[numLigne][numCol]   =0;
		this.currentState[numLigne][numCol]=0;
	}
	public void modifyState(int l, int c, int state){//modifie l'état initial d'un rectangle ligne l colonne c pour le mettre à l'état state
		currentState[l][c] = state;
		pastState[l][c] = state;
		initState[l][c] = state;
	}
	public void modifyCurrentState(int l, int c, int state){//modifie l'état initial d'un rectangle ligne l colonne c pour le mettre à l'état state
		currentState[l][c] = state;
	}
	public void newState() {//calcul et met à jour l'état des rectangles pour l'étape suivante
		for(int l=0;l<currentState.length;l++) {
			for(int c=0;c<currentState[0].length;c++){//pour chaques rectangles :
				//calcul nb voisin vivant autour de la case ligne l colonne c :
				int lsuiv = l+1; if(lsuiv==currentState.length){lsuiv=0;}
				int lprec = l-1; if(lprec==-1){lprec=currentState.length-1;}
 				int csuiv = c+1; if(csuiv==currentState[0].length){csuiv=0;}
				int cprec = c-1; if(cprec==-1){cprec=currentState[0].length-1;}//les 4 lignes precedentes permettent de considérer la grille comme circulaire (comme voulu dans le sujet)
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
	public void reInit() {//On remet les coordonnées de tous les points à leurs valeurs initiales stocké dans pointInit
		for(int i=0;i<currentState.length;i++){
			for(int k=0;k<currentState[0].length;k++){
				this.currentState[i][k]=this.initState[i][k];
				this.pastState[i][k]=this.initState[i][k];
			}
		}
	}

	@Override
	public String toString() {//on retourne les coordonnées de chacun des rectangles de l'objet
		String a = "";
		for(Point b : rectangle) {
			a += "x : "+ b.getX();
			a += "; y : "+ b.getY() + "\n";
		}
		return a;
	}

}
