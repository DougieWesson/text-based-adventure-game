import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
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


  //Get Player
  @Test
  void getPlayer() {
    assertEquals(mockedPlayer, gameUnderTest.getPlayer());
  }


  //Empty Command
  @Test
  void runCommand_Null() {
    String mockedCommand = "";
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Enter a command";
    assertEquals(expected, actual);
  }


  //Move Command
  @Test
  void runCommand_MoveNorth() {
    String mockedCommand = "move north";
    when(mockedPlayer.move(Direction.NORTH)).thenReturn("Player moved North");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedLevelOne.getEndingRoom()).thenReturn(mockedEndingRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Where are you gonna go?";
    assertEquals(expected, actual);
  }


  //Inspect Command
  @Test
  void runCommand_Inspect_Null() {
    String mockedCommand = "inspect";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What did you want to inspect?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_Inspect_Room() {
    String mockedCommand = "inspect room";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    when(mockedRoom.listContents()).thenReturn("mock room contents");
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "mock room contents";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_Inspect_RoomThing_Correct() {
    String mockedCommand = "inspect screwdriver";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.inspect()).thenReturn("screwdriver description");
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "screwdriver description";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_Inspect_RoomThing_Wrong() {
    String mockedCommand = "inspect screwball";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.inspect()).thenReturn("screwdriver description");
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What did you want to inspect?";
    assertEquals(expected, actual);
  }


  //Talk Command
  @Test
  void runCommand_Talk_Null() {
    String mockedCommand = "talk";
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Who did you want to talk to?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_Talk_EmptyRoom() {
    String mockedCommand = "talk screwdriver";
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "This is an empty room...";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_Talk_WrongThing() {
    String mockedCommand = "talk hammer";
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That's not something in this room";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_Talk_Correct() {
    String mockedCommand = "talk screwdriver";
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedRoomThingTool.talkTo()).thenReturn("screwdriver speech");
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "screwdriver speech";
    assertEquals(expected, actual);
  }



  //Check Inventory Command
  @Test
  void runCommand_CheckEmptyInventory() {
    String mockedCommand = "check inventory";
    HashMap<String, RoomThingTool> mockedInventory = mock(HashMap.class);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedInventory.isEmpty()).thenReturn(true);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You have these items in your inventory:mockedRoomThingToolName";
    assertEquals(expected, actual);
  }


  //Take Command
  @Test
  void runCommand_TakeItem() {
    String mockedCommand = "take screwdriver";
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    HashMap<String, RoomThing> mockedContents = new HashMap<>();
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    mockedContents.put("screwdriver", mockedRoomThingTool);
    when(mockedRoom.getContents()).thenReturn(mockedContents);
    when(mockedRoomThingTool.getName()).thenReturn("screwdriver");
    when(mockedRoomThingTool.takeThing(mockedInventory, mockedContents)).thenReturn("You pick up the screwdriver");
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What do you want to take?";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_TakeMissingItem() {
    String mockedCommand = "take screwdriver";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "This room is empty.";
    assertEquals(expected, actual);
  }


  //Use Command
  @Test
  void runCommand_UseCommand_EmptyInventory() {
    String mockedCommand = "use screwdriver grate";
    when(mockedPlayer.getInventory()).thenReturn(new HashMap<>());
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You don't have anything to use.";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_NullInput() {
    String mockedCommand = "use";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedInventory.put("screwdriver", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What do you want to use?";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_NotInInventory() {
    String mockedCommand = "use screwdriver grate";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedInventory.put("hammer", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That's not a thing in your inventory.";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_NullInputTwo() {
    String mockedCommand = "use screwdriver";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    RoomThingTool mockedRoomThingTool = mock(RoomThingTool.class);
    mockedInventory.put("screwdriver", mockedRoomThingTool);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What do you want to use that on?";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_NotInRoom() {
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That's not a thing in this room.";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_WrongUse() {
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "That doesn't work here.";
    assertEquals(expected, actual);
  }

  @Test
  void runCommand_UseCommand_Correct() {
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
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    Room mockedVent = mock(Room.class);
    mockedLevelRooms.put("office", mockedRoom);
    mockedLevelRooms.put("vent", mockedVent);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "You open the grate, somehow...";
    assertEquals(expected, actual);
  }


  //Show Map Command
  @Test
  void runCommand_ShowMapCommand_Null() {
    String mockedCommand = "show";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Are you trying to show the map?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_ShowMapCommand_Wrong() {
    String mockedCommand = "show mop";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Pardon?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_ShowMapCommand_Correct() {
    String mockedCommand = "show map";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Map shown";
    assertEquals(expected, actual);
  }


  //Hide Map Command
  @Test
  void runCommand_HideMapCommand_Null() {
    String mockedCommand = "hide";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What are you hiding?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_HideMapCommand_Wrong() {
    String mockedCommand = "hide mop";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "What do you want to do?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_HideMapCommand_Correct() {
    String mockedCommand = "hide map";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Map hidden";
    assertEquals(expected, actual);
  }


  //Cheat Command
  @Test
  void runCommand_CheatCommand_Null() {
    String mockedCommand = "cheat";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Are you trying to cheat?";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_CheatCommand_WrongCheat() {
    String mockedCommand = "cheat NOT-A-CHEAT";
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Cheat failed, ya cheater.";
    assertEquals(expected, actual);
  }
  @Test
  void runCommand_CheatCommand_Primus() {
    String mockedCommand = "cheat primus";
    HashMap<String, RoomThingTool> mockedInventory = new HashMap<>();
    when(mockedPlayer.getCurrentRoom()).thenReturn(mockedRoom);
    when(mockedPlayer.getInventory()).thenReturn(mockedInventory);
    HashMap<String, Room> mockedLevelRooms = new HashMap<>();
    when(mockedLevelOne.getLevelRooms()).thenReturn(mockedLevelRooms);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    JTextArea mockedTextArea = mock(JTextArea.class);
    when(mockedLevelOne.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getTextArea()).thenReturn(mockedTextArea);
    mockedLevelRooms.put("office", mockedRoom);
    String actual = gameUnderTest.runCommand(mockedCommand);
    String expected = "Level One Skipped, use keycard on grate to escape.";
    assertEquals(expected, actual);
  }







}
