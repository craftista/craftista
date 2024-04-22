package com.example.voting.service;

import com.example.voting.config.AppProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.voting.model.Origami;
import com.example.voting.repository.OrigamiRepository;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.web.client.RestClientException;
import java.util.Collections;

@Service
public class OrigamiSynchronizationService {

    private static final Logger log = LoggerFactory.getLogger(OrigamiSynchronizationService.class);

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
        try {
            List<Origami> origamis = fetchOrigamisFromCatalogueService();
            if (!origamis.isEmpty()) {
                origamiRepository.saveAll(origamis);
            } else {
                log.info("No origamis fetched, skipping database update.");
            }
        } catch (Exception e) {
            log.error("Error during synchronization of origamis: " + e.getMessage(), e);
        }
    }

    private List<Origami> fetchOrigamisFromCatalogueService() {

        try{
    	    // Fetch origamis using REST Template (Adjust Origami class properties accordingly)
            Origami[] origamis = restTemplate.getForObject(catalogueServiceUrl, Origami[].class);
            return Arrays.asList(origamis);
	} catch (RestClientException e) {
            // Log the exception and handle it appropriately
            log.error("Failed to fetch origamis from Catalogue Service: " + e.getMessage(), e);
            // Return an empty list or any other appropriate response
            return Collections.emptyList();
        }  
    }
}
