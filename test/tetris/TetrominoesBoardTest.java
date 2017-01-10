package tetris;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rolo on 2017/1/11.
 */
public class TetrominoesBoardTest {
    @Test
    public void testInit() throws Exception {
        Board b = new Board(new Tetris());
        TetrominoesBoard TB = new TetrominoesBoard(b.BoardHeight,b.BoardWidth);
        for(int index = 0; index < TB.size(); index++){
            Assert.assertEquals(Shape.Tetrominoes.NoShape, TB.get(index));
        }

    }
}