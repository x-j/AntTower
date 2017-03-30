public class Ant {

    Board board;
    boolean first; //true if it's the first
    Case current;
    boolean crazy; //true if it moves randomly
    int steps; //number of steps traveled
    boolean alive;

    public Ant(boolean _first,boolean _crazy, Board _board){
	first = _first;
	board = _board;
	current = board.cases[board.xStart][board.yStart]; //starting case
	crazy = _crazy;
	steps = 0;
	alive = true;
    }


    public boolean getCrazy(){return crazy;}
    public void setCrazy(boolean b){crazy = b;}

    public boolean getFirst(){return first;}
    public void setFirst(boolean b){first = b;}

    public void stepAnts(){
	current = this.current.next(this.crazy);
	return;
    }

    public void killed(){
	alive = false;
	current.ants.remove(this);
	board.setNbAnts();
    }
}
