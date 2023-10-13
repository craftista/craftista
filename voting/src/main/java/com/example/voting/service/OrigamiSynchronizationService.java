package com.example.voting.service;

import com.example.voting.config.AppProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.voting.model.Origami;
import com.example.voting.repository.OrigamiRepository;
import java.util.Arrays;
import java.util.List;

@Service
public class OrigamiSynchronizationService {


    private final OrigamiRepository origamiRepository;
    private final RestTemplate restTemplate;

    private final String catalogueServiceUrl;

    public OrigamiSynchronizationService(OrigamiRepository origamiRepository, RestTemplate restTemplate, AppProperties appProperties) {
        this.origamiRepository = origamiRepository;
        this.restTemplate = restTemplate;
	this.catalogueServiceUrl = appProperties.getServiceUrl();
    }

    @Scheduled(fixedRate = 60000) // 1 minute = 60000 ms
    public void synchronizeOrigamis() {
        // Fetch origami data from the catalogue service
        List<Origami> origamis = fetchOrigamisFromCatalogueService();

        // Update local database
        origamiRepository.saveAll(origamis);
    }

    private List<Origami> fetchOrigamisFromCatalogueService() {
        // Fetch origamis using REST Template (Adjust Origami class properties accordingly)
        Origami[] origamis = restTemplate.getForObject(catalogueServiceUrl, Origami[].class);

        return Arrays.asList(origamis);
    }
}
