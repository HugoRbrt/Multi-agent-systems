import java.awt.Point;
import java.util.LinkedList;
import java.util.Iterator;

class RectanglesImGame extends Rectangles{
//ATTRIBUTS
  private int[][] statesVoisins;//statesVoisins[l][c] correspond au nb de voisin de la case ligne l colonne c qui a pour état 1+etat de la case ligne l colonne c
  private int nbStates;//nb d'états de la simulation
//CONSTRUCTEUR
  public RectanglesImGame(int HeightRectangle,int WidthRectangle, int Heightgui, int Widthgui,int nbEtats){//constructeur : création de rectangle de taille HeightRectangle*WidthRectangle uniformement répartille dans un cadre Heightgui*Widthgui pouvant prendre nbEtats possibles
    super(HeightRectangle, WidthRectangle,  Heightgui, Widthgui);
    int ligne =Heightgui/HeightRectangle;
    int col =Widthgui/WidthRectangle;
    this.statesVoisins=new int[ligne][col];
    this.nbStates = nbEtats;
  }
//METHODES
  public int getnbStates(){//renvoie le nombre d'état qu'un rectangle peut prendre
    return this.nbStates;
  }
  @Override
  public void newState() {//calcul l'état de la simulation à l'instant suivant
  //calcul du nombre de voisin pour chaques rectangles :
    for(int l=0;l<this.getcurrentStateLength();l++) {
			for(int c=0;c<this.getcurrentStateColonnesLength();c++){
				//calcul nb voisin vivant du rectangle ligne l colonne c:
				int lsuiv = l+1; if(lsuiv==this.getcurrentStateLength()){lsuiv=0;}
				int lprec = l-1; if(lprec==-1){lprec=this.getcurrentStateLength()-1;}
 				int csuiv = c+1; if(csuiv==this.getcurrentStateColonnesLength()){csuiv=0;}
				int cprec = c-1; if(cprec==-1){cprec=this.getcurrentStateColonnesLength()-1;}
        LinkedList<Integer> voisins= new LinkedList<Integer>();
        voisins.add(this.getpastState(lsuiv,csuiv));
        voisins.add(this.getpastState(lsuiv,c));
        voisins.add(this.getpastState(lsuiv,cprec));
        voisins.add(this.getpastState(l,csuiv));
        voisins.add(this.getpastState(l,cprec));
        voisins.add(this.getpastState(lprec,csuiv));
        voisins.add(this.getpastState(lprec,c));
        voisins.add(this.getpastState(lprec,cprec));
        Iterator<Integer> itVoisins=voisins.descendingIterator();
        while(itVoisins.hasNext()){
          Integer voisin=itVoisins.next();
          if(voisin==1+this.getpastState(l,c)){
            statesVoisins[l][c] +=1;
          }
        }
				//on effectue les chgmts necessaire :
				if(statesVoisins[l][c] >2){
					this.setcurrentState(l,c,1);
          if(this.getcurrentState(l,c)==nbStates){this.setcurrentState(l,c,0);}
				}
			}
		}
		//le nouvel état calculé devient alors l'ancien état pour la mise en mémoire :
    // et on remet à 0 le tableau de la valeur des voisins
		for(int l=0;l<this.getcurrentStateLength();l++) {
			for(int c=0;c<this.getcurrentStateColonnesLength();c++){
				this.setpastState(l,c,this.getcurrentState(l,c));
        statesVoisins[l][c]=0;
			}
		}
  }
}
