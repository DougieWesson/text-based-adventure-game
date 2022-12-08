import java.util.HashMap;

public class Level {

    private static final int CELL_SIZE = 75;
    private final Room startingRoom;
    private final Room endingRoom;
    private final String successMessage;
    private final BoardWindow boardWindow;
    private final HashMap<String, Room> levelRooms;

    public Level(Room startingRoom, Room endingRoom, String successMessage) {
        this.startingRoom = startingRoom;
        this.endingRoom = endingRoom;
        this.successMessage = successMessage;
        this.levelRooms = new HashMap<>();
        levelRooms.put(startingRoom.getName(), startingRoom);
        levelRooms.put(endingRoom.getName(), endingRoom);
        Board board = new Board(Descriptions.MAP_SIZE.get("height"), Descriptions.MAP_SIZE.get("width"));
        this.boardWindow = new BoardWindow(board, "Area Map", CELL_SIZE);
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

    public void putLevelRooms(Room newRoom) {
        levelRooms.put(newRoom.getName(), newRoom);
    }
}
