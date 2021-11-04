import  java.lang.Math;

class Vector{
  private float x;
  private float y;
  private float mag;
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
  public Vector (Vector v){
    x = v.x;
    y = v.y;
    mag = v.mag;
  }

  public float getX(){return x;}
  public float getY(){return y;}
  public float getMag(){return mag;}
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
    y*=scalar;
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

  public void normalize(){
    if(mag!=0){
      x=x/mag;
      y=y/mag;
      mag=1;
    }
  }
  public void setMag(float Mag){
    this.normalize();
    x*=Mag;
    y*=Mag;
    mag=Mag;
  }
  public void limit(int Mag){
    if(mag>Mag){
      this.setMag(Mag);
    }
  }

  public float distanceBetween(Vector vec1,Vector vec2){
    Vector distance = new Vector(vec1);
    distance.sub(vec2);
    return distance.mag;
  }

}
