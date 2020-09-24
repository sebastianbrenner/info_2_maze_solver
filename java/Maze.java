import java.util.*;

/**
 * The Maze class holds all important informations about the maze, like size,
 * position of the walls and start and end point.
 * 
 * @author Sebastian
 * 
 */
public class Maze {
  // Attributes

  /**
   * An integer to define the width of the maze.
   */
  private int sizeX;

  /**
   * An integer to define the heigth of the maze.
   */
  private int sizeY;

  /**
   * A string array to define the position of the walls.
   */
  private String[] maze;

  /**
   * An integer to define the start point of the maze.
   */
  private int start;

  /**
   * An integer to define the end point of the maze.
   */
  private int end;

  /**
   * a 2D array which contains every block of the maze.
   */
  private Block[][] fieldArray;

  /**
   * A block which is identified as starting point.
   */
  private Block startBlock;

  /**
   * A block which is identified as end point.
   */
  private Block endBlock;

  // Methods

  /**
   * @param sizeX set size x value
   */
  public void setSizeX(int sizeX) {
    this.sizeX = sizeX;
  }

  /**
   * @return current size x value
   */
  public int getSizeX() {
    return sizeX;
  }

  /**
   * @param sizeY set size y value
   */
  public void setSizeY(int sizeY) {
    this.sizeY = sizeY;
  }

  /**
   * @return current size y value
   */
  public int getSizeY() {
    return sizeY;
  }

  /**
   * @param maze set maze array
   */
  public void setMaze(String[] maze) {
    this.maze = maze;
  }

  /**
   * @return current maze array
   */
  public String[] getMaze() {
    return maze;
  }

  /**
   * @param start set start value
   */
  public void setStart(int start) {
    this.start = start;
  }

  /**
   * @return current start value
   */
  public int getStart() {
    return start;
  }

  /**
   * @param end set end value
   */
  public void setEnd(int end) {
    this.end = end;
  }

  /**
   * @return current end value
   */
  public int getEnd() {
    return end;
  }

  /**
   * @return Start block
   */
  public Block getStartBlock() {
    return this.startBlock;
  }

  /**
   * @param Start block
   */
  public void setStartBlock(Block startBlock) {
    this.startBlock = startBlock;
  }

  /**
   * @return End block
   */
  public Block getEndBlock() {
    return this.endBlock;
  }

  /**
   * @param End block
   */
  public void setEndBlock(Block endBlock) {
    this.endBlock = endBlock;
  }

  // get 2D Array

  public Block[][] getFieldArray() {
    return fieldArray;
  }

  /**
   * Method to convert maze-String into 2D-Array of Blocks
   * 
   * @author Julian
   */
  public void stringToArray() {
    // create 2D-Array of Block Objects with given size
    this.fieldArray = new Block[sizeY][sizeX];

    // iterators for width
    int j;
    // and heigth
    int i;

    // iterator for maze-String
    int k = 0;

    // fill in Blocks with coordinates and unvisited Status

    for (i = 0; i < sizeY; ++i) {
      for (j = 0; j < sizeX; ++j) {
        this.fieldArray[i][j] = new Block();
        this.fieldArray[i][j].setRow(i);
        this.fieldArray[i][j].setCol(j);
        this.fieldArray[i][j].setVisited(false);

        // set positionType corner-blocks
        if (i == 0 && j == 0) {
          this.fieldArray[i][j].setPositionType(Position.UPPERLEFT);
        } else if (i == 0 && j == (sizeX - 1)) {
          this.fieldArray[i][j].setPositionType(Position.UPPERRIGHT);
        } else if (i == (sizeY - 1) && j == 0) {
          this.fieldArray[i][j].setPositionType(Position.LOWERLEFT);
        } else if (i == (sizeY - 1) && j == (sizeX - 1)) {
          this.fieldArray[i][j].setPositionType(Position.LOWERRIGHT);
        }

        // set positionType edge-blocks
        else if (i == 0 && j != 0 && j != (sizeX - 1)) {
          this.fieldArray[i][j].setPositionType(Position.UPPEREDGE);
        } else if (j == (sizeX - 1) && i != 0 && i != (sizeY - 1)) {
          this.fieldArray[i][j].setPositionType(Position.RIGHTEDGE);
        } else if (i == (sizeY - 1) && j != 0 && j != (sizeX - 1)) {
          this.fieldArray[i][j].setPositionType(Position.LOWEREDGE);
        } else if (i != 0 && i != (sizeY - 1) && j == 0) {
          this.fieldArray[i][j].setPositionType(Position.LEFTEDGE);
        }

        // set positionType Middle-blocks
        else if (i != 0 && i != (sizeY - 1) && j != 0 && j != (sizeX - 1)) {
          this.fieldArray[i][j].setPositionType(Position.MIDDLE);
        }

        // fill Blocks with fieldType
        switch (maze[k]) {
        case "EMPTY":
          this.fieldArray[i][j].setFieldType(Field.EMPTY);
          break;

        case "WALL":
          this.fieldArray[i][j].setFieldType(Field.WALL);
          break;

        case "START":
          this.fieldArray[i][j].setFieldType(Field.START);
          startBlock = this.fieldArray[i][j];
          break;

        case "END":
          this.fieldArray[i][j].setFieldType(Field.END);
          endBlock = this.fieldArray[i][j];
          break;

        // catch unknown String
        default:
          System.out.println("Unkown field status");
        }
        ++k;
      }
    }

  }

  // create List containing all neighbour-blocks
  public static ArrayList<Block> getNeighbours(Block b, Block[][] fieldArray) {
    ArrayList<Block> neighbours = new ArrayList<>();

    // only add Block to neighbour list if fieldType is not "WALL"
    switch (b.getPositionType()) {
    case UPPEREDGE:
      if (fieldArray[b.getRow()][(b.getCol() - 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() - 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() - 1)]);
      }
      if (fieldArray[(b.getRow() + 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() + 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() + 1)][b.getCol()]);
      }
      if (fieldArray[b.getRow()][(b.getCol() + 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() + 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() + 1)]);
      }
      break;
    case RIGHTEDGE:
      if (fieldArray[(b.getRow() - 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() - 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() - 1)][b.getCol()]);
      }
      if (fieldArray[b.getRow()][(b.getCol() - 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() - 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() - 1)]);
      }
      if (fieldArray[(b.getRow() + 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() + 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() + 1)][b.getCol()]);
      }
      break;
    case LEFTEDGE:
      if (fieldArray[(b.getRow() - 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() - 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() - 1)][b.getCol()]);
      }
      if (fieldArray[b.getRow()][(b.getCol() + 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() + 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() + 1)]);
      }
      if (fieldArray[(b.getRow() + 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() + 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() + 1)][b.getCol()]);
      }
      break;
    case LOWEREDGE:
      if (fieldArray[b.getRow()][(b.getCol() - 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() - 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() - 1)]);
      }
      if (fieldArray[(b.getRow() - 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() - 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() - 1)][b.getCol()]);
      }
      if (fieldArray[b.getRow()][(b.getCol() + 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() + 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() + 1)]);
      }
      break;
    case UPPERLEFT:
      if (fieldArray[b.getRow()][(b.getCol() + 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() + 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() + 1)]);
      }
      if (fieldArray[(b.getRow() + 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() + 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() + 1)][b.getCol()]);
      }
      break;
    case UPPERRIGHT:
      if (fieldArray[b.getRow()][(b.getCol() - 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() - 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() - 1)]);
      }
      if (fieldArray[(b.getRow() + 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() + 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() + 1)][b.getCol()]);
      }
    case LOWERLEFT:
      if (fieldArray[b.getRow()][(b.getCol() + 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() + 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() + 1)]);
      }
      if (fieldArray[(b.getRow() - 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() - 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() - 1)][b.getCol()]);
      }
      break;
    case LOWERRIGHT:
      if (fieldArray[b.getRow()][(b.getCol() - 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() - 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() - 1)]);
      }
      if (fieldArray[(b.getRow() - 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() - 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() - 1)][b.getCol()]);
      }
      break;
    case MIDDLE:
      if (fieldArray[b.getRow()][(b.getCol() - 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() - 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() - 1)]);
      }
      if (fieldArray[b.getRow()][(b.getCol() + 1)].getFieldType() != Field.WALL
          && !fieldArray[b.getRow()][(b.getCol() + 1)].getVisited()) {
        neighbours.add(fieldArray[b.getRow()][(b.getCol() + 1)]);
      }
      if (fieldArray[(b.getRow() - 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() - 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() - 1)][b.getCol()]);
      }
      if (fieldArray[(b.getRow() + 1)][b.getCol()].getFieldType() != Field.WALL
          && !fieldArray[(b.getRow() + 1)][b.getCol()].getVisited()) {
        neighbours.add(fieldArray[(b.getRow() + 1)][b.getCol()]);
      }
      break;
    default:
      return neighbours;
    }
    return neighbours;
  }

  /**
   * toString method to visualize testing maze
   * 
   * @return string to display the maze
   * @author Julian
   */
  public String toString() {
    String ret = new String();
    for (int i = 0; i < sizeY; ++i) {
      for (int j = 0; j < sizeX; ++j) {
        switch (fieldArray[i][j].getFieldType()) {
        case EMPTY:
          ret += "🔳";
          break;
        case WALL:
          ret += "🚫";
          break;
        case START:
          ret += "✅";
          break;
        case END:
          ret += "🔴";
          break;
        default:
          return "Unkown field status";
        }
      }
      ret += "\n";
    }
    ret += "✅: START     🔴: END";
    return ret;
  }

  // method to convert coordinates of Block into integer

  public int coordsToIndex(Block b) {
    return b.getCol() + b.getRow() * sizeY;
  }

  public void printMazeWithSolution(Solution s) {
    String[][] pic = new String[sizeY][sizeX];
    for (int i = 0; i < sizeY; ++i) {
      for (int j = 0; j < sizeX; ++j) {
        pic[i][j] = new String();
        switch (fieldArray[i][j].getFieldType()) {
        case EMPTY:
          pic[i][j] = "🔳";
          break;
        case WALL:
          pic[i][j] = "🚫";
          break;
        case START:
          pic[i][j] = "🔴";
          break;
        case END:
          pic[i][j] = "✅";
          break;
        }
      }
    }
    for (int k : s.getSolution()) {
      int i = k / sizeX;
      int j = k % sizeX;
      pic[i][j] = "✅";
    }

    for (int i = 0; i < sizeY; ++i) {
      for (int j = 0; j < sizeX; ++j) {
        System.out.print(pic[i][j]);
      }
      System.out.print("\n");
    }

  }
}
