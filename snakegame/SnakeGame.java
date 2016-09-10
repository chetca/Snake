package inx.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame; //Знаю что проще было сразу весь свинг подключать, но мы же 
import javax.swing.JPanel; //русские быдлокодеры, и Ctrl+Shift+O наше всё
import javax.swing.Timer;

import inx.snakegame.objects.Apple; //подключаем объект "Яблоко"
import inx.snakegame.objects.Snake; //подключаем объект "Змея"

public class SnakeGame extends JPanel implements ActionListener {	//наследуем JPanel
	
	//тут пилим сетку игрового поля - переменные	
	public static final int SCALE = 32; //размер клетки
	public static final int WIDTH = 20; //ширина
	public static final int HEIGHT = 20; //высота
	public static final int SPEED = 5; //скорость
	
	Apple a = new Apple((int)(Math.random()*WIDTH),(int)(Math.random()*HEIGHT));
	Snake s = new Snake(10, 10, 9, 10); //создаём экземпляр змейки с начальными координатами
	Timer t = new Timer(1000/SPEED, this); //скорость движения змейки
	
	public SnakeGame(){ //конструктор движения
		t.start(); //движение согласно таймеру 		
		addKeyListener(new Keyboard()); //получаем нажатие клавиши
		setFocusable(true); //вводим в класс
	}
	
	public void paint(Graphics g){	//отрисовка
		g.setColor(color(5, 50, 10));	//цвет фона
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);	//отрисовка фона
		g.setColor(color(255, 255, 0));	//цвет сетки
		
		for(int xx = 0; xx <= WIDTH*SCALE; xx+=SCALE){
			g.drawLine(xx, 0, xx, HEIGHT*SCALE);	//вертикальная сетка
		}
		for(int yy = 0; yy <= HEIGHT*SCALE; yy+=SCALE){
			g.drawLine(0, yy, WIDTH*SCALE, yy);		//горизонтальная сетка
		}
		
		for(int d = 0; d < s.lenght; d++){ //рисуем змейку
			g.setColor(color(200, 150, 0)); //цвет
			g.fillRect(s.snakeX[d]*SCALE+1, s.snakeY[d]*SCALE+1, SCALE-1, SCALE-1);	//сама змейка		
		}
		
		g.setColor(color(255, 0, 0)); //цвет яблока
		g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1); //рисуем яблоко
	}
	
	public Color color(int red, int green, int blue){	//какая то корявая реализация RGB
		return new Color(red, green, blue);
	}
		
	public static void main(String[] args) {  //Главная майновская функция
		JFrame f = new JFrame("SnakeGame");	//Создаём окошко
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//закрытие по крестику
		f.setResizable(false);	//нерасширяемое окошко
		f.setSize(WIDTH*SCALE+7, HEIGHT*SCALE+29);	//размер
		f.setLocationRelativeTo(null);	//делаем по центру	
		f.add(new SnakeGame());	//запускаем конструктор
		f.setVisible(true);	//делаем видимым		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		s.move(); //ВСТАНЬ И ИДИ!
		if((s.snakeX[0] == a.posX) & (s.snakeY[0] == a.posY)){ //если всретили яблоко, то
			a.setRandomPosition(); //рисуем новое
			s.lenght++; //увеличиваем длину
		}
		for(int l = 1; l < s.lenght; l++){	//Это что бы яблоко не рандомилось на теле змейки
			if((s.snakeX[l] == a.posX) & (s.snakeY[l] == a.posY)){ 
				a.setRandomPosition();
			}
		}
		repaint(); //ну он и в Африке репайнт
	}
	
	private class Keyboard extends KeyAdapter{ //тут обработка клавиш
		public void keyPressed(KeyEvent kEvt) {
			int key = kEvt.getKeyCode(); //получаем код клавиши
			
			//в зависимости от нажатой клавиши задаём движение
			if((key == KeyEvent.VK_RIGHT) & s.direction != 2) s.direction = 0;
			if((key == KeyEvent.VK_DOWN) & s.direction != 3) s.direction = 1;
			if((key == KeyEvent.VK_LEFT) & s.direction != 0) s.direction = 2;
			if((key == KeyEvent.VK_UP) & s.direction != 1) s.direction = 3;
		}
	}
}
