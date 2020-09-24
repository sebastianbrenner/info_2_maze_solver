import java.util.*;

public class Backtracking extends MazeSolver implements Solveable {

  public boolean backtrack_block(Block b, Solution s) {
    ArrayList<Block> neighbours = Maze.getNeighbours(b, mazeObject.getFieldArray());
    s.addToHistory(mazeObject.coordsToIndex(b), "CURRENT");
    b.setVisited(true);
    s.addToHistory(mazeObject.coordsToIndex(b), "VISITED");
    s.addToSolution(mazeObject.coordsToIndex(b));

    if (b.getFieldType() == Field.END) {
      return true;
    }

    if (neighbours.isEmpty()) {
      s.addToHistory(mazeObject.coordsToIndex(b), "DEAD_END");
      return false;
    }

    for (Block n : neighbours) {
      boolean backtrack = !backtrack_block(n, s);
      if (backtrack) {
        s.removeFromSolution();
      } else {
        return true;
      }
    }
    // end up here if all paths are false/no solution path found
    return false;
  }

  public Solution solve(Maze m) {
    super.setMazeObject(m);
    Solution s = new Solution();
    if (backtrack_block(m.getStartBlock(), s)) {
      s.setSolved();

      if (s.getSolution().size() == 0) {
        return s;
      }

      // Walk back along the solving path and add to histroy
      for (int i = s.getSolution().size() - 1; i >= 0; --i) {
        s.addToHistory(s.getSolution().get(i), "CURRENT");
        s.addToHistory(s.getSolution().get(i), "SOLUTION");
      }
    }

    return s;
  }
}