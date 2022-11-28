import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerTest {

  @Test
  void movePlayerIntoWall() {
    Room mockedRoom = mock(Room.class);
    Level mockedLevel = mock(Level.class);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    Board mockedBoard = mock(Board.class);
    HashMap<Direction, Room> mockedHashmap = mock(HashMap.class);
    when(mockedRoom.getDirections()).thenReturn(mockedHashmap);
    when(mockedHashmap.get(Direction.NORTH)).thenReturn(null);
    when(mockedLevel.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedBoardWindow.getBoard()).thenReturn(mockedBoard);
    String expected = Player.WRONG_WAY_MESSAGE;
    Player playerUnderTest = new Player(mockedRoom, mockedLevel);
    String actual = playerUnderTest.move(Direction.NORTH);
    assertEquals(expected, actual);
  }

  @Test
  void movePlayerCorrectDirection() {
    Room mockedRoom = mock(Room.class);
    Room mockedStartingRoom = mock(Room.class);
    Level mockedLevel = mock(Level.class);
    BoardWindow mockedBoardWindow = mock(BoardWindow.class);
    Board mockedBoard = mock(Board.class);
    HashMap<Direction, Room> mockedHashmap = mock(HashMap.class);
    when(mockedRoom.getDirections()).thenReturn(mockedHashmap);
    Room mockedNextRoom = mock(Room.class);
    when(mockedHashmap.get(Direction.NORTH)).thenReturn(mockedNextRoom);
    when(mockedLevel.getBoardWindow()).thenReturn(mockedBoardWindow);
    when(mockedLevel.getStartingRoom()).thenReturn(mockedStartingRoom);
    when(mockedBoardWindow.getBoard()).thenReturn(mockedBoard);
    when(mockedRoom.getName()).thenReturn("mockedRoomName");
    when(mockedRoom.getDescription()).thenReturn("mockedRoomDescription");
    when(mockedNextRoom.getName()).thenReturn("mockedNextRoomName");
    when(mockedNextRoom.getDescription()).thenReturn("mockedNextRoomDescription");
    String expected = "You have moved NORTH\nYou are in the mockedNextRoomName.\nmockedNextRoomDescription";
    Player playerUnderTest = new Player(mockedRoom, mockedLevel);
    String actual = playerUnderTest.move(Direction.NORTH);
    assertEquals(expected, actual);
  }


}