package tetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Tetris extends JFrame {

    JLabel statusbar;
    
    public static void main(String[] args) {

        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    } 

    public Tetris() {      
    	
    	  statusbar = new JLabel("Lines : 0");
          add(statusbar, BorderLayout.NORTH);
    	
        Board board = new Board(this);
        add(board);
        board.start(); // game start
        

        setSize(400, 800);       // set frame size
        setTitle("Tetris Game"); // set title
        setDefaultCloseOperation(EXIT_ON_CLOSE); // use for default option
        
        //add model
        //add key event 
   }

   public JLabel getStatusBar() {
       return statusbar;
   }

   
}