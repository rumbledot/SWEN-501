package ac.bram.TheIsland;

import java.util.Comparator;

public class ComparatorOfPlant implements Comparator<Plant> {

	int bugX = 0, bugY = 0;
	
	@Override
	public int compare(Plant o1, Plant o2) {
		int o1Dist = Math.abs(o1.getX() - bugX) + (Math.abs(o1.getY()) - bugY);
		int o2Dist = Math.abs(o2.getX() - bugX) + (Math.abs(o2.getY()) - bugY);

	return (o1Dist - o2Dist);
	}
	
	public void setBugX (int bugX) {
		this.bugX = bugX;
	}
	
	public void setBugY (int bugX) {
		this.bugY = bugY;
	}

}

