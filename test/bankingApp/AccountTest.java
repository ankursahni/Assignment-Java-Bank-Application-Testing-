package bankingApp;

import org.junit.Before;
import org.junit.Test;

import bankingApp.Account;
import bankingApp.AccountDoesNotExistException;
import bankingApp.Bank;
import bankingApp.Currency;
import bankingApp.Money;

import static org.junit.Assert.*;

public class AccountTest {
	protected Currency CAD, HKD;
	protected Bank TD;
	protected Bank HSBC;
	protected Bank RBC;
	protected Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		
		// setup test currencies
		CAD = new Currency("CAD", 0.75);
		RBC = new Bank("Royal Bank of Canada", CAD);
		RBC.openAccount("Piyush");
		// setup test accounts
		testAccount = new Account("Raghav",CAD);
		testAccount.deposit(new Money(100,CAD));
                
		// setup an initial deposit
		RBC.deposit("Piyush", new Money(500,CAD));
       
	}
        
	@Test
	public void testAddWithdraw() {
		// Something to consider - can you withdraw in a different currency?
		try {
			RBC.withdraw("Raghav",new Money(50, CAD));
                        assertEquals(50,testAccount.getBalance().getAmount(),2);
		} 
                catch (AccountDoesNotExistException e) 
                {
			//System.out.println(e);
                    e.printStackTrace();
		}
                //Money balance = testAccount.getBalance();
                //assertEquals(50.0, balance.getAmount(),0);
                //assertEquals(75.0, balance.getAmount(),0);
		
	}
	
	@Test
	public void testGetBalance() {
            Money balance = testAccount.getBalance();
		assertEquals(100, balance.getAmount(),2);
	}
}
