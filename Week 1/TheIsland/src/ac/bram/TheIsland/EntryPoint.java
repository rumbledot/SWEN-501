package ac.bram.TheIsland;

import java.util.Random;

public class EntryPoint {

	public Island island;
	public Island island1;
	private int iWidth = 20, iHeight = 12;
	private char[][] islandFeatures;

	private Long timerStart, timerEnd;

	public EntryPoint() {
		this.createIsland();
		this.updateIsland();
		this.endIsland();
	}

	public void createIsland() {
		char[][] stage =
			{
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },

					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },

					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },

					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
					{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~' },

					{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~' },
					{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~' },
					{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~' },
					{'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~' }
			};

		island = new Island(iWidth, iHeight);
		island.createStage(stage);

		//island1 = new Island(iWidth, iHeight);
		//island1.createStage(stage);

		//this.addRabit();
		island.addEntity(this.createRabbit());
		island.addEntity(this.createKiwi());
		island.addEntity(this.createSparrow());
		island.addEntity(this.createCat());
		//island1.addEntity(this.createRabbit());

	}

	public void updateIsland() {
		timerStart = System.currentTimeMillis();
		int step = 0;
		while (step < 100) {
			timerEnd = System.currentTimeMillis() - timerStart;
			if (timerEnd >= 1000) {
				int Chance = (int)(Math.random() * 100);
				if (Chance > 20 && Chance < 55) {
					island.addEntity(this.createGrass());
				} else if (Chance > 1 && Chance < 2) {

				} else if (Chance > 55 && Chance < 80) {

				} else if (Chance > 80 && Chance < 83) {

				}
				island.updateIsland();
				//island1.updateIsland();
				timerStart = System.currentTimeMillis();
				step++;
			}
		}
	}

	public void endIsland() {

	}

	public static void main(String[] args) {
		new EntryPoint();
	}

	private void addRabit() {
		int x = (int) (Math.random() * this.iWidth - 1);
		if (x <= 0) { x = 1; }
		int y = (int) (Math.random() * this.iHeight - 1);
		if (y <= 0) { y = 1; }
		int energy = (int) (Math.random() * 40) + 10;
		char sym = 'r';
		Herbivore e = new Herbivore(x, y, energy, 1, 1, 5, 0, sym, island);
		//System.out.println(x + " ," + y + ":" + energy);
		island.addEntity(e);
	}

	private Entity createRabbit() {
		int x = (int) (Math.random() * this.iWidth - 1);
		if (x <= 0) { x = 1; }
		int y = (int) (Math.random() * this.iHeight - 1);
		if (y <= 0) { y = 1; }
		int energy = (int) (Math.random() * 40) + 10;
		char sym = 'r';
		int dX = (int)(Math.random() * 1) - 1; // either positive (right) / negative (left)
		if(dX == 0) { dX = 1; }
		int dY = (int)(Math.random() * 1) - 1; // either positive (down) / negative (up)
		if(dY == 0) { dY = 1; }
		Herbivore e = new Herbivore(x, y, energy, dX, dY, 5, 0, sym, island);
		return e;
	}

	private Entity createGrass() {
		int x = (int) (Math.random() * this.iWidth - 1);
		if (x <= 0) { x = 1; }
		int y = (int) (Math.random() * this.iHeight - 1);
		if (y <= 0) { y = 1; }
		int energy = 20;
		char sym = 'v';
		Plant e = new Plant(x, y, energy, 1, sym);
		return e;
	}

	private Entity createKiwi() {
		int x = (int) (Math.random() * this.iWidth - 1);
		if (x <= 0) { x = 1; }
		int y = (int) (Math.random() * this.iHeight - 1);
		if (y <= 0) { y = 1; }
		int energy = (int) (Math.random() * 20) + 10;
		char sym = 'k';
		int dX = (int)(Math.random() * 1) - 1; // either positive (right) / negative (left)
		if(dX == 0) { dX = 1; }
		int dY = (int)(Math.random() * 1) - 1; // either positive (down) / negative (up)
		if(dY == 0) { dY = 1; }
		Kiwi e = new Kiwi(x, y, energy, dX, dY, 3, 0, sym, island);
		return e;
	}

	private Entity createSparrow() {
		int x = (int) (Math.random() * this.iWidth - 1);
		if (x <= 0) { x = 1; }
		int y = (int) (Math.random() * this.iHeight - 1);
		if (y <= 0) { y = 1; }
		int energy = (int) (Math.random() * 20) + 10;
		char sym = 's';
		Herbivore e = new Herbivore(x, y, energy, 1, 1, 10, 1, sym, island);
		return e;
	}
	
	private Entity createCat() {
		int x = (int) (Math.random() * this.iWidth - 1);
		if (x <= 0) { x = 1; }
		int y = (int) (Math.random() * this.iHeight - 1);
		if (y <= 0) { y = 1; }
		int energy = (int) (Math.random() * 80) + 10;
		char sym = 'C';
		Carnivore e = new Carnivore(x, y, energy, 1, 1, 6, 1, sym, island);
		return e;
	}
}
