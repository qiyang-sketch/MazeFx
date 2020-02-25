package Eluvian.algorithm;
/*
 * Search Algorithm using Euclidean and Manhattan heuristic
 * @Heurisitc Euclidean: geometric distance between 2 points
 * calculated using pythagorean theorem
 * @Heurisitc Manhattan: actual traveling distance in a grid
 * formula is |x1-x2|+|y1-y2|
 * @author Qiyang Zhong
 */
import Eluvian.objects.Agent;
import Eluvian.objects.Cell;
import Eluvian.objects.PriorityAgent;
import Eluvian.objects.PriorityComparator;
import javafx.scene.paint.Color;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch extends AlgorithmTemplate {

    private PriorityAgent lastVisited;
    private int[][] visitedCell;
    public AStarSearch(Cell[][] grid, int DIM, boolean isEuclidean) {
        super(grid, DIM);
        if (isEuclidean) {
            startEuclidean();
        } else {
            startManhattan();
        }
    }

    public void startEuclidean() {
        long startTime = System.nanoTime();
        int[] xDirection = {1, 0, -1, 0};
        int[] yDirection = {0, 1, 0, -1};
        int endGetX = DIM - 1;
        int endGetY = DIM - 1;

        this.visitedCell = new int[DIM][DIM];
        this.visitedCell[0][0] = 1;
        double toGoal = Math.sqrt(Math.pow(endGetX - 0, 2)+ Math.pow(endGetY - 0, 2));
        PriorityAgent current = new PriorityAgent(0,0, null, toGoal, 0);
        Agent aNode;
        PriorityAgent neighbor;

        Comparator<PriorityAgent> comparator = new PriorityComparator();
        //Algo will choose Agent with lowest heuristic to visit first
        PriorityQueue<PriorityAgent> distanceQueue = new PriorityQueue<>(10, comparator);
        distanceQueue.add(current);
        // 10 is initial capacity, which will grows

        double savedHeuristic = 0;
        while(visitedCell[endGetX][endGetY] != 1 && !distanceQueue.isEmpty()) {
            current = distanceQueue.remove();
            savedHeuristic = Math.sqrt(Math.pow(endGetX - current.getX(), 2)+
                    Math.pow(endGetY - current.getY(), 2));
            for (int i = 0; i <= 3; i++) {
                //visiting the four neighbors
                if (reachable(current.getX() + xDirection[i], current.getY() + yDirection[i])) {

                    aNode = new Agent(current.getX() + xDirection[i], current.getY() + yDirection[i],
                            null, current.getLength() + 1);

                    //Calculating distance from the start to neighbor
                    double currentDistance = current.getPriority() - savedHeuristic + 1;
                    //Calculating heuristic
                    double euclideanDistance = Math.sqrt(Math.pow(endGetX - aNode.getX(), 2)+
                            Math.pow(endGetY - aNode.getY(), 2));
                    double heuristic = currentDistance + euclideanDistance;

                    //saving neighbor to Queue
                    neighbor = new PriorityAgent(current.getX() + xDirection[i],
                            current.getY() + yDirection[i], current, heuristic, current.getLength() + 1);
                    distanceQueue.add(neighbor);
                }
            }
            this.MAXIMUM_FRINGE_SIZE = Math.max(this.MAXIMUM_FRINGE_SIZE, distanceQueue.size());
        }
        if (visitedCell[endGetX][endGetY] == 1) {
            this.FOUND_PATH = true;
            this.lastVisited = current;
        }
        else {
            this.lastVisited = current;
        }
        long endTime = System.nanoTime();
        this.SEARCH_TIME = endTime - startTime;

    }


    public void startManhattan() {
        long startTime = System.nanoTime();
        int[] xDirection = {1, 0, -1, 0};
        int[] yDirection = {0, 1, 0, -1};
        int endGetX = DIM - 1;
        int endGetY = DIM - 1;
        this.visitedCell = new int[DIM][DIM];
        this.visitedCell[0][0] = 1;
        double toGoal = Math.abs(endGetX - 0) + Math.abs(endGetY - 0);
        PriorityAgent current = new PriorityAgent(0,0, null, toGoal, 0);
        Agent aNode;
        PriorityAgent neighbor;
        Comparator<PriorityAgent> comparator = new PriorityComparator();
        PriorityQueue<PriorityAgent> distanceQueue = new PriorityQueue<PriorityAgent>(10, comparator);
        // 10 is initial capacity, which will grows
        distanceQueue.add(current);
        double savedHeuristic = 0;

        while(visitedCell[endGetX][endGetY] != 1 && !distanceQueue.isEmpty()) {
            current = distanceQueue.remove();
            savedHeuristic = Math.abs(endGetX - current.getX()) +
                    Math.abs(endGetY - current.getY());

            for (int i = 0; i <= 3; i++) {
                if (reachable(current.getX() + xDirection[i], current.getY() + yDirection[i])) {
                    //visiting the four neighbors
                    aNode = new Agent(current.getX() + xDirection[i],
                            current.getY() + yDirection[i], null, current.getLength() + 1);

                    //Calculating distance from the start to neighbor
                    double currentDistance = current.getPriority() - savedHeuristic + 1;

                    //Calculating heuristic
                    double manhattanDistance = Math.abs(endGetX - aNode.getX()) +
                            Math.abs(endGetY - aNode.getY());
                    double heuristic = currentDistance + manhattanDistance;

                    //saving neighbor to Queue
                    neighbor = new PriorityAgent(current.getX() + xDirection[i], current.getY() + yDirection[i],
                            current, heuristic, current.getLength() + 1);

                    distanceQueue.add(neighbor);

                }
            }
            this.MAXIMUM_FRINGE_SIZE = Math.max(this.MAXIMUM_FRINGE_SIZE, distanceQueue.size());
        }

        if (visitedCell[endGetX][endGetY] == 1) {
            this.FOUND_PATH = true;
            this.lastVisited = current;
        }
        else {
            this.lastVisited = current;
        }
        long endTime = System.nanoTime();
        this.SEARCH_TIME = endTime - startTime;
    }

    private boolean reachable(int xCoordinate, int yCoordinate) {
        //checking whether the neighbor is visited or is a block
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

    public String report() {
        return "Found path :" + this.FOUND_PATH + "; Search time is " + this.SEARCH_TIME + "; Maximum fringe size is " + this.MAXIMUM_FRINGE_SIZE
                + "; The length of the path is " + this.returnPathLength();
    }
    @Override
    public int[][] showVisitedCells() {
        return this.visitedCell;
    }
    @Override
    public boolean pathFound() {
        return this.FOUND_PATH;
    }
    @Override
    public int countVisitedCells() {
        int count = 0;
        for (int row = 0; row < DIM; row++) {
            for (int col = 0; col < DIM; col++) {
                if (visitedCell[row][col] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    @Override
    public int returnPathLength() {
        return this.lastVisited.getLength();
    }
}
