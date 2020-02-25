package Eluvian.objects;


public class Agent {
    private int row;
    private int col;
    private Agent parent;
    private int length;
    public Agent(int row, int col, Agent parent, int length) {
        this.col = col;
        this.row = row;
        this.parent = parent;
        this.length = length;
    }
    public int getLength() {
        return this.length;
    }
    public Agent getParent() {
        return this.parent;
    }
    public void setParent(Agent parent) {
        this.parent = parent;
    }
    public int getX(){
        return this.row;
    }
    public int getY(){
        return this.col;
    }
    public String toString() {
        return "(" + this.row + " - " + this.col + " )";
    }
}