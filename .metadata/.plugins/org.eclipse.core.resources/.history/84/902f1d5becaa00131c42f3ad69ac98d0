import java.util.Scanner;

public class Game {

    //
    // Public
    //

    // Globals
    private static final boolean DEBUGGING  = false; // Debugging flag.
    private static final int MAX_LOCALES = 11;        // Total number of rooms/locations we have in the game.
    private static int currentLocale = 0;            // Player starts in locale 0.
    private static String command;                  // What the player types as he or she plays the game.
    private static boolean stillPlaying = true;    // Controls the game loop.
    private static Locale[] locations;            // An uninitialized array of type Locale. See init() for initialization.
    private static int[][]  nav;                 // An uninitialized array of type int int.
    private static double moves = 0;            // Counter of the player's moves.
    private static double score = 0;           // Tracker of the player's score.
    private static double achievementratio=0; // Ratio made up of score/moves.
    private static String map;			     // The ASCII map!
    private static int completeKey=0;       // The integer to make the key to the cottage!
    private static Item[] inventory;       // Keeping an inventory!
    public static void main(String[] args) {
        if (DEBUGGING) {
            // Display the command line args.
            System.out.println("Starting with args:");
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + ":" + args[i]);
            }
        }

        // Set starting locale, if it was provided as a command line parameter.
        if (args.length > 0) {
            try {
                int startLocation = Integer.parseInt(args[0]);
                // Check that the passed-in value for startLocation is within the range of actual locations.
                if ( startLocation >= 0 && startLocation <= MAX_LOCALES) {
                    currentLocale = startLocation;
                } else {
                    System.out.println("WARNING: passed-in starting location (" + args[0] + ") is out of range.");
                }
            } catch(NumberFormatException ex) {
                System.out.println("WARNING: Invalid command line arg: " + args[0]);
                if (DEBUGGING) {
                    System.out.println(ex.toString());
                }
            }
        }

        // Get the game started.
        init();
        updateDisplay();

        // Game Loop
        while (stillPlaying) {
            getCommand();
            navigate();
            updateDisplay();
        }

        // We're done. Thank the player and exit.
        System.out.println("Thank you for playing.");
    }
    // Private
    //

    private static void init() {
        // Initialize any uninitialized globals.
        command = new String();
        stillPlaying = true;   // TODO: Do we need this?

        // Set up the location instances of the Locale class.
        Locale loc0 = new Locale(0);
        loc0.setName("The Pub");
        loc0.setDesc("A really derty pub. You don't remember how you got here. \n A map is on the floor.");
        loc0.setNavi("south");
        

        Locale loc1 = new Locale(1);
        loc1.setName("The Town");
        loc1.setDesc("A nice quaint town with a wild nightlife!");
    	loc1.setNavi("north south east");
    	
        Locale loc2 = new Locale(2);
        loc2.setName("Manly Magicks Shoppe");
        loc2.setDesc("Welcome to my store! \nType in 'magic' to see my inventory of items.");
        loc2.setNavi("west");
        
        Locale loc3 = new Locale(3);
        loc3.setName("Entryway");
        loc3.setDesc("The way out of town. We'll need to find the Master to go further south!");
        loc3.setNavi("north south east west");
        
        Water loc4 = new Water(4);
        loc4.setName("Waterfall");
        loc4.setDesc("The water is moving at a calm pace. \n Your master is flailing about.");
        loc4.setWaterSpeed(20);
        loc4.setNavi("east");
        
        Deserted loc5 = new Deserted(5);
        loc5.setName("Cliff");
        loc5.setDesc("What a beautiful view!");
        loc5.setheatIndex(50);
        loc5.setNavi("east");
        
        Locale loc6 = new Locale(6);
        loc6.setName("Entity Meadow");
        loc6.setDesc("There's a nice meadow ahead.");
        loc6.setNavi("north south east west");
        
        Water loc7 = new Water(7);
        loc7.setName("Sensual Stream");
        loc7.setDesc("A nice refreshing stream. There's a key piece on the bank.");
        loc7.setWaterSpeed(5);
        loc7.setNavi("east");
        
        Locale loc8 = new Locale(8);
        loc8.setName("Friendly Forest");
        loc8.setDesc("The forest where your master's cottage lies.\nFind the three key pieces to get home sweet home!");
        loc8.setNavi("north south");
        
        Deserted loc9 = new Deserted(9);
        loc9.setName("Devoid Desert");
        loc9.setDesc("This is a very dry place. There's another piece of the key lying in the sand.");
        loc9.setheatIndex(70);
        loc9.setNavi("west");
        
        Locale loc10 = new Locale(10);
        loc10.setName("Cottage");
        loc10.setDesc("Home Sweet Home! Welcome back to the Cottage after your crazy night!");
        loc10.setNavi("north");
     // Set up the location array.
        locations = new Locale[11];
        locations[0] = loc0; // "The Pub"   // 
        locations[1] = loc1; // "The Town"  //  
        locations[2] = loc2; // "Manly Magick Shoppe"  //
        locations[3] = loc3; // "Entryway" //
        locations[4] = loc4;
        locations[5] = loc5;
        locations[6] = loc6;
        locations[7] = loc7;
        locations[8] = loc8;
        locations[9] = loc9;
        locations[10] = loc10; 
        if (DEBUGGING) {
            System.out.println("All game locations:");
            for (int i = 0; i < locations.length; ++i) {
                System.out.println(i + ":" + locations[i].toString());
            }
        }
        // Set up the navigation matrix.
        nav = new int[][] {
                                 /* N   S   E   W */
                                 /* 0   1   2   3 */
         /* nav[0] for loc 0 */  { -1,  1, -1, -1 },
         /* nav[1] for loc 1 */  {  0,  3,  2, -1 },
         /* nav[2] for loc 2 */  { -1, -1, -1,  1 },
         /* nav[3] for loc 3 */  {  1,  6,  5,  4 },
         /* nav[4] for loc 4 */  { -1, -1,  3, -1 },
         /* nav[5] for loc 5 */  { -1, -1, -1,  3 },
         /* nav[6] for loc 6 */  {  3,  8,  9,  7 },
         /* nav[7] for loc 7 */  { -1, -1,  6, -1 },
         /* nav[8] for loc 8 */  {  6, 10, -1, -1 },
         /* nav[9] for loc 9 */  { -1, -1, -1,  6 },
         /* nav[10] for loc 10 */{  8, -1, -1, -1 }
        };
     Item item0=new Item(0);
     item0.setName("map");
     item0.setDescription("This is your map of the world! Type in map or m to look at it. ");
     
     Item item1=new Item(1);
     item1.setName("master");
     item1.setDescription("This is your master! His name is Matteo. ");
     
     Item item2=new Item(2);
     item2.setName("KeyPart");
     item2.setDescription("This is the first key to the cottage. ");
     
     Item item3=new Item(3);
     item3.setName("KeyPart2");
     item3.setDescription("This is the second key to the cottage ");
     
     Item item4=new Item(4);
     item4.setName("KeyPart3");
     item4.setDescription("This is the third key to the cottage. ");
     
    inventory= new Item[5];
    inventory[0]=item0;
    inventory[1]=item1;
    inventory[2]=item2;
    inventory[3]=item3;
    inventory[4]=item4;
     
        locations[currentLocale].setHasVisited(true);
       System.out.println("Presenting The Drunken Robot!!!");
       System.out.print("You wake up after a huge night of partying and possibly creating cyborgs.\nGet back to your cottage while finding out wherever the heck your master is!");
    }

    private static void updateDisplay() {
        System.out.println(locations[currentLocale].getText());
    }

    private static void getCommand() {
    	if(moves!=0){
            achievementratio=score/moves;	  
            long l = (int)Math.round(achievementratio * 100); // truncates  
            achievementratio = l / 100.0;  
    	}
    	System.out.print("[" + moves + " moves, score " + score + " achievement ratio: "+ achievementratio + "] ");
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }
    private static void navigate() {
        final int INVALID = -1;
        int dir = INVALID;  // This will get set to a value > 0 if a direction command was entered.

        if (        command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {
            dir = 0;
        } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {
            dir = 1;
        } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {
            dir = 2;
        } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {
            dir = 3;

        } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q")) {
            quit();
        } else if ( command.equalsIgnoreCase("take")  || command.equalsIgnoreCase("t")) {
        	takeItem();
        } else if ( command.equalsIgnoreCase("magic") ){
        	if(currentLocale==2){
        	createMagicItems();
        	}
        	else{
        	System.out.println("You need to go to the magick shoppe to do this.");
        	}
        } else if ( command.equalsIgnoreCase("inventory")|| command.equalsIgnoreCase("i")){
        	inventory();
        } else if ( command.equalsIgnoreCase("map") || command.equalsIgnoreCase("m")){
        	mapDisplay();
        } else if ( command.equalsIgnoreCase("dance") || command.equalsIgnoreCase("d")){
        	System.out.println("You do the robot. What redundancy.");
        } else if ( command.equalsIgnoreCase("help")  || command.equalsIgnoreCase("h")) {
            help();
        };

        if (dir > -1) {   // This means a dir was set.
            int newLocation = nav[currentLocale][dir];
             if (newLocation == 10 && completeKey !=3){ // You need the keys to pass through here.
            	 System.out.println("The door is locked.");
            	 newLocation = INVALID;
             }
             if (newLocation == 6 && inventory[1].isFound()==false){
            	 System.out.println("Where's your master?");
             }
             if (newLocation == INVALID) {
                System.out.println("You cannot go that way.");
            } else {
                currentLocale = newLocation;
                moves = moves + 1;
                if(locations[currentLocale].getHasVisited()==true){
                	System.out.println("Your memory starts to trigger. \n It seems that you have previously visited here.");
                }
                else{
                	locations[currentLocale].setHasVisited(true);
                	score=score+5;
                }
            }
        }
    }
    private static void mapDisplay(){
    	if(inventory[0].isFound()){
    	map= "                   The Pub         \n"+ 
    		 "                      |            \n"+
    		 "                   The Town--- Manly Magick Shoppe      \n"+
    		 "                      |            \n"+
    		 "      Waterfall--- Entryway--- The Cliff\n"+
    		 "                      |            \n"+
    		 " Sensual Stream--- Entity Meadow--- Devoid Desert\n"+
    		 "                      |            \n"+
    		 "                   Friendly Forest \n"+
    		 "                      |            \n"+
    		 "                   Cottage          ";
    	System.out.println(map);
    	}
    	else{
    		System.out.println("What Map?");
    	}
    }
    private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   s/south");
        System.out.println("   magic");
        System.out.println("   m/map");
        System.out.println("   h/help");
        System.out.println("   t/take");
        System.out.println("   d/dance");
        System.out.println("   i/inventory");
        System.out.println("   q/quit");
    }
    private static void inventory(){
    	String bag="";
    	if(inventory[0].isFound()){
    		bag= bag+inventory[0].toString()+ "\n";
    	}
    	if(inventory[1].isFound()){	
    		bag =bag+inventory[1].toString()+ "\n";
    	}
    	if(inventory[2].isFound()){
    		bag =bag+inventory[2].toString()+ "\n";
    	}
    	if(inventory[3].isFound()){
    		bag =bag+inventory[3].toString()+ "\n";
    	}
    	if(inventory[4].isFound()){
    		bag =bag+inventory[4].toString()+ "\n";
    	}
    	System.out.println(bag);
    }
    private static void takeItem(){
    	switch(currentLocale){
    	case 0:
    		inventory[0].setFound(true);
    		locations[currentLocale].setDesc("A derty pub. You don't recall any of last night's events.");
    	break;
    	case 4:
    		inventory[1].setFound(true);
    		locations[currentLocale].setDesc("A large waterfall. It seems quite dangerous.");
    	break;
    	case 7:
    		inventory[2].setFound(true);
    		completeKey++;
    		locations[currentLocale].setDesc("A calm stream. It's very refreshing. ");
    	break;
    	case 8:
    		inventory[3].setFound(true);
    		completeKey++;
    		locations[currentLocale].setDesc("A friendly forest. You're almost everything ");
    	break;
    	case 9:
    		inventory[4].setFound(true);
    		completeKey++;
    		locations[currentLocale].setDesc("A really hot desert.");
    	break;
    	default:
    		System.out.println("There's no item here. Check somewhere else.");
    	break;
    	}
    	}	
    private static void quit() {
        stillPlaying = false;
    }
    private static void createMagicItems() {
        // Create the list manager for our magic items.
        List0 magicItems  = new List0();
        magicItems.setName("Magic Items");
        magicItems.setDesc("These are the magic items.");
        magicItems.setHead(null);

        // Create some magic items and put them in the list.
        ListItem i1 = new ListItem();
        i1.setName("+2 Diamond Sword");
        i1.setDesc("A pointy sword");
        ListItem i2 = new ListItem();
        i2.setName("water ring");
        i2.setDesc("");
        ListItem i3 = new ListItem();
        i3.setName("Robe of Death");

        // Link it all up.
        magicItems.setHead(i1);
        i1.setNext(i2);
        i2.setNext(i3);
        i3.setNext(null);

        System.out.println(magicItems.toString());
    }
}