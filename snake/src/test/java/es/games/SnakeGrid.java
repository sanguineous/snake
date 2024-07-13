package es.games;

public class SnakeGrid {
    private Cell[][] grid;

    public SnakeGrid(int colums, int rows) {
        grid = new Cell[colums][rows];
    }

    public Cell getCell(int x, int y) {
        Cell cell = grid[x][y];
        return cell == null ? new Cell() : cell;
    }

}
