package com.revature.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.OverBalanceException;
import com.revature.pojos.Account;
import com.revature.pojos.User;
import com.revature.service.AccountService;
import com.revature.service.AccountTypeService;
import com.revature.service.UserService;

public class App {

	public static UserService userService = new UserService();
	public static AccountService accountService = new AccountService();
	public static AccountTypeService accountTypeService = new AccountTypeService();
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		run();
	}

	static void run() {
		User user;
		user = askIfNew();
		boolean looping = true;
		while (looping) {
			System.out.println("press 1 to make a new account and 2 to access an existing one");
			int newAccount = 0;
			try {
				newAccount = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				scan.nextLine();
				continue;
			}

			if (newAccount == 1) {
				createAccount(user);
			} else if (newAccount == 2) {
				Account selectedAccount = null;
				selectedAccount = selectAccount(user);
				if (selectedAccount == null) {
					System.out.println("You have no accounts to select");
					continue;
				}

				boolean selectingWOrD = true;
				while (selectingWOrD) {
					try {
						int choice = giveUserOptions(selectedAccount);
						if (choice == 1) {
							withdraw(selectedAccount);
							selectingWOrD = false;
						} else if (choice == 2) {
							deposit(selectedAccount);
							selectingWOrD = false;
						} else {
							System.out.println("you did not enter 1 or 2");
						}

					} catch (InputMismatchException e) {

					}
				}
			} else {
				System.out.println("You did not enter 1 or 2");
				continue;
			}
			System.out.println("Press 1 to stay. Press 2 to logout");

			boolean reading = true;
			while (reading) {
				try {
					int stay = scan.nextInt();
					scan.nextLine();
					if (stay == 1) {
						break;
					}
					if (stay == 2) {
						looping = false;
						System.out.println("Goodbye");
						break;
					} else {
						System.out.println("You didn't enter a 2 or a 1");
					}
				} catch (InputMismatchException e) {
					scan.nextLine();
					System.out.println("You didn't enter a 2 or a 1");
				}
			}

		}
	}

	private static void createAccount(User user) {

		boolean looping = true;
		while (looping) {
			System.out.println("Would you like to make a " + "\n 1)Credit" + "\n 2)Checking" + "\n 3)Savings");
			int choice = 0;
			try {
				choice = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				scan.nextLine();
				continue;
			}
			switch (choice) {
			case 1:
				accountService.createAccount(1, user.getUserID());
				looping = false;
				break;
			case 2:
				accountService.createAccount(2, user.getUserID());
				looping = false;
				break;
			case 3:
				accountService.createAccount(3, user.getUserID());
				looping = false;
				break;
			default:
				System.out.println("You didn't input 1, 2, or 3");
			}
		}
	}

	static User askIfNew() {
		boolean looping = true;
		User user = new User();
		while (looping) {
			System.out.println(
					"Welcome to The Bank of Barney" + "\n Please select an option" + "\n 1) New User" + "\n 2) Login");

			try {
				int option = scan.nextInt();
				scan.nextLine();
				switch (option) {
				case 1:
					return createNewUser();
				case 2:
					return login();
				default:
					System.out.println("Please enter 1 or 2");
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("Please enter a 1 or 2");
			} catch(Exception e) {
				scan.nextLine();
				System.out.println("Please enter a 1 or 2");
			}
		}
		return user;
	}

	private static User login() {
		User user = null;
		String userName = "";
		String password = "";
		while (user == null) {
			try {
				System.out.println("Login");
				System.out.println("Please enter your username");
				userName = scan.nextLine();
				System.out.println("Please enter your password");
				password = scan.nextLine();
			} catch (Exception e) {

			}
			user = userService.loginUser(userName, password);
			if (user == null) {
				System.out.println("Incorrect username or password");
			}
		}
		System.out.println("Welcome " + user.getUserName());
		return user;
	}

	private static Account selectAccount(User user) {
		boolean hasAccount = false;
		System.out.println("Select which account you'd like to access by it's id");
		List<Account> accounts = accountService.getUserAccounts(user);
		for (Account account : accounts) {
			if (account.getUserID() == user.getUserID()) {
				hasAccount = true;
				System.out.println("id) " + account.getAccountID() + " balance " + account.getBalance() + " balance "
						+ accountTypeService.getNameFromID(account.getTypeID()));
			}
		}
		if (!hasAccount) {
			return null;
		}
		while (true) {
			try {
				int choice = scan.nextInt();
				scan.nextLine();
				for (Account account : accounts) {
					if (account.getAccountID() == choice) {
						return account;
					}
				}
				System.out.println("Please enter a valid option");
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("Please enter a valid option");
			}
		}

	}

	private static int giveUserOptions(Account account) {
		System.out.println("Please select an option" + "\n 1) Withdraw" + "\n 2) Deposit");
		while (true) {
			try {
				int option = scan.nextInt();
				scan.nextLine();
				switch (option) {
				case 1:
					return 1;
				case 2:
					return 2;
				default:
					System.out.println("Please select an option" + "\n 1) Withdraw" + "\n 2) Deposit");
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("Please select an option" + "\n 1) Withdraw" + "\n 2) Deposit");
			}
		}
	}

	private static void deposit(Account account) {
		System.out.println("Please enter how much you'd like to deposit");
		while (true) {
			try {
				double amount = scan.nextDouble();
				scan.nextLine();
				if(amount + account.getBalance() > 10000000) {
					System.out.println("Sorry, but you need a premium account to store that much money");
					break;
				}
				accountService.deposit(amount, account);
				break;
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("Please enter a valid amount");
			}
		}

	}

	private static void withdraw(Account account) {
		System.out.println("Please enter how much you'd like to withdraw");
		while (true) {
			try {
				double amount = scan.nextDouble();
				scan.nextLine();
				if (amount <= account.getBalance()) {
					accountService.withdraw(amount, account);
					System.out.println("Old balance: " + (account.getBalance() + amount) + " New balance: "
							+ account.getBalance());
					break;
				} else {
					throw new OverBalanceException("You don't have enough money for that");
				}
			} catch (OverBalanceException e) {
				System.out.println(e.getMessage());
				break;
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("Please enter a number");
			}
		}

	}

	private static User createNewUser() {
		try {
			while (true) {
				System.out.println("Please enter what you want your username to be");
				String userName = scan.nextLine();
				System.out.println("Please enter what you'd like your password to be");
				String password = scan.nextLine();
				if(userName.length()>30||password.length()>30) {
					System.out.println("Your username or password was too long");
					continue;
				}
				if(userName.length()<=0||password.length()<=0) {
					System.out.println("You did not enter a username or password");
					continue;
				}
				if (userService.createUser(userName, password)) {
					break;
				}
			}
		} catch (InputMismatchException e) {

		}

		return login();
	}
}
