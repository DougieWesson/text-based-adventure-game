import java.util.Scanner;

public class Main {
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
    final int LEVEL_ONE_WIDTH = 5;
    final int LEVEL_ONE_HEIGHT = 5;
    Room brig = new Room("brig", Descriptions.BRIG, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT, 2, 1);
    Room hallway = new Room("hallway", Descriptions.HALLWAY, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room workshop = new Room("workshop", Descriptions.WORKSHOP, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room lockerRoom = new Room("lockerRoom", Descriptions.LOCKER_ROOM, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room office = new Room("office", Descriptions.OFFICE, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);
    Room vent = new Room("vent", Descriptions.VENT, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);

    brig.putDirection(Direction.EAST, hallway);

    hallway.putDirection(Direction.NORTH, office);
    hallway.putDirection(Direction.SOUTH, workshop);
    hallway.putDirection(Direction.WEST, brig);

    workshop.putDirection(Direction.NORTH, hallway);
    office.putDirection(Direction.SOUTH, hallway);

    hallway.putContents(new RoomThingCharacter("Jimbo", "A man who look like Stephen Merchant, tall and gaunt", "you're in the wrong room lad, go south and grab the key card for me would you, while you do that ill unlock this door to the EAST"));
    hallway.putContents(new RoomThingTool("hammer", "A great big hammer, looks dangerous"));
    hallway.putContents(new RoomThingDecoration("chandelier", "shiny"));
    office.putContents(new RoomThingDecoration("grate", "it's a grate with a scanner"));
    Level levelOne = new Level(brig, vent, Descriptions.LEVEL_ONE_SUCCESS, LEVEL_ONE_WIDTH, LEVEL_ONE_HEIGHT);

    levelOne.putLevelRooms(brig);
    levelOne.putLevelRooms(hallway);
    levelOne.putLevelRooms(workshop);
    levelOne.putLevelRooms(lockerRoom);
    levelOne.putLevelRooms(office);
    levelOne.putLevelRooms(vent);

    return levelOne;
  }

  private static Level initLevel2() {
    final int LEVEL_WIDTH = 5;
    final int LEVEL_HEIGHT = 5;
    Room hallwayTwo = new Room("hallwayTwo", Descriptions.HALLWAY_TWO, LEVEL_WIDTH, LEVEL_HEIGHT, 3, 2);
    Room lounge = new Room("lounge", Descriptions.LOUNGE, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room bunks = new Room("bunks", Descriptions.BUNKS, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room kitchen = new Room("kitchen", Descriptions.KITCHEN, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room accessWay = new Room("accessway", Descriptions.ACCESSWAY, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room storage = new Room("storage", Descriptions.STORAGE, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room stairway = new Room("stairway", Descriptions.STAIRWAY, LEVEL_WIDTH, LEVEL_HEIGHT);

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
    kitchen.addContents(new RoomThingTool("knife", "A sharp knife, for cutting meat."));
    kitchen.addContents(new RoomThingDecoration("passcode", "Storage passcode:7324 \n TOP SECRET"));
    accessWay.addContents(new RoomThingDecoration("scanner", "A facial recognition scanner."));
    storage.addContents(new RoomThingDecoration("nothing", "Still nothing..."));

    Level levelTwo = new Level(hallwayTwo, stairway, "You descend down into the flight deck of the ship...", LEVEL_WIDTH, LEVEL_HEIGHT);

    levelTwo.putLevelRooms(hallwayTwo);
    levelTwo.putLevelRooms(lounge);
    levelTwo.putLevelRooms(bunks);
    levelTwo.putLevelRooms(kitchen);
    levelTwo.putLevelRooms(accessWay);
    levelTwo.putLevelRooms(storage);
    levelTwo.putLevelRooms(stairway);

    return levelTwo;
  }

  private static Level initLevel3() {
    final int LEVEL_WIDTH = 5;
    final int LEVEL_HEIGHT = 6;
    Room corridorOne = new Room("corridor", Descriptions.CORRIDOR_ONE, LEVEL_WIDTH, LEVEL_HEIGHT, 2, 1);
    Room corridorTwo = new Room("corridor", Descriptions.CORRIDOR_TWO, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room armory = new Room("armory", Descriptions.ARMOURY, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room bathroom = new Room("bathroom", Descriptions.BATHROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room foyer = new Room("foyer", Descriptions.FOYER, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room firstMateRoom = new Room("first mate's room", Descriptions.FIRST_MATE_ROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room hanger = new Room("Hanger", Descriptions.HANGER, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room captainRoom = new Room("captain's room", Descriptions.CAPTAIN_ROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room fuelRoom = new Room("fuel room", Descriptions.FUEL_ROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room escapePod = new Room("escape pod", Descriptions.ESCAPE_POD, LEVEL_WIDTH, LEVEL_HEIGHT);

    corridorOne.putDirection(Direction.NORTH, hanger);
    corridorOne.putDirection(Direction.SOUTH, corridorTwo);

    corridorTwo.putDirection(Direction.NORTH, corridorOne);
    corridorTwo.putDirection(Direction.SOUTH, fuelRoom);
    corridorTwo.putDirection(Direction.EAST, armory);

    armory.putDirection(Direction.WEST, corridorTwo);
    armory.putDirection(Direction.SOUTH, bathroom);
    armory.putDirection(Direction.NORTH, foyer);

    bathroom.putDirection(Direction.NORTH, armory);

    foyer.putDirection(Direction.SOUTH, armory);
    foyer.putDirection(Direction.NORTH, captainRoom);
    foyer.putDirection(Direction.EAST, firstMateRoom);

    firstMateRoom.putDirection(Direction.WEST, foyer);

    hanger.putDirection(Direction.SOUTH, corridorOne);
    hanger.putDirection(Direction.NORTH, escapePod);

    captainRoom.putDirection(Direction.SOUTH, foyer);

    fuelRoom.putDirection(Direction.NORTH, corridorTwo);

    escapePod.putDirection(Direction.SOUTH, hanger);

    corridorOne.addContents(new RoomThingDecoration("a passcode terminal", "on the entry to the hanger, this terminal requires a passcode, the buttons are few in number and primary colours"));
    corridorTwo.addContents(new RoomThingDecoration("a passcode terminal", "on the entry to the fuel storage room, this terminal requires a passcode, and judging from several carvings from a previous attempt it is animal based."));
    foyer.addContents(new RoomThingDecoration("a passcode terminal.", "a gold plated password terminal that requires your sausage like fingers to enter a passcode"));
    captainRoom.addContents(new RoomThingCharacter("el capitan", "a fleshy beast of a man wearing only a silk scarf around his bulbous neck, a thickly feathered hat, and a chunky leather belt with a jewel encrusted seahorse buckle", "you tweak his nipples, you're disgusting. this man is dead."));
    captainRoom.addContents(new RoomThingDecoration("first erotic painting", "titled, 'the mighty shetland pony'"));
    captainRoom.addContents(new RoomThingDecoration("second erotic painting", "titled, 'Mr Tug Boat'"));
    captainRoom.addContents(new RoomThingDecoration("third erotic painting", "titled, the lonesome plumber"));
    captainRoom.addContents(new RoomThingDecoration("belt", "the captain's seahorse belt, the jewels that encrust it are worth more than your life but is it worth having to touch it.."));
    captainRoom.addContents(new RoomThingDecoration( "hat", "the captain's hat, it's decorated with some form of giant parrot feathers"));
    //passcode for room found in journal in hanger
    firstMateRoom.addContents(new RoomThingDecoration("stuffed unicorns", "it seems whoever lived in this room had some form of obsessive organisational disorder as all the unicorns are organised from blue to red"));
    firstMateRoom.addContents(new RoomThingDecoration( "the sticky unicorn", "I wouldn't touch this one, again."));
    bathroom.addContents(new RoomThingDecoration("cubicle one", "it's locked, and judging by the cacophony of brutal smells, best left that way"));
    bathroom.addContents(new RoomThingDecoration( "cubicle two", "opening the door reveals a fetid mess gently drifting around the toilet bowl, a finger pops out of the water as you watch. a number is scrawled on the wall ensuring if you call Andy on 77892 you'll receive a 'good time'"));
    fuelRoom.addContents(new RoomThingTool("a large fuel canister", "its filled with fuel, take a lumbering guess what it's for..."));
    //password for fuel room is pony, seahorse, parrot
    hanger.addContents(new RoomThingDecoration("Escape Pods", "These look like they could get you out of here..."));
    hanger.addContents(new RoomThingDecoration("Ornaments", "Rustic old fashioned balls with alarmingly large thrusters line the northern wall"));
    hanger.addContents(new RoomThingDecoration(  "Table", "A table covered with papers"));
    hanger.addContents(new RoomThingTool("a leather bound captain's journal", "it seems to be mainly erotic fan fiction, but also contains the numbers 554641 in massive letters on the first page"));
    //password for hanger is blue yellow green red
    escapePod.addContents(new RoomThingDecoration("a fuel port", "this fuel port could be useful, it's for fuel."));

    Level levelThree = new Level(corridorOne, escapePod, "you feel a sense of elation as the pod finally blasts off hurtling you into the stars where due to low oxygen, low fuel, and more optimism than sense you will freeze or suffocate to death within minutes, congratulations on your escape.", LEVEL_WIDTH, LEVEL_HEIGHT);

    levelThree.putLevelRooms(corridorOne);
    levelThree.putLevelRooms(corridorTwo);
    levelThree.putLevelRooms(armory);
    levelThree.putLevelRooms(bathroom);
    levelThree.putLevelRooms(foyer);
    levelThree.putLevelRooms(firstMateRoom);
    levelThree.putLevelRooms(hanger);
    levelThree.putLevelRooms(captainRoom);
    levelThree.putLevelRooms(fuelRoom);
    levelThree.putLevelRooms(escapePod);

    return levelThree;
  }

}
