package tetris;

import java.awt.Color;
import java.awt.Graphics;

import tetris.Shape.Tetrominoes;

public class GUI {
	
	 int BoardWidth = 10;
     int BoardHeight = 20;
	
	public GUI() {
		// TODO Auto-generated constructor stub
		
	}
	public void drawSquare(Graphics g, int x, int y, Tetrominoes shape,
			int squareWidth,int squareHeight)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x+1 , y + 1, squareWidth , squareHeight );

     
    }

}
