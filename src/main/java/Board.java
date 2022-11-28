public class Board {

  private final int rowCount;
  private final int columnCount;
  private Cell[][] cells;

  public Board(int rowCount, int columnCount) {
    this.rowCount = rowCount;
    this.columnCount = columnCount;
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

  public int getRowCount() {
    return rowCount;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public void setCell(int row, int column, CellType cellType) {
    cells[row][column].setCellType(cellType);
  }
}
