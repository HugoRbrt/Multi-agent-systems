import  java.lang.Math;

class Vecteur{
  private float x;
  private float y;
  private float mag;
  public Vecteur(){ // le constructeur par défaut, si l'on ne donne pas de coordonnées
    x=0;
    y=0;
    mag=0;
  }
  public Vecteur(float X, float Y){
    x=X;
    y=Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public Vecteur (Vecteur v){
    x = v.x;
    y = v.y;
    mag = v.mag;
  }

  public float getX(){return x;}
  public float getY(){return y;}
  public float getMag(){return mag;}
  public void add(Vecteur vec){
    x+=vec.x;
    y+=vec.y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void sub(Vecteur vec){
    x-=vec.x;
    y-=vec.y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void mult(float scalar){
    x=scalar*x;
    y=scalar*y;
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
      mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
    }
  }
  public void setX(float X){
    x = X;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void setY(float Y){
    y = Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }
  public void setXandY(float X, float Y){
    x = X;
    y = Y;
    mag=(float)(Math.sqrt((double)(Math.pow(x,2)+Math.pow(y,2))));
  }

  public void normalize(){
    if(mag!=0){
      x=x/mag;
      y=y/mag;
      mag=1;
    }
  }
  public void setMag(float value){
    normalize();
    this.mult(value);
  }
  public void limit(float Mag){
    if(mag>Mag){
      this.setMag(Mag);
    }
  }

  public float distanceBetween(Vecteur vec1,Vecteur vec2){
    Vecteur distance = new Vecteur(vec1);
    distance.sub(vec2);
    return distance.mag;
  }

}
