public class Ant {

    Board board;
    boolean first; //if it's the first
    int x, y; //position
    boolean crazy; //true if it moves randomly
    int steps; //number of steps traveled
    int life;

    public Ant(boolean first){
	first = true;
	x = board.xStart;
	y = board.yStart;
	crazy = true;
	steps = 0;
	life = 100;
    }

    public Ant(int x, int y, boolean crazy){
	first = false;
	this.x = x;
	this.y = y;
	crazy = true;
	steps = 0;
	life = 100;
    }

    public boolean getCrazy(){
	return crazy;
    }

    public void setCrazy(boolean b){
	crazy = b;
    }

    public boolean getFirst(){
	return first;
    }

    public void setFirst(boolean b){
	first = b;
    }

}
