package Eluvian.algorithm;
/*
 * Simple implementation of the BFS algo
 * @author Qiyang Zhong
 */
import Eluvian.objects.Agent;
import Eluvian.objects.Cell;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends AlgorithmTemplate {

    private Agent lastVisited;
    private int[][] visitedCell;
    public BreadthFirstSearch(Cell[][] grid, int DIM) {
        super(grid, DIM);
        startBFS();
    }

    private void startBFS(){
        long startTime = System.nanoTime();
        int[] xDirection = {1, 0, -1, 0};
        int[] yDirection = {0, 1, 0, -1};
        int endGetX = DIM - 1;
        int endGetY = DIM - 1;

        this.visitedCell = new int[DIM][DIM];
        this.visitedCell[0][0] = 1;
        Queue<Agent> BFSQueue = new LinkedList<>();
        Agent theBegining = new Agent(0, 0, null, 0);
        BFSQueue.add(theBegining);
        Agent current =null;

        while(!BFSQueue.isEmpty() && visitedCell[endGetX][endGetY]!=1)
        {
            current= BFSQueue.poll();
            System.out.println(current.getX()+", "+current.getY());
            System.out.println();

            for (int i = 0; i <= 3; i++) {
                if (reachable(current.getX() + xDirection[i], current.getY() + yDirection[i])) {
                    Agent neighbor = new Agent(current.getX() + xDirection[i],
                            current.getY() + yDirection[i], current, current.getLength() + 1);
                    BFSQueue.add(neighbor);
                }

            }
            this.MAXIMUM_FRINGE_SIZE = Math.max(this.MAXIMUM_FRINGE_SIZE, BFSQueue.size());
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
            visitedCell[xCoordinate][yCoordinate] = 1;
            return true;
        }
        return false;
    }
    @Override
    public Agent returnShortestPath() {
        return this.lastVisited;
    }

    @Override
    public int returnPathLength()  {
        return this.lastVisited.getLength();
    }

    @Override
    public int[][] showVisitedCells() {
        return new int[0][];
    }

    @Override
    public boolean pathFound()  {
        return this.FOUND_PATH;
    }

    @Override
    public int countVisitedCells() {
        return 0;
    }

}
