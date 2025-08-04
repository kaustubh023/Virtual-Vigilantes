import java.util.Scanner;

class UserAcc {
    private int pin;
    private int balance;

    public UserAcc(int pin, int balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public boolean correctPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        }
    }

    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return false;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
            return true;
        }
    }
}
 class Atm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserAcc user = new UserAcc(1234, 20000);

        System.out.println("Welcome to the ATM");

        // Allow up to 3 PIN attempts
        int attempts = 0;
        boolean isVerified = false;

        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = sc.nextInt();

            if (user.correctPin(enteredPin)) {
                isVerified = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
            }
        }

        if (!isVerified) {
            System.out.println("Too many failed attempts. Access denied.");
            return;
        }

        System.out.println("PIN verified successfully.");

        int choice;
        do {
            System.out.println("\nATM Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: " + user.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = sc.nextInt();
                    user.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = sc.nextInt();
                    user.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

        } while (choice != 4);

        sc.close();
    }
}
