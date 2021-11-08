import  java.lang.Math;

//un vecteur 2D possède 3 attributs : sa taille selon x, selon y et sa norme (ici noté mag)
class Vecteur{
//ATTRIBUTS
  private float x;
  private float y;
  private float mag;
//CONSTRUCTEUR
  public Vecteur(){ //Constructeur : vecteur nul
    x=0;
    y=0;
    mag=0;
  }
  public Vecteur(float X, float Y){//constructeur : vecteur de coordonnées définit par X et Y
    x=X;
    y=Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public Vecteur (Vecteur v){//constructeur : clone un vecteur existant
    x = v.x;
    y = v.y;
    mag = v.mag;
  }
//METHODES
  public float getX(){return x;}//renvoie taille du vecteur sur x
  public float getY(){return y;}//renvoie taille du vecteur sur y
  public float getMag(){return mag;}//renvoie norme du vecteur

  public void add(Vecteur vec){//somme 2 vecteurs "this+=vec"
    x+=vec.x;
    y+=vec.y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void sub(Vecteur vec){//soustrait 2 vecteur "this-=vec"
    x-=vec.x;
    y-=vec.y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void mult(float scalar){//multiplie le vecteur par un scalaire "this*=scalar"
    x=scalar*x;
    y=scalar*y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void div(float scalar){//divise le vecteur par un scalaire "this/=scalar"
    if(scalar==0){
      throw new ArithmeticException("Division par 0");
    }
    else
    {
      x=x/scalar;
      y=y/scalar;
      mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
    }
  }

  public void setX(float X){//modifie taille du vecteur selon x
    x = X;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void setY(float Y){//modifie taille du vecteur selon y
    y = Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void setXandY(float X, float Y){////modifie taille du vecteur selon x et y
    x = X;
    y = Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }

  public void normalize(){//normalise le vecteur
    if(mag!=0){
      x=x/mag;
      y=y/mag;
      mag=1;
    }
  }
  public void setMag(float value){//modifie la norme du vecteur (sans mdodifier son orientation)
    normalize();
    this.mult(value);
  }
  public void limit(float Mag){//limite la n orme du vecteur (sans mdodifier son orientation)
    if(mag>Mag){
      this.setMag(Mag);
    }
  }

  public float distanceBetween(Vecteur vec1,Vecteur vec2){//renvoie la distance entre 2 vecteurs
    Vecteur distance = new Vecteur(vec1);
    distance.sub(vec2);
    return distance.mag;
  }

}
