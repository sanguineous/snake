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


    public Snake(Direction initialDirection) {
        this.direction = initialDirection;
    }

    // public Snake(SnakeGrid snakeGrid) {
    //     snakeGrid.getMiddleCell().put(new SnakePart());
    //     snakeGrid.getMiddleCell().getLeft().put(new SnakePart());
    // }

    public Snake(SnakeGrid mockGrid, int i, int j) {
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

}