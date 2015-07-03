package org.brsu.exercise5.control;

import org.brsu.exercise5.model.Board;

public class BoardValidator {

  private Board board;
  private int[] rowConstraints;
  private int[] columnConstraints;

  public BoardValidator(Board board, int[] rowConstraints, int[] columnConstraints) {
    this.board = board;
    this.rowConstraints = rowConstraints;
    this.columnConstraints = columnConstraints;
    if (board.getWidth() != rowConstraints.length || board.getHeight() != columnConstraints.length) {
      throw new IllegalArgumentException(String.format("The dimensions of the constraints are wrong."));
    }

  }

  /**
   * Validates that all tiles are set according to the constraints.
   * 
   * @return
   */
  public boolean validateTiles() {
    boolean isValid = true;
    for (int i = 0; i < board.getWidth(); i++) {
      for (int j = 0; j < board.getHeight(); j++) {
        if (board.getTile(i, j)) {
          isValid = checkColumnConstraints();
          isValid = isValid && checkRowConstraints();
          isValid = isValid && checkForValidRectangle(i, j);
          isValid = isValid && checkForTouchingRectangles(i, j);
          if (!isValid) {
            return false;
          }
        }
      }
    }
    return isValid;
  }

  private boolean checkForTouchingRectangles(int x, int y) {
    // Check left neighbor
    if (x > 0 && !board.getTile(x - 1, y)) {
      if (y > 0 && board.getTile(x - 1, y - 1)) {
        return false;
      }
      if (y < board.getHeight() - 1 && board.getTile(x - 1, y + 1)) {
        return false;
      }
    }
    // Check right neighbor
    if (x < board.getWidth() - 1 && !board.getTile(x + 1, y)) {
      if (y > 0 && board.getTile(x + 1, y - 1)) {
        return false;
      }
      if (y < board.getHeight() - 1 && board.getTile(x + 1, y + 1)) {
        return false;
      }
    }
    // Check top neighbor
    if (y > 0 && !board.getTile(x, y - 1)) {
      if (x > 0 && board.getTile(x - 1, y - 1)) {
        return false;
      }
      if (y < board.getHeight() - 1 && board.getTile(x + 1, y - 1)) {
        return false;
      }
    }
    // check bottom neighbor
    if (y < board.getHeight() - 1 && !board.getTile(x, y + 1)) {
      if (x > 0 && board.getTile(x - 1, y + 1)) {
        return false;
      }
      if (x < board.getWidth() - 1 && board.getTile(x + 1, y + 1)) {
        return false;
      }
    }
    return true;
  }

  private boolean checkForValidRectangle(int x, int y) {
    boolean left = false;
    boolean right = false;
    boolean top = false;
    boolean bottom = false;
    if (x > 0 && board.getTile(x - 1, y)) {
      left = true;
    }
    if (x < board.getWidth() - 1 && board.getTile(x + 1, y)) {
      right = true;
    }
    if (y > 0 && board.getTile(x, y - 1)) {
      top = true;
    }
    if (y < board.getHeight() - 1 && board.getTile(x, y + 1)) {
      bottom = true;
    }

    return (top || bottom) && (left || right);
  }

  private boolean checkRowConstraints() {
    for (int i = 0; i < board.getWidth(); i++) {
      if (board.getNumberOfBlackTilesInRow(i) != rowConstraints[i]) {
        return false;
      }
    }
    return true;
  }

  private boolean checkColumnConstraints() {
    for (int i = 0; i < board.getHeight(); i++) {
      if (board.getNumberOfBlackTilesInColumn(i) != columnConstraints[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Creates a new {@link Board} having only black tiles within the given block
   * and checks the validity of this board configuration.
   * 
   * @param xLeft
   * @param xRight
   * @param yTop
   * @param yBottom
   * @return
   */
  public boolean checkValidBlock(int xLeft, int xRight, int yTop, int yBottom) {
    Board possibleBoard = new Board(board);
    possibleBoard.setBlockOfTiles(xLeft, yTop, xRight, yBottom);
    BoardValidator boardValidator = new BoardValidator(possibleBoard, rowConstraints, columnConstraints);
    return boardValidator.validateTiles();
  }
}
