package SnakeAndLadder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;

public class SnakeBoard {

	private int p1;
	private int p2;
	private int dice;
	private String currentPlayer;
	public JFrame Jframe;
    private JButton[] ctrlBtn = new JButton[10];
    private Map<String, JButton> bmap = new HashMap<String, JButton>();
    private JButton[] btn = new JButton[100];

    public SnakeBoard() {
        p1=0;
        p2=0;
        dice=0;
        currentPlayer="Player1";
        Jframe = new JFrame();
        Jframe.getContentPane().setBackground(new Color(240, 248, 255));
        Jframe.setBounds(10, 10, 770, 700);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.getContentPane().setLayout(new BorderLayout(0, 0));
    }

    public void start() {
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.textHighlight);
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        Jframe.getContentPane().add(panel);
        panel.setLayout(new GridLayout(10, 10, 0, 0));

        for(int i=9; i>=0; i--) {
            for (int j=1; j <= 10; j++) {
                int n;
                String image;
                if(j==10)
                    n = Integer.parseInt(String.valueOf(i) + String.valueOf(j - 1)) + 1;
                else
                    n = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
                image = "image/" + n + ".png";
                btn[n-1] = new JButton(new ImageIcon(((new ImageIcon(this.getClass().getResource(image))).getImage()).getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
                btn[n-1].setFont(new Font("Arial", Font.BOLD, 12));
                btn[n-1].setForeground(red);
            }
            ctrlBtn[i] = new JButton();
            ctrlBtn[i].setForeground(blue);
            ctrlBtn[i].setHorizontalAlignment(SwingConstants.CENTER);
            ctrlBtn[i].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 8));
            ctrlBtn[i].setBackground(orange);
        }

        for(int i=9; i>=0; i--) {
            panel.add(ctrlBtn[i]);
            if(i%2==0) {
                for (int j = 1; j <= 10; j++) {
                    int n;
                    if (j == 10)
                        n = Integer.parseInt(String.valueOf(i) + String.valueOf(j - 1)) + 1;
                    else
                        n = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
                    panel.add(btn[n - 1]);
                }
            }
            else {
                for (int j = 10; j >= 1; j--) {
                    int n;
                    if (j == 10)
                        n = Integer.parseInt(String.valueOf(i) + String.valueOf(j - 1)) + 1;
                    else
                        n = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
                    panel.add(btn[n - 1]);
                }
            }
        }

        initBtn();
        bmap.get("btn0").setText("P1,P2");

        ctrlBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                bmap.get("btn"+p1).setText(null);
                bmap.get("btn"+p2).setText(null);
				dice= (int)((Math.random()*6)+1);
                ctrlBtn[2].setText(String.valueOf(dice));
				play();
				if(currentPlayer=="Player1")
                    ctrlBtn[4].setText("P1");
				else
                    ctrlBtn[4].setText("P2");
                ctrlBtn[8].setText(String.valueOf(p1));
                ctrlBtn[6].setText(String.valueOf(p2));
                if(p1==p2)
                    bmap.get("btn"+p1).setText("P1,P2");
                else{
                    bmap.get("btn"+p1).setText("P1");
                    bmap.get("btn"+p2).setText("P2");
                }
			}
		});

        ctrlBtn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                bmap.get("btn" + p1).setText(null);
				bmap.get("btn" + p2).setText(null);
                bmap.get("btn0").setText("P1,P2");
				p1=0;
				p2=0;
				currentPlayer="Player1";
                ctrlBtn[2].setText("0");
                ctrlBtn[4].setText("P1");
                ctrlBtn[8].setText("0");
                ctrlBtn[6].setText("0");
			}
		});

        ctrlBtn[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });
	}


    private void play(){
        int position = 0;
        if(currentPlayer.equals("Player1"))
            position = p1;
        else if(currentPlayer.equals("Player2"))
            position = p2;

        if(position+dice==100){
            if(currentPlayer.equals("Player1"))
                p1 = 100;
            else if(currentPlayer.equals("Player1"))
                p2 = 100;
            JOptionPane.showMessageDialog(Jframe, currentPlayer+" has won", "Snake Board Game", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(position+dice<100){
            position+=dice;
            if(position==12)
                position=31;
            else if(position==22)
                position=61;
            else if(position==24)
                position=2;
            else if(position==43)
                position=77;
            else if(position==46)
                position=72;
            else if(position==54)
                position=26;
            else if(position==64)
                position=85;
            else if(position==70)
                position=57;
            else if(position==97)
                position=75;

            if(currentPlayer.equals("Player1")) {
                p1 = position;
                currentPlayer = "Player2";
            }
            else if(currentPlayer.equals("Player2")) {
                p2 = position;
                currentPlayer = "Player1";
            }
        }
    }

    private void initBtn(){

        for(int i=0; i<100; i++) {
            bmap.put("btn" + i, btn[i]);
            bmap.get("btn"+i).setText("");
            bmap.get("btn"+i).setVerticalTextPosition(JButton.CENTER);
            bmap.get("btn"+i).setHorizontalTextPosition(JButton.CENTER);
        }

        ctrlBtn[9].setText("Player 1");
        ctrlBtn[8].setText("0");
        ctrlBtn[8].setFont(new Font("Arial", Font.BOLD, 18));
        ctrlBtn[8].setForeground(magenta);
        ctrlBtn[7].setText("Player 2");
        ctrlBtn[6].setText("0");
        ctrlBtn[6].setFont(new Font("Arial", Font.BOLD, 18));
        ctrlBtn[6].setForeground(magenta);
        ctrlBtn[5].setText("Next Player");
        ctrlBtn[4].setText("P1");
        ctrlBtn[4].setFont(new Font("Arial", Font.BOLD, 18));
        ctrlBtn[4].setForeground(magenta);
        ctrlBtn[3].setText("Dice Value");
        ctrlBtn[2].setText("0");
        ctrlBtn[2].setFont(new Font("Arial", Font.BOLD, 18));
        ctrlBtn[2].setForeground(magenta);
        ctrlBtn[1].setText("RESET");
        ctrlBtn[1].setFont(new Font("Arial", Font.BOLD, 10));
        ctrlBtn[1].setForeground(magenta);
        ctrlBtn[0].setText("ROLE");
        ctrlBtn[0].setFont(new Font("Arial", Font.BOLD, 12));
        ctrlBtn[0].setForeground(white);
        ctrlBtn[0].setBackground(red);
    }
}
