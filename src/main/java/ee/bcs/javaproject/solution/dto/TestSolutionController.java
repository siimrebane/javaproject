package ee.bcs.javaproject.solution.dto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestSolutionController {

    @GetMapping("solution/carSample")
    public ClientResponse carSample() {
        CarDto car1 = new CarDto();
        car1.setRegNr("123ABC");
        car1.setColor("black");

        CarDto car2 = new CarDto();
        car2.setRegNr("124ABC");
        car2.setColor("white");

        List<CarDto> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        ClientDto client1 = new ClientDto();
        client1.setName("John");
        client1.setAddress("USA");
        client1.setCars(carList);

        ClientDto client2 = new ClientDto();
        client2.setName("John");
        client2.setAddress("USA");

        ClientDto client3 = new ClientDto();
        client3.setName("John");
        client3.setAddress("USA");

        List<ClientDto> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);

        ClientResponse response = new ClientResponse();
        response.setClients(clients);
        return response;
    }
}
