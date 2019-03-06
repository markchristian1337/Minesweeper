package game;


public class MineSweeper {
    public enum CellState {EXPOSED, UNEXPOSED, SEALED, MINE, INPROGRESS, LOST, WON}

     private final int MAX_BOUNDS = 10;
    private CellState[][] cellStatus;

   private CellState[][] cellStatusDuplicate = new CellState[MAX_BOUNDS][MAX_BOUNDS];

    public MineSweeper() {

        cellStatus = new CellState[MAX_BOUNDS][MAX_BOUNDS];
        for (int i = 0; i < MAX_BOUNDS; i++) {
            for (int j = 0; j < MAX_BOUNDS; j++) {
                cellStatus[i][j] = CellState.UNEXPOSED;
            }
        }
        for (int i = 0; i < MAX_BOUNDS; i++) {
            for (int j = 0; j < MAX_BOUNDS; j++) {
                cellStatusDuplicate[i][j] = cellStatus[i][j];
            }
        }
    }

    public void expose(int row, int column) {
        if (cellStatus[row][column] == CellState.UNEXPOSED) {
            cellStatus[row][column] = CellState.EXPOSED;

        }

    }

    public void exposeNeighbors(int row, int column) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (row + i >= 0 && row + i < MAX_BOUNDS &&
                        column + j >= 0 && column + j < MAX_BOUNDS) {
                    expose(row + i, column + j);
                }
            }
        }

    }

    public CellState getCellStatus(int row, int column) {
        return cellStatus[row][column];
    }

    public void toggleSeal(int row, int column) {
        if (cellStatus[row][column] == CellState.EXPOSED) {
            return;
        }

        if (cellStatus[row][column] == CellState.SEALED) {
            cellStatus[row][column] = CellState.UNEXPOSED;
        } else {
            cellStatus[row][column] = CellState.SEALED;
        }
    }

    public boolean isMineAt(int row, int column) {
        if (row >= 0 && row <= MAX_BOUNDS && column >= 0 && column <= MAX_BOUNDS) {
            if (getCellStatus(row, column) == CellState.MINE) {
                return true;
            }
        }
        return false;
    }

    public void setMine(int row, int column) {

        if (cellStatus[row][column] != CellState.MINE) {
            cellStatus[row][column] = CellState.MINE;

        }

    }


   

    public int adjacentMinesCount(int row, int column) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (row + i >= 0 && row + i < MAX_BOUNDS &&
                        column + j >= 0 && column + j < MAX_BOUNDS) {
                    if (isMineAt(row + i, column + j)) {
                        count = count + 1;
                    }

                }
            }
        }
        return count;
    }

    public CellState getGameStatus() {
        CellState temp;
        int sealedMineCount = 0;
        int unexposedCellCount = 0;
        int unexposedCellCountconditionTwo = 0;

        for (int i = 0; i < MAX_BOUNDS; i++) {
            for (int j = 0; j < MAX_BOUNDS; j++) {
                if (cellStatus[i][j] == CellState.UNEXPOSED) {

                    temp = CellState.INPROGRESS;
                }
            }
        }

        for (int i = 0; i < MAX_BOUNDS; i++) {
            for (int j = 0; j < MAX_BOUNDS; j++) {
                if (cellStatus[i][j] == CellState.SEALED && cellStatusDuplicate[i][j] == CellState.MINE) {

                    sealedMineCount += 1;
                }
            }
        }
        if (sealedMineCount == 10) {
            for (int i = 0; i < MAX_BOUNDS; i++) {
                for (int j = 0; j < MAX_BOUNDS; j++) {

                    if (cellStatus[i][j] == CellState.UNEXPOSED) {
                        unexposedCellCount += 1;
                    }
                }
            }
            if (unexposedCellCount != 0) {

                temp = CellState.INPROGRESS;
            } else {
                temp = CellState.WON;
            }

        }


        for (int i = 0; i < MAX_BOUNDS; i++) {
            for (int j = 0; j < MAX_BOUNDS; j++) {

                if (cellStatus[i][j] == CellState.SEALED && cellStatusDuplicate[i][j] == CellState.UNEXPOSED) {
                    unexposedCellCountconditionTwo += 1;
                }
            }
        }
        if (unexposedCellCountconditionTwo != 0) {

            temp = CellState.INPROGRESS;
        } else {
            temp = CellState.WON;
        }


        return temp;
    }
}



