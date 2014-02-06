import java.util.Scanner;

public class Game {

   // Globals
   public static final int MAX_LOCALES = 2;   // Total number of rooms/locations we have in the game.
   public static int currentLocale = 0;       // Player starts in locale 0.
   public static String command;              // What the player types as he or she plays the game.
   public static String[] locations;          // An uninitialized array of type String. See init() for initialization.
   public static int[][]nav= new int[3][4];
   
   public static void main(String[] args) {
	   // Set starting location, if any. 
	   // Display the command line args.
	   int startLocation;
	  if(args.length==0){ 
		  startLocation=0;
		 System.out.println("Starting without args:");
	  }
	  else{
	  System.out.println("Starting with args:");
      for (int i = 0; i < args.length; i++) {
         System.out.println(i + ":" + args[i]);
      }
      
      startLocation = Integer.parseInt(args[0]);
      if ( startLocation >= 0 && startLocation <= MAX_LOCALES) {
         currentLocale = startLocation;
      }
	  }
      init();
      updateDisplay();

      // Game Loop
      while (true) {
         getCommand();
         navigate();
         updateDisplay();
      }
   }

   private static void init() {
      // Initialize any uninitialized globals.
      command = new String();
      

      // Set up the location list.
      locations = new String[3];
      locations[2] = "TARDIS";   //  ^
      locations[0] = "The Lab";  //  N
      locations[1] = "Dungeon";  //  |

      System.out.println("All game locations:");
      for (int i = 0; i < locations.length; ++i) {
         System.out.println(i + ":" + locations[i]);
      }
      
      
      nav[0][0]=2;nav[0][1]=1;nav[0][2]=-1;nav[0][3]=-1;
      nav[1][0]=0;nav[1][1]=-1;nav[1][2]=-1;nav[1][3]=-1;
      nav[2][0]=-1;nav[2][1]=0;nav[2][2]=-1;nav[2][3]=-1;
      
      // Set up the navigation matrix.
      
      {                         /* N   S   E   W */
        /* nav[0] for loc 0   {  2,  1, -1, -1 },
        /* nav[1] for loc 1   {  0, -1, -1, -1 },
        /* nav[2] for loc 2   { -1,  0, -1, -1 }*/
      };
   }

  private static void updateDisplay() {
     System.out.println(locations[currentLocale]);
  }

   private static void getCommand() {
      Scanner inputReader = new Scanner(System.in);
      command = inputReader.nextLine();  // command is global.
   }

   private static void navigate() {
      // TODO Separate directional navigation (n, s, e, w) from other commands (like quit).
      // TODO Refactor this part code to use the nav[][] matrix.
      int newLocale=-1;
	   
	   if ( command.equals("north") || command.equals("n") ) {
         newLocale=nav[currentLocale][0];
      } 
	   else if ( command.equals("south") || command.equals("s") ) {
         newLocale=nav[currentLocale][1];
      } 
	   else if ( command.equals("east") || command.equals("e") ) {
		 newLocale=nav[currentLocale][2];
	   }
	   else if ( command.equals("west") || command.equals("w") ) {
		 newLocale=nav[currentLocale][3];
	   }
	   else if ( command.equals("quit") ) {
         System.out.println("Goodbye");
         System.exit(0);
      }
	  if(newLocale==-1){
		  System.out.println("You can't go that way, Please turn around");
	  }
	  else{
		  currentLocale=newLocale;
	  }
   }

}