import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

/**
 * The MazeSolver class immplements all necessary attributes for the
 * implementation of the backtracking algorithm
 */
abstract class MazeSolver {
  /**
   * An array of the type Field.
   */
  private Field[] maze;

  /**
   * An integer to define the width of the maze.
   */
  private int sizeX;

  /**
   * An integer to define the heigth of the maze.
   */
  private int sizeY;

  /**
   * An integer to define the start point of the maze.
   */
  private int start;

  /**
   * An integer to define the end point of the maze.
   */
  private int end;

  // Object from class Maze. This maze is beeing solved from MazeSolver
  protected Maze mazeObject;

  // Methods

  public void setMazeObject(Maze m) {
    this.mazeObject = m;
  }

  /**
   * @return Maze object, the JSON string gets converted to
   */
  public Maze parseFromJSON() {
    ObjectMapper objectMapper = new ObjectMapper();
    Maze maze = new Maze();

    try {
      File file = new File("../maze.json");
      maze = objectMapper.readValue(file, Maze.class);
    } catch (Exception e) {
      System.out.println(e);
    }
    return maze;
  }
}