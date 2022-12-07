package ee.bcs.javaproject.solution.dto;

import java.util.List;

public class ClientResponse {
    private List<ClientDto> clients;

    public List<ClientDto> getClients() {
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }
}
