package test;
import Controller.MainController;
import Model.*;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Michelle Fast on 5/7/2016.
 * This is the tester class to test deposit and withdraw functions of this program
 */
public class AccountModelTest {

    AccountModel tester = new AccountModel();
    private ArrayList<Account> testerList = new ArrayList<Account>();
    Account userTest = new Account(0, "Tester McGee", 2000.00);

    @Test
    public void withdraw() {
        tester.userAccounts.add(userTest);
        tester.withdraw(100.00, "USD", userTest);
        assertEquals("Withdrawing $100 and should have $1900", 1900.00, userTest.funds, .01);
    }

    @Test
    public void deposit()
    {
        tester.userAccounts.add(userTest);
        tester.deposit(100.00, "USD", userTest);
        assertEquals("Depositing $100 and should have $2100", 2100.00, userTest.funds, .01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeToFile()
    {
        testerList.add(userTest);
        MainController tester = new MainController(testerList, "ThisDoesNotExist.poo");
        tester.writeToFile("ThisIsNotReal.poo");
    }
}