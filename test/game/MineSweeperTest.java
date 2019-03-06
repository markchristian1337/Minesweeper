package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.MineSweeper.CellState.*;
import static org.junit.jupiter.api.Assertions.*;

public class MineSweeperTest {

    private game.MineSweeper mineSweeper;

    @BeforeEach
    public void init() {
        mineSweeper = new game.MineSweeper();
    }

    @Test
    public void canary() {
        assertTrue(true);
    }

    @Test
    public void exposeCell() {
        mineSweeper.expose(4, 2);

        assertEquals(EXPOSED, mineSweeper.getCellStatus(4, 2));
    }

    @Test
    public void exposeAnExposedCell() {
        mineSweeper.expose(4, 2);
        mineSweeper.expose(4, 2);

        assertEquals(EXPOSED, mineSweeper.getCellStatus(4, 2));
    }

 
    @Test
    public void sealCell() {
        mineSweeper.toggleSeal(3, 4);

        assertEquals(SEALED, mineSweeper.getCellStatus(3, 4));
    }

    @Test
    public void unsealASealedCell() {
        mineSweeper.toggleSeal(3, 4);
        mineSweeper.toggleSeal(3, 4);

        assertEquals(UNEXPOSED, mineSweeper.getCellStatus(3, 4));
    }

    //Feedback: Let's write here the toggleSealOutOfBounds tests

    @Test
    public void sealExposedCell() {
        mineSweeper.expose(4, 8);
        mineSweeper.toggleSeal(4, 8);

        assertEquals(EXPOSED, mineSweeper.getCellStatus(4, 8));
    }

    @Test
    public void exposeASealedCell() {
        mineSweeper.toggleSeal(4, 8);
        mineSweeper.expose(4, 8);

        assertEquals(SEALED, mineSweeper.getCellStatus(4, 8));
    }

    @Test
    public void isMineAt(){

        assertTrue(mineSweeper.isMineAt(3, 2));
    }


@Test
public void setMine(){

        mineSweeper.setMine(3,2);

    assertTrue(mineSweeper.isMineAt(3, 2));
}

    @Test
    public void isMineOutOFBoundsLowRow(){

        assertTrue(mineSweeper.isMineAt(-1, 2));
    }
    @Test
    public void isMineOutOFBoundsHighRow(){

        assertTrue(mineSweeper.isMineAt(11, 2));
    }

    @Test
    public void adjacentMinesCountZero(){

        mineSweeper.setMine(3,4);

        assertEquals(0 , mineSweeper.adjacentMinesCount(3,4));
    }

    @Test
    public void adjacentMinesCountOne(){

        mineSweeper.setMine(3,4);

        assertEquals(1 , mineSweeper.adjacentMinesCount(3,5));

    }

    @Test
    public void adjacentMinesCountTwo(){

        mineSweeper.setMine(3,4);
        mineSweeper.setMine(2,6);

        assertEquals(2 , mineSweeper.adjacentMinesCount(3,5));

    }

    @Test
    public void getGameStatus(){



    }

    class MineSweeperStubWithExposeNeighbors extends game.MineSweeper {
        public boolean exposeNeighborsCalled;

        public void exposeNeighbors(int row, int column) {
            exposeNeighborsCalled = true;
        }
    }

    @Test
    public void exposeCallsExposeNeighbors() {
        MineSweeperStubWithExposeNeighbors mineSweeper =
                new MineSweeperStubWithExposeNeighbors();

        mineSweeper.expose(2, 9);

        assertTrue(mineSweeper.exposeNeighborsCalled);
    }

    @Test
    public void exposeDoesNotCallsExposeNeighborsForAlreadyExposedCell() {
        MineSweeperStubWithExposeNeighbors mineSweeper =
                new MineSweeperStubWithExposeNeighbors();

        mineSweeper.expose(2, 9);
        mineSweeper.exposeNeighborsCalled = false;
        mineSweeper.expose(2, 9);

        assertFalse(mineSweeper.exposeNeighborsCalled);
    }

    @Test
    public void exposeDoesNotCallExposeNeighborsWhenCalledOnSealedCell() {
        MineSweeperStubWithExposeNeighbors mineSweeper =
                new MineSweeperStubWithExposeNeighbors();

        mineSweeper.toggleSeal(4, 9);
        mineSweeper.expose(4, 9);

        assertFalse(mineSweeper.exposeNeighborsCalled);
    }

    class MineSweeperStubWithExpose extends game.MineSweeper {
        public int count = 0;

        public void expose(int row, int column) {
            count++;

        }

    }

    @Test
    public void exposeNeighborsCallsExposeOnEightNeighbors() {
        MineSweeperStubWithExpose mineSweeper =
                new MineSweeperStubWithExpose();

        mineSweeper.exposeNeighbors(4, 6);

        assertEquals(8, mineSweeper.count);
    }

    @Test
    public void exposeNeighborsOnTopLeftCellCallsExposeOnThreeCells() {
        MineSweeperStubWithExpose mineSweeper =
                new MineSweeperStubWithExpose();

        mineSweeper.exposeNeighbors(0, 0);

        assertEquals(3, mineSweeper.count);
    }

    @Test
    public void exposeNeighborsOnBottomRightCellCallsExposeOnThreeCells() {
        MineSweeperStubWithExpose mineSweeper =
                new MineSweeperStubWithExpose();

        mineSweeper.exposeNeighbors(9, 9);

        assertEquals(3, mineSweeper.count);
    }
}