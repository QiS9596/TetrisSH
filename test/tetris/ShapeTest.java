package tetris;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rolo on 2017/1/10.
 */
public class ShapeTest {
    @org.junit.Test
    public void setShape() throws Exception {
        Shape shape=new Shape();
        shape.setShape(Shape.Tetrominoes.LShape);
        Assert.assertNotNull(shape.getShape());
        Assert.assertEquals(Shape.Tetrominoes.LShape,shape.getShape());
    }

    @org.junit.Test
    public void setRandomShape() throws Exception {
        Shape shape=new Shape();
        shape.setRandomShape();
        Assert.assertNotNull(shape.getShape());
    }

    @org.junit.Test
    public void x() throws Exception {
        Shape shape=new Shape();
        shape.setRandomShape();
        int expected = 5;
        shape.setX(0,expected);
        Assert.assertEquals(expected,shape.x(0));
    }

    @org.junit.Test
    public void y() throws Exception {
        Shape shape=new Shape();
        shape.setRandomShape();
        int expected = 3;
        shape.setY(3,expected);
        Assert.assertEquals(expected,shape.y(3));
    }

    @org.junit.Test
    public void getShape() throws Exception {
        Shape shape=new Shape();
        shape.setRandomShape();
        Assert.assertNotEquals(Shape.Tetrominoes.NoShape,shape.getShape());
    }

    @org.junit.Test
    public void minX() throws Exception {
        Shape shape = new Shape();
        shape.setShape(Shape.Tetrominoes.LineShape);
        Assert.assertEquals(-1,shape.minX());
    }

    @org.junit.Test
    public void minY() throws Exception {
        Shape shape = new Shape();
        shape.setShape(Shape.Tetrominoes.SquareShape);
        Assert.assertEquals(0,shape.minY());
    }

    @org.junit.Test
    public void rotateRight() throws Exception {
        Shape shape = new Shape();
        shape.setShape(Shape.Tetrominoes.LineShape);
        shape.rotateRight();

        Assert.assertEquals(-1,shape.x(0));
        Assert.assertEquals(0,shape.y(0));

        Assert.assertEquals(0,shape.x(1));
        Assert.assertEquals(0,shape.y(1));

        Assert.assertEquals(1,shape.x(2));
        Assert.assertEquals(0,shape.y(2));

        Assert.assertEquals(2,shape.x(3));
        Assert.assertEquals(0,shape.y(3));
    }

}