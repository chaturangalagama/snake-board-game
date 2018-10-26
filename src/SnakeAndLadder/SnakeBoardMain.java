package SnakeAndLadder;

import java.awt.*;

public class SnakeBoardMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeBoard game = new SnakeBoard();
                    game.start();
					game.Jframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
