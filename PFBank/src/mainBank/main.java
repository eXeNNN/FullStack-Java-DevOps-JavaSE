// 1.1.2 Creation of main class for tests
// 1.2.3 Creation of the tablea account

package mainBank;

import components.Client;
import components.Account;
import components.CurrentAccount;
import components.SavingsAccount;
import components.Flow;
import components.Credit;
import components.Debit;
import components.Transfer;
import java.util.*;
import java.time.LocalDate;

import javax.annotation.processing.SupportedSourceVersion;

public class main {

	public static void main(String[] args) {
		
		// 1.1.2  Creation of main class for tests
		Client[] aOfClients = fillArray(3);
		printArray(aOfClients);
		
		/*-------------------------------------------------*/
		System.out.println("----------------------------------------------");
		
		// 1.2.3 Creation of the table account
		Account[] aOfAccounts = fillArray2(aOfClients);
		printArray2(aOfAccounts);
		
		/*-------------------------------------------------*/
		System.out.println("----------------------------------------------");
		
		// 1.3.1 Adaptation of the table of accounts
		Hashtable<Integer, Object> accs = adaptation(aOfAccounts);
		printHash(accs);
		
		// This is for sorting the HashTable, but I can't sort it by balance, just by Account Number
		Map<Integer, Object> accsSorted = new TreeMap<Integer, Object>(accs);
		
		printHash(accsSorted);
		
		/*-------------------------------------------------*/
		System.out.println("----------------------------------------------");
		
		Flow[] flows = loadFlows(aOfAccounts);
		
		for (int i = 0; i < flows.length; i++) {
			System.out.println(flows[i].toString());
		}
		
		//updateAccounts(flows, accsSorted);
		
		
	}
	
	/*-------------------------------------------------------------------------------*/
	
	//1.3.5 Updating accounts
	public static void updateAccounts(Flow[] f, Map<Integer, Object> m) {
		for (Flow flow : f) {
			int targetAcc = flow.getTargetAccountNumber();
			//m.get(targetAcc).setBalance(flow);
			
		}
	}
	
	// 1.3.4 Creation of the flow array
	
	//Function that given a flow array adds a Credit flow of 100.50 to all Curreent acounts and saves it on the flow array and then returns it
		public static Flow[] addCreditFlow(Flow[] arr, Account[] acc) {
			LocalDate localDate = LocalDate.now().plusDays(2);
			int currentAccountCount = 0;
			for (int i = 0; i < acc.length; i++) {
				if (acc[i].getClass().getName() == "components.CurrentAccount") {
					currentAccountCount++;
				}
			}
			Flow[] flows = new Flow[arr.length + currentAccountCount];
			for (int i = 0; i < arr.length; i++) {
				flows[i] = arr[i];
			}
			int count = arr.length;
			for (int i = 0; i < acc.length; i++) {
				if (acc[i].getClass().getName() == "components.CurrentAccount") {
					flows[count] = new Credit(100.50, acc[i].getAccNumber(), localDate);
					count++;
				}
			}
			return flows;
		}

		public static Flow[] addSavingsFlow(Flow[] arr, Account[] acc) {
			LocalDate localDate = LocalDate.now().plusDays(2);
			int savingsAccountCount = 0;
			for (int i = 0; i < acc.length; i++) {
				if (acc[i].getClass().getName() == "components.SavingsAccount") {
					savingsAccountCount++;
				}
			}
			Flow[] flows = new Flow[arr.length + savingsAccountCount];
			for (int i = 0; i < arr.length; i++) {
				flows[i] = arr[i];
			}
			int count = arr.length;
			for (int i = 0; i < acc.length; i++) {
				if (acc[i].getClass().getName() == "components.SavingsAccount") {
					flows[count] = new Credit(1500, acc[i].getAccNumber(), localDate);
					count++;
				}
			}
			return flows;
		}

		//Function that transfers 50 from account 1 to account 2
		public static Flow[] addTransferFlow(Flow[] arr, Account[] acc, int issAccount, int targetAccount, double amount) {
			LocalDate localDate = LocalDate.now().plusDays(2);
			Flow[] flows = new Flow[arr.length + 2];
			for (int i = 0; i < arr.length; i++) {
				flows[i] = arr[i];
			}
			flows[arr.length] = new Transfer(amount, targetAccount, issAccount, localDate);
			flows[arr.length + 1] = new Transfer(amount, issAccount, issAccount, localDate);
			return flows;
		}
	
	public static Flow[] loadFlows(Account[] arr) {
		LocalDate localDate = LocalDate.now().plusDays(2);
		Flow[] flows = new Flow[1];
		flows[0] = new Debit(50, 1, localDate);
		flows = addCreditFlow(flows, arr);
		flows = addSavingsFlow(flows, arr);
		flows = addTransferFlow(flows, arr, 1, 2, 50);
		return flows;
	}
	
	// 1.3.1 Adaptation of the table of accounts
	public static Hashtable<Integer, Object> adaptation(Account[] a) {
		Hashtable<Integer, Object> acc = new Hashtable<Integer, Object>();
		for (int i = 0; i < a.length; i++) {
			acc.put(a[i].getAccNumber(), a[i]);
		}
		return acc;
	}
	
	/*public static void printHash(Map<Integer, Object> h) {
		for (Integer key: h.keySet()) {
			System.out.println(key + " -> " + h.get(key));
		}
	}*/
	
	public static void printHash(Map<Integer, Object> h) {
		
	}
	
	
	// 1.1.2  Creation of main class for tests
	public static Client[] fillArray(int numberOfClients) {
		Client[] client = new Client[numberOfClients];
		for (int i = 0; i < numberOfClients; i++) {
			client[i] = new Client("name" + (i+1), "firstname" + (i+1));
		}
		return client;
	}
	
	public static void printArray(Client[] arr) {
		Arrays.stream(arr).forEach(System.out::println);
	}
	
	
	// 1.2.3 Creation of the table account
	public static Account[] fillArray2(Client[] arr) {
		Account[] accounts = new Account[arr.length*2];
		for (int i = 0; i < arr.length; i++) {
			accounts[i] = new CurrentAccount("label" + i, arr[i]);
		}
		int count = 0;
		for (int i = arr.length; i < accounts.length; i++) {
			accounts[i] = new SavingsAccount("label" + i, arr[count]);
			count++;
		}
		return accounts;
	}
	
	public static void printArray2(Account[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].toString() + " Class -> " + arr[i].getClass().getName());
		}
	}
}
