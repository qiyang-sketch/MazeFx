package Eluvian.view;
/*
 * Manging actions for generating mazes and running algos
 * @author Qiyang Zhong
 */
import Eluvian.algorithm.AStarSearch;
import Eluvian.algorithm.DepthFirstSearch;
import Eluvian.objects.Agent;
import Eluvian.algorithm.BreadthFirstSearch;
import Eluvian.objects.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

public class PaneController {

    private Cell[][] grid;
    //use grid array to represent maze
    private int DIM;
    private double PROBABILITY;

    @FXML AnchorPane MazeGrid;
    @FXML Button GetNewMap, GetBFS, GetDFS, GetMan, GetEuc, GetSolvMap;
    @FXML TextField ReqDim, ReqProb;
    @FXML TextArea printedReport;
    public void start(Stage mainStage) {
        DIM = 30;
        PROBABILITY = 0.2;
        newMap(DIM, PROBABILITY);
    }

    private void newMap(int DIM, double PROBABILITY){
        //creating a new maze
        int horizontal = 15, vertical = 15;
        MazeGrid.getChildren().clear();
        //clearing previous maze
        grid = new Cell[DIM][DIM];
        for (int row = 0; row < DIM; row++) {
            for (int col = 0; col < DIM; col++) {
                Cell aCell = new Cell(horizontal*col, vertical*row,
                        horizontal, vertical, PROBABILITY, row, col);
                aCell.setStroke(Color.LIGHTGREY);

                MazeGrid.getChildren().add(aCell);
                //Add Rectangle to grid
                grid[row][col] = aCell;
            }
        }
        grid[0][0].setFill(Color.LIGHTGREEN);
        grid[DIM-1][DIM-1].setFill(Color.LIGHTGREEN);
    }

    public void solvableMap(ActionEvent event){
        //creating a new solvable maze
        Button checkButton = (Button)event.getSource();
        if(ReqDim.getLength() == 0){
            DIM = 30;
        }else{
            DIM = Integer.parseInt(ReqDim.getText());
        }
        if(ReqProb.getLength() == 0){
            PROBABILITY = 0.2;
        }else{
            PROBABILITY = Double.parseDouble(ReqProb.getText());
        }

        if(checkButton == GetSolvMap){
            AStarSearch Euc = new AStarSearch(grid, DIM, true);
            while(!Euc.pathFound()){
                newMap(DIM, PROBABILITY);
                Euc = new AStarSearch(grid, DIM, true);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Maze is now solvable");
            alert.showAndWait();
        }
    }

    public void resetMap(ActionEvent event){
        Button checkButton = (Button)event.getSource();
        if(ReqDim.getLength() == 0){
            DIM = 30;
        }else{
            DIM = Integer.parseInt(ReqDim.getText());
        }
        if(ReqProb.getLength() == 0){
            PROBABILITY = 0.2;
        }else{
            PROBABILITY = Double.parseDouble(ReqProb.getText());
        }
        if(checkButton == GetNewMap){
            newMap(DIM, PROBABILITY);
        }
    }

    public void startBFS(ActionEvent event){
        Button checkButton = (Button)event.getSource();
        if(checkButton == GetBFS){
            BreadthFirstSearch bfs = new BreadthFirstSearch(grid, DIM);
            if(!bfs.pathFound()){
                System.out.println("no");
                return;
            }
            trace(bfs.returnShortestPath());
        }
    }

    public void startDFS(ActionEvent event){
        Button checkButton = (Button)event.getSource();
        if(checkButton == GetDFS){
            DepthFirstSearch dfs = new DepthFirstSearch(grid, DIM);
            if(!dfs.pathFound()){
                System.out.println("no");
                return;
            }
            trace(dfs.returnShortestPath());
        }
    }

    public void startEuc(ActionEvent event){
        Button checkButton = (Button)event.getSource();
        if(checkButton == GetEuc){
            AStarSearch Euc = new AStarSearch(grid, DIM, true);
            if(!Euc.pathFound()){
                System.out.println("no");
                return;
            }
            trace(Euc.returnShortestPath());
        }
    }

    public void startMan(ActionEvent event){
        Button checkButton = (Button)event.getSource();
        if(checkButton == GetMan){
            AStarSearch Man = new AStarSearch(grid, DIM, false);
            if(!Man.pathFound()){

                return;
            }
            trace(Man.returnShortestPath());
        }
    }

    private void trace(Agent lastVisited) {
        if (lastVisited != null) {
            while (lastVisited.getParent() != null) {
                grid[lastVisited.getX()][lastVisited.getY()].setFill(Color.ORANGERED);
                lastVisited = lastVisited.getParent();
            }
        }
    }

}
