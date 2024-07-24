package es.games;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

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
    public void newSnakeHasSizeOfTwoAndTailOnTheLeftOfHisHead() throws UnsupportedPlacementException
    {
        new Snake(a4x3SnakeGrid, 3, 2);
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{3,2}, new int[]{2,2});
    }


    @Test
    public void newSnakeMovesRight() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 2);
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{3,2}, new int[]{2,2});
    }

    @Test
    public void newSnakeMoves2TimesRight() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 1, 2);
        snake.direction(Snake.Direction.RIGHT);
        snake.proceed();
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{3,2}, new int[]{2,2});
    }

    @Test
    public void newSnakeMovesUp() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 2);
        snake.direction(Snake.Direction.UP);
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{2,1}, new int[]{2,2});
    }

    @Test
    public void newSnakeMoves2TimesUp() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 2);
        snake.direction(Snake.Direction.UP);
        snake.proceed();
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{2,0}, new int[]{2,1});
    }

    @Test
    public void newSnakeMovesDown() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 1);
        snake.direction(Snake.Direction.DOWN);
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{2,2}, new int[]{2,1});
    }

    @Test
    public void newSnakeMoves2TimesDown() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 0);
        snake.direction(Snake.Direction.DOWN);
        snake.proceed();
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{2,2}, new int[]{2,1});
    }

    @Test
    public void newSnakeCannotMoveLeft() throws UnsupportedPlacementException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 2);
        snake.direction(Snake.Direction.LEFT);
        assertThatThrownBy(() -> { snake.proceed(); }).isInstanceOf(IllegalMoveException.class);
    }

    @Test
    public void newSnakeCannotMoveUpAndThenDown() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 2);
        snake.direction(Snake.Direction.UP);
        snake.proceed();
        snake.direction(Snake.Direction.DOWN);
        assertThatThrownBy(() -> { snake.proceed(); }).isInstanceOf(IllegalMoveException.class);
    }

    @Test
    public void newSnakeCannotMoveDownAndThenUp() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 1);
        snake.direction(Snake.Direction.DOWN);
        snake.proceed();
        snake.direction(Snake.Direction.UP);
        assertThatThrownBy(() -> { snake.proceed(); }).isInstanceOf(IllegalMoveException.class);
    }

    @Test
    public void newSnakeCannotMoveLeftAndThenRight() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 1);
        snake.direction(Snake.Direction.DOWN);
        snake.proceed();
        snake.direction(Snake.Direction.LEFT);
        snake.proceed();
        snake.direction(Snake.Direction.RIGHT);
        assertThatThrownBy(() -> { snake.proceed(); }).isInstanceOf(IllegalMoveException.class);
    }

    @Test
    public void newSnakeMoveRightDownLeftUpRight() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 1);
        snake.proceed();
        snake.direction(Snake.Direction.DOWN);
        snake.proceed();
        snake.direction(Snake.Direction.LEFT);
        snake.proceed();
        snake.direction(Snake.Direction.UP);
        snake.proceed();
        snake.direction(Snake.Direction.RIGHT);
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{3,1}, new int[]{2,1});
    }

    @Test
    public void newSnakeMovesRightAndEatsAFly() throws UnsupportedPlacementException, IllegalMoveException
    {
        Snake snake = new Snake(a4x3SnakeGrid, 2, 1);
        a4x3SnakeGrid.getCell(3, 1).put(new Fly());
        snake.proceed();
        assert4x3GridCellsContainsOnly(SnakePart.class, new int[]{3,1}, new int[]{2,1}, new int[]{1,1});
    }

    private void assert4x3GridCellsContainsOnly(Class<? extends CellContent> contentType, int[]... cellCoords) {
        for(int x = 0; x < gridColums ; x++){
            for(int y = 0; y < gridRows; y++){
                if( coordsContains(cellCoords, x, y) ){
                    assertThat(a4x3SnakeGrid.getCell(x, y).getContent()).isInstanceOf(SnakePart.class);
                } else {
                    assertThat(a4x3SnakeGrid.getCell(x, y).getContent()).isInstanceOf(EmptySpace.class);
                }
            }
        }
    }

    private boolean coordsContains(int[][] cellCoords, int x, int y) {
        return Stream.of(cellCoords).anyMatch(coord -> coord[0] == x && coord[1] == y);
    }

    @Test
    public void cellRightLeft(){
        Cell cell = Cell.init();
        assertThat(cell.getRight().getLeft()).isEqualTo(cell);
    }

    @Test
    public void cellLeftRight(){
        Cell cell = Cell.init();
        assertThat(cell.getLeft().getRight()).isEqualTo(cell);
    }

    @Test
    public void cellUpDown(){
        Cell cell = Cell.init();
        assertThat(cell.getUp().getDown()).isEqualTo(cell);
    }

    @Test
    public void cellDownUp(){
        Cell cell = Cell.init();
        assertThat(cell.getDown().getUp()).isEqualTo(cell);
    }

    @Test
    public void cellDownDownUpUp(){
        Cell cell = Cell.init();
        assertThat(cell.getDown().getDown().getUp().getUp()).isEqualTo(cell);
    }

    @Test
    public void cellDownRightUpLeft(){
        Cell cell = Cell.init();
        assertThat(cell.getDown().getRight().getUp().getLeft()).isEqualTo(cell);
    }

    @Test
    public void cellDownLeftUpRight(){
        Cell cell = Cell.init();
        assertThat(cell.getDown().getLeft().getUp().getRight()).isEqualTo(cell);
    }
}
