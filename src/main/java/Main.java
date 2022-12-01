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
    game.getCurrentLevel().getBoardWindow().getInputField().addKeyListener(new UserInput(game));
    game.getCurrentLevel().getBoardWindow().getTextArea().append(Descriptions.BOOT_MESSAGE);
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

    hallway.addContents(new RoomThingCharacter("Jimbo", "A service-bot, short and neat, but rather dusty. He's prone to loops apparently...", "Howdy there, lemme open up this here locker room for ya. By the way, I put that screwdriver back in the workshop, keep things nice and tidy."));
    hallway.addContents(new RoomThingTool("hammer", "A great big hammer, looks dangerous"));
    hallway.addContents(new RoomThingDecoration("light", "Shiny."));
    office.addContents(new RoomThingDecoration("vent", "It's the vent you crawled through to get in here, usually covered with a metal cover."));
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

    storage.putDirection(Direction.SOUTH, accessWay);

    hallwayTwo.addContents(new RoomThingDecoration("notices", "Lots of boring and old notices, although one of them does mention a misplaced keycard somewhere in storage..."));

    lounge.addContents(new RoomThingDecoration("sofa", "Raggedy and broken, you're not sure how the person on it is able to sleep. It looks like someone's been scrounging around."));
    lounge.addContents(new RoomThingCharacter("vagrant", "Unconscious and kinda smelly, waking them up would surely be a bad idea.", "zzz"));

    bunks.addContents(new RoomThingCharacter("annie", "A little child, she's angry.", "Grr I'm very angry for some reason, get killed my dude. (You think she might just be really hungry)"));
    bunks.addContents(new RoomThingDecoration("beds", "Bare beds baring bear-like beatniks, built badly."));

    kitchen.addContents(new RoomThingTool("knife", "A sharp knife, for cutting meat."));
    kitchen.addContents(new RoomThingDecoration("fridge", "A giant metal box, cold on the inside and cold on the outside. There's " +
            "barely enough food to last the rest of the ships scheduled journey and nothing else, although there does appear to be a note on the side though."));
    kitchen.addContents(new RoomThingDecoration("heater", "It has just enough space for a regulation meal packet, or bagel if you can find one."));

    accessWay.addContents(new RoomThingDecoration("scanner", "A keycard scanner. Now just to find a keycard..."));
    accessWay.addContents(new RoomThingDecoration("keypad", "A passcode keypad, basic security but enough to keep prying eyes out."));

    storage.addContents(new RoomThingDecoration("dust", "Definitely not sanitary, kind of impressive really."));
    storage.addContents(new RoomThingDecoration("box", "This box seems slightly less dusty, and even has a locked box in it."));

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
    Room corridorOne = new Room("corridorOne", Descriptions.CORRIDOR_ONE, LEVEL_WIDTH, LEVEL_HEIGHT, 2, 1);
    Room corridorTwo = new Room("corridorTwo", Descriptions.CORRIDOR_TWO, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room corridorThree = new Room("corridorThree", Descriptions.CORRIDOR_TWO, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room armory = new Room("armory", Descriptions.ARMOURY, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room bathroom = new Room("bathroom", Descriptions.BATHROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room foyer = new Room("foyer", Descriptions.FOYER, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room firstMateRoom = new Room("first mate's room", Descriptions.FIRST_MATE_ROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room hanger = new Room("hanger", Descriptions.HANGER, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room captainRoom = new Room("captainRoom", Descriptions.CAPTAIN_ROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room fuelRoom = new Room("fuelRoom", Descriptions.FUEL_ROOM, LEVEL_WIDTH, LEVEL_HEIGHT);
    Room escapePod = new Room("escapePod", Descriptions.ESCAPE_POD, LEVEL_WIDTH, LEVEL_HEIGHT);

    corridorOne.putDirection(Direction.SOUTH, corridorTwo);

    corridorTwo.putDirection(Direction.NORTH, corridorOne);
    corridorTwo.putDirection(Direction.EAST, corridorThree);
    corridorTwo.putDirection(Direction.WEST, armory);

    armory.putDirection(Direction.EAST, corridorTwo);

    corridorThree.putDirection(Direction.WEST, corridorTwo);
    corridorThree.putDirection(Direction.SOUTH, bathroom);
    corridorThree.putDirection(Direction.NORTH, foyer);

    bathroom.putDirection(Direction.NORTH, corridorThree);

    foyer.putDirection(Direction.SOUTH, corridorThree);
    foyer.putDirection(Direction.EAST, firstMateRoom);

    firstMateRoom.putDirection(Direction.WEST, foyer);

    captainRoom.putDirection(Direction.SOUTH, foyer);



    corridorOne.addContents(new RoomThingDecoration("terminal", "At the entryway to the hanger to the North, this terminal requires a passcode, the buttons are few in number and primary colours"));

    corridorTwo.addContents(new RoomThingDecoration("keypad", "At the entry to the fuel storage room, this terminal requires a passcode, and judging from several carvings from a previous attempt it is animal based."));

    foyer.addContents(new RoomThingDecoration("sofa", "A sofa that can actually be called a sofa, it's comfy and after a little poking around, you find a key down the side of one of the cushions."));
    foyer.addContents(new RoomThingDecoration("table", "Made of a mysterious brown material, it looks to be far more expensive and decorative than more other tables."));
    foyer.addContents(new RoomThingDecoration("carpet", "It appears to be some kind of fabric layered onto the floor, which seems both frivolous but also comfy."));
    foyer.addContents(new RoomThingDecoration("padlock", "Seems to be a padlock keeping the door shut. You reckon the key can't be far."));

    captainRoom.addContents(new RoomThingDecoration("desk", "old and covered in mess and notes."));
    captainRoom.addContents(new RoomThingTool("combination", "Seems to be a flight initialisation combination, for a personal escape pod."));

    //passcode for room found in journal in hanger
    firstMateRoom.addContents(new RoomThingDecoration("unicorn", "it seems whoever lived in this room had some form of obsession with unicorns as there are far, far more than the normal 17 expected in a room."));
    firstMateRoom.addContents(new RoomThingDecoration("desk", "A relatively neat desk, you find a passcode with 'Fuel room' written on it. How convenient."));
    firstMateRoom.addContents(new RoomThingDecoration("bed", "A bed with actual pillows and comfort, you wish you had time for a little nap."));
    firstMateRoom.addContents(new RoomThingDecoration("poster", "A poster of a majestic unicorn, a perfectly normal thing to be on a futuristic spaceship"));
    firstMateRoom.addContents(new RoomThingDecoration("ashpile", "A pile of ash, in a suspiciously human shaped arrangement... The cleaners mustn't have gotten to here in a while."));

    bathroom.addContents(new RoomThingDecoration("cubicles", "unoccupied, but entirely unuseful, mostly because all the toilets appear to be broken. The does appear to be some cleaner in one of them though."));

    armory.addContents(new RoomThingDecoration("munitions", "These are really, really old and volatile warheads, just looking at them seems dangerous..."));

    fuelRoom.addContents(new RoomThingTool("canister", "its filled with fuel, but heavy enough that you can only take one at a time."));
    //password for fuel room is pony, seahorse, parrot

    hanger.addContents(new RoomThingDecoration("pod", "This looks like they could get you out of here..."));
    hanger.addContents(new RoomThingDecoration(  "table", "A table covered with papers"));
    hanger.addContents(new RoomThingDecoration("papers", "You find nothing of note in these archaic and garbled papers."));
    hanger.addContents(new RoomThingDecoration("puddle", "A puddle of grease, looks like there's something in it but it's gross and maybe corrosive?"));
    hanger.addContents(new RoomThingDecoration(  "screen", "A flight status screen, shows the viability of flight and fuel status."));
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
