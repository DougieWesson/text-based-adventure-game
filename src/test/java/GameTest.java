import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {

  private static Player mockedPlayer;
  private static Level mockedLevelOne;
  private static Level mockedLevelTwo;
  private static Level mockedLevelThree;
  private static Room mockedRoom;
  private static Room mockedEndingRoom;
  private static Game gameUnderTest;

  @BeforeEach
  void setup() {
    mockedPlayer = mock(Player.class);
    mockedLevelOne = mock(Level.class);
    mockedLevelTwo = mock(Level.class);
    mockedLevelThree = mock(Level.class);
    mockedRoom = mock(Room.class);
    mockedEndingRoom = mock(Room.class);
    gameUnderTest = new Game(mockedPlayer, mockedLevelOne, mockedLevelTwo, mockedLevelThree);
  }

  @Test
  void runCommand_MoveNorth() {
    String mockedCommand = "move north";
    when(mockedPlayer.move(Direction.NORTH)).thenReturn("Player moved North");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Player moved North";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_MoveEast() {
    String mockedCommand = "move east";
    when(mockedPlayer.move(Direction.EAST)).thenReturn("Player moved East");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Player moved East";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_MoveSouth() {
    String mockedCommand = "move south";
    when(mockedPlayer.move(Direction.SOUTH)).thenReturn("Player moved South");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Player moved South";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_MoveWest() {
    String mockedCommand = "move west";
    when(mockedPlayer.move(Direction.WEST)).thenReturn("Player moved West");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Player moved West";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_BadMoveKeyword() {
    String mockedCommand = "mauve north";
    when(mockedPlayer.move(Direction.NORTH)).thenReturn("Player moved North");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "I don't understand";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_MoveBadDirection() {
    String mockedCommand = "move nord";
    when(mockedPlayer.move(Direction.NORTH)).thenReturn("Player moved North");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You cannot move there!";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_MoveNullDirection() {
    String mockedCommand = "move";
    when(mockedPlayer.move(Direction.NORTH)).thenReturn("Player moved North");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Where are you gonna go?";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_CheckEmptyInventory() {
    String mockedCommand = "check inventory";
    HashMap<String, RoomThingTool> mockedInventory = mock(HashMap.class);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedInventory.isEmpty()).thenReturn(true);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Nothing in your inventory";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_CheckNullCommand() {
    String mockedCommand = "check";
    HashMap<String, RoomThingTool> mockedInventory = mock(HashMap.class);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedInventory.isEmpty()).thenReturn(true);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Check what?";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_CheckInventory() {
    String mockedCommand = "check inventory";
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    mockedInventory.put("mockedRoomThingToolName", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "mockedRoomThingToolName, ";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_TakeItem() {
    String mockedCommand = "take screwdriver";
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");
    when(mockedRoomThingTool.takeThing(mockedInventory, mockedContents)).thenReturn("You pick up the screwdriver");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You pick up the screwdriver";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_TakeNullCommand() {
    String mockedCommand = "take";
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    mockedContents.put("mockedRoomThingTool", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");
    when(mockedRoomThingTool.takeThing(mockedInventory, mockedContents)).thenReturn("You pick up the screwdriver");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Can't pick that up.";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_TakeMissingItem() {
    String mockedCommand = "take screwdriver";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "This room is empty.";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_EmptyInventory(){
    String mockedCommand = "use screwdriver grate";
    when(mockedPlayer.getInventory()).thenReturn(new HashMap<>());
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You don't have anything to use.";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_UseCommand_NullInput(){
    String mockedCommand = "use";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedInventory.put("screwdriver", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What do you want to use?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_UseCommand_NotInInventory(){
    String mockedCommand = "use screwdriver grate";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedInventory.put("hammer", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That's not a thing in your inventory.";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_UseCommand_NullInputTwo(){
    String mockedCommand = "use screwdriver";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedInventory.put("screwdriver", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What do you want to use that on?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_UseCommand_NotInRoom(){
    String mockedCommand = "use screwdriver grate";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    RoomThingDecoration mockedRoomThingDecoration = mock(RoomThingDecoration.class);
    mockedInventory.put("screwdriver", mockedRoomThingTool);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    mockedContents.put("chandelier", mockedRoomThingDecoration);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That's not a thing in this room.";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_UseCommandWrongUse(){
    String mockedCommand = "use screwdriver grate";

    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    mockedInventory.put("screwdriver", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");

    RoomThingDecoration mockedRoomThingDecoration = mock(RoomThingDecoration.class);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    mockedContents.put("grate", mockedRoomThingDecoration);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingDecoration.getName()).thenReturn("grate");

    when(mockedRoom.getName()).thenReturn("brig");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That doesn't work here.";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_UseCommandCorrectly(){
    String mockedCommand = "use keycard grate";

    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    mockedInventory.put("keycard", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedRoomThingTool.getName()).thenReturn("keycard");

    RoomThingDecoration mockedRoomThingDecoration = mock(RoomThingDecoration.class);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    mockedContents.put("grate", mockedRoomThingDecoration);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingDecoration.getName()).thenReturn("grate");

    when(mockedRoom.getName()).thenReturn("office");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);

    HashMap<String, Room> mockedLevelRooms = new HashMap<>();
    when(mockedLevelOne.getLevelRooms()).thenReturn(mockedLevelRooms);
    Room mockedVent = mock(Room.class);
    mockedLevelRooms.put("office", mockedRoom);
    mockedLevelRooms.put("vent", mockedVent);

    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You open the grate, somehow...";
    assertEquals(expected, actual);
  }
}