public class Character implements Speakable, RoomThing {

  private final String name;
  private final String description;
  private final String speech;

  public Character(String name, String description, String speech) {
    this.name = name;
    this.description = description;
    this.speech = speech;
  }

  public String getName() {
    return name;
  }

  public String inspect() {
    return description;
  }

  @Override
  public String talkTo() {
    return this.speech;
  }
}
