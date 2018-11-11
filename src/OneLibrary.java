import java.util.Random;
import java.util.concurrent.Semaphore;

public class OneLibrary {
    private int peopleCount;
    private int maxAmount;
    private static Semaphore semaphore;

    public OneLibrary(int peopleCount, int maxAmount) {
        this.peopleCount = peopleCount;
        this.maxAmount = maxAmount;
        semaphore = new Semaphore(maxAmount, true);
    }

    public static void main(String[] args) throws InterruptedException {
        int peopleCount;
        int maxAmount;
        MyScanner myScanner = new MyScanner();
        peopleCount = myScanner.getPeopleCount();
        maxAmount = myScanner.getMaxAmount();
        OneLibrary oneLibrary = new OneLibrary(peopleCount, maxAmount);
        for(int i = 0; i <= peopleCount; i++) {
            new Thread(new People(i)).start();
            Thread.sleep(5000);
        }
    }

    public static class People implements Runnable {
        private int peopleNumber;

        public People(int peopleNumber) {
            this.peopleNumber = peopleNumber;
        }

        @Override
        public void run() {
            System.out.printf("Человек №%d пришел ко входу в библиотеку \n ", peopleNumber);
            if(semaphore.availablePermits() == 0) {
                System.out.printf("Человек №%d ждет входа в библиотеку \n ",  peopleNumber);
            }
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Человек №%d вошел в библиотеку \n", peopleNumber);
            System.out.printf("Человек №%d читает книгу \n", peopleNumber);
            Random random = new Random();

            try {
                Thread.sleep(random.nextInt(7000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            semaphore.release();
            System.out.printf("Человек №%d вышел из библиотеки \n", peopleNumber);
        }
    }
}
