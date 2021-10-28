import java.awt.Point;
import java.util.LinkedList;
import java.util.Iterator;

class RectanglesImGame extends Rectangles{
//ATTRIBUTS
  private int[][] statesVoisins;//statesVoisins[l][c] correspond au nb de voisin de la case ligne l colonne c qui a pour état 1+etat de la case ligne l colonne c
  private int nbStates;//nb d'états de la simulation
//CONSTRUCTEUR
  public RectanglesImGame(int ligne, int col,int nbEtats){
    super(ligne,col);
    this.statesVoisins=new int[ligne][];
    this.nbStates = nbEtats;
    for(int i=0;i<this.statesVoisins.length;i++){
			this.statesVoisins[i] = new int[col];
		}
  }
//METHODES
  public int getnbStates(){
    return this.nbStates;
  }
  @Override
  public void newState() {
    for(int l=0;l<this.getcurrentState().length;l++) {
			for(int c=0;c<this.getcurrentState()[0].length;c++){
				//calcul nb voisin vivant :
				int lsuiv = l+1; if(lsuiv==this.getcurrentState().length){lsuiv=0;}
				int lprec = l-1; if(lprec==-1){lprec=this.getcurrentState().length-1;}
 				int csuiv = c+1; if(csuiv==this.getcurrentState()[0].length){csuiv=0;}
				int cprec = c-1; if(cprec==-1){cprec=this.getcurrentState()[0].length-1;}
        LinkedList<Integer> voisins= new LinkedList<Integer>();
        voisins.add(this.getpastState()[lsuiv][csuiv]);
        voisins.add(this.getpastState()[lsuiv][c]);
        voisins.add(this.getpastState()[lsuiv][cprec]);
        voisins.add(this.getpastState()[l][csuiv]);
        voisins.add(this.getpastState()[l][cprec]);
        voisins.add(this.getpastState()[lprec][csuiv]);
        voisins.add(this.getpastState()[lprec][c]);
        voisins.add(this.getpastState()[lprec][cprec]);
        //for(int ) boucle non implémentée pour créer n variables nbVoisinsEtat...
        Iterator<Integer> itVoisins=voisins.descendingIterator();

        while(itVoisins.hasNext()){
          Integer voisin=itVoisins.next();
          if(voisin==1+this.getpastState()[l][c]){
            statesVoisins[l][c] +=1;
          }
        }
				//on effectue les chgmts necessaire :
				if(statesVoisins[l][c] >2){
					this.getcurrentState()[l][c]=+1;
          if(this.getcurrentState()[l][c]==nbStates){this.getcurrentState()[l][c]=0;}
				}
			}
		}
		//le nouvel état calculé devient alors l'ancien état pour la mise en mémoire :
    // et on remet à 0 le tableau de la valeur des voisins
		for(int l=0;l<this.getcurrentState().length;l++) {
			for(int c=0;c<this.getcurrentState()[0].length;c++){
				this.getpastState()[l][c]=this.getcurrentState()[l][c];
        statesVoisins[l][c]=0;
			}
		}
  }
}
