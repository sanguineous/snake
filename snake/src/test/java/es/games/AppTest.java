package es.games;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

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
