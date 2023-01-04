/*
## Group members’ names and x500s
Jiatan Huang, huan2460
Ziyue Zhuang, zhuan203
 */
public class MatrixEntry {
    private int row;
    private int col;
    private int data;

    private MatrixEntry nextRow=null;
    private MatrixEntry nextCol=null;

    public MatrixEntry(int row, int col, int data){
        this.row = row;
        this.col = col;
        this.data = data;
    }
    public int getColumn(){
        return col;
    }
    public void setColumn(int col){
        this.col = col;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getData(){
        return data;
    }
    public void setData(int data){
        this.data = data;
    }
    public MatrixEntry getNextRow(){
        return nextRow;
    }
    public void setNextRow(MatrixEntry el){
        this.nextRow = el;

    }
    public MatrixEntry getNextCol(){
        return nextCol;

    }
    public void setNextCol(MatrixEntry el){
        this.nextCol = el;

    }


}
