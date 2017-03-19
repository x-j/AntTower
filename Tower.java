public class Tower {

    int x, y; //position
    int damages;
    int area; //the shooting area around the tower
    int level; 

    public Tower(int x, int y) {
	this.x = x;
	this.y = y;
	this.damages = 50;
	this.area = 1;
	this.level = 1;
    }
}
