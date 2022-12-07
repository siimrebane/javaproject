package ee.bcs.javaproject.solution;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class Lesson3HardSolutionController {

    Random random = new Random();
    int randomNumber = random.nextInt(100);
    int count = 0;


    // http://localhost:8080/lesson3hard/number?number
    @GetMapping("solution/lesson3hard/number")
    public String number(@RequestParam("number") int number) {
        count++;
        if (number > randomNumber) {
            return "Sisestasid liiga suure arvu";
        } else if (number < randomNumber) {
            return "Sisestasid liiga väikse arvu";
        } else {
            randomNumber = random.nextInt(100);
            String message = "Arvasid ära " + count + " korraga";
            count = 0;
            return message;
        }
    }
}
