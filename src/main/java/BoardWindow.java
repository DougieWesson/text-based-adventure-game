import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class BoardWindow extends Canvas {

  private final Integer width;
  private final Integer height;
  private JFrame frame;
  private JTextArea textArea;
  private JTextField inputField;
  private Board board;
  private String title;

  private int cellSize = 30;


  public BoardWindow(Board board, String title, int cellSize) {
    this.board = board;
    this.width = Descriptions.MAP_WIDTH;
    this.height = Descriptions.MAP_HEIGHT;
    this.title = title;
    this.cellSize = cellSize;
    initBoardWindow();
  }

  private void initBoardWindow() {
    this.frame = new JFrame(this.title);
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize((width * cellSize), (height * cellSize));
    this.textArea = new JTextArea();
    this.inputField = new JTextField();
    this.frame.getContentPane().add(this, BorderLayout.NORTH);
    textArea.setRows(10);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setEditable(false);
    JScrollPane scroll = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    DefaultCaret caret = (DefaultCaret) textArea.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    this.frame.getContentPane().add(scroll, BorderLayout.CENTER);
    this.frame.getContentPane().add(inputField, BorderLayout.SOUTH);
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

  private void drawCurrentCell(int row, int column, Color color, Graphics g) {
    g.setColor(color);
    g.fillRect(column * cellSize, row * cellSize, cellSize, cellSize);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image playerIcon = toolkit.getImage("resources/player-icon.png");
    g.drawImage(playerIcon, column * cellSize, row * cellSize, this);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Cell cell = board.getCells()[i][j];
        switch (cell.getCellType()) {
          case VISITED:
            drawCell(i, j, new Color(0x413F42), g);
            break;
          case CURRENT_ROOM:
            drawCurrentCell(i, j, new Color(0x7F8487), g);
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

  public JTextArea getTextArea() {
    return textArea;
  }

  public JTextField getInputField() {
    return inputField;
  }
}
