package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.MyMechanicApp;

import com.mycompany.myapp.domain.Demande;
import com.mycompany.myapp.repository.DemandeRepository;
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
 * Test class for the DemandeResource REST controller.
 *
 * @see DemandeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyMechanicApp.class)
public class DemandeResourceIntTest {

    private static final String DEFAULT_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_UTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDE = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDE = "BBBBBBBBBB";

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDemandeMockMvc;

    private Demande demande;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DemandeResource demandeResource = new DemandeResource(demandeRepository);
        this.restDemandeMockMvc = MockMvcBuilders.standaloneSetup(demandeResource)
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
    public static Demande createEntity(EntityManager em) {
        Demande demande = new Demande()
            .utilisateur(DEFAULT_UTILISATEUR)
            .demande(DEFAULT_DEMANDE);
        return demande;
    }

    @Before
    public void initTest() {
        demande = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemande() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();

        // Create the Demande
        restDemandeMockMvc.perform(post("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande)))
            .andExpect(status().isCreated());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate + 1);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getUtilisateur()).isEqualTo(DEFAULT_UTILISATEUR);
        assertThat(testDemande.getDemande()).isEqualTo(DEFAULT_DEMANDE);
    }

    @Test
    @Transactional
    public void createDemandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();

        // Create the Demande with an existing ID
        demande.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeMockMvc.perform(post("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDemandes() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get all the demandeList
        restDemandeMockMvc.perform(get("/api/demandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demande.getId().intValue())))
            .andExpect(jsonPath("$.[*].utilisateur").value(hasItem(DEFAULT_UTILISATEUR.toString())))
            .andExpect(jsonPath("$.[*].demande").value(hasItem(DEFAULT_DEMANDE.toString())));
    }

    @Test
    @Transactional
    public void getDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", demande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demande.getId().intValue()))
            .andExpect(jsonPath("$.utilisateur").value(DEFAULT_UTILISATEUR.toString()))
            .andExpect(jsonPath("$.demande").value(DEFAULT_DEMANDE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemande() throws Exception {
        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);
        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Update the demande
        Demande updatedDemande = demandeRepository.findOne(demande.getId());
        updatedDemande
            .utilisateur(UPDATED_UTILISATEUR)
            .demande(UPDATED_DEMANDE);

        restDemandeMockMvc.perform(put("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemande)))
            .andExpect(status().isOk());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getUtilisateur()).isEqualTo(UPDATED_UTILISATEUR);
        assertThat(testDemande.getDemande()).isEqualTo(UPDATED_DEMANDE);
    }

    @Test
    @Transactional
    public void updateNonExistingDemande() throws Exception {
        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Create the Demande

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDemandeMockMvc.perform(put("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande)))
            .andExpect(status().isCreated());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);
        int databaseSizeBeforeDelete = demandeRepository.findAll().size();

        // Get the demande
        restDemandeMockMvc.perform(delete("/api/demandes/{id}", demande.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demande.class);
        Demande demande1 = new Demande();
        demande1.setId(1L);
        Demande demande2 = new Demande();
        demande2.setId(demande1.getId());
        assertThat(demande1).isEqualTo(demande2);
        demande2.setId(2L);
        assertThat(demande1).isNotEqualTo(demande2);
        demande1.setId(null);
        assertThat(demande1).isNotEqualTo(demande2);
    }
}
