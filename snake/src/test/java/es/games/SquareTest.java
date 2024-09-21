package es.games;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class SquareTest 
{

    @Test
    public void squareRightIncrementX(){
        Square square = new Square(2,3);
        assertEquals(new Square(3,3), square.right());
    }

}
