public class Decoration implements RoomThing {
  private final String name;
  private final String description;

  public Decoration(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  @Override
  public String inspect() {
    return description;
  }

}
