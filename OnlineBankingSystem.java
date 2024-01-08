import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank {
    private Map<String, Double> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, initialBalance);
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account already exists.");
        }
    }

    public void viewBalance(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            double balance = accounts.get(accountNumber);
            System.out.println("Account Balance: $" + balance);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performTransaction(String accountNumber, double amount, String transactionType) {
        if (accounts.containsKey(accountNumber)) {
            double currentBalance = accounts.get(accountNumber);
            if ("deposit".equalsIgnoreCase(transactionType)) {
                currentBalance += amount;
                accounts.put(accountNumber, currentBalance);
                System.out.println("Deposit successful.");
            } else if ("withdraw".equalsIgnoreCase(transactionType)) {
                if (currentBalance >= amount) {
                    currentBalance -= amount;
                    accounts.put(accountNumber, currentBalance);
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Invalid transaction type.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}

public class OnlineBankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Account\n2. View Balance\n3. Perform Transaction\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter initial balance: $");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(accNumber, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String viewAccNumber = scanner.nextLine();
                    bank.viewBalance(viewAccNumber);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String transAccNumber = scanner.nextLine();
                    System.out.print("Enter transaction amount: $");
                    double transAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter transaction type (deposit/withdraw): ");
                    String transType = scanner.nextLine();
                    bank.performTransaction(transAccNumber, transAmount, transType);
                    break;
                case 4:
                    System.out.println("Exiting the online banking system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}