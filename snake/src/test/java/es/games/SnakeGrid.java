package es.games;

public class SnakeGrid {
    private Cell[][] grid;

    public SnakeGrid(int colums, int rows) {
        grid = new Cell[colums][rows];
    }

    public Cell getCell(int x, int y) {
        if(grid[x][y] == null){
            grid[x][y] = new Cell();
        }
        return grid[x][y];
    }

    public Cell getMiddleCell() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMiddleCell'");
    }

    public Object put(int eq, int eq2, SnakePart any) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

}
