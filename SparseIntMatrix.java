/*
## Group members’ names and x500s
Jiatan Huang, huan2460
Ziyue Zhuang, zhuan203
 */

import java.io.*;
import java.util.*;

public class SparseIntMatrix {

    private int numRows;
    private int numCols;
    private MatrixEntry[] colList;
    private MatrixEntry[] rowList;

    public SparseIntMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        colList = new MatrixEntry[this.numCols];
        rowList = new MatrixEntry[this.numRows];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) {
        this.numRows = numRows;
        this.numCols = numCols;
        colList = new MatrixEntry[this.numCols];
        rowList = new MatrixEntry[this.numRows];
        MatrixEntry[] out;

        File input = new File(inputFile);

        try {//use Scanner to convert the data in file to a MatrixEntry array
            Scanner s = new Scanner(input);
            MatrixEntry[] matEntry = new MatrixEntry[numRows * numCols];
            int count = 0;

            while (s.hasNext()) {
                String line = s.nextLine();
                Scanner inputLine = new Scanner(line).useDelimiter(",");// re-assign the default separators
                int row = Integer.parseInt(inputLine.next());
                int col = Integer.parseInt(inputLine.next());
                int data = Integer.parseInt(inputLine.next());

                if (row <= numRows && col <= numCols && data != 0) {
                    MatrixEntry entry = new MatrixEntry(row, col, data);
                    matEntry[count] = entry;
                    count++;
                }
            }
            out = Arrays.copyOf(matEntry, getLength(matEntry));
        }

        catch (Exception e) {
            System.out.println("File not found");
            out = new MatrixEntry[0];
        }

        fileToAList(out);
    }

    //helper function to add element in the linked list( row and col separate)
    private void fileToAList(MatrixEntry[] newMat) {
        for (int col = 0; col < numCols; col++) {
            boolean firstHead = true; //head element not exist; put ele here;
            MatrixEntry beforeEntry = null;
            for (MatrixEntry entry : newMat) {
                if (entry.getColumn() == col) {// if the column is checking now
                    if (firstHead) {//the first ele in the col
                        colList[col] = entry;
                        beforeEntry = entry;
                        firstHead = false; // head element has existed; not put ele here;
                    } else {//is not empty
                        beforeEntry.setNextRow(entry);
                        beforeEntry = entry;
                    }
                }
            }
        }

        for (int row = 0; row < numRows; row++) {
            boolean firstHead = true;//head element not exist; put ele here;
            MatrixEntry beforeEntry = null;
            for (MatrixEntry entry : newMat) {//again ,check all the ele's data not 0;
                if (entry.getRow() == row) {// if the row is checking now
                    if (firstHead) { //head
                        rowList[row] = entry;
                        beforeEntry = entry;
                        firstHead = false;// head element has existed; not put ele here;
                    } else {
                        beforeEntry.setNextCol(entry);
                        beforeEntry = entry;
                    }
                }
            }
        } // end for (rows)
    }

    private int getLength(Object[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        return count;
    }

    public int getElement(int row, int col) {
        int data = 0;
        MatrixEntry entry = colList[col];
        while (entry != null && entry.getRow() <= row) {//this line is not empty; and the first ele'ROW in this line
            if (entry.getRow() == row) {
                data = entry.getData();
                entry = entry.getNextRow();//to finish the loop---if not change;still in the while loop
            } else {
                entry = entry.getNextRow();
            }
        }
        return data;
    } // end getElement

    public boolean setElement(int row, int col, int data) {
        //we use two boolean variable to check the col and row separately; if both finished, return true; that means we already finished the linked list;
        boolean checkCol, checkRow;
        checkCol = setColumnElement(row, col, data);
        checkRow = setRowElement(row, col, data);
        if (checkCol && checkRow) {
            return true;}
        else {
            return false;
        }
    } // end setElement

    public boolean setColumnElement(int row, int col, int data) {
        /*
        1. get the col we want to set;
        2. if not exist--just add new matrix
        3. out of the bound--just jump out
        4. find the correct row
            then supply the node
                1. the beforeNode
                2. the afterNode
            try--catch
         */
        boolean hasNext;
        MatrixEntry newEntry = new MatrixEntry(row, col, data);
        MatrixEntry entry = colList[col];
        MatrixEntry last = null;

        if (row >= numRows && col >= numCols) {
            return false;
        } else if (entry == null) {
            colList[col] = newEntry;
            return true;
        } else {
            try {
                entry.getNextRow(); //if outcome is "null" that works
                hasNext = true;
            } catch (Exception e) {
                hasNext = false;
            }
            while (hasNext && entry.getRow() <= row) {
                if (entry.getRow() == row) {//in the middle
                    entry.setData(data);
                    return true;
                } else {
                    last = entry;//140
                    entry = entry.getNextRow();
                    try {
                        entry.getNextRow();
                    } catch (Exception e) {
                        hasNext = false;
                    }
                }
            } // end while

            if (hasNext) {//in the middle
                last.setNextRow(newEntry);
                newEntry.setNextRow(entry);
            } else {//last insert
                last.setNextRow(newEntry);//auto- the last ele.getNext() ---null;
            }
            return true;
        } // end if/else
    } // end setColumnElement

    public boolean setRowElement(int row, int col, int data) {
        boolean hasNext;
        MatrixEntry newEntry = new MatrixEntry(row, col, data);
        MatrixEntry entry = rowList[row];
        MatrixEntry last = null;

        if (row >= numRows && col >= numCols) {
            return false;
        } else if (entry == null) {
            rowList[row] = newEntry;
            return true;
        } else {
            try {
                entry.getNextCol();
                hasNext = true;
            } catch (Exception e) {
                hasNext = false;
            }
            while (hasNext && entry.getColumn() <= col) {
                if (entry.getColumn() == col) {
                    entry.setData(data);
                    return true;
                } else {
                    last = entry;
                    entry = entry.getNextCol();
                    try {
                        entry.getNextCol();
                    } catch (Exception e) {
                        hasNext = false;
                    }
                }
            } // end while

            if (hasNext) {
                last.setNextCol(newEntry);
                newEntry.setNextCol(entry);
            } else {//if lase.setnextcol==null;
                last.setNextCol(newEntry);
                //?
            }
            return true;
        } // end if/else
    } // end setRowElement

    public boolean removeElement(int row, int col, int data) {
        boolean success = false; //check remove successfully
        MatrixEntry entry = colList[col];
        MatrixEntry last = null;

        while (entry.getRow() <= row) {
            if (entry.getRow() == row) {
                if (last == null) { // if first in column
                    colList[col] = entry.getNextRow();
                } else if (entry.getNextRow() == null) { // if last in column
                    last.setNextRow(null);
                } else {
                    last.setNextRow(entry.getNextRow());
                }
                success = true;
            } else {
                last = entry;
                entry = entry.getNextRow();
            }
        } // end while

        return success;
    } // end removeElement


    public boolean plus(SparseIntMatrix otherMat) {
        if (this.numRows == otherMat.getNumRows() && this.numCols == otherMat.getNumCols()) {
            SparseIntMatrix newMat = new SparseIntMatrix(numRows, numCols);

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    int newData = this.getElement(i, j) + otherMat.getElement(i, j);
                    if (newData != 0) {
                        newMat.setElement(i, j, newData);
                    }
                }
            }
            this.colList = newMat.colList;
            return true;
        } else {
            return false;
        }
    } // end plus

    public boolean minus(SparseIntMatrix otherMat) {
        if (this.numRows == otherMat.getNumRows() && this.numCols == otherMat.getNumCols()) {//check the range
            SparseIntMatrix newMat = new SparseIntMatrix(numRows, numCols);

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    int newData = this.getElement(i, j) - otherMat.getElement(i, j);
                    if (newData != 0) {//default data is 0;
                        newMat.setElement(i, j, newData);
                    }
                }
            }
            this.colList = newMat.colList;
            return true;
        } else {
            return false;
        }
    } // end minus


    // accessor methods
    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }


}




