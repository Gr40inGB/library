package org.gr40in.library.provider;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.gr40in.library.dao.Client;
import org.gr40in.library.dao.Client;
import org.gr40in.library.repository.ClientRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientProvider {
    private final String BASE_URI = "http://host.docker.internal:8890/api/client";
    private final ClientRepository clientRepository;

    RestClient restClient = RestClient.create();

    public List<Client> getClients() {
        List<Client> clients = restClient.get()
                .uri(BASE_URI)
                .retrieve()
                .body(ParameterizedTypeReference.forType(TypeUtils.parameterize(List.class, Client.class)));

        assert clients != null;
        clientRepository.saveAll(clients);
        return clients;
    }

    public Client getClientById(Long id) {
        Client client = restClient.get()
                .uri(BASE_URI + "/" + id)
                .retrieve()
                .body(Client.class);
        if (client == null) throw new NoSuchElementException();
        clientRepository.save(client);
        return client;
    }

}
