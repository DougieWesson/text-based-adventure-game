import java.util.Scanner;

public class Main {
  private static final String BRIG_DESCRIPTION = "you awaken in a tiny gloomy room. you are sat on a filthy bed covered with " +
          "a coarse blanket, no pillow. \n and a foul smelling bucket for your more primitive moments is on the opposite side " +
          "of the room, the thick iron door on the east wall has been left ajar,\n you briefly consider giving them a scathing " +
          "review on trip advisor, but settle on escaping this dingy vessel instead.";
  private static final String HALLWAY_DESCRIPTION = "you walk into a hallway, a light flickers from the ceiling proving a dim view " +
          "of your surroundings, you see the Mona Lisa tracking you from a wall, she creeps you out, and three doorways, one straight " +
          "ahead of you, to the north, and two either side of you to the east and west.";
  private static final String WORKSHOP_DESCRIPTION = "it smells very flammable.";
  private static final String LOCKER_ROOM_DESCRIPTION = "this room has lockers in it";
  private static final String OFFICE_DESCRIPTION = "you see an office, it has a big picture of Dan, eating pastries on one wall, it " +
          "is both a confusing and inspiring piece. and a chair. theres also a vent on the north wall";
  private static final String VENT_DESCRIPTION = "you see an office, it has a big picture of Dan, eating pastries on one wall, it is both a " +
          "confusing and inspiring piece. and a chair. theres also a vent on the north wall";
  private static final String LEVEL_ONE_SUCCESS_MESSAGE = "You have crawled into the vent and and feel your sweaty love handles scrape " +
          "against the metallic surfaces of the vent. you've become stuck, you die questioning why you ever dropped hot yoga, in the " +
          "darkness, the sounds of your sobbing echoes out to the rest of the ship for months.";
  public static Game game;

  public static void main(String[] args) {
    Level levelOne = initLevel1();
    Level levelTwo = initLevel2();
    levelTwo.getBoardWindow().hideBoard();
    Level levelThree = initLevel3();
    levelThree.getBoardWindow().hideBoard();
    game = new Game(new Player(levelOne.getStartingRoom(), levelOne), levelOne, levelTwo, levelThree);
    Scanner scanner = new Scanner(System.in);
    String input;
    String output;
    do {
      System.out.print("> ");
      input = scanner.nextLine().toLowerCase();
      if (!game.hasFinishedGame()) {
        output = game.runCommand(input);
        System.out.println(output);
      }
      if (game.hasFinishedGame() && !input.equals("quit")) {
        if (input.equals("yes")) {
          output = game.getPlayer().setCurrentRoom(levelOne.getStartingRoom());
          System.out.println(output);
        } else {
          System.out.println("Do you want to restart, enter yes or quit?");
        }
      }
    } while (!"quit".equals(input));
    System.exit(0);
  }

  private static Level initLevel1() {
    final int LEVEL_ONE_WIDTH = 6;
    final int LEVEL_ONE_HEIGHT = 6;
    Room brig = new Room("brig", BRIG_DESCRIPTION, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT, 2, 1);
    Room hallway = new Room("hallway", HALLWAY_DESCRIPTION, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room workshop = new Room("workshop", WORKSHOP_DESCRIPTION, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room lockerRoom = new Room("lockerRoom", LOCKER_ROOM_DESCRIPTION, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room office = new Room("office", OFFICE_DESCRIPTION, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room vent = new Room("vent", VENT_DESCRIPTION, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);

    brig.putDirection(Direction.EAST, hallway);

    hallway.putDirection(Direction.NORTH, office);
    hallway.putDirection(Direction.SOUTH, workshop);
    hallway.putDirection(Direction.WEST, brig);

    workshop.putDirection(Direction.NORTH, hallway);
    office.putDirection(Direction.SOUTH, hallway);

    hallway.putContents(new RoomThingCharacter("Jimbo", "A man who look like Stephen Merchant, tall and gaunt", "you're in the wrong room lad, go south and grab the key card for me would you, while you do that ill unlock this door to the EAST"));
    hallway.putContents(new RoomThingTool("Hammer", "A great big hammer, looks dangerous", false, false, false));
    hallway.putContents(new RoomThingDecoration("Chandelier", "shiny"));
    office.putContents(new RoomThingDecoration("grate", "it's a grate with a scanner"));
    Level levelOne = new Level(brig, vent, LEVEL_ONE_SUCCESS_MESSAGE, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);

    levelOne.putLevelRooms(brig.getName(), brig);
    levelOne.putLevelRooms(hallway.getName(), hallway);
    levelOne.putLevelRooms(workshop.getName(), workshop);
    levelOne.putLevelRooms(lockerRoom.getName(), lockerRoom);
    levelOne.putLevelRooms(office.getName(), office);
    levelOne.putLevelRooms(vent.getName(), vent);

    return levelOne;
  }

  private static Level initLevel2() {
    int levelWidth = 6;
    int levelHeight = 6;
    Room hallwayTwo = new Room("hallwayTwo", "Hallway flavour space", levelWidth, levelHeight, 2, 1);
    Room lounge = new Room("lounge", "Lounge flavour", levelWidth, levelHeight);
    Room bunks = new Room("bunks", "A room full of beds.", levelWidth, levelHeight);
    Room kitchen = new Room("kitchen", "A barren but functional kitchen", levelWidth, levelHeight);
    Room accessWay = new Room("accessWay", "A passage to access further into the ship.", levelWidth, levelHeight);
    Room storage = new Room("storage", "A storage room, for dust apparently.", levelWidth, levelHeight);
    Room stairway = new Room("stairway", "Stairs down to the flight deck.", levelWidth, levelHeight);

    hallwayTwo.putDirection(Direction.EAST, lounge);
    hallwayTwo.putDirection(Direction.WEST, kitchen);

    lounge.putDirection(Direction.NORTH, bunks);
    lounge.putDirection(Direction.WEST, hallwayTwo);

    bunks.putDirection(Direction.SOUTH, lounge);

    kitchen.putDirection(Direction.EAST, hallwayTwo);
    kitchen.putDirection(Direction.NORTH, accessWay);

    accessWay.putDirection(Direction.SOUTH, kitchen);
    accessWay.putDirection(Direction.NORTH, storage);
    accessWay.putDirection(Direction.EAST, stairway);

    storage.putDirection(Direction.SOUTH, accessWay);

    bunks.addContents(new RoomThingCharacter("Annie", "A little child, she's angry", "Grr I'm very angry for some reason, get killed my dude."));
    kitchen.addContents(new RoomThingTool("knife", "A sharp knife, for cutting meat.", false, false, false));
    kitchen.addContents(new RoomThingDecoration("passcode", "Storage passcode:7324 \n TOP SECRET"));
    accessWay.addContents(new RoomThingDecoration("scanner", "A facial recognition scanner."));
    storage.addContents(new RoomThingDecoration("nothing", "Still nothing..."));

    Level levelTwo = new Level(hallwayTwo, stairway, "You descend down into the flight deck of the ship...", levelWidth, levelHeight);

    levelTwo.putLevelRooms(hallwayTwo.getName(), hallwayTwo);
    levelTwo.putLevelRooms(lounge.getName(), lounge);
    levelTwo.putLevelRooms(bunks.getName(), bunks);
    levelTwo.putLevelRooms(kitchen.getName(), kitchen);
    levelTwo.putLevelRooms(accessWay.getName(), accessWay);
    levelTwo.putLevelRooms(storage.getName(), storage);
    levelTwo.putLevelRooms(stairway.getName(), stairway);

    return levelTwo;
  }

  private static Level initLevel3() {
    int levelWidth = 6;
    int levelHeight = 6;
//    Room startingRoom = new Room("corridor", "you waddle up the stairs, puffing and wheezing despite it only being up a single floor, and end up in a metallic corridor", levelWidth, levelHeight, 2, 1); )
    Room corridorOne = new Room("corridor", "you enter a long metallic corridor", levelWidth, levelHeight, 2, 1);
    Room corridorTwo = new Room("corridor", "you walk into another bland metallic corridor and wonder at the sheer lack of imagination of the ship architect.", levelWidth, levelHeight);
    Room armory = new Room("armory", "you creep into the armory, it stinks of munitions, grease and gun powder.", levelWidth, levelHeight);
    Room dirtyBathroom = new Room("a dirty bathroom", "why you chose to walk in here is beyond mortal comprehension as tears spring to your eyes,\n" +
            " the hellish scent from outside was merely the briefest of introductions to the new wave of rippling nightmares now assaulting your nostrils.\n " +
            "your eyes take in the cracked mirrors, then wander across the urinal blasted apart by a ferocity you feel unwilling to imagine and over\n" +
            " the floor, smeared and crusted with experiences best left in the darker pools of the imagination,\n" +
            " two cubicles loom at you from across the room, one of which is sealed off, and by the looks of the naked grey foot peeking out from the gap, has been occupied for some time.", levelWidth, levelHeight);
    Room foyer = new Room("foyer", "you bounce into a surprisingly lovely room, your feet are cuddled by a thick fluffy carpet, a chandelier gracefully drifts down from the ceiling, thick velvety cushioned seats surround the room, as well as a delicate cheese platter\n" +
            " and thick gold plated doors bar entry to what can only be more beautiful rooms.", levelWidth, levelHeight);
    Room firstMateRoom = new Room("first mate's room", "you wander into a well furnished room with a four poster princess bed, every corner of the room seems buried in sparkly stuffed unicorns.", levelWidth, levelHeight);
    Room hanger = new Room("Hanger", "you enter a large hanger, theres a desk covered in books and papers, a greasy puddle and most importantly, several ever so slightly rusty escape pods line the northern wall", levelWidth, levelHeight);
    Room captainRoom = new Room("captain's room", "you scuttle into the captains room, it's a velvety room, majestic in it's extravagance, filled with audacious self portraits of the captain in horrifyingly extreme erotic poses, several stuffed monstrous animals decorate the room and a huge golden clam\n" +
            " dominates the far end of the room, it appears to of been the captains bed and is worryingly etched with the words 'pleasure chamber' a large fleshy body lies within the clam\n" +
            "he has a silk scarf stretched over his neck, and wears nothing but a thick leather belt with a jewel encrusted buckle crafted into a fat seahorse.", levelWidth, levelHeight);
    Room fuelRoom = new Room("fuel room", "a dank room, containing shelves of fuel, some of which even have fuel in them.", levelWidth, levelHeight);
    Room theEscapePod = new Room("the escape pod", "its a creaky old coffin, with a navigation system that can barely run Doom, despite evidence of multiple attempts to do so, but if it can get you off the ship, you'd blast off in a bin", levelWidth, levelHeight);

    corridorOne.putDirection(Direction.NORTH, hanger);
    corridorOne.putDirection(Direction.SOUTH, corridorTwo);

    corridorTwo.putDirection(Direction.NORTH, corridorOne);
    corridorTwo.putDirection(Direction.SOUTH, fuelRoom);
    corridorTwo.putDirection(Direction.EAST, armory);

    armory.putDirection(Direction.WEST, corridorTwo);
    armory.putDirection(Direction.SOUTH, dirtyBathroom);
    armory.putDirection(Direction.NORTH, foyer);

    dirtyBathroom.putDirection(Direction.NORTH, armory);

    foyer.putDirection(Direction.SOUTH, armory);
    foyer.putDirection(Direction.NORTH, captainRoom);
    foyer.putDirection(Direction.EAST, firstMateRoom);

    firstMateRoom.putDirection(Direction.WEST, foyer);

    hanger.putDirection(Direction.SOUTH, corridorOne);
    hanger.putDirection(Direction.NORTH, theEscapePod);

    captainRoom.putDirection(Direction.SOUTH, foyer);

    fuelRoom.putDirection(Direction.NORTH, corridorTwo);

    theEscapePod.putDirection(Direction.SOUTH, hanger);

    corridorOne.addContents(new RoomThingDecoration("a passcode terminal", "on the entry to the hanger, this terminal requires a passcode, the buttons are few in number and primary colours"));
    corridorTwo.addContents(new RoomThingDecoration("a passcode terminal", "on the entry to the fuel storage room, this terminal requires a passcode, and judging from several carvings from a previous attempt it is animal based."));
    foyer.addContents(new RoomThingDecoration("a passcode terminal.", "a gold plated password terminal that requires your sausage like fingers to enter a passcode"));
    captainRoom.addContents(new RoomThingCharacter("El Capitan", "a fleshy beast of a man wearing only a silk scarf around his bulbous neck, a thickly feathered hat, and a chunky leather belt with a jewel encrusted seahorse buckle", "you tweak his nipples, your disgusting. this man is dead."));
    captainRoom.addContents(new RoomThingDecoration("first erotic painting", "titled, 'the mighty shetland pony'"));
    captainRoom.addContents(new RoomThingDecoration("second erotic painting", "titled, 'Mr Tug Boat'"));
    captainRoom.addContents(new RoomThingDecoration("third erotic painting", "titled, the lonesome plumber"));
    captainRoom.addContents(new RoomThingDecoration("the captain's seahorse belt", "the jewels that encrust it are worth more than your life, but is it worth having to touch it.."));
    captainRoom.addContents(new RoomThingDecoration( "the Captain's hat", "it's decorated with some form of giant parrot feathers"));
    //passcode for room found in journal in hanger
    firstMateRoom.addContents(new RoomThingDecoration("stuffed unicorns", "it seems whoever lived in this room had some form of obsessive organisational disorder as all the unicorns are organised from blue to red"));
    firstMateRoom.addContents(new RoomThingDecoration( "the sticky unicorn", "I wouldn't touch this one, again."));
    dirtyBathroom.addContents(new RoomThingDecoration("a poo", "don't make eye contact with it"));
    dirtyBathroom.addContents(new RoomThingDecoration("cubicle one", "it's locked, and judging by the cacophony of brutal smells, best left that way"));
    dirtyBathroom.addContents(new RoomThingDecoration( "cubicle two", "opening the door reveals a fetid mess gently drifting around the toilet bowl, a finger pops out of the water as you watch. a number is scrawled on the wall ensuring if you call Andy on 77892 you'll receive a 'good time'"));
    fuelRoom.addContents(new RoomThingTool("a large fuel canister", "its filled with fuel, take a lumbering guess what it's for...", false, false, false));
    //password for fuel room is pony, seahorse, parrot
    hanger.addContents(new RoomThingDecoration("Escape Pods", "These look like they could get you out of here..."));
    hanger.addContents(new RoomThingDecoration("Ornaments", "Rustic old fashioned balls with alarmingly large thrusters line the northern wall"));
    hanger.addContents(new RoomThingDecoration(  "Table", "A table covered with papers"));
    hanger.addContents(new RoomThingTool("a leather bound captain's journal", "it seems to be mainly erotic fan fiction, but also contains the numbers 554641 in massive letters on the first page", false, false, false));
    //password for hanger is blue yellow green red
    theEscapePod.addContents(new RoomThingDecoration("a fuel port", "this fuel port could be useful, it's for fuel."));

    Level levelThree = new Level(corridorOne, theEscapePod, "you feel a sense of elation as the pod finally blasts off hurtling you into the stars where due to low oxygen, low fuel, and more optimism than sense you will freeze or suffocate to death within minutes, congratulations on your escape.", levelWidth, levelHeight);

    levelThree.putLevelRooms(corridorOne.getName(), corridorOne);
    levelThree.putLevelRooms(corridorTwo.getName(), corridorTwo);
    levelThree.putLevelRooms(armory.getName(), armory);
    levelThree.putLevelRooms(dirtyBathroom.getName(), dirtyBathroom);
    levelThree.putLevelRooms(foyer.getName(), foyer);
    levelThree.putLevelRooms(firstMateRoom.getName(), firstMateRoom);
    levelThree.putLevelRooms(hanger.getName(), hanger);
    levelThree.putLevelRooms(captainRoom.getName(), captainRoom);
    levelThree.putLevelRooms(fuelRoom.getName(), fuelRoom);
    levelThree.putLevelRooms(theEscapePod.getName(), theEscapePod);

    return levelThree;
  }
}
