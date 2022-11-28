import javax.swing.*;
import java.awt.*;

public class BoardWindow extends Canvas {

  private JFrame frame;
  private Board board;

  private String title;

  private int cellSize = 30;

  public BoardWindow(Board board, String title) {
    this.board = board;
    this.title = title;
    initBoardWindow();
  }

  public BoardWindow(Board board, String title, int cellSize) {
    this.board = board;
    this.title = title;
    this.cellSize = cellSize;
    initBoardWindow();
  }

  private void initBoardWindow() {
    this.frame = new JFrame(this.title);
    this.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    this.setSize(board.getColumnCount() * cellSize, board.getRowCount() * cellSize);
    this.frame.add(this);
    this.frame.pack();
    this.frame.setVisible(true);
  }

  public void showBoard() {
    this.frame.setVisible(true);
  }

  public void hideBoard() {
    this.frame.setVisible(false);
  }

  private void drawCell(int row, int column, Color color, Graphics g) {
    g.setColor(color);
    g.fillRect(column * cellSize, row * cellSize, cellSize, cellSize);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int i = 0; i < board.getRowCount(); i++) {
      for (int j = 0; j < board.getColumnCount(); j++) {
        Cell cell = board.getCells()[i][j];
        switch (cell.getCellType()) {
          case VISITED:
            drawCell(i, j, new Color(0x413F42), g);
            break;
          case CURRENT_ROOM:
            drawCell(i, j, new Color(0x7F8487), g);
            break;
          case START:
            drawCell(i, j, new Color(0xCAF0F8), g);
            break;
          case EMPTY:
            drawCell(i, j, new Color(0x181818), g);
            break;
          default:
            break;
        }
      }
    }
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public int getCellSize() {
    return cellSize;
  }

  public void setCellSize(int cellSize) {
    this.cellSize = cellSize;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
