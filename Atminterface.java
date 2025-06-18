package ATMINTERFACE;

import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";

    Scanner sc = new Scanner(System.in);

    public void register() {
        System.out.print("Enter your Name: ");
        this.name = sc.nextLine();
        System.out.print("Enter your Username: ");
        this.userName = sc.nextLine();
        System.out.print("Enter your Password: ");
        this.password = sc.nextLine();
        System.out.print("Enter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("Registration Successful. Please log in to your Bank Account.");
    }

    public boolean login() {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.print("Enter your username: ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(userName)) {
                while (!isLogin) {
                    System.out.print("Enter your password: ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("Login Successful.");
                        isLogin = true;
                    } else {
                        System.out.println("Incorrect Password. Try again.");
                    }
                }
            } else {
                System.out.println("Username not found. Try again.");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.print("Enter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Clear buffer
        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("Withdrawal Successful.");
            transactionHistory += amount + " Rs Withdrawn\n";
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    public void deposit() {
        System.out.print("Enter Amount to Deposit: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Clear buffer
        if (amount <= 10000f) {
            transactions++;
            balance += amount;
            System.out.println("Deposit Successful.");
            transactionHistory += amount + " Rs Deposited\n";
        } else {
            System.out.println("Sorry. The deposit limit is 10000.");
        }
    }

    public void transfer() {
        System.out.print("Enter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter Amount to Transfer: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // Clear buffer
        if (balance >= amount) {
            if (amount <= 50000f) {
                transactions++;
                balance -= amount;
                System.out.println("Successfully Transferred to " + recipient + ".");
                transactionHistory += amount + " Rs transferred to " + recipient + "\n";
            } else {
                System.out.println("Sorry. The transfer limit is 50000.");
            }
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("No Transactions have occurred.");
        } else {
            System.out.println("Transaction History:\n" + transactionHistory);
        }
    }
}

public class Atminterface {

    public static int takenIntegerInput(int limit) {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    flag = true;
                } else {
                    System.out.println("Choose a number between 1 and " + limit);
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value.");
                sc.next(); // clear invalid input
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("********** WELCOME TO THE ATM INTERFACE **********");
        System.out.println("1. Register\n2. Exit");
        System.out.print("Choose an option: ");

        int choice = takenIntegerInput(2);
        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register();

            while (true) {
                System.out.println("\n1. Login\n2. Exit");
                System.out.print("Enter your choice: ");
                int ch = takenIntegerInput(2);

                if (ch == 1 && account.login()) {
                    System.out.println("\n********** WELCOME BACK, " + account.name + " **********");
                    boolean isFinished = false;

                    while (!isFinished) {
                        System.out.println("\n1. Withdraw\n2. Deposit\n3. Transfer\n4. Check Balance\n5. Transaction History\n6. Exit");
                        System.out.print("Enter your choice: ");
                        int action = takenIntegerInput(6);

                        switch (action) {
                            case 1:
                                account.withdraw();
                                break;
                            case 2:
                                account.deposit();
                                break;
                            case 3:
                                account.transfer();
                                break;
                            case 4:
                                account.checkBalance();
                                break;
                            case 5:
                                account.transHistory();
                                break;
                            case 6:
                                isFinished = true;
                                System.out.println("Logging out...");
                                break;
                        }
                    }
                } else {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Thank you. Exiting...");
            System.exit(0);
        }
    }
}
