public class Test {
  public static void main(String[] args) {

    Maze m = new Maze();
    Backtracking t = new Backtracking();

    m = t.parseFromJSON();

    m.stringToArray();

    // Maze printen
    System.out.println(m);

    // Maze loesen
    Solution s = t.solve(m);

    // Checken ob Lösung existiert
    if (!s.isSolved()) {
      System.out.println("🚫 Could not find solution 🚫");
      return;
    }
    // Loesungsarray printen
    System.out.println(s.getSolution());

    // Loesungweg durch Maze printen
    m.printMazeWithSolution(s);
  }
}
