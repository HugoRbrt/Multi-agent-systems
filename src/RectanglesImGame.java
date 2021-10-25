import java.util.ArrayList;
import java.awt.Point;
import java.util.LinkedList;

class RectanglesImGame extends Rectangles{
//ATTRIBUTS
  public int[][] statesVoisins;
  public int nbStates
//int States[] states;
  public RectanglesImGame(int ligne, int col,int nbEtats){){
    super(ligne,col);
    this.statesVoisins=new int[nbStates][];
    this.nbStates = nbEtats;
    for(int i=0;i<this.statesVoisins.length;i++){
			this.statesVoisins[i] = new int[nbStates];
		}
  }
  @Override
  public NewState(){
    for(int l=0;l<currentState.length;l++) {
			for(int c=0;c<currentState[0].length;c++){
				//calcul nb voisin vivant :
				int lsuiv = l+1; if(lsuiv==currentState.length){lsuiv=0;}
				int lprec = l-1; if(lprec==-1){lprec=currentState.length-1;}
 				int csuiv = c+1; if(csuiv==currentState[0].length){csuiv=0;}
				int cprec = c-1; if(cprec==-1){cprec=currentState[0].length-1;}
        LinkedList<Integer> voisins= new LinkedList<Integer>;
        voisins.add(pastState[lsuiv][csuiv]);
        voisins.add(pastState[lsuiv][c]);
        voisins.add(pastState[lsuiv][cprec]);
        voisins.add(pastState[l][csuiv]);
        voisins.add(pastState[l][cprec]);
        voisins.add(pastState[lprec][csuiv]);
        voisins.add(pastState[lprec][c]);
        voisins.add(pastState[lprec][cprec]);
        //for(int ) boucle non implémentée pour créer n variables nbVoisinsEtat...
        Iterator<Integer> voisins=voisins.iterator();

        while(voisins.hasNext()){
          Integer voisin=voisins.next();
          if(voisin=1+pastState[l][c]){
            statesVoisins[l][c] +=1;
          }
        }

				//on effectue les chgmts necessaire :
				if(pastState[l][c]>2){//cas cellule morte qui va naitre
					currentState[l][c]=+1;
          if(currentState[l][c]==nbStates){currentState[l][c]=0;}
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
}
