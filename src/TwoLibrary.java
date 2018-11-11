import java.util.Random;
import java.util.concurrent.Semaphore;

public class TwoLibrary {
    private int peopleCount;
    private int maxAmount;
    private static Semaphore semaphore;
    private static Semaphore door = new Semaphore(1, true);

    public TwoLibrary(int peopleCount, int maxAmount) {
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
        TwoLibrary twoLibrary = new TwoLibrary(peopleCount, maxAmount);
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
            System.out.printf("Человек №%d подошел к двери библиотеки \n", peopleNumber);

            try {
                if(semaphore.availablePermits() == 0) {
                    System.out.printf("Человек №%d ожидает возле двери библиотеки \n", peopleNumber);
                }
                semaphore.acquire();

            System.out.printf("Человек №%d подошел к двери с улицы \n", peopleNumber);

            if(door.availablePermits() == 0) {
                System.out.printf("Человек №%d ожидает возле двери с улицы \n", peopleNumber);
            }
                door.acquire();

            System.out.printf("Человек №%d проходит через дверь внутрь \n", peopleNumber);
                Thread.sleep(500);
            System.out.printf("Человек №%d прошел через дверь внутрь \n", peopleNumber);
            door.release();

            System.out.printf("Человек №%d вошел в библиотеку \n", peopleNumber);

            System.out.printf("Человек №%d читает книгу \n", peopleNumber);
            Random random = new Random();

                Thread.sleep(random.nextInt(7000) + 2000);

            System.out.printf("Человек №%d подошел к двери изнутри \n", peopleNumber);

            if(door.availablePermits() == 0) {
                System.out.printf("Человек №%d ожидает возле двери изнутри \n", peopleNumber);
            }

                door.acquire();

            System.out.printf("Человек №%d проходит через дверь наружу \n", peopleNumber);

                Thread.sleep(500);

            System.out.printf("Человек №%d прошел через дверь наружу \n", peopleNumber);
            door.release();

            semaphore.release();
            System.out.printf("Человек №%d вышел с библиотеки \n", peopleNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}
