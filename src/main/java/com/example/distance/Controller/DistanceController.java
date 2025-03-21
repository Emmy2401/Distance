package com.example.distance.Controller;

import com.example.distance.DTO.DistanceRequestDTO;
import com.example.distance.Service.OSRMClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// Contrôleur REST pour gérer les requêtes liées aux distances
@RestController
@RequestMapping("/api/distance") // URL de base pour toutes les routes du contrôleur
public class DistanceController {
    @Autowired
    private OSRMClientService osrmClientService; // Injection du service OSRMClient

    /**
     * Endpoint pour calculer la distance routière entre deux points.
     *
     * @param request DTO contenant les coordonnées des deux points.
     * @return La distance en mètres entre les deux points.
     */
    @PostMapping
    @Operation(summary = "getDistance", description = "permet de faire des calcul de distance entre 2 coord geographique la1long1 et lat1long2")
    public Double getDistance(@RequestBody DistanceRequestDTO request) {
        return osrmClientService.getDistance(
                request.getLatitudeFrom(),
                request.getLongitudeFrom(),
                request.getLatitudeTo(),
                request.getLongitudeTo()

        );
    }
}
