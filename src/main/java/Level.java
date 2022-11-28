import java.util.HashMap;

public class Level {

    private static final int CELL_SIZE = 100;
    private Room startingRoom;
    private Room endingRoom;
    private String successMessage;
    private int width;
    private int height;
    private Board board;
    private BoardWindow boardWindow;
    private HashMap<String, Room> levelRooms;

    public Level(Room startingRoom, Room endingRoom, String successMessage, int width, int height) {
        this.startingRoom = startingRoom;
        this.endingRoom = endingRoom;
        this.successMessage = successMessage;
        this.levelRooms = new HashMap<>();
        levelRooms.put(startingRoom.getName(), startingRoom);
        levelRooms.put(endingRoom.getName(), endingRoom);
        this.board = new Board(height, width);
        this.boardWindow = new BoardWindow(board, "Area Map", CELL_SIZE);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Board getBoard() {
        return board;
    }

    public BoardWindow getBoardWindow() {
        return boardWindow;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }

    public Room getEndingRoom() {
        return endingRoom;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public HashMap<String, Room> getLevelRooms() {
        return levelRooms;
    }

    public void putLevelRooms(String roomName, Room newRoom) {
        levelRooms.put(roomName, newRoom);
    }
}
