import java.util.Scanner;
public class HomeWork2 {
    public static void main(String[] args) {
        System.out.println("hello world");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите свое имя: ");
        String userName = scanner.next();
        System.out.println("Hello: " + userName);

        System.out.println("Введите число:");
        int degC = scanner.nextInt();
        float degF;
        degF = (float)((degC * 1.8) + 32);
        System.out.println(degF);

    }
}
