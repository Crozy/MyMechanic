package com.example.paul.mymechanic;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Leuck on 23/06/2017.
 */

public class TestJava {

    private ArrayList<String> ListeGarage = new ArrayList<String>();
    private boolean DonneeRecu;

    @Test
    public void testListeGarageJHipster() throws Exception {

        Activity_GarageList LesGarage = new Activity_GarageList();

        LesGarage.appelAsynchrone();

        assertEquals(3, LesGarage.ListeDesGarages().size());
    }
}
