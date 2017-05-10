import java.util.concurrent.TimeUnit;

public class Board {

	int n, m; //dimensions
	Case[][] cases;
	int xStart, yStart;
	int xStop, yStop;
	int nb_ants;

	View view;
	boolean useGui = true;
    /*
      public Board(int n, int m){
      cases = new Case[n+2][m+1];
      xStart = 1;
      yStart = 0;
      xStop = m;
      yStop = n;
      nb_ants = 0;
      }*/

	public Board(int _n, int _m, int _xStart, int _yStart, int _xStop, int _yStop, View _view) {
		int i, j;
		n = _n;
		m = _m;

		view = _view;

		cases = new Case[n + 2][m + 2];
		for (i = 0; i < n; i++)
			for (j = 0; j < m; j++)
				cases[i][j] = new Case(i, j, this);
		for (i = 0; i < n; i++)
			for (j = 0; j < m; j++)
				cases[i][j].syncAround();
		xStart = _xStart;
		yStart = _yStart;
		xStop = _xStop;
		yStop = _yStop;
		nb_ants = 0;
	}

	public void setNbAnts() {
		nb_ants--;
	}

	public void show(boolean useGui) {
		System.out.print(" ROUND N-" + nb_ants);
		for (int k = 0; k < m * 12; k++)
			System.out.print('-');
		System.out.print('\n');
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.format("%4d|%-5.3f%-3s",
						this.cases[i][j].ants.size(),
						(float) (this.cases[i][j].pheromones * 100) / nbPheromones(), "% ");
			System.out.print("\n");
		}
		if (useGui) {
			view.setStatus("Round N-" + nb_ants);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					view.getCells()[j][i].setText("" + this.cases[i][j].ants.size());
					view.getCells()[j][i].setColorIntensity(((float) (this.cases[i][j].pheromones)) / nbPheromones());
				}
			}
			// feel free to remove the delay below
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stepBoard() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				cases[i][j].stepCase();

		if (nb_ants % 40 == 0) //one crazy for 40 normal
			cases[xStart][yStart].addAnt(new Ant(false, true, this));
		else
			cases[xStart][yStart].addAnt(new Ant(false, false, this));
	}

	public int nbPheromones() {
		int result = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				result += cases[i][j].pheromones;
		if (result == 0)
			return 1;
		return result;
	}

	public void run() {

		while (true) {
			this.cases[this.xStart][this.yStart].addAnt(new Ant(true, true, this));
			this.nb_ants++;
        /*try{
        TimeUnit.SECONDS.sleep(1);
	    }catch(InterruptedException e){
		System.err.println("ERROR SLEEP");
		}*/
			this.show(useGui);
			this.stepBoard();
		}
	}

}
