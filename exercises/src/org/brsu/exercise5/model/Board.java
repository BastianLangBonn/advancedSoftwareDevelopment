package org.brsu.exercise5.model;

/**
 * Class to represent the solution to the robot tiler problem.
 * 
 * @author bastian
 * 
 */
public class Board {

  private boolean[][] board;
  private int width;
  private int height;

  public Board() {
    board = new boolean[10][10];
    width = 10;
    height = 10;
    initializeTiles();
  }

  public Board(int width, int height) {
    board = new boolean[width][height];
    this.width = width;
    this.height = height;
    initializeTiles();
  }

  public Board(boolean[][] boardConfiguration) {
    this.width = boardConfiguration.length;
    this.height = boardConfiguration[0].length;
    this.board = new boolean[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        this.board[i][j] = boardConfiguration[i][j];
      }
    }
  }

  public Board(Board board) {
    width = board.getWidth();
    height = board.getHeight();
    this.board = new boolean[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        this.board[i][j] = board.getTile(i, j);
      }
    }

  }

  private void initializeTiles() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        board[i][j] = false;
      }
    }
  }

  /**
   * Sets a single tile of the board to the given value.
   * 
   * @param row
   * @param column
   * @param black
   */
  public void setTile(int width, int height, boolean black) {
    board[width][height] = black;
  }

  public void setBlockOfTiles(int xLeft, int yTop, int xRight, int yBottom) {
    for (int x = xLeft; x <= xRight; x++) {
      for (int y = yTop; y <= yBottom; y++) {
        board[x][y] = true;
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        stringBuilder.append(board[i][j]);
        stringBuilder.append("; ");
      }
      stringBuilder.append("\n");
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  public static void main(String[] args) {
    Board solution = new Board(100, 5);
    System.out.println(solution.toString());
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public boolean getTile(int x, int y) {
    return board[x][y];
  }

  /**
   * Returns the number of black tiles in the board.
   * 
   * @return
   */
  public int getNumberOfBlackTilesInBoard() {
    int result = 0;
    for (int i = 0; i < width; i++) {
      result += getNumberOfBlackTilesInColumn(i);
    }
    return result;
  }

  /**
   * Returns the number of black tiles in the given row.
   * 
   * @param row
   * @return
   */
  public int getNumberOfBlackTilesInRow(int row) {
    if (row >= height) {
      return 0;
    }
    int result = 0;
    for (int i = 0; i < width; i++) {
      if (getTile(i, row)) {
        result++;
      }
    }
    return result;
  }

  /**
   * Returns the number of black tiles in the given column.
   * 
   * @param column
   * @return
   */
  public int getNumberOfBlackTilesInColumn(int column) {
    if (column >= width) {
      return 0;
    }
    int result = 0;
    for (int i = 0; i < height; i++) {
      if (getTile(column, i)) {
        result++;
      }
    }
    return result;
  }

}