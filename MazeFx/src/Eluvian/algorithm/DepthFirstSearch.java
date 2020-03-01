package Eluvian.algorithm;

import Eluvian.objects.Agent;
import Eluvian.objects.Cell;
import javafx.scene.paint.Color;

import java.util.Stack;

public class DepthFirstSearch extends AlgorithmTemplate {

    private Agent lastVisited;
    private int[][] visitedCell;
    public DepthFirstSearch(Cell[][] grid, int DIM) {
        super(grid, DIM);
        startDFS();
    }

    private void startDFS() {
        long startTime = System.nanoTime();
        //int[] xDirection = {1, 0, -1, 0};
        //int[] yDirection = {0, 1, 0, -1};
        int[] xDirection = {0, -1, 0, 1};
        int[] yDirection = {-1, 0, 1, 0};
        int endGetX = DIM - 1;
        int endGetY = DIM - 1;

        this.visitedCell = new int[DIM][DIM];
        Stack<Agent> DFSStack = new Stack<>();
        Agent theBegining = new Agent(0, 0, null, 0);
        DFSStack.push(theBegining);
        Agent current =null;
        //boolean neighborNotFound = true;
        while(!DFSStack.empty() && visitedCell[endGetX][endGetY]!=1)
        {
            current= DFSStack.pop();
            System.out.println(current.getX()+", "+current.getY());
            System.out.println();

            if(visitedCell[current.getX()][current.getY()]==1) {
                continue;
            }
            visitedCell[current.getX()][current.getY()] = 1;
            for (int i = 0; i <= 3; i++) {
                if (reachable(current.getX() + xDirection[i], current.getY() + yDirection[i])) {
                    Agent neighbor = new Agent(current.getX() + xDirection[i],
                            current.getY() + yDirection[i], current, current.getLength() + 1);
                    DFSStack.push(neighbor);
                    //neighborNotFound = false;
                }

            }
            //neighborNotFound =true;
            this.MAXIMUM_FRINGE_SIZE = Math.max(this.MAXIMUM_FRINGE_SIZE, DFSStack.size());
        }

        if(visitedCell[endGetX][endGetY] == 1) {
            this.lastVisited = current;
            this.FOUND_PATH = true;
        }else {
            this.lastVisited = current;
        }
        long endTime = System.nanoTime();
        this.SEARCH_TIME = endTime - startTime;
    }

    private boolean reachable(int xCoordinate, int yCoordinate) {
        if ((xCoordinate < DIM) && (yCoordinate < DIM)
                && (xCoordinate >= 0) && (yCoordinate >= 0)
                && (visitedCell[xCoordinate][yCoordinate] == 0)
                && (grid[xCoordinate][yCoordinate].getFill() != Color.BLACK)) {
            return true;
        }
        return false;
    }
    @Override
    public Agent returnShortestPath() {
        return this.lastVisited;
    }

    @Override
    public int returnPathLength() {
        return this.lastVisited.getLength();
    }

    @Override
    public int[][] showVisitedCells() {
        return new int[0][];
    }

    @Override
    public boolean pathFound() {
        return this.FOUND_PATH;
    }

    @Override
    public int countVisitedCells() {
        return 0;
    }

}