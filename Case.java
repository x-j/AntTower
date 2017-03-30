import java.lang.Math;
import java.util.*;

public class Case {

    Board board;
    boolean in; //false if it's a border
    int x, y; //position
    int pheromones;
    Case [] around; //0-top 1-top_r 2-right 3-bot_r 4-bot
    boolean t; //if there is a tower
    Tower tower;
    ArrayList <Ant> ants;

    public Case(int _x, int _y, Board _board){
	board = _board;
	x = _x;
	y = _y;
	//if the case is a border
	if((x == 0) || (x == board.n+1) || (y == board.m)){
	    in = false;
	    return;
	}
	in = true; //not a border
	pheromones = 0;
	around = new Case[5];
	around[0] = board.cases[x-1][y];
	around[1] = board.cases[x-1][y+1];
	around[2] = board.cases[x][y+1];
	around[3] = board.cases[x+1][y+1];
	around[4] = board.cases[x+1][y];
	t = false;
	ants = new ArrayList<Ant>();
    }

    public void addAnt(Ant _ant){
	ants.add(_ant);
    }

    public void stepCase(){
	for(Ant a : ants)
	    a.stepAnts();
	if(t)
	    tower.stepTower();
    }

    public Case next(boolean crazy){
	if(crazy)
	    return next_random();
	else
	    return next_best();
    }

    public Case next_random(){
	int i = (int)(Math.random()*5);
	return this.around[i];
    }

    public Case next_best(){

	double total = (double)total_next();
	double [] p = new double[5];
	int i;

	for(i = 0; i< 5; i++)
	    p[i] = (double)(this.around[i].pheromones)/total;

	double random = Math.random(); //random number (double) between 0 and 1
	i = 0;
	double cumulate_p = p[0];

	while(!(random < cumulate_p) && i<5)
	    cumulate_p += p[++i];
	
	return this.around[i];
    }
    
    //total number of pheromones on the five next possible case
    public int total_next(){ 
	int total = 0;
	for(int i = 0; i<5; i++)
	    total += this.around[i].pheromones;
	return total;
    }

}
