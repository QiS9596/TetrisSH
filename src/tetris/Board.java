package tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.Shape.Tetrominoes;


public class Board extends JPanel implements ActionListener {//controller


     int BoardWidth = 10;
     int BoardHeight = 20;
    JLabel statusbar;
    GUI draw;
    //
    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    //boolean isPaused = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    
    Shape curPiece;
    TetrominoesBoard board;



    public Board(Tetris parent) {
       draw=new GUI();
       setFocusable(true);//
       curPiece = new Shape();
       timer = new Timer(1000, this);
       timer.start(); 

       statusbar =  parent.getStatusBar();
       board = new TetrominoesBoard(BoardHeight,BoardWidth);
//       for(int index = 0; index < BoardWidth*BoardHeight; index++){
//           board.add(Tetrominoes.NoShape);
//       }

       addKeyListener(new TAdapter());//key event
       clearBoard();  
    }
    
    public void start()
    {
        //if (isPaused)
            //return;

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        timer.start();
    }
    
    private void clearBoard()
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board.set(i, Tetrominoes.NoShape);
    }
    
    private void newPiece()
    {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 ;
        curY = BoardHeight - 1 + curPiece.minY();//

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            statusbar.setText("game over");
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }


    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    Tetrominoes shapeAt(int x, int y) { return board.get((y * BoardWidth) + x); }

    public void paint(Graphics g)
    { 
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();


        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape)
                	
                draw.drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), shape,
                               squareWidth()-1 ,squareHeight()-1 );
            }
        }

        if (curPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                draw.drawSquare(g, 0 + x * squareWidth(),
                           boardTop + (BoardHeight - y - 1) * squareHeight(),
                           curPiece.getShape(),
                           squareWidth() - 1,squareHeight() - 1);
            }
        }
    }

    private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();//更新board 陣列
    }

    private void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }
     
    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board.set((y * BoardWidth) + x, curPiece.getShape());
        }

        removeFullLines();//若有消除列 isFallingFinished 變為true

        if (!isFallingFinished)//預設為false 
            newPiece();
    }

   

    private boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                return false;
            if (shapeAt(x, y) != Tetrominoes.NoShape)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j)
                         board.set((k * BoardWidth) + j, shapeAt(j, k + 1));//shapeAt 先給 x 座標
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText("Lines : "+String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoes.NoShape);
            repaint();
        }
     }

  

    class TAdapter extends KeyAdapter {
         public void keyPressed(KeyEvent e) {

             if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {  
                 return;
             }

             int keycode = e.getKeyCode();

             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 tryMove(curPiece, curX - 1, curY);
                 break;
             case KeyEvent.VK_RIGHT:
                 tryMove(curPiece, curX + 1, curY);
                 break;
             case 'c':case'C':case KeyEvent.VK_UP:
                 tryMove(curPiece.rotateRight(), curX, curY);
                 break;
             //case 'z':case 'Z':
                // tryMove(curPiece.rotateLeft(), curX, curY);
                 //break;
             case KeyEvent.VK_SPACE:
                 dropDown();
                 break;
             case KeyEvent.VK_DOWN:
                 oneLineDown();
                 break;
             case 'D':
                 oneLineDown();
                 break;
             }

         }
     }
}