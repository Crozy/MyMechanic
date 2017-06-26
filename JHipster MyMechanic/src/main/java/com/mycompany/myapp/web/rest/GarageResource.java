package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Garage;

import com.mycompany.myapp.repository.GarageRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Garage.
 */
@RestController
@RequestMapping("/api")
public class GarageResource {

    private final Logger log = LoggerFactory.getLogger(GarageResource.class);

    private static final String ENTITY_NAME = "garage";

    private final GarageRepository garageRepository;

    public GarageResource(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    /**
     * POST  /garages : Create a new garage.
     *
     * @param garage the garage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new garage, or with status 400 (Bad Request) if the garage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/garages")
    @Timed
    public ResponseEntity<Garage> createGarage(@RequestBody Garage garage) throws URISyntaxException {
        log.debug("REST request to save Garage : {}", garage);
        if (garage.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new garage cannot already have an ID")).body(null);
        }
        Garage result = garageRepository.save(garage);
        return ResponseEntity.created(new URI("/api/garages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /garages : Updates an existing garage.
     *
     * @param garage the garage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated garage,
     * or with status 400 (Bad Request) if the garage is not valid,
     * or with status 500 (Internal Server Error) if the garage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/garages")
    @Timed
    public ResponseEntity<Garage> updateGarage(@RequestBody Garage garage) throws URISyntaxException {
        log.debug("REST request to update Garage : {}", garage);
        if (garage.getId() == null) {
            return createGarage(garage);
        }
        Garage result = garageRepository.save(garage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, garage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /garages : get all the garages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of garages in body
     */
    @GetMapping("/garages")
    @Timed
    public List<Garage> getAllGarages() {
        log.debug("REST request to get all Garages");
        return garageRepository.findAll();
    }

    /**
     * GET  /garages/:id : get the "id" garage.
     *
     * @param id the id of the garage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the garage, or with status 404 (Not Found)
     */
    @GetMapping("/garages/{id}")
    @Timed
    public ResponseEntity<Garage> getGarage(@PathVariable Long id) {
        log.debug("REST request to get Garage : {}", id);
        Garage garage = garageRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(garage));
    }

    /**
     * DELETE  /garages/:id : delete the "id" garage.
     *
     * @param id the id of the garage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/garages/{id}")
    @Timed
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        log.debug("REST request to delete Garage : {}", id);
        garageRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
