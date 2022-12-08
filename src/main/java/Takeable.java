import java.util.HashMap;

public interface Takeable {
  String takeThing(HashMap<String, Tool> inventory, HashMap<String, RoomThing> contents);
}
