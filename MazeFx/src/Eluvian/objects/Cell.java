package Eluvian.objects;
/*
 * rectangles, called Cell, of the maze grid
 * @author Qiyang Zhong
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Cell extends Rectangle{
    private Color color;
    private int col , row;
    private double arcHeight, arcWidth, height, width;
    public Cell (double arcHeight, double arcWidth, double height, double width, double probability, int row, int col) {
        super(arcHeight, arcWidth, height, width);
        this.color = Math.random() > probability ? Color.WHITE : Color.BLACK;
        this.row =row;
        this.col = col;
        this.setFill(color);
    }
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }
    public void reset(double probability) {
        this.color = Math.random() > probability ? Color.WHITE : Color.BLACK;
        this.setFill(color);
    }
    public Color getFill(Color color) {
        return this.color;
    }
    @Override
    public String toString() {
        return "";
    }
}
