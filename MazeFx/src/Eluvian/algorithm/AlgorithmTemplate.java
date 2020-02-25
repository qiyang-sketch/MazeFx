package Eluvian.algorithm;
/**
 * template for BFS, DFS, A*
 */
import Eluvian.objects.Agent;
import Eluvian.objects.Cell;

abstract class AlgorithmTemplate {
    protected long SEARCH_TIME;
    protected int MAXIMUM_FRINGE_SIZE;
    protected boolean FOUND_PATH;
    protected int DIM;
    protected Cell[][] grid;
    public AlgorithmTemplate(Cell[][] grid, int DIM) {
        this.grid = grid;
        this.DIM = DIM;
    }
    /**
     * return the last node of a singly linked list for the shortest path
     * @return last Agent of the shortest path
    */
    abstract public Agent returnShortestPath();

    /*
     * return the length of the shortest path
     * @return integer the length of the shortest path
     */
    abstract public int returnPathLength();
    /**
     * show all visited cell, regardless success or failure
     * @return int[][] for all the cells visited (1)
     */
    abstract public int[][] showVisitedCells();

    /**
     * check if algorithm found a shortest path
     * @return true if found a path, false otherwise
     */
    abstract public boolean pathFound();

    /**
     * count the number of max expand node
     * @return int the size of Expand Node
     */
    abstract public int countVisitedCells();

}
