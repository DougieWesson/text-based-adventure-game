import java.util.HashMap;

public class Tool implements Takeable, RoomThing {

  private final String name;
  private final String description;

  public Tool(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String inspect() {
    return description;
  }

  public String takeThing(HashMap<String, Tool> inventory, HashMap<String, RoomThing> contents) {
    inventory.put(this.getName(), this);
    contents.remove(this.name);
    return "You pick up the " + this.name;
  }

}
