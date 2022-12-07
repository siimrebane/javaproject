package ee.bcs.javaproject.solution;

import java.util.Random;
import java.util.Scanner;

public class Lesson3HardSolution {

    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        System.out.println("Cheating: " + randomNumber);
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        while (true) {
            count++;
            System.out.println("Siseta number");
            int arv = scanner.nextInt();
            if (arv > randomNumber) {
                System.out.println("Sisestasid liiga suure arvu");
            } else if (arv < randomNumber) {
                System.out.println("Sisestasid liiga väikse arvu");
            } else {
                System.out.println("Arvasid ära " + count + " korraga");
                break;
            }
        }
    }
}
