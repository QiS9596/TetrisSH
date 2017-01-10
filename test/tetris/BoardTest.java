package tetris;


import org.junit.Assert;
import org.junit.Test;
import sun.security.provider.SHA;

import static org.junit.Assert.*;

/**
 * Created by rolo on 2017/1/10.
 */
public class BoardTest {
    @org.junit.Test
    public void start() throws Exception {
        Board b = new Board(new Tetris());
        b.start();
        Assert.assertTrue(b.isStarted);
        Assert.assertFalse(b.isFallingFinished);
        Assert.assertEquals(0,b.numLinesRemoved);
    }

    @org.junit.Test
    public void squareWidth() throws Exception {
        Board b = new Board(new Tetris());
        double x = b.BoardWidth;
        double y = b.getSize().getWidth();

        Assert.assertEquals((int)(y/x), b.squareWidth());
    }

    @org.junit.Test
    public void squareHeight() throws Exception {
        Board b = new Board(new Tetris());
        double x = b.BoardHeight;
        double y = b.getSize().getHeight();

        Assert.assertEquals((int)(y/x),b.squareHeight());
    }

    @org.junit.Test
    public void shapeAt() throws Exception {
        Board b = new Board(new Tetris());
        b.board.set(1, Shape.Tetrominoes.LineShape);
        Assert.assertEquals(Shape.Tetrominoes.LineShape, b.shapeAt(1,0));
        b.board.set(2*b.BoardWidth, Shape.Tetrominoes.SquareShape);
        Assert.assertEquals(Shape.Tetrominoes.SquareShape, b.shapeAt(0,2));
        Assert.assertEquals(Shape.Tetrominoes.NoShape, b.shapeAt(10,10));
    }

    @Test
    public void clearBoard() throws Exception {
        Board b = new Board(new Tetris());
        TetrominoesBoard TB = new TetrominoesBoard(b.BoardHeight,b.BoardWidth);
        b.board.set(1, Shape.Tetrominoes.LineShape);
        Assert.assertNotEquals(b.board.get(1),TB.get(1));
        b.clearBoard();
        Assert.assertEquals(TB,b.board);
    }
}