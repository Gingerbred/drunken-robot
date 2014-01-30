import java.util.Scanner;
public class Game {
	static boolean stillPlaying= true;
  // Global
 public int currentLocale = 0;
 public String command="";
 
  public static void main(String[] args) {
     Game gamers=new Game();
     gamers.updateDisplay();
    //Game Loop
     while(stillPlaying){ 
     gamers.getCommand();
     gamers.navigate();
     gamers.updateDisplay();
  }
     System.out.println("Thanks for playing!");
  }
  private void updateDisplay() {
     String msg = new String();
     msg = "";
     switch (currentLocale) {
        case 0: msg = "The Town";
                break;
        case 1: msg = "The Pub";
        		break;
        case 2: msg = "Town Exit";
        		break;
        default: msg = "currentLocale does not compute.";
     }
     System.out.println(msg);
  } 

  private  void getCommand(){
	  Scanner inputReader = new Scanner(System.in);
	  command = inputReader.nextLine();
	
  }
  
  private void navigate(){
	  System.out.println(">>>>"+ command+"<<<<");
	  if(command.equalsIgnoreCase("north")|| command.equals("n")){
		  
	  }
	  
	  if(command.equalsIgnoreCase("exit")){
		  stillPlaying=false;
	  }
  }
}