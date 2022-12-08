import java.util.Scanner;

public class Main {
  public static Game game;

  public static void main(String[] args) {
    game = startGame();
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
        if (input.equals("restart")) {
          game = startGame();
        } else {
          System.out.println("Do you want to restart or quit?");
        }
      }
    } while (!"quit".equals(input));
    System.exit(0);
  }

  public static Game startGame() {
    Level levelOne = initLevel1();
    Level levelTwo = initLevel2();
    levelTwo.getBoardWindow().hideBoard();
    Level levelThree = initLevel3();
    levelThree.getBoardWindow().hideBoard();
    game = new Game(new Player(levelOne.getStartingRoom(), levelOne), levelOne, levelTwo, levelThree);
    game.getCurrentLevel().getBoardWindow().getInputField().addKeyListener(new UserInput(game));
    game.getCurrentLevel().getBoardWindow().getTextArea().append(Descriptions.BOOT_MESSAGE);
    return game;
  }

  private static Level initLevel1() {
    Room brig = new Room("brig", Descriptions.BRIG, 2, 2);
    Room hallway = new Room("hallway", Descriptions.HALLWAY);
    Room workshop = new Room("workshop", Descriptions.WORKSHOP);
    Room lockerRoom = new Room("locker room", Descriptions.LOCKER_ROOM);
    Room office = new Room("office", Descriptions.OFFICE);
    Room vent = new Room("vent", Descriptions.VENT);

    brig.putDirection(Direction.EAST, hallway);

    hallway.putDirection(Direction.NORTH, office);
    hallway.putDirection(Direction.SOUTH, workshop);
    hallway.putDirection(Direction.WEST, brig);

    workshop.putDirection(Direction.NORTH, hallway);
    office.putDirection(Direction.SOUTH, hallway);

    hallway.addContents(new Character("Jimbo", "A service-bot, short and neat, but rather dusty. He's prone to loops apparently...",
            "Howdy there, lemme open up this here locker room for ya. By the way, I put that screwdriver back in the workshop, keep things nice and tidy."));
    hallway.addContents(new Tool("hammer", "A great big hammer, looks dangerous"));
    hallway.addContents(new Decoration("light", "Shiny."));
    office.addContents(new Decoration("vent", "It's the vent you crawled through to get in here, usually covered with a metal cover."));
    Level levelOne = new Level(brig, vent, Descriptions.LEVEL_ONE_SUCCESS);

    levelOne.putLevelRooms(brig);
    levelOne.putLevelRooms(hallway);
    levelOne.putLevelRooms(workshop);
    levelOne.putLevelRooms(lockerRoom);
    levelOne.putLevelRooms(office);
    levelOne.putLevelRooms(vent);

    return levelOne;
  }

  private static Level initLevel2() {
    Room hallwayTwo = new Room("hallwayTwo", Descriptions.HALLWAY_TWO, 3, 3);
    Room lounge = new Room("lounge", Descriptions.LOUNGE);
    Room bunks = new Room("bunks", Descriptions.BUNKS);
    Room kitchen = new Room("kitchen", Descriptions.KITCHEN);
    Room accessWay = new Room("accessway", Descriptions.ACCESSWAY);
    Room storage = new Room("storage", Descriptions.STORAGE);
    Room stairway = new Room("stairway", Descriptions.STAIRWAY);

    hallwayTwo.putDirection(Direction.EAST, lounge);
    hallwayTwo.putDirection(Direction.WEST, kitchen);

    lounge.putDirection(Direction.NORTH, bunks);
    lounge.putDirection(Direction.WEST, hallwayTwo);

    bunks.putDirection(Direction.SOUTH, lounge);

    kitchen.putDirection(Direction.EAST, hallwayTwo);
    kitchen.putDirection(Direction.NORTH, accessWay);

    accessWay.putDirection(Direction.SOUTH, kitchen);


    hallwayTwo.addContents(new Decoration("notices", "Lots of boring and old notices, although one of them does mention a misplaced keycard somewhere in storage..."));

    lounge.addContents(new Decoration("sofa", "Raggedy and broken, you're not sure how the person on it is able to sleep. It looks like someone's been scrounging around."));
    lounge.addContents(new Character("vagrant", "Unconscious and kinda smelly, waking them up would surely be a bad idea.", "zzz"));

    bunks.addContents(new Character("annie", "A little child, she's angry.", "Grr I'm very angry for some reason, get killed my dude. (You think she might just be really hungry)"));
    bunks.addContents(new Decoration("beds", "Bare beds baring bear-like beatniks, built badly."));

    kitchen.addContents(new Tool("knife", "A sharp knife, for cutting meat."));
    kitchen.addContents(new Decoration("fridge", "A giant metal box, cold on the inside and cold on the outside. There's " +
            "barely enough food to last the rest of the ships scheduled journey and nothing else, although there does appear to be a code on the side though."));
    kitchen.addContents(new Decoration("heater", "It has just enough space for a regulation meal packet, or bagel if you can find one."));

    accessWay.addContents(new Decoration("scanner", "A keycard scanner. Now just to find a keycard..."));
    accessWay.addContents(new Decoration("keypad", "A passcode keypad, basic security but enough to keep prying eyes out."));

    storage.addContents(new Decoration("dust", "Definitely not sanitary, kind of impressive really."));
    storage.addContents(new Decoration("box", "This box seems slightly less dusty, and even has a lockbox in it."));

    Level levelTwo = new Level(hallwayTwo, stairway, "You descend down into the flight deck of the ship...");

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
    Room corridorOne = new Room("corridorOne", Descriptions.CORRIDOR_ONE, 2, 2);
    Room corridorTwo = new Room("corridorTwo", Descriptions.CORRIDOR_TWO);
    Room corridorThree = new Room("corridorThree", Descriptions.CORRIDOR_TWO);
    Room armory = new Room("armory", Descriptions.ARMOURY);
    Room bathroom = new Room("bathroom", Descriptions.BATHROOM);
    Room foyer = new Room("foyer", Descriptions.FOYER);
    Room firstMateRoom = new Room("first mate's room", Descriptions.FIRST_MATE_ROOM);
    Room hanger = new Room("hanger", Descriptions.HANGER);
    Room captainRoom = new Room("captain's room", Descriptions.CAPTAIN_ROOM);
    Room fuelRoom = new Room("fuel room", Descriptions.FUEL_ROOM);
    Room escapePod = new Room("escape pod", Descriptions.ESCAPE_POD);

    corridorOne.putDirection(Direction.SOUTH, corridorTwo);
    corridorOne.putDirection(Direction.NORTH, hanger);

    hanger.putDirection(Direction.SOUTH, corridorOne);

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


    corridorTwo.addContents(new Decoration("keypad", "At the entry to the fuel storage room, this terminal requires a passcode, and judging from several carvings from a previous attempt it is animal based."));

    foyer.addContents(new Decoration("sofa", "A sofa that can actually be called a sofa, it's comfy and after a little poking around, you find a key down the side of one of the cushions."));
    foyer.addContents(new Decoration("table", "Made of a mysterious brown material, it looks to be far more expensive and decorative than more other tables."));
    foyer.addContents(new Decoration("carpet", "It appears to be some kind of fabric layered onto the floor, which seems both frivolous but also comfy."));
    foyer.addContents(new Decoration("padlock", "Seems to be a padlock keeping the door shut. You reckon the key can't be far."));

    captainRoom.addContents(new Decoration("desk", "Old and covered in mess and notes."));
    captainRoom.addContents(new Decoration("notes", "The notes look innocuous, until you find one that reads 'use in emergency, escape pod combination'. Huh."));

    firstMateRoom.addContents(new Decoration("unicorn", "It seems whoever lived in this room had some form of obsession with unicorns as there are far, far more than the normal 17 expected in a room."));
    firstMateRoom.addContents(new Decoration("desk", "A relatively neat desk, you find a passcode with 'Fuel room' written on it. How convenient."));
    firstMateRoom.addContents(new Decoration("bed", "A bed with actual pillows and comfort, you wish you had time for a little nap."));
    firstMateRoom.addContents(new Decoration("poster", "A poster of a majestic unicorn, a perfectly normal thing to be on a futuristic spaceship"));
    firstMateRoom.addContents(new Decoration("ashpile", "A pile of ash, in a suspiciously human shaped arrangement... The cleaners mustn't have gotten to here in a while."));

    bathroom.addContents(new Decoration("cubicles", "Unoccupied, but entirely unuseful, mostly because all the toilets appear to be broken. The does appear to be some cleaner in one of them though."));

    armory.addContents(new Decoration("munitions", "These are really, really old and volatile warheads, just looking at them seems dangerous..."));

    fuelRoom.addContents(new Tool("canister", "its filled with fuel, but heavy enough that you can only take one at a time."));

    hanger.addContents(new Decoration("pod", "This looks like they could get you out of here..."));
    hanger.addContents(new Decoration("table", "A table covered with papers"));
    hanger.addContents(new Decoration("papers", "You find nothing of note in these archaic and garbled papers."));
    hanger.addContents(new Decoration("puddle", "A puddle of grease, looks like there's something in it but it's gross and maybe corrosive?"));
    hanger.addContents(new Decoration("screen", "A flight status screen, shows the viability of flight and fuel status."));


    Level levelThree = new Level(corridorOne, escapePod, "You feel a sense of elation as the pod finally blasts off, hurtling you down to the planets surface. Congratulations on your escape.");

    levelThree.putLevelRooms(corridorOne);
    levelThree.putLevelRooms(corridorTwo);
    levelThree.putLevelRooms(corridorThree);
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
