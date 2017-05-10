import java.util.*;

public class Ant {

    Board board;
    boolean first; //true if it's the first
    Case current; //the current case
    Case previous; // the previous case
    boolean crazy; //true if it moves randomly
    int steps; //number of steps traveled
    boolean alive;
    int pheromones; //amount of pheromones that the ant transport
    boolean moved; // if it moved this round
    ArrayList <Case> traveled_case; //list of traveled cases

    public Ant(boolean _first,boolean _crazy, Board _board){
	pheromones = 100;
	first = _first;
	board = _board;
	current = board.cases[board.xStart][board.yStart]; //starting case
	crazy = _crazy;
	steps = 0;
	alive = true;
	traveled_case = new ArrayList <Case>();
	previous = null;
	moved = false;
    }


    public boolean getCrazy(){return crazy;}
    public void setCrazy(boolean b){crazy = b;}

    public boolean getFirst(){return first;}
    public void setFirst(boolean b){first = b;}

    //function executed for a step of an ant
    public void stepAnts(){
	if(!moved){
	    int x = current.x;
	    int y = current.y;
	    current.ants.remove(this);
	    current.ants.trimToSize();
	    current = this.current.next(this.crazy,this.previous);
	    previous = this.board.cases[x][y];
	    current.ants.add(this);
	    if(current != board.cases[board.xStart][board.yStart]){
		traveled_case.add(current);
		steps++;
	    }
	    moved = true;
	    if((current.x == board.xStop) && (current.y == board.yStop))
		this.finished();
	}
     }

     //kill the ant
     public void killed(){
	 alive = false;
	 current.ants.remove(this);
	 current.ants.trimToSize();
     }

     //when the ant reach the stop, pheromones are set on its path
     public void finished(){
	 for(Case c : traveled_case)
	     c.setPheromones(pheromones/steps);
	 this.killed();
     }
 }
