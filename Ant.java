import java.util.*;

public class Ant {

    Board board;
    boolean first; //true if it's the first
    Case current;
    boolean crazy; //true if it moves randomly
    int steps; //number of steps traveled
    boolean alive;
    int pheromones;
    ArrayList <Case> traveled_case;

    public Ant(boolean _first,boolean _crazy, Board _board){
	pheromones = 1000;
	first = _first;
	board = _board;
	current = board.cases[board.xStart][board.yStart]; //starting case
	crazy = _crazy;
	steps = 0;
	alive = true;
	traveled_case = new ArrayList <Case>();
    }


    public boolean getCrazy(){return crazy;}
    public void setCrazy(boolean b){crazy = b;}

    public boolean getFirst(){return first;}
    public void setFirst(boolean b){first = b;}

    public void stepAnts(){
	current.ants.remove(this);
	current = this.current.next(this.crazy);
	current.ants.add(this);
	traveled_case.add(current);
	steps++;
	return;
    }

    public void killed(){
	alive = false;
	current.ants.remove(this);
	board.setNbAnts();
    }

    public void finished(){
	for(Case c : traveled_case)
	    c.setPheromones(pheromones/steps);
	this.killed();
    }
}
