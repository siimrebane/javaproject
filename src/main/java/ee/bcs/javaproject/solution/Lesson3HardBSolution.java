package ee.bcs.javaproject.solution;

import java.util.Scanner;

public class Lesson3HardBSolution {

    // TODO kirjuta mäng kus kasutaja peab ära arvama numbri 0-99 (nagu 3Hard)
    // NB programm ei tohi kohe alguses välja mõelda numbrit
    // vaid eesmärk on öelda kasutajale, et ta eksis nii kaua kui võimalik
    // ilma selleta, et ta läheks vastuollu ühegi varasema väitega
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = 0; // väikseim lubatud väärtus
        int max = 99; // suurim lubatud väärtus

        int count = 0;
        while (true) {
            count++;
            System.out.println("Siseta number");
            int arv = scanner.nextInt();
            int smallerNrCount = nrCount(min, arv -1);
            int biggerNrCount = nrCount(arv + 1, max);
            System.out.println(min + " " + max + " " + smallerNrCount + " " + biggerNrCount);
            if(min == max && arv == min){
                System.out.println("Arvasid ära " + count + " korraga");
                break;
            } else if(smallerNrCount >= biggerNrCount){
                System.out.println("Sisestasid liiga suure arvu");
                if(arv < max){
                    max = arv - 1;
                }
            } else {
                System.out.println("Sisestasid liiga väikse arvu");
                if(arv > min){
                    min = arv + 1;
                }
            }
            System.out.println(min + " " + max + " " + smallerNrCount + " " + biggerNrCount);
        }
    }

    private static int nrCount(int min, int i) {
        return i - min + 1;
    }
}
