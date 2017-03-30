public class Tower {

    Case current;
    int damages;
    int area; //the shooting area around the tower
    int level; 

    public Tower(Case _case) {
	current = _case;
	damages = 50;
	area = 1;
	level = 1;
    }

    public void stepTower(){
	if(!current.ants.isEmpty())
	    current.ants.get(0).killed();
    }
}
