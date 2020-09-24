// Attributes

/**
 * The Block class holds all informations about a single block in the maze.
 */
public class Block {
    // Attributes

    /**
     * An integer to define the row of a block.
     */
    private int row;

    /**
     * An integer to define the column of a block.
     */
    private int col;

    /**
     * A variable of the enumeration type @see Field to save whether a block is
     * empty, a wall block or the start or end position.
     */
    private Field fieldType;

    /**
     * An boolean to define the visited state of a block.
     */
    private boolean visited;
    private Position positionType;

    // Methodes
    /**
     * @param row set row value
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return current row value
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @param col set column value
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @return current column value
     */
    public int getCol() {
        return this.col;
    }

    /**
     * @param fieldType set fieldtype value
     */
    public void setFieldType(Field fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * @return current fieldtype value
     */
    public Field getFieldType() {
        return this.fieldType;
    }

    /**
     * @param visited set visited value
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return current visited value
     */
    public boolean getVisited() {
        return this.visited;
    }

    // getter and setter for positionType
    public void setPositionType(Position positionType) {
        this.positionType = positionType;
    }

    public Position getPositionType() {
        return this.positionType;
    }

    public String toString() {
        return "(" + getRow() + ", " + getCol() + ")";
    }
}
