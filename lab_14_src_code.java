//task 1---------------------------------------------------------
public class MultithreadingExample {
    public static void main(String[] args) {
       
        Thread thread_1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Number: " + i);
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread_2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Square: " + (i * i));
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}


//task 2-----------------------------------------------------
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class ThreadSynchronization {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Runnable incrementTask = () -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        };
        Thread thread_1 = new Thread(incrementTask);
        Thread thread_2 = new Thread(incrementTask);
        Thread thread_3 = new Thread(incrementTask);

        // Start threads
        thread_1.start();
        thread_2.start();
        thread_3.start();

        try {
            // Wait for all threads to finish
            thread_1.join();
            thread_2.join();
            thread_3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final count should be 300
        System.out.println("Final counter value: " + counter.getCount());
    }
}

// Task 3------------------------------------------------------
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentDataStructures {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Runnable write_task = () -> {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println("Added: " + i);
            }
        };

        // Create a thread that reads elements from the list
        Runnable read_task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Read: " + list.get(i));
            }
        };

        // Create multiple threads
        Thread writer_1 = new Thread(writerTask);
        Thread writer_2 = new Thread(writerTask);
        Thread reader = new Thread(readerTask);

        // Start threads
        writer_1.start();
        writer_2.start();
        reader.start();

        try {
            // Wait for all threads to finish
            writer_1.join();
            writer_2.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


//task 4-------------------------------------------------------
import java.util.concurrent.atomic.AtomicInteger;

class BankAccount {
    private AtomicInteger balance = new AtomicInteger(1000); 
    public void deposit(int amount) {
        balance.addAndGet(amount);
    }
    public void withdraw(int amount) {
        balance.addAndGet(-amount);
    }
    public int getBalance() {
        return balance.get();
    }
}

public class BankTransactionSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Runnable clientTask = () -> {
            for (int i = 0; i < 10; i++) {
                if (Math.random() > 0.5) {
                    account.deposit((int) (Math.random() * 100));
                    System.out.println("Deposited. Current balance: " + account.getBalance());
                } else {
                    account.withdraw((int) (Math.random() * 100));
                    System.out.println("Withdrew. Current balance: " + account.getBalance());
                }
                try {
                    Thread.sleep(50); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread client1 = new Thread(clientTask);
        Thread client2 = new Thread(clientTask);
        Thread client3 = new Thread(clientTask);

        // Start threads
        client1.start();
        client2.start();
        client3.start();

        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final account balance: " + account.getBalance());
    }
}
