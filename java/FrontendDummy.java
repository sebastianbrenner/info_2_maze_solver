import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class FrontendDummy {

    static Maze maze;
    static Solution solution;
    static Solveable solver;

    public static void main(String[] args) {
        maze = jsonToMaze(args[1]);
        maze.stringToArray();
        solver = new Backtracking();
        solution = solver.solve(maze);
        System.out.println(solutionToString(solution));
        solution.generateJson();
    }

    private static Maze jsonToMaze(String json) {
        // use the Jackson library to parse the String to a Maze object
        // return new Maze(...);
        ObjectMapper objectMapper = new ObjectMapper();
        Maze maze = new Maze();
        try {
            File file = new File(json);
            maze = objectMapper.readValue(file, Maze.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return maze;
    }

    private static String solutionToString(Solution solution) {
        return "Is Solved: " + solution.isSolved() + "\n" + "runtime: " + solution.getRuntime() + "\n" + "solution: "
                + solution.getSolution() + "\n" + "history: " + solution.getHistory();
    }

}
