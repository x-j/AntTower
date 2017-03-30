import java.util.Scanner;

public class Main{

    public static void main (String[] args){
	Board board = askInfo();
	Ant first = new Ant(true,true,board); //the first ant

    }

    public static Board askInfo(){
	System.out.println("Enter the dimension of the board (m x n)");
	Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
	int m = sc.nextInt();
	System.out.println("Enter the start position (x,y)");
	int xStart = sc.nextInt();
	int yStart = sc.nextInt();
	System.out.println("Enter the stop position (x,y)");
	int xStop = sc.nextInt();
	int yStop = sc.nextInt();
	return (new Board(n,m,xStart,yStart,xStop,yStop));
    }
}
