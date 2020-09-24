import java.util.*;
import java.io.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonEncoding;

/**
 * A class representing a maze's solution.
 * 
 * It holds various useful information, including whether the solution actually
 * solved anything, a list of solving indices, etc.
 */
public class Solution extends MazeSolver {

  private boolean solved = false;
  private long runtime;
  private ArrayList<Integer> solution = new ArrayList<Integer>();
  private ArrayList<ArrayList<String>> history = new ArrayList<ArrayList<String>>();

  // Methods

  /**
   * Add an element to the solution histroy
   * 
   * @param index  The index of the block to add
   * @param status The status of the block to add
   */
  public void addToHistory(int index, String status) {
    ArrayList<String> list = new ArrayList<>();
    list.add(Integer.toString(index));
    list.add(status);
    history.add(list);
  }

  // return history
  public ArrayList<ArrayList<String>> getHistory() {
    return history;
  }

  /**
   * Add an index representing one of the maze's blocks to the solution
   * 
   * @param index Index to add
   */
  public void addToSolution(int index) {

    solution.add(index);
  }

  /**
   * @return the time that was needed to solve the maze
   */
  public long getRuntime() {
    return runtime;
  }

  public void setRuntime(long runtime) {
    this.runtime = runtime;
  }

  /**
   * @return The maze's solution as a list of indices
   */
  public ArrayList<Integer> getSolution() {
    return solution;
  }

  /**
   * Remove top of current solution list
   */
  public void removeFromSolution() {
    if (this.solution.size() > 1)
      this.solution.remove(this.solution.size() - 1);
  }

  /**
   * Mark solution as successful
   */
  public void setSolved() {
    this.solved = true;
  }

  /**
   * @return True if the solution actually solved a maze, false otherwise
   */
  public boolean isSolved() {
    return this.solved;
  }

  public void generateJson() {
    JsonFactory factory = new JsonFactory();
    try {
      JsonGenerator generator = factory.createGenerator(new File("../solution.json"), JsonEncoding.UTF8);

      generator.writeStartObject();
      generator.writeBooleanField("solved", solved);
      generator.writeRaw('\n');
      generator.writeNumberField("runtime", runtime);
      generator.writeRaw('\n');
      generator.writeFieldName("solution");
      generator.writeStartArray();
      for (int i : solution) {
        generator.writeNumber(i);
      }
      generator.writeEndArray();
      generator.writeRaw('\n');
      generator.writeFieldName("history");
      generator.writeStartArray();
      for (ArrayList<String> hist : history) {
        generator.writeStartArray();
        for (String str : hist) {
          generator.writeString(str);
        }
        generator.writeEndArray();
      }
      generator.writeEndArray();
      generator.writeEndObject();
      generator.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
