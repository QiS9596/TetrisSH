package tetris;


import org.junit.Assert;

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

    }

    @org.junit.Test
    public void squareHeight() throws Exception {

    }

    @org.junit.Test
    public void shapeAt() throws Exception {

    }

}