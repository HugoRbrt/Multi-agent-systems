import  java.lang.Math;

class Vector{
  public float x;
  public float y; // pas besoin de mettre les attributs en private
  public float mag; // la norme du vecteur
  public Vector(){ // le constructeur par défaut, si l'on ne donne pas de coordonnées
    x=0;
    y=0;
    mag=0;
  }
  public Vector(float X, float Y){
    x=X;
    y=Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }

  public void add(Vector vec){
    x+=vec.x;
    y+=vec.y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void sub(Vector vec){
    x-=vec.x;
    y-=vec.y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void mult(float scalar){
    x*=scalar;
    y+=scalar;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void div(float scalar){
    if(scalar==0){
      throw new ArithmeticException("Division par 0");
    }
    else
    {
      x=x/scalar;
      y=y/scalar;
    }
  }

  public void normalize(){
    if(mag!=0){
      x=x/mag;
      y=y/mag;
      mag=1;
    }
  }
  public void limit(int Mag){
    if(mag>Mag){
      mag=Mag;
    }
  }

  public float distanceBetween(Vector vec1,Vector vec2){
    Vector distance=new Vector(vec1.x,vec1.y);
    distance.sub(vec2);
    return distance.mag;
  }

}
