public class TestEventManager {
  public static void main(String[] args) throws InterruptedException {
    // On crée un simulateur
    EventManager manager = new EventManager () ;
    // On poste un événement
    for(int i=2;i<=10;i+=2){
      manager.addEvent(new MessageEvent(i,"[PING]"));
    }
    for(int i=3;i<=9;i+=3){
      manager.addEvent(new MessageEvent(i,"[PONG]"));
    }
    while(!manager.isFinished()){
      manager.next() ;

      Thread.sleep(1000) ;
    }
  }
}
