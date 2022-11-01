package ee.bcs.javaproject.tasks;

import java.util.Random;
import java.util.Scanner;

public class Lesson3Hard {

    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
    public static void main(String[] args){
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);

        // TODO 1 tehke lõputu while tsükkel
        // TODO 2 tsükli sees küsige scanneri abil kasutaja käes 1 täisarv (int)
        // TODO 2.5 Trüki välja täis arv (vaata, et töötab)
        // TODO 3. Kas sisestatud arv oli õige või suurem/väiksem? Trüki välja
        // TODO 4. Kui arv oli õige. Lõpeta while tsükkel (break lause või täienda while tingimust)
        // TODO 5. Loo uus muutuja näiteks int count = 0, enne while tsüklit
        // TODO 6 iga kord kui tsükkel tööle läheb suurenda counti 1 võrra
        // TODO 7 juhul kui tsükkel saab läbi (kasutaja arvas numbri ära) Trüki count välja
        Scanner scanner = new Scanner(System.in);
        int arv = scanner.nextInt();
    }
}
