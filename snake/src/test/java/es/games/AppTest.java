package es.games;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private int gridColums = 4;
    private int gridRows = 3;
    private SnakeGrid a4x3SnakeGrid = new SnakeGrid(gridColums, gridRows);

    @Test
    public void inANewGridAllCellsShouldBeEmpty()
    {
        for(int x = 0; x < gridColums ; x++){
            for(int y = 0; y < gridRows; y++){
                assertThat(a4x3SnakeGrid.getCell(2,2).getContent()).isInstanceOf(EmptySpace.class);
            }
        }
    }

    @Test
    public void outOfBoundCellRead()
    {
        assertThatThrownBy(() -> { a4x3SnakeGrid.getCell(5, 2); }).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> { a4x3SnakeGrid.getCell(2, 4); }).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> { a4x3SnakeGrid.getCell(7, 9); }).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void gettingSameCellTwiceResultInSameCell(){
        Cell cell1 = a4x3SnakeGrid.getCell(2, 2);
        Cell cell2 = a4x3SnakeGrid.getCell(2, 2);
        assertThat(cell1).isEqualTo(cell2);
    }

    @Test
    public void putAsnakePartOverAnEmptyCell() throws UnsupportedPlacementException
    {
        Cell cell = new Cell();
        cell.put(new SnakePart());
        assertThat(cell.getContent()).isInstanceOf(SnakePart.class);
    }

    @Test
    public void putAsnakePartOverASnakePart() throws UnsupportedPlacementException
    {
        Cell cell = new Cell();
        cell.put(new SnakePart());
        assertThatThrownBy(() -> { cell.put(new SnakePart()); })
            .isInstanceOf(UnsupportedPlacementException.class);
    }

    @Test
    public void putAflyOverAnEmptyCell() throws UnsupportedPlacementException
    {
        Cell cell = new Cell();
        cell.put(new Fly());
        assertThat(cell.getContent()).isInstanceOf(Fly.class);
    }

    @Test
    public void putASnakePartOverAFly() throws UnsupportedPlacementException
    {
        Cell cell = new Cell();
        cell.put(new Fly());
        cell.put(new SnakePart());
        assertThat(cell.getContent()).isInstanceOf(SnakePart.class);
    }

    @Test
    public void putAFlyOverAFly() throws UnsupportedPlacementException
    {
        Cell cell = new Cell();
        cell.put(new Fly());
        assertThatThrownBy(() -> { cell.put(new Fly()); })
            .isInstanceOf(UnsupportedPlacementException.class);
    }

    // @Test
    // public void boh(){
    //     SnakeGrid mockGrid = mock(SnakeGrid.class);
    //     new Snake(mockGrid, 3, 4);
    //     verify(mockGrid.put(eq(3), eq(4), any(SnakePart.class)));
    //     verify(mockGrid.put(eq(2), eq(4), any(SnakePart.class)));
    // }

    // @Test
    // public void boh2(){
    //     Cell headCell = new Cell();
    //     Cell tailCell = new Cell();
    //     headCell.setLeft(tailCell);
    //     tailCell.setRight(headCell);

    //     Snake snake = new Snake(headCell);

    //     assertThat(headCell.getContent()).isInstanceOf(SnakePart.class);
    //     assertThat(initialCell.getContent()).isInstanceOf(SnakePart.class);

    // }

    @Test
    public void newSnakeHasSizeOfTwoAndTailOnTheLeftOfTheHead() throws UnsupportedPlacementException
    {
        new Snake(a4x3SnakeGrid, 3, 2);
        for(int x = 0; x < gridColums ; x++){
            for(int y = 0; y < gridRows; y++){
                if(x == 3 && y == 2 || x == 2 && y == 2){
                    assertThat(a4x3SnakeGrid.getCell(x, y).getContent()).isInstanceOf(SnakePart.class);
                } else {
                    assertThat(a4x3SnakeGrid.getCell(x, y).getContent()).isInstanceOf(EmptySpace.class);
                }
            }
        }
    }

    @Test
    public void testSnakeMovingRightThenLeftShouldDie()
    {
        Snake snake = new Snake(Snake.Direction.RIGHT);
        snake.move();
        snake.direction(Snake.Direction.LEFT);
        assertThat(snake.move()).isEqualTo(Snake.Operations.DIE);
    }

    // @Test
    // public void testSnakEatsItself()
    // {
    //     Snake snake = new Snake(Snake.Direction.RIGHT);
    //     snake.moveAndEat();
    //     snake.moveAndEat();
    //     snake.moveAndEat();
    //     snake.direction(Snake.Direction.DOWN);
    //     snake.move();
    //     snake.direction(Snake.Direction.LEFT);
    //     snake.move();
    //     snake.direction(Snake.Direction.LEFT);
    //     assertThat(snake.move()).isEqualTo(Snake.Operations.DIE);
    // }
}
