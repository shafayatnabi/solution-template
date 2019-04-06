package com.tigerit.exam;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
class Table {
    String name;
    String alias;
    int numofCol;
    int numofRow;
    String[] colName;
    int[][] data;

    public Table() {
        colName = new String[101];
        data = new int[101][101];
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getNumofCol() {
        return numofCol;
    }

    public int getNumofRow() {
        return numofRow;
    }

    public String[] getColName() {
        return colName;
    }

    public String getColName(int col) {
        return colName[col];
    }

    public int[][] getData() {
        return data;
    }

    public int getData(int row, int col) {
        return data[row][col];
    }

    public void setNumofCol(int numofCol) {
        this.numofCol = numofCol;
    }

    public void setNumofRow(int numofRow) {
        this.numofRow = numofRow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setColName(String nameofCol, int col) {
        colName[col] = nameofCol;
    }

    public void setData(int value, int rowNumber, int colNumber) {
        data[rowNumber][colNumber] = value;
    }

    public String toString() {
        String temp = "Name " + name + " Number of Row " + numofRow + " Number Of Column " + numofCol + "\n";
        for (int index = 0; index < numofCol; index++) {
            temp += colName[index] + " ";
        }
        temp += "\n";
        for (int rowIndex = 0; rowIndex < numofRow; rowIndex++) {
            for (int colIndex = 0; colIndex < numofCol; colIndex++) {
                temp += data[rowIndex][colIndex] + " ";
                //temp+="\n";
            }
            temp += "\n";
        }

        return temp;
    }
}

class Pair {

    private int tableId;
    private int colId;

    public Pair(int tableId, int colId) {
        this.tableId = tableId;
        this.colId = colId;
    }

    public int getTableId() {
        return tableId;
    }

    public Pair() {
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }
}

class QueryTable {

    private ArrayList<String> columList;
    private String nameOfTable1;
    private String nameOfTable2;
    private int tableId1;
    private int tableId2;
    private int joinColumnId1;
    private int joinColumnId2;
    private String aliasName1;
    private String aliasName2;
    private ArrayList<ArrayList<Integer>> resultList;

    public QueryTable() {
        resultList = new ArrayList<>();
        columList = new ArrayList<>();
    }

    public String getAliasName1() {
        return aliasName1;
    }

    public void setAliasName1(String aliasName1) {
        this.aliasName1 = aliasName1;
    }

    public String getAliasName2() {
        return aliasName2;
    }

    public void setAliasName2(String aliasName2) {
        this.aliasName2 = aliasName2;
    }

    public String getNameOfTable1() {
        return nameOfTable1;
    }

    public void setNameOfTable1(String nameOfTable1) {
        this.nameOfTable1 = nameOfTable1;
    }

    public String getNameOfTable2() {
        return nameOfTable2;
    }

    public void setNameOfTable2(String nameOfTable2) {
        this.nameOfTable2 = nameOfTable2;
    }

    public ArrayList<ArrayList<Integer>> getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList<ArrayList<Integer>> resultSet) {
        this.resultList = resultSet;
    }

    public ArrayList<String> getColumList() {
        return columList;
    }

    public void setColumList(ArrayList<String> columList) {
        this.columList = columList;
    }

    public int getTableId1() {
        return tableId1;
    }

    public void setTableId1(int tableId1) {
        this.tableId1 = tableId1;
    }

    public int getTableId2() {
        return tableId2;
    }

    public void setTableId2(int tableId2) {
        this.tableId2 = tableId2;
    }

    public int getJoinColumnId1() {
        return joinColumnId1;
    }

    public void setJoinColumnId1(int joinColumnId1) {
        this.joinColumnId1 = joinColumnId1;
    }

    public int getJoinColumnId2() {
        return joinColumnId2;
    }

    public void setJoinColumnId2(int joinColumnId2) {
        this.joinColumnId2 = joinColumnId2;
    }

    public String toString() {
        String str = "Table Name1 " + getNameOfTable1() + " Table Name2 " + getNameOfTable2() + '\n';
        str += "Querey Name1 " + getAliasName1() + " Query Name2 " + getAliasName2() + "\n";
        str += "ColumnId1 " + getJoinColumnId1() + " ColumnId2 " + getJoinColumnId2() + "\n";
        return str;
    }

}

public class Solution implements Runnable {
    @Override
    public void run() {
        // your application entry point


        // sample input process
        int testCase;
        Table[] table = new Table[20];
        testCase = readLineAsInteger();
        for (int test = 1; test <= testCase; test++) {
            int numofTable = readLineAsInteger();
            for (int tableIndex = 0; tableIndex < numofTable; tableIndex++) {
                table[tableIndex] = getInput();
            }

            int query = readLineAsInteger();
            String instruction[] = new String[4];
            printLine("Test: " + test);
            while (query > 0) {
                instruction[0] = readLine();
                instruction[1] = readLine();
                instruction[2] = readLine();
                instruction[3] = readLine();
                readLine();
                processQuery(instruction, table, numofTable);
                query--;
            }

        }

    }

    public void processQuery(String[] instruction, Table[] table, int numberofTable) {
        QueryTable queryTable = new QueryTable();
        ArrayList<Pair> tabelToCol = null;
        int queryMode = 3;

        String[] splitQuery = instruction[1].split(" ");
        if (instruction[0].equals("SELECT *")) {
            if (splitQuery.length == 2) {
                queryMode = 1;
            } else {
                queryMode = 2;
            }
        }

        if (queryMode == 1) {
            queryTable.setAliasName1(splitQuery[1]);
            queryTable.setNameOfTable1(splitQuery[1]);
            splitQuery = instruction[2].split(" ");
            queryTable.setAliasName2(splitQuery[1]);
            queryTable.setNameOfTable2(splitQuery[1]);
        } else {
            queryTable.setAliasName1(splitQuery[2]);
            queryTable.setNameOfTable1(splitQuery[1]);
            splitQuery = instruction[2].split(" ");
            queryTable.setAliasName2(splitQuery[2]);
            queryTable.setNameOfTable2(splitQuery[1]);
        }

        int id = -1;
        for (int index = 0; index < numberofTable; index++) {
            if (table[index].getName().equals(queryTable.getNameOfTable1())) {
                id = index;
                break;
            }
        }
        queryTable.setTableId1(id);

        for (int index = 0; index < numberofTable; index++) {
            if (table[index].getName().equals(queryTable.getNameOfTable2())) {
                id = index;
                break;
            }
        }
        queryTable.setTableId2(id);

        splitQuery = instruction[3].split(" ");

        int colSize = table[queryTable.getTableId1()].getNumofCol();
        //printLine("Column Size "+colSize);
        id = queryTable.getTableId1();
        int colId = -1;
        String search = splitQuery[1].substring(queryTable.getAliasName1().length() + 1);
        //printLine("Search String "+search+" Lenght "+search.length());
        for (int index = 0; index < colSize; index++) {
            String name = table[id].getColName(index);
            if (name.compareTo(search) == 0) {
                //printLine("Column "+table[id].getColName(index)+" Search "+ splitQuery[1].substring(joinQuery.getAliasName1().length()+1));
                //printLine("Column Name "+name +" Length "+name.length());
                colId = index;
                break;
            }
        }

        queryTable.setJoinColumnId1(colId);

        colSize = table[queryTable.getTableId2()].getNumofCol();
        // printLine("Column Size "+colSize);
        search = splitQuery[3].substring(queryTable.getAliasName2().length() + 1);
        id = queryTable.getTableId2();
        colId = -1;
        for (int index = 0; index < colSize; index++) {
            String name = table[id].getColName(index);
            if (name.compareTo(search) == 0) {
                //printLine("Column "+table[id].getColName(index)+" Search "+ splitQuery[3].substring(joinQuery.getAliasName2().length()+1));
                colId = index;
                break;
            }
        }

        queryTable.setJoinColumnId2(colId);

        if (queryMode == 3) {
            tabelToCol = new ArrayList<>();

            splitQuery = instruction[0].split(" ");
            for (int i = 1; i < splitQuery.length; i++) {
                String colNm;
                if (splitQuery[i].startsWith(queryTable.getAliasName1() + ".")) {
                    if (i != splitQuery.length - 1) {
                        colNm = splitQuery[i].substring(queryTable.getAliasName1().length() + 1, splitQuery[i].length() - 1);
                    } else {
                        colNm = splitQuery[i].substring(queryTable.getAliasName1().length() + 1);
                    }
                    queryTable.getColumList().add(colNm);
                    String[] colSearch = table[queryTable.getTableId1()].getColName();
                    colSize = table[queryTable.getTableId1()].getNumofCol();
                    for (int index = 0; index < colSize; index++) {
                        if (colSearch[index].equals(colNm)) {
                            tabelToCol.add(new Pair(queryTable.getTableId1(), index));
                            break;
                        }
                    }


                } else {
                    if (i != splitQuery.length - 1) {
                        colNm = splitQuery[i].substring(queryTable.getAliasName2().length() + 1, splitQuery[i].length() - 1);
                    } else {
                        colNm = splitQuery[i].substring(queryTable.getAliasName2().length() + 1);
                    }
                    queryTable.getColumList().add(colNm);
                    String[] colSearch = table[queryTable.getTableId2()].getColName();
                    colSize = table[queryTable.getTableId2()].getNumofCol();
                    for (int index = 0; index < colSize; index++) {
                        if (colSearch[index].equals(colNm)) {
                            tabelToCol.add(new Pair(queryTable.getTableId2(), index));
                            break;
                        }
                    }
                }
            }
        } else {

            colSize = table[queryTable.getTableId1()].getNumofCol();
            for (int index = 0; index < colSize; index++) {
                queryTable.getColumList().add(table[queryTable.getTableId1()].getColName(index));
            }

            colSize = table[queryTable.getTableId2()].getNumofCol();
            for (int index = 0; index < colSize; index++) {
                queryTable.getColumList().add(table[queryTable.getTableId2()].getColName(index));
            }

        }
        //printLine(queryTable);
        for (int rowIndex1 = 0; rowIndex1 < table[queryTable.getTableId1()].getNumofRow(); rowIndex1++) {
            for (int rowIndex2 = 0; rowIndex2 < table[queryTable.getTableId2()].getNumofRow(); rowIndex2++) {
                if (table[queryTable.getTableId1()].getData()[rowIndex1][queryTable.getJoinColumnId1()] == table[queryTable.getTableId2()].getData()[rowIndex2][queryTable.getJoinColumnId2()]) {
                    ArrayList<Integer> foundResult = new ArrayList<>();

                    if (queryMode == 3) {
                        if (tabelToCol != null) {
                            for (Pair entry : tabelToCol) {
                                if (entry.getTableId() == queryTable.getTableId1()) {
                                    foundResult.add(table[queryTable.getTableId1()].getData()[rowIndex1][entry.getColId()]);
                                } else {
                                    foundResult.add(table[queryTable.getTableId2()].getData()[rowIndex2][entry.getColId()]);
                                }
                            }
                        }
                    } else {
                        for (int colIndex1 = 0; colIndex1 < table[queryTable.getTableId1()].getNumofCol(); colIndex1++) {
                            foundResult.add(table[queryTable.getTableId1()].getData()[rowIndex1][colIndex1]);
                        }
                        for (int colIndex2 = 0; colIndex2 < table[queryTable.getTableId2()].getNumofCol(); colIndex2++) {
                            foundResult.add(table[queryTable.getTableId2()].getData()[rowIndex2][colIndex2]);
                        }
                    }

                    queryTable.getResultList().add(foundResult);
                }
            }
        }
        processandPrintResult(queryTable);


    }

    private void processandPrintResult(QueryTable queryTable) {
        Collections.sort(queryTable.getResultList(), new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> objects1, ArrayList<Integer> objects2) {
                int size = objects1.size();
                for (int i = 0; i < size; i++) {
                    if (!Objects.equals(objects1.get(i), objects2.get(i))) {
                        return (objects1.get(i)).compareTo(objects2.get(i));
                    }
                }
                return 0;
            }
        });

        boolean isFirst = false;
        for (String column : queryTable.getColumList()) {
            if (isFirst) {
                System.out.print(" " + column);
            } else {
                System.out.print(column);
            }
            isFirst = true;
        }
        printLine("");
        for (ArrayList<Integer> row : queryTable.getResultList()) {
            isFirst = false;
            for (Integer r : row) {
                if (isFirst) {
                    System.out.print(" " + r);
                } else {
                    System.out.print(r);
                }
                isFirst = true;
            }
            printLine("");
        }
        printLine("");
    }

    public Table getInput() {
        Table inputTable = new Table();
        String tableName = readLine();
        inputTable.setName(tableName);
        //printLine(tableName);
        String[] colandRaw = readLine().split(" ");
        int row = Integer.parseInt(colandRaw[0]);
        int col = Integer.parseInt(colandRaw[1]);
        // printLine(row +" "+col);
        inputTable.setNumofRow(row);
        inputTable.setNumofCol(col);
        String[] colName = readLine().split(" ");
        for (int index = 0; index < col; index++) {
            inputTable.setColName(colName[index], index);
            // printLine(colName[index]);
        }
        //printLine("Now print Row Col data");
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            String[] colData = readLine().split(" ");
            //printLine("Row index "+rowIndex);
            for (int colIndex = 0; colIndex < col; colIndex++) {
                inputTable.setData(Integer.parseInt(colData[colIndex]), rowIndex, colIndex);
                // printLine(colData[colIndex]);
            }
        }
        return inputTable;
    }
}
