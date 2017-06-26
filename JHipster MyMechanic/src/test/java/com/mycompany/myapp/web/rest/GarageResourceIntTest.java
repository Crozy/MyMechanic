package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MyMechanicApp;

import com.mycompany.myapp.domain.Garage;
import com.mycompany.myapp.repository.GarageRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GarageResource REST controller.
 *
 * @see GarageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyMechanicApp.class)
public class GarageResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final Float DEFAULT_LONGITUDE = 1F;
    private static final Float UPDATED_LONGITUDE = 2F;

    private static final Float DEFAULT_LATITUDE = 1F;
    private static final Float UPDATED_LATITUDE = 2F;

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGarageMockMvc;

    private Garage garage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GarageResource garageResource = new GarageResource(garageRepository);
        this.restGarageMockMvc = MockMvcBuilders.standaloneSetup(garageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Garage createEntity(EntityManager em) {
        Garage garage = new Garage()
            .nom(DEFAULT_NOM)
            .adresse(DEFAULT_ADRESSE)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE);
        return garage;
    }

    @Before
    public void initTest() {
        garage = createEntity(em);
    }

    @Test
    @Transactional
    public void createGarage() throws Exception {
        int databaseSizeBeforeCreate = garageRepository.findAll().size();

        // Create the Garage
        restGarageMockMvc.perform(post("/api/garages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garage)))
            .andExpect(status().isCreated());

        // Validate the Garage in the database
        List<Garage> garageList = garageRepository.findAll();
        assertThat(garageList).hasSize(databaseSizeBeforeCreate + 1);
        Garage testGarage = garageList.get(garageList.size() - 1);
        assertThat(testGarage.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testGarage.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testGarage.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testGarage.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
    }

    @Test
    @Transactional
    public void createGarageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = garageRepository.findAll().size();

        // Create the Garage with an existing ID
        garage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGarageMockMvc.perform(post("/api/garages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garage)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Garage> garageList = garageRepository.findAll();
        assertThat(garageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGarages() throws Exception {
        // Initialize the database
        garageRepository.saveAndFlush(garage);

        // Get all the garageList
        restGarageMockMvc.perform(get("/api/garages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(garage.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())));
    }

    @Test
    @Transactional
    public void getGarage() throws Exception {
        // Initialize the database
        garageRepository.saveAndFlush(garage);

        // Get the garage
        restGarageMockMvc.perform(get("/api/garages/{id}", garage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(garage.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGarage() throws Exception {
        // Get the garage
        restGarageMockMvc.perform(get("/api/garages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGarage() throws Exception {
        // Initialize the database
        garageRepository.saveAndFlush(garage);
        int databaseSizeBeforeUpdate = garageRepository.findAll().size();

        // Update the garage
        Garage updatedGarage = garageRepository.findOne(garage.getId());
        updatedGarage
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE);

        restGarageMockMvc.perform(put("/api/garages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGarage)))
            .andExpect(status().isOk());

        // Validate the Garage in the database
        List<Garage> garageList = garageRepository.findAll();
        assertThat(garageList).hasSize(databaseSizeBeforeUpdate);
        Garage testGarage = garageList.get(garageList.size() - 1);
        assertThat(testGarage.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testGarage.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testGarage.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testGarage.getLatitude()).isEqualTo(UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingGarage() throws Exception {
        int databaseSizeBeforeUpdate = garageRepository.findAll().size();

        // Create the Garage

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGarageMockMvc.perform(put("/api/garages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garage)))
            .andExpect(status().isCreated());

        // Validate the Garage in the database
        List<Garage> garageList = garageRepository.findAll();
        assertThat(garageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGarage() throws Exception {
        // Initialize the database
        garageRepository.saveAndFlush(garage);
        int databaseSizeBeforeDelete = garageRepository.findAll().size();

        // Get the garage
        restGarageMockMvc.perform(delete("/api/garages/{id}", garage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Garage> garageList = garageRepository.findAll();
        assertThat(garageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Garage.class);
        Garage garage1 = new Garage();
        garage1.setId(1L);
        Garage garage2 = new Garage();
        garage2.setId(garage1.getId());
        assertThat(garage1).isEqualTo(garage2);
        garage2.setId(2L);
        assertThat(garage1).isNotEqualTo(garage2);
        garage1.setId(null);
        assertThat(garage1).isNotEqualTo(garage2);
    }
}
