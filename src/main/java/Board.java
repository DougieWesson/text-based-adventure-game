public class Board {

  private Cell[][] cells;

  public Board() {
    int rowCount = Descriptions.MAP_HEIGHT;
    int columnCount = Descriptions.MAP_WIDTH;
    cells = new Cell[rowCount][columnCount];
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < columnCount; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }
  }

  public Cell[][] getCells() {
    return cells;
  }

  public void setCells(Cell[][] cells) {
    this.cells = cells;
  }

  public void setCell(int row, int column, CellType cellType) {
    cells[row][column].setCellType(cellType);
  }
}
