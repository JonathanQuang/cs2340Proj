package cs2340.spacetraders;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import cs2340.spacetraders.entity.Player;
import cs2340.spacetraders.viewmodels.RepairRefuelViewModel;


/**
 * Tests for fuelError()
 *
 * @author Jonathan Quang
 * @version 1.0
 *
 */

public class RepairRefuelViewModelTest {

    private RepairRefuelViewModel repairRefuelVM;
    private Player player;

    private static final int TIMEOUT = 200;


    @Before
    public void setUp() {
        player = new Player("Jonathan", 0, 0, 0, 0);
        repairRefuelVM = new RepairRefuelViewModel(player);
    }

    @Test(timeout = TIMEOUT)
    public void nonPositiveWorks() {
        assertEquals("please enter a positive quantity",repairRefuelVM.fuelError(-1));
        assertEquals("please enter a positive quantity", repairRefuelVM.fuelError(0));
    }

    @Test(timeout = TIMEOUT)
    public void cannotOverFill() {
        //ship is already full by default
        assertEquals("buying more fuel than player ship can hold", repairRefuelVM.fuelError(10));
        //remove 10 units of fuel from the ship
        player.getShip().setFuel(player.getShip().getFuel() - 10);
        assertEquals("buying more fuel than player ship can hold", repairRefuelVM.fuelError(11));
        assertNotEquals("buying more fuel than player ship can hold", repairRefuelVM.fuelError(10));
        assertNotEquals("buying more fuel than player ship can hold", repairRefuelVM.fuelError(9));
    }

    @Test(timeout = TIMEOUT)
    public void cannotBeTooPoor() {
        final int FUEL_UNIT_PRICE = repairRefuelVM.getFuelUnitPrice();

        //leave player with nothing in the bank except one credit
        player.changeCredits(player.getCredits() * -1 + 1);
        //remove 2 units of fuel from the ship
        player.getShip().setFuel(player.getShip().getFuel() - 2);


        assertEquals("too poor, fuel costs " + FUEL_UNIT_PRICE + " here", repairRefuelVM.fuelError(1));
        assertEquals("too poor, fuel costs " + FUEL_UNIT_PRICE + " here", repairRefuelVM.fuelError(2));
        player.changeCredits(FUEL_UNIT_PRICE);
        assertNotEquals("too poor, fuel costs " + FUEL_UNIT_PRICE + " here", repairRefuelVM.fuelError(1));
    }

    @Test(timeout = TIMEOUT)
    public void validPurchases() {
        //remove 3 units of fuel from the ship
        player.getShip().setFuel(player.getShip().getFuel() - 3);
        assertNull(repairRefuelVM.fuelError(1));
        assertNull(repairRefuelVM.fuelError(2));
    }

}
