import java.awt.Color;
//
public class MessageEvent extends Event {
  private String message ;
  public MessageEvent(int date, String message){
    super(date) ;
    this.message = message ;
  }
  public Event execute () {
    System.out.println(this.getDate() + this.message);
    return null;
  }
  @Override
  public String toString(){
    return "date : " + this.getDate() + " message : "+ this.message;
  }
  public void affichage(Color couleur){;}
}
