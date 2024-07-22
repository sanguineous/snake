package es.games;

import java.util.function.Predicate;

public class Snake {

    public static enum Operations{ REMOVE_TAIL_AND_ADD_HEAD_RIGHT, DIE }
    public static enum Direction { UP, RIGHT, DOWN, LEFT;

        boolean oposite(Direction other) {
            return 
                UP.equals(this) && DOWN.equals(other) || 
                RIGHT.equals(this) && LEFT.equals(other) ||
                DOWN.equals(this) && UP.equals(other) ||
                LEFT.equals(this) && RIGHT.equals(other);
    } };

    private Direction direction;
    private Direction lastStepDirection;
    private SnakeGrid grid;
    private int headCellXCoord = 3;
    private int headCellYCoord = 2;
    private int tailCellXCoord = 1;
    private int tailCellYCoord = 2;


    public Snake(Direction initialDirection) {
        this.direction = initialDirection;
    }

    // public Snake(SnakeGrid snakeGrid) {
    //     snakeGrid.getMiddleCell().put(new SnakePart());
    //     snakeGrid.getMiddleCell().getLeft().put(new SnakePart());
    // }

    public Snake(SnakeGrid grid, int headX, int headY) throws UnsupportedPlacementException {
        this.grid = grid;
        grid.getCell(headX, headY).put(new SnakePart());
        grid.getCell(headX - 1, headY).put(new SnakePart());
    }

    public Snake(Cell cell) {
        //TODO Auto-generated constructor stub
    }

    public Operations move() {
        Operations op = null;
        if(direction.oposite(lastStepDirection)){
            return Operations.DIE;
        }
        switch(direction){
            case RIGHT:
                op = Operations.REMOVE_TAIL_AND_ADD_HEAD_RIGHT;
                lastStepDirection = Direction.RIGHT;
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case UP:
                break;
            default:
                break;
        }
        return op;
    }

    public void direction(Direction newDirection) {
        direction = newDirection;
    }

    public void moveAndEat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveAndEat'");
    }

    public SnakePart head() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'head'");
    }

    public SnakePart tail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tail'");
    }

    public Predicate nextEvent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextEvent'");
    }

    public void proceed() throws UnsupportedPlacementException {

        grid.getCell(headCellXCoord + 1, headCellYCoord).put(new SnakePart());
        grid.getCell(tailCellXCoord, tailCellYCoord).put(new EmptySpace());
    }

}