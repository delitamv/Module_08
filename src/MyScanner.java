import java.util.Scanner;

public class MyScanner {
    private int peopleCount;

    public int getPeopleCount() {
        return peopleCount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    private int maxAmount;

    public MyScanner() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of visitors: ");
        peopleCount = sc.nextInt();
        System.out.print("Enter library capacity: ");
        maxAmount = sc.nextInt();
    }
}
