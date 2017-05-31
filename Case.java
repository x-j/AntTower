import java.lang.Math;
import java.util.*;

public class Case {

    Board board;
    int x, y; //position
    double pheromones; //amount of pheromones on this case
    Case [] around; //0-topL 1-top 2-topR 3-right 4-botR 5-bot 6-botL 7-left
    boolean t; //true if there is a tower
    Tower tower;
    ArrayList <Ant> ants; //list of ants on this case

    //Constructor
    public Case(int _x, int _y, Board _board){
	board = _board;
	x = _x;
	y = _y;

	pheromones = 0.0; // 0 pheromone by default
	around = new Case[8]; //set of the array of the "neighbour cases"
	t = false; //no tower by default
	ants = new ArrayList<Ant>(); //empty list of ants by default
    }
    
    //Synchronize the array around with the board
    public void syncAround(){
	//around[0]
	if((x == 0) || (y == 0))
	    around[0] = null;
	else
	    around[0] = board.cases[x-1][y-1];
	//around[1]
	if((x == 0))
	    around[1] = null;
	else
	    around[1] = board.cases[x-1][y];
	//around[2]
	if((x == 0) || (y == board.m-1))
	    around[2] = null;
	else
	    around[2] = board.cases[x-1][y+1];
	//around[3]
	if((y == board.m-1))
	    around[3] = null;
	else
	    around[3] = board.cases[x][y+1];
	//around[4]
	if((x == board.n-1) || (y == board.m-1))
	    around[4] = null;
	else
	    around[4] = board.cases[x+1][y+1];
	//around[5]
	if((x == board.n-1))
	    around[5] = null;
	else
	    around[5] = board.cases[x+1][y];
	//around[6]
	if((x == board.n-1) || (y == 0))
	    around[6] = null;
	else
	    around[6] = board.cases[x+1][y-1];
	//around[7]
	if((y == 0))
	    around[7] = null;
	else
	    around[7] = board.cases[x][y-1];
    }

    public void evaporation(double p){
	if(this.pheromones != 0) 
	    this.pheromones = this.pheromones * p;
    }

    //add p pheromones
    public void setPheromones(double p){pheromones += p;}

    //add an ant in this case
    public void addAnt(Ant _ant){ants.add(_ant);this.board.nb_ants++;}

    //step for a case 
    public void stepCase(){
	//if there is ants on this case, start stepAnts() for every ants in this case

	if(ants != null)
	    for(int i = 0; i < ants.size(); i++)
		ants.get(i).stepAnts();

	//if there is a tower in this case, start stepTower() for this one
	if(t)
	    tower.stepTower();
    }

    //function for the ant in this case to choose the next case
    public Case next(boolean crazy,Case previous){
	if(crazy)
	    return next_random(previous);
	else
	    return next_best(previous);
    }

    //choose the next case randomly (for crazy ants)
    public Case next_random(Case previous){
	int i;
	for(int j = 0; j < 8; j++)
	    if(this.around[j] == board.cases[board.xStop][board.yStop])
		return this.around[j];
	do{
	    i = (int)(Math.random()*8);
	}while(this.around[i] == null || this.around[i] == previous);
	//until it's not null (outside) and it's not the previous one
	return this.around[i];
    }

    //choose the next case regarding to the amount of pheromones
    //in the "neighbour cases" to choose the best one
    public Case next_best(Case previous){

	double alpha = 1.0;
	double total = total_next(alpha);
	if(total == 0)
	    return next_random(previous);
	double [] p = new double[8];
	int i;
	//calcul of probabilities for "neighbour cases"
	for(i = 0; i< 8; i++){
	    if(this.around[i] == null){
		p[i] = 0;
	    }else{
		if(this.around[i] == this.board.cases[this.board.xStop][this.board.yStop])
		    return this.around[i];
		p[i] = Math.pow(this.around[i].pheromones,alpha)/total;
	    }
	}

	//ici

	double random, cumulate_p;
	do{	    
	    random = Math.random(); //random number (double) between 0 and 1
	    i = 0;
	    cumulate_p = p[0];
	    //select one case regarding to the amount of pheromones
	    while((random >= cumulate_p) && i<7 && cumulate_p<1)
		cumulate_p += p[++i];
	    if(cumulate_p == 1 && this.around[i] == previous)
		return next_random(previous);
	}while(this.around[i] == null || this.around[i] == previous);

	return this.around[i];
    }
    
    //total number of pheromones on the eight next possible case
    public double total_next(){ 
	double total = 0;
	for(int i = 0; i<8; i++)
	    if(this.around[i] != null)
		total += this.around[i].pheromones;
	return total;
    }

    //total number of pheromones on the eight next possible case
    public double total_next(double alpha){ 
	double total = 0;
	for(int i = 0; i<8; i++)
	    if(this.around[i] != null)
		total += Math.pow(this.around[i].pheromones,alpha);
	return total;
    }

}
