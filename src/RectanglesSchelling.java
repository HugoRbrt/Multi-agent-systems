import java.awt.Point;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

//Rectangles : ensembles des rectangles représentés par une liste de point qui ont chacun un état (pouvant être vide) courant, passé et initial
class RectanglesSchelling extends Rectangles{
//ATTRIBUTS
  //pile de couples vacants : 1er int : numero de ligne, 2eme : numero de colonne. Une case vacante est une case dont le numero couleur est 0
  private Queue<int[]> vacants;
  private int nbColor;//nb de couleur de la simulation
  private int k;//seuil du nb de voisin pour qu'une famille déménage
//CONSTRUCTEUR
  public RectanglesSchelling(int HeightRectangle,int WidthRectangle, int Heightgui, int Widthgui,int nbEtats, int k){//constructeur : création de rectangle de taille HeightRectangle*WidthRectangle uniformement répartille dans un cadre Heightgui*Widthgui pouvant prendre nbEtats possibles et de seuil k
    super(HeightRectangle, WidthRectangle,  Heightgui, Widthgui);
    this.setK(k);
    this.setnbColor(nbEtats);
    this.vacants = new LinkedList<int[]> ();
		}
//METHODES
  @Override public void setState(int l, int c, int state){//modifie la valeur courante du rectangle ligne l colonne c
    int[] vac = new int[]{l,c};
    if(state==0){this.vacants.add(vac);}//si on rend une case vacante, on l'ajoute à vacants
    super.setState(l,c,state);
  }
  @Override public void setcurrentState(int l, int c, int state){//modifie l'état courant du rectangle ligne l colonne c pour le mettre à state
    int[] vac = new int[]{l,c};
    if(state==0){this.vacants.add(vac);}//si on rend une case vacante, on l'ajoute à vacants
    super.setcurrentState(l,c,state);
  }
  public void setnbColor(int n){//initialise le nombre de couleur pour la simulation
      if(n<1){
        throw new IllegalArgumentException("nombre de couleur doit être superieur a 0");
      }
      this.nbColor = n+1;//car on ajoute la case vacante qui correspond à la couleur numéro 0
  }
  public void setK(int k){//initialise le paramètre k
      if(k<1 || k>7){
        throw new IllegalArgumentException("nombre K doit être entre 1 et 7 compris");
      }
      this.k = k;
}
  public int getnbColor(){//retourne de nb de couleur
    return this.nbColor;
  }
  @Override public void newState() {//calcul l'état futur de la simulation
  int nbdiff;
    for(int l=0;l<this.getcurrentStateLength();l++) {
			for(int c=0;c<this.getcurrentStateColonnesLength();c++){
        //on fait les modifs uniquement là ou il y a des familles (donc on ne traite pas les cases vacantes)
        if(super.getpastState(l,c)!=0){
          nbdiff=0;
  				//calcul nb voisin de couleur différente :
  				int lsuiv = l+1; if(lsuiv==this.getcurrentStateLength()){lsuiv=0;}//dans les if on a les cas particulier si on est au bord d'une simulation
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
            if(voisin!=this.getpastState(l,c) && voisin!=0 ){//on ajoute 1 au nb de voisins differents si la couleur du voisin n'est pas vacante et qu'elle est differente de la case en question
              nbdiff++;
            }
          }
  				//on effectue les chgmts necessaire :
  				if(nbdiff>this.k){
  					this.demenagement(l,c);
  				}
        }
			}
		}
		//le nouvel état calculé devient alors l'ancien état pour la mise en mémoire :
    // et on remet à 0 le tableau de la valeur des voisins
		for(int l=0;l<this.getcurrentStateLength();l++) {
			for(int c=0;c<this.getcurrentStateColonnesLength();c++){
				this.setpastState(l,c,this.getcurrentState(l,c));
			}
		}
  }
  private void demenagement(int ligne, int colonne){//déménage la case (ligne,colonne) vers une case vacante
    int[] caseVacante = new int[2];
    caseVacante = this.vacants.poll();
    this.setcurrentState(caseVacante[0],caseVacante[1],super.getpastState(ligne, colonne));//la famille est désormais dans la case vacante
    this.setcurrentState(ligne,colonne,0);//la case de la famille avant déménagement devient alors vacante
  }
}
