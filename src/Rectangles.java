import java.awt.Point;
import java.util.LinkedList;

//Rectangles : ensembles des rectangles représentés par une liste de point qui ont chacun un état courant, passé et initial
class Rectangles{
//ATTRIBUTS
	private LinkedList<Point> rectangle;//liste des rectangles de longeur correspondant au nombre de rectangle
	private int[][] currentState;//currentState[k][i] : état actuel au rectangle ligne k colonne i
	private int[][] pastState;//ancien état des rectangles
	private int[][] initState;// état initial des rectangles
//CONSTRUCTEUR
	public Rectangles(int HeightRectangle,int WidthRectangle, int Heightgui, int Widthgui) {//constructeur : création de rectangle de taillle HeightRectangle*WidthRectangle uniformement répartille dans un cadre Heightgui*Widthgui
		//l : nombre de rectangle par ligne, c : nombre de rectangle par colonne
		int l = Heightgui/HeightRectangle;
		int c = Widthgui/WidthRectangle;
		//creation des tableaux et liste
		this.currentState = new int[l][c];
		this.pastState = new int[l][c];
		this.initState = new int[l][c];
		this.rectangle = new LinkedList<Point>();
		//initialisation liste et tableaux
		for(int k=0;k<l-1;k++){
			for(int i=0;i<c;i++){
				add(new Point(HeightRectangle*i+HeightRectangle/2,WidthRectangle*k+WidthRectangle/2));
			}
		}
	}
//METHODES
	public void setcurrentState(int l, int c, int value){//modifie la valeur courante du rectangle ligne l colonne c
		if(l>-1 & c>-1 & l<currentState.length & c<currentState[0].length){
			this.currentState[l][c] = value;
		}
	}
	public void setpastState(int l, int c, int value){//modifie la valeur passé du rectangle ligne l colonne c
		if(l>-1 & c>-1 & l<pastState.length & c<pastState[0].length){
			this.pastState[l][c] = value;
		}
	}
	public int getcurrentState(int l, int c){//renvoie la valeur courante du rectangle ligne l colonne c
		if(l>-1 & c>-1 & l<currentState.length & c<currentState[0].length){
			return this.currentState[l][c];
		}
		return 0;
	}
	public int getcurrentStateLength(){//renvoie la taille du tableau currentState = nb de lignes
		return currentState.length;
	}
	public int getcurrentStateColonnesLength(){//renvoie la taille du tableau currentState[0] = nb de colonne
		return currentState[0].length;
	}
	public int getpastState(int l, int c){//renvoie la valeur passé du rectangle ligne l colonne c
		if(l>-1 & c>-1 & l<pastState.length & c<pastState[0].length){
			return this.pastState[l][c];
		}
		return 0;
	}
	public LinkedList<Point> getrectangle(){//renvoie la liste des points représentant la position de chaques rectangles
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
	public void setState(int l, int c, int state){//modifie l'état initial d'un rectangle ligne l colonne c pour le mettre à l'état state
		currentState[l][c] = state;
		pastState[l][c] = state;
		initState[l][c] = state;
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
