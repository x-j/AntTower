public class Board {
    
    int n, m; //dimensions
    Case [][] cases;
    int xStart, yStart;
    int xStop, yStop;
    int nb_ants;

    /*
    public Board(int n, int m){
	cases = new Case[n+2][m+1];
	xStart = 1;
	yStart = 0;
	xStop = m;
	yStop = n;
	nb_ants = 0;
    }*/

    public Board(int n, int m, int _xStart, int _yStart, int _xStop, int _yStop){
	cases = new Case[n+2][m+1];
	xStart = _xStart;
	yStart = _yStart;
	xStop = _xStop;
	yStop = _yStop;
	nb_ants = 0;
    }

    public void setNbAnts(){nb_ants--;}

    public void stepBoard(){
	for(int i = 1; i < n+1; i++)
	    for(int j = 0; j < m; j++)
		cases[i][j].stepCase();

	if(nb_ants%10 == 0) //one crazy for 10 normal
	    cases[xStart][yStart].addAnt(new Ant(false,true,this));
	else 
	    cases[xStart][yStart].addAnt(new Ant(false,false,this));    
    }

}
