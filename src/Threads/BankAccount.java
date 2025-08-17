package Threads;

class MainAccount {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1, 500);
        BankAccount account2 = new BankAccount(2, 500);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                BankAccount.transfer(account1, account2, 10);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                BankAccount.transfer(account2, account1, 10);
            }
        });

        t1.start();
        t2.start();
    }
}

public class BankAccount {
    private final int id;
    private int balance;

    public BankAccount(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public static void transfer(BankAccount from, BankAccount to, int amount) {
        // TODO: Add logic to lock accounts in reverse ID order (higher ID first).
        BankAccount first = from.id > to.id ? from : to;
        BankAccount second = from.id > to.id ? to : from;
        synchronized (first) {

            try {
                Thread.sleep(100);  // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (second) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    System.out.println("Transferred " + amount + " from Account " + from.getId() + " to Account " + to.getId());
                } else {
                    System.out.println("Insufficient funds in Account " + from.getId());
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (balance < amount) return false;
        balance -= amount;
        return true;
    }
}