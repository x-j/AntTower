public class Board {
    
    int n, m; //dimensions
    Case [][] cases;
    int xStart, yStart;
    int xStop, yStop;

    public Board(int n, int m){
	cases = new Case[n+2][m+1];
	xStart = 1;
	yStart = 0;
	xStop = n+1;
	yStop = m;
    }

    public Board(int n, int m, int _xStart, int _yStart, int _xStop, int _yStop){
	cases = new Case[n+2][m+1];
	xStart = _xStart;
	yStart = _yStart;
	xStop = _xStop;
	yStop = _yStop;
    }

}
