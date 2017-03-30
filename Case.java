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
	if((x == 0) || (x == board.n+1) || (y == 0) || (y == board.m)){
	    t = false;
	    ants = null;
	    in = false;
	    pheromones = 0;
	    return;
	}
	in = true; //not a border
	pheromones = 0;
	around = new Case[8];
	//WARNING : Border Around !
	around[0] = board.cases[x-1][y-1];
	around[1] = board.cases[x-1][y];
	around[2] = board.cases[x-1][y+1];
	around[3] = board.cases[x][y+1];
	around[4] = board.cases[x+1][y+1];
	around[5] = board.cases[x+1][y];
	around[6] = board.cases[x+1][y-1];
	around[7] = board.cases[x][y-1];
	//END WARNING
	t = false;
	ants = new ArrayList<Ant>();
    }

    public void setPheromones(int p){pheromones += p;}

    public void addAnt(Ant _ant){ants.add(_ant);}

    public void stepCase(){
	if(ants != null)
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
	int i;
	do{
	    i = (int)(Math.random()*8);
	}while((this.around[i] == null) || (!this.around[i].in)); //until it's not null or a border
	return this.around[i];
    }

    public Case next_best(){

	double total = (double)total_next();
	double [] p = new double[8];
	int i;

	for(i = 0; i< 8; i++)
	    p[i] = (double)(this.around[i].pheromones)/total;
	double random, cumulate_p;
	do{
	    random = Math.random(); //random number (double) between 0 and 1
	    i = 0;
	    cumulate_p = p[0];

	    while(!(random < cumulate_p) && i<5)
		cumulate_p += p[++i];
	}while(!this.around[i].in);

	return this.around[i];
    }
    
    //total number of pheromones on the five next possible case
    public int total_next(){ 
	int total = 0;
	for(int i = 0; i<8; i++)
	    total += this.around[i].pheromones;
	return total;
    }

}
