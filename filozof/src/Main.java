import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ile_filozofow = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj liczbe filozofow: ");
        while (ile_filozofow < 2 || ile_filozofow > 100) {
            ile_filozofow = scan.nextInt();
        }
        System.out.println("Wybierz jeden z 3 wariantow: ");
        int temp = scan.nextInt();
        switch (temp) {
            case 1: {
                for (int i = 0; i < ile_filozofow; i++) {
                    new f1(i, ile_filozofow).start();
                }
            }
                break;
                case 2: {
                    for (int i = 0; i < ile_filozofow; i++)
                        new f2(i, ile_filozofow).start();
                }
                break;
                case 3: {
                    for (int i = 0; i < ile_filozofow; i++)
                        new f3(i, ile_filozofow).start();
                }
                break;


        }
    }
}
