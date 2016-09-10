package inx.snakegame.objects;

import inx.snakegame.SnakeGame;

public class Apple {
	
	SnakeGame main; //конструктор который нас свяжет с майном
	
	//координаты яблока
	public int posX;
	public int posY;
	
	//задаём координаты позиции
	public Apple(int startX, int startY){
		posX = startX;
		posY = startY;
	}
	
	@SuppressWarnings("static-access") //иначе говоря "в методе"
	public void setRandomPosition(){ //рандомизатор позиций
		posX = (int)(Math.random()*main.WIDTH);
		posY = (int)(Math.random()*main.HEIGHT);
	}
}
