package Eluvian.algorithm;

import Eluvian.objects.Agent;
import Eluvian.objects.Cell;

public class DepthFirstSearch extends AlgorithmTemplate {

    private Agent lastVisited;
    private int[][] visitedCell;
    public DepthFirstSearch(Cell[][] grid, int DIM) {
        super(grid, DIM);
        startDFS();
    }

    private void startDFS() {
        long startTime = System.nanoTime();
        int[] xDirection = {1, 0, -1, 0};
        int[] yDirection = {0, 1, 0, -1};
        int endGetX = DIM - 1;
        int endGetY = DIM - 1;

        this.visitedCell = new int[DIM][DIM];
        this.visitedCell[0][0] = 1;
    }

    @Override
    public Agent returnShortestPath() {
        return null;
    }

    @Override
    public int returnPathLength() {
        return 0;
    }

    @Override
    public int[][] showVisitedCells() {
        return new int[0][];
    }

    @Override
    public boolean pathFound() {
        return false;
    }

    @Override
    public int countVisitedCells() {
        return 0;
    }

}