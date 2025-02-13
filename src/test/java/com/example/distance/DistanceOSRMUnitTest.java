package com.example.distance;

import com.example.distance.Service.OSRMClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class DistanceOSRMUnitTest {
    /**
     * Test "qui fonctionne" : on vérifie qu'entre Paris et Rome,
     * la distance renvoyée par l'API est supérieure à 0
     */
    @Test
    void testGetDistanceOk() {
        OSRMClientService osrmClientService = new OSRMClientService();
        // Paris (48.8566, 2.3522)
        // Rome  (41.9028, 12.4964)
        Double distance = osrmClientService.getDistance(
                48.8566, 2.3522,
                41.9028, 12.4964
        );

        // On s'attend à une distance significative entre ces deux villes
        Assertions.assertTrue(distance > 0,
                "La distance entre Paris et Rome devrait être > 0");
    }

    /**
     * Test "qui échoue" : provoque une erreur en passant une latitude incomplète (valeur hors limites).
     */
    @Test
    void testGetDistanceWithInvalidLatitude() {
        OSRMClientService osrmClientService = new OSRMClientService();

        // Latitude invalide (-9999 est en dehors des limites géographiques)
        Double invalidLatitude = -9999.0;

        // Vérifie qu'une exception est levée
        Assertions.assertThrows(Exception.class, () -> {
            osrmClientService.getDistance(
                    invalidLatitude, 2.3522, // Latitude invalide
                    41.9028, 12.4964
            );
        }, "Un appel avec une latitude invalide devrait lever une exception");
    }
}