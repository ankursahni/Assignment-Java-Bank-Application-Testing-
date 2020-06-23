package bankingApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bankingApp.AccountDoesNotExistException;
import bankingApp.AccountExistsException;
import bankingApp.Bank;
import bankingApp.Currency;

public class BankTest {
	protected Currency CAD;
	protected Currency HKD;
	protected Bank RBC;
	protected Bank TD;
	protected Bank HSBC;
	
	
	@Before
	public void setUp() throws Exception {
		
		// setup some test currencies
		this.HKD = new Currency("HKD", 0.13);
		this.CAD = new Currency("CAD", 0.75);
               		
		// setup test banks
		this.RBC = new Bank("Royal Bank of Canada", CAD);
		this.TD = new Bank("TD Bank", CAD);
		this.HSBC = new Bank("Hong Kong Shanghai Banking Corporation", HKD);
		
		// add sample customers to the banks
		
		
		// HINT:  uncomment these lines AFTER you test the openAccount() function
		// You can quickly uncomment / comment by highlighting the lines of code and pressing 
		// CTRL + / on your keyboard  (or CMD + / for Macs)
		
		this.RBC.openAccount("Ankur");
		this.RBC.openAccount("Syed");
		this.TD.openAccount("Kshitij");
		this.HSBC.openAccount("Rajinder");
	}

	@Test
	public void testGetName() {
		assertEquals("Royal Bank of Canada", RBC.getName());
                assertEquals("TD Bank", TD.getName());
                assertEquals("Hong Kong Shanghai Banking Corporation", HSBC.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(CAD, RBC.getCurrency());
                assertEquals(CAD,TD.getCurrency());
		assertEquals(HKD,HSBC.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		try 
                {
		RBC.openAccount("Ankur");
                    System.out.println("Fails");
                }
                catch(AccountExistsException e)
                {
                    assertTrue(e.getMessage().contains("Already Exits"));
                }}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		
		try {
			//Account testAccount = new Account("Syed", HKD);
			//testAccount.deposit(new Money(100, CAD));
			
                        RBC.deposit("Ankur", new Money(100, CAD));
			assertEquals("", RBC.getBalance("Ankur"));
			fail("fail");
			
		}
		catch (Exception e){
			assertTrue(e.getMessage().contains("Account already Exits"));
		}
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		try {
                    Account testAccount = new Account("Syed", HKD);
                    testAccount.deposit(new Money(100, CAD));
                        RBC.deposit("Ankur",new Money(100, CAD));
                        
			RBC.withdraw("Ankur", new Money(50,CAD));
			
			assertEquals(" ", RBC.getBalance("Ankur"));
			fail("fail");
				
		}
		catch (AccountDoesNotExistException e){
			assertEquals("does'nt exist", e.getMessage());
		}
		
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		
		try {
			RBC.getBalance("Ankur");
			assertEquals(50.0,RBC.getBalance("Ankur"),2);
			fail("fail");
			}
			catch (AccountDoesNotExistException e){
				assertEquals(" ", e.getMessage());
			}
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// Note: You should test both types of transfers:
		// 1. Transfer from account to account
		// 2. Transfer between banks
		// See the Bank.java file for more details on Transfers
		try {
			//1. Transfer from account to account
			
			RBC.transfer("Ankur", "Syed", new Money(50, CAD));
                        //assertEquals()
                       
			// 2. Transfer between banks
			RBC.transfer("Ankur", TD, "Kshitij", new Money(50, CAD));
			fail("fail");
		}
		catch(AccountDoesNotExistException e) {
			assertEquals("Does not exist", e.getMessage());
		}
	}
	
}
