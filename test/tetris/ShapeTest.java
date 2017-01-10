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
        Assert.assertNotNull(shape.x(0));
        Assert.assertNotNull(shape.x(1));
        Assert.assertNotNull(shape.x(2));
        Assert.assertNotNull(shape.x(3));
    }

    @org.junit.Test
    public void y() throws Exception {
        Shape shape=new Shape();
        shape.setRandomShape();
        Assert.assertNotNull(shape.y(0));
        Assert.assertNotNull(shape.y(1));
        Assert.assertNotNull(shape.y(2));
        Assert.assertNotNull(shape.y(3));
    }

    @org.junit.Test
    public void getShape() throws Exception {
        Shape shape=new Shape();
        shape.setRandomShape();
        Assert.assertNotNull(shape.getShape());
    }

    @org.junit.Test
    public void minX() throws Exception {

    }

    @org.junit.Test
    public void minY() throws Exception {

    }

    @org.junit.Test
    public void rotateRight() throws Exception {

    }

}