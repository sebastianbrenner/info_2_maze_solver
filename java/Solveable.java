/**
 * The interface Solveable implements the method solve, which is necessary for
 * the implementation of the backtracking algorithm
 * 
 * @author Sebastian
 */
interface Solveable {
    /**
     * @param maze maze object for the backtracking algorithm
     */
    public Solution solve(Maze maze);
}