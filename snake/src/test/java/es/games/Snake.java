package es.games;

public class Snake {

    public static enum Direction {
        UP, RIGHT, DOWN, LEFT;
    };

    private Direction direction;
    private SnakeGrid grid;
    private int headCellXCoord;
    private int headCellYCoord;
    private int tailCellXCoord;
    private int tailCellYCoord;

    public Snake(Direction initialDirection) {
        this.direction = initialDirection;
    }

    public Snake(SnakeGrid grid, int headX, int headY) throws UnsupportedPlacementException {
        this.grid = grid;
        this.headCellXCoord = headX;
        this.headCellYCoord = headY;
        this.tailCellXCoord = headX - 1;
        this.tailCellYCoord = headY;
        grid.getCell(headCellXCoord, headCellYCoord).put(new SnakePart());
        grid.getCell(tailCellXCoord, tailCellYCoord).put(new SnakePart());
        direction = Direction.RIGHT;
    }

    public void direction(Direction newDirection) {
        direction = newDirection;
    }

    public void proceed() throws IllegalMoveException {
        int newHeadCellXCoord = headCellXCoord;
        int newHeadCellYCoord = headCellYCoord;
            
        switch (direction) {
            case RIGHT:
                newHeadCellXCoord++;
                break;
            case DOWN:
                newHeadCellYCoord++;
                break;
            case LEFT:
                newHeadCellXCoord--;
                break;
            case UP:
                newHeadCellYCoord--;
                break;
            default:
                break;
        }

        if (newHeadCellXCoord == tailCellXCoord && newHeadCellYCoord == tailCellYCoord) {
            throw new IllegalMoveException();
        }

        if(!(grid.getCell(newHeadCellXCoord, newHeadCellYCoord).getContent() instanceof Fly)){
            grid.getCell(tailCellXCoord, tailCellYCoord).put(new EmptySpace());
            tailCellXCoord = headCellXCoord;
            tailCellYCoord = headCellYCoord;
        }
        
        grid.getCell(newHeadCellXCoord, newHeadCellYCoord).put(new SnakePart());
        headCellXCoord = newHeadCellXCoord;
        headCellYCoord = newHeadCellYCoord;
    }

}