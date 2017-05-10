import java.util.Scanner;

public class Main {

	public static void main(String[] args) {


//        default values:
//        int n = 10;
//        int m = 10;
		int xStart = 0;
		int yStart = 0;
		int xStop = 9;
		int yStop = 9;

		View view = new View();

		// get N and M from user:

		int n = view.obtainN();
		int m = view.obtainM();

		view.buildGrid();
		view.display();

		// ask the user to specify the start and end square.
		synchronized (view) {
			try {
				view.setStatus("Please select the starting square.");
				view.wait();
				xStart = view.getStartX();
				yStart = view.getStartY();

				view.setStatus("Please select the ending square.");
				view.wait();
				xStop = view.getEndX();
				yStop = view.getEndY();

				view.setStatus("Add towers (not implemented yet) and then press GO to start.");
				view.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Board board = new Board(n, m, xStart, yStart, xStop, yStop, view);


		//Board board = askInfo();
		//	board.cases[board.xStart][board.yStart].addAnt(new Ant(true,true,board)); //the first ant
		//	board.stepBoard();
		board.run();
	}

	public static Board askInfo() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the dimension of the board (n x m)");
		System.out.print("value of n : ");
		int n = sc.nextInt();
		System.out.print("value of m : ");
		int m = sc.nextInt();

		int xStart, yStart, xStop, yStop;

		System.out.println("Enter the start position (xStart,yStart)");
		System.out.print("value of xStart : ");
		xStart = sc.nextInt();
		while ((xStart < 0) || (xStart > n - 1)) {
			System.out.println("Wrong value of xStart, retry (0 < xStart < " + (n + 1) + ")");
			System.out.print("value of xStart : ");
			xStart = sc.nextInt();
		}
		System.out.print("value of yStart : ");
		yStart = sc.nextInt();
		while ((yStart < 0) || (yStart > m - 1)) {
			System.out.println("Wrong value of y, retry (0 <= yStart < " + m + ")");
			System.out.print("value of yStart : ");
			yStart = sc.nextInt();
		}

		System.out.println("Enter the stop position (xStop,yStop)");
		System.out.print("value of xStop : ");
		xStop = sc.nextInt();
		while ((xStop < 0) || (xStop > n - 1)) {
			System.out.println("Wrong value of xStop, retry (0 < xStop < " + (n + 1) + ")");
			System.out.print("value of xStop : ");
			xStop = sc.nextInt();
		}
		System.out.print("value of yStop : ");
		yStop = sc.nextInt();
		while ((yStop < 0) || (yStop > m - 1)) {
			System.out.println("Wrong value of y, retry (0 <= yStop < " + m + ")");
			System.out.print("value of yStop : ");
			yStop = sc.nextInt();
		}

		return (new Board(n, m, xStart, yStart, xStop, yStop, null));
	}
}
