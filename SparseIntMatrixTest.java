/*
## Group members’ names and x500s
Jiatan Huang, huan2460
Ziyue Zhuang, zhuan203
 */
public class SparseIntMatrixTest {
    public static void main(String[] args){
//        SparseIntMatrix newMat1 = new SparseIntMatrix(1000,1000,"matrix1_data.txt");
//        MatrixViewer.show(newMat1);
        SparseIntMatrix newMat2 = new SparseIntMatrix(1000,1000,"matrix2_data.txt");

        SparseIntMatrix newMat3 = new SparseIntMatrix(1000,1000,"matrix2_noise.txt");
        newMat2.minus(newMat3);
        MatrixViewer.show(newMat2);

    }
}
