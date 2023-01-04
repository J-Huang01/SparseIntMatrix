/*
## Group members’ names and x500s
Jiatan Huang, huan2460
Ziyue Zhuang, zhuan203
 */

public class MatrixEntryTest {
    public static void main(String[] args){
        MatrixEntry newMatrix = new MatrixEntry(1,2,3);
        MatrixEntry Matrix1 = new MatrixEntry(2,2,4);
        MatrixEntry Matrix2 = new MatrixEntry(1,1,5);
        newMatrix.setRow(2);
        newMatrix.setColumn(1);
        newMatrix.setData(5);
        newMatrix.setNextCol(Matrix1);
        newMatrix.setNextRow(Matrix2);
        System.out.println("result of getRow(): " + newMatrix.getRow()+ ", expected value: 2");
        System.out.println("result of getColumn(): " + newMatrix.getColumn()+ ", expected value: 1");
        System.out.println("result of getData(): " + newMatrix.getData()+ ", expected value: 5");
        System.out.println("result of getNextCol(): " + newMatrix.getNextCol().getData() + ", expected value: 4");
        System.out.println("result of getNextRow(): " + newMatrix.getNextRow().getData() + ", expected value: 5");
    }

}
