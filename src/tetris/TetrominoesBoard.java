package tetris;

import java.util.ArrayList;

/**
 * Created by rolo on 2017/1/10.
 */
public class TetrominoesBoard extends ArrayList<Shape.Tetrominoes> {

    public TetrominoesBoard(int BoardHeight,int BoardWidth){
        super();
        for(int index = 0; index < BoardWidth*BoardHeight; index++){
            this.add(Shape.Tetrominoes.NoShape);
        }
    }
}
