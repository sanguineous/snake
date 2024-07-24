package es.games;

public class Cell {

    private CellContent content;
    private Cell right;
    private Cell left;
    private Cell up;
    private Cell down;

    public Cell() {
        this.content = new EmptySpace();
    }

    public CellContent getContent() {
        return content;
    }

    public void put(CellContent newContent) {
        content = newContent;
    }

    public Cell getRight() {
        if(right == null){
            right = new Cell();
            right.left = this;
            
            if(up.right == null){
                up.right = new Cell();
                up.right.left = up.right;
            }
            right.up = up.right;

            if(down.right == null){
                down.right = new Cell();
                down.right.left = down.right;
            }
            right.down = down.right;
        }
        return right;
    }

    public Cell getLeft() {
        if(left == null){
            left = new Cell();
            left.right = this;
        }
        return left;
    }

    public Cell getUp() {
        if(up == null){
            up = new Cell();
            up.down = this;
        }
        return up;
    }

    public Cell getDown() {
        if(down == null){
            down = new Cell();
            down.up = this;
        }
        return down;
    }

    public static Cell init() {
        Cell initCell = new Cell();
        initCell.right = new Cell();
        initCell.right.left = initCell;
        initCell.up = new Cell();
        initCell.up.down = initCell;
        initCell.down = new Cell();
        initCell.down.up = initCell;
        initCell.left = new Cell();
        initCell.left.right = initCell;

        initCell.right.up = new Cell();
        initCell.up.right = initCell.right.up;
        initCell.right.up.down = initCell.right;
        initCell.right.up.left = initCell.up;

        initCell.right.down = new Cell();
        initCell.down.right = initCell.right.down;
        initCell.right.down.up = initCell.right;
        initCell.right.down.left = initCell.down;

        initCell.down.left = new Cell();
        initCell.left.down = initCell.down.left;
        initCell.down.left.up = initCell.left;
        initCell.down.left.right = initCell.down;
        
        initCell.up.left = new Cell();
        initCell.left.up = initCell.up.left;
        initCell.up.left.right = initCell.up;
        initCell.up.left.down = initCell.left;        
        
        return initCell;
    }

}
