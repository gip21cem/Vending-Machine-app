import com.company.vendingmachineapp.dao.InsufficientFundsException;
import com.company.vendingmachineapp.dao.NoItemInventoryException;
import com.company.vendingmachineapp.dao.VendingMachineAppDAOImpl;
import com.company.vendingmachineapp.dto.VendingMachineItem;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class UnitTest {
    /*
     * this method tests that the loadItemsFromInventory method
     * in the VendingMachineAppDAOImpl class does not return
     * a null arrayList
     */
    @Test
    public void testReturnedValueIsNotNull(){
        VendingMachineAppDAOImpl classUnderTest = new VendingMachineAppDAOImpl();
        List<VendingMachineItem> valueUnderTest = classUnderTest.loadItemsFromInventory();
        assertNotNull(valueUnderTest);
    }

    /*
     * this method tests that the vendingTransaction method
     * in the VendingMachineAppDAOImpl class throws
     * an object of InsufficientFundsException
     */
    @Test
    public void testInsufficientFundsException() {
        VendingMachineAppDAOImpl testImpl = new VendingMachineAppDAOImpl();
        List<VendingMachineItem> testList = new ArrayList<>();
        testList.add(new VendingMachineItem(1, "Coke can", 100, 2 ));

        assertThrows(InsufficientFundsException.class, () -> {
            testImpl.vendingTransaction(testList, 1, 95); // amountEntered is less that itemPrice
        });
    }

    /*
     * this method tests that the vendingTransaction method
     * in the VendingMachineAppDAOImpl class throws
     * an object of NoItemInventoryException;
     * in addition it tests for exception messages equality
     */
    @Test
    public void testNoItemInventoryException() {
        VendingMachineAppDAOImpl testImpl = new VendingMachineAppDAOImpl();
        List<VendingMachineItem> testList = new ArrayList<>();
        testList.add(new VendingMachineItem(1, "Coke can", 150, 0)); // itemNumberOfPieces is zero

        Throwable exception = assertThrows(NoItemInventoryException.class, () -> {
            testImpl.vendingTransaction(testList, 1, 200);
        });

        assertEquals("Sorry the selected item is not available", exception.getMessage());
    }
}
