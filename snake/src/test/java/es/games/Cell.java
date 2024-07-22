package es.games;

public class Cell {

    private CellContent content;

    public Cell() {
        this.content = new EmptySpace();
    }

    public CellContent getContent() {
        return content;
    }

    public void put(CellContent newContent) {
        content = newContent;
    }

}
