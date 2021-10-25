
class RectanglesImGame extends Rectangles{
  //int States[] states;
  public RectanglesImGame(int ligne, int col,){ //int nbStates){
    super(ligne,col);
    this.states=new int[nbStates];
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
        LinkedList<Rectangle> Voisins= new LinkedList<Rectangle>;
        Voisins.add(pastState[lsuiv][csuiv]);
        Voisins.add(pastState[lsuiv][c]);
        Voisins.add(pastState[lsuiv][cprec]);
        Voisins.add(pastState[l][csuiv]);
        Voisins.add(pastState[l][cprec]);
        Voisins.add(pastState[lprec][csuiv]);
        Voisins.add(pastState[lprec][c]);
        Voisins.add(pastState[lprec][cprec]);
        //for(int ) boucle non implémentée pour créer n variables nbVoisinsEtat...
        int nbVoisinsState0=0;
        int nbVoisinsState1=0;
        int nbVoisinsState2=0;
        Iterator<Rectangle> itVoisins=Voisins.iterator();

        while(itVoisins.hasNext()){
          Rectangle voisin=it.next();
          if(voisin.state==0){
            nbVoisinsState0++;
          }
          if(voisin.state==1){
            nbVoisinsState1++;
          }
          if(voisin.state==2){
            nbVoisinsState2++;
          }
          nbVoisinsVivant=nbVoisinsState1+nbVoisinsState2;
        }

				//on effectue les chgmts necessaire :
				if(pastState[l][c]==0 && (nbVoisinsState1==3)){//cas cellule morte qui va naitre
					currentState[l][c]=1;
				}
        if(pastState[l][c]==1 && (nbVoisinsState2==3)){//cas cellule morte qui va naitre
					currentState[l][c]=2;
				}
				if((pastState[l][c]==1 || pastState[l][c]==2) && (nbVoisinsVivant>3 || nbVoisinsVivant<2)){//cas cellule vivante qui va mourir
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
}
