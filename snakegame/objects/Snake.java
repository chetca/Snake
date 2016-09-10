package inx.snakegame.objects;

import inx.snakegame.SnakeGame;

public class Snake {
	
	SnakeGame main; //передаём значение для размера

	public int direction = 0; // направление сдвига
	// 0 - вправо, 1 - вниз, 2 - влево, 3 - вверх
	public int lenght = 2; // длина змейки 2 клетки

	// массивы координат. максимальный размер - всё поле
	@SuppressWarnings("static-access")//от ошибок, не обращать внимания 
	public int snakeX[] = new int[main.WIDTH*main.HEIGHT];
	public int snakeY[] = new int[main.WIDTH*main.HEIGHT];

	public Snake(int x0, int y0, int x1, int y1) { // конструктор змейки
		//принимаем на входе координаты старта змейки
		snakeX[0] = x0;
		snakeY[0] = y0;
		snakeX[1] = x1;
		snakeY[1] = y1;
	}

	public void move() { // движение
		
		for (int d = lenght; d > 0; d--) {
			snakeX[d] = snakeX[d - 1];
			snakeY[d] = snakeY[d - 1];
		}

		// определяемся с направлением
		if (direction == 0) snakeX[0]++;
		if (direction == 1) snakeY[0]++;
		if (direction == 2) snakeX[0]--;
		if (direction == 3) snakeY[0]--;
		
		//съедаем свой хвост(при пересечении себя сокращаем длину )
		for (int k = lenght-1; k > 0; k--) {
			if((snakeX[0] == snakeX[k]) & (snakeY[0] == snakeY[k])) lenght = k-2;
		}		
		if(lenght  < 2) lenght = 2;	//минимальная длина	
		
		//ограничение поля
		if(snakeX[0] > main.WIDTH) snakeX[0] = 0;
		if(snakeX[0] < 0) snakeX[0] = main.WIDTH-1;		
		if(snakeY[0] > main.HEIGHT-1) snakeY[0] = 0;
		if(snakeY[0] < 0) snakeY[0] = main.HEIGHT-1;		
	}
}
