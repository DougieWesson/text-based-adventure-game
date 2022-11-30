import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInput extends KeyAdapter {
  private final Game game;

  public UserInput(Game game) {
    this.game = game;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
      game.runCommand(game.getCurrentLevel().getBoardWindow().getInputField().getText());
      game.getCurrentLevel().getBoardWindow().getInputField().setText("");
    }
  }
}
