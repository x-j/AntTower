import java.util.Scanner;

public class Main{

    public static void main (String[] args){

	//*
	  int n = 100;
	  int m = 100;
	  int xStart = 1;
	  int yStart = 1;
	  int xStop = 50;
	  int yStop = 96;
	  Board board = new Board(n,m,xStart,yStart,xStop,yStop);
	  //*/

	  //Board board = askInfo();
	board.cases[board.xStart][board.yStart].addAnt(new Ant(true,true,board)); //the first ant
	board.stepBoard();
    }

    public static Board askInfo(){

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
	while((xStart < 1) || (xStart > n)){
	    System.out.println("Wrong value of xStart, retry (0 < xStart < "+(n+1)+")");
	    System.out.print("value of xStart : ");
	    xStart = sc.nextInt();
	}
	System.out.print("value of yStart : ");
	yStart = sc.nextInt();
	while((yStart < 0) || (yStart > m-1)){
	    System.out.println("Wrong value of y, retry (0 <= yStart < "+m+")");
	    System.out.print("value of yStart : ");
	    yStart = sc.nextInt();
	}

	System.out.println("Enter the stop position (xStop,yStop)");
	System.out.print("value of xStop : ");
	xStop = sc.nextInt();
	while((xStop < 1) || (xStop > n)){
	    System.out.println("Wrong value of xStop, retry (0 < xStop < "+(n+1)+")");
	    System.out.print("value of xStop : ");
	    xStop = sc.nextInt();
	}
	System.out.print("value of yStop : ");
	yStop = sc.nextInt();
	while((yStop < 0) || (yStop > m-1)){
	    System.out.println("Wrong value of y, retry (0 <= yStop < "+m+")");
	    System.out.print("value of yStop : ");
	    yStop = sc.nextInt();
	}

	return (new Board(n,m,xStart,yStart,xStop,yStop));
    }
}
