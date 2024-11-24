package ma.emsi.tpgraphqlmutation.DTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import ma.emsi.tpgraphqlmutation.Entities.Etudiant;

import java.util.List;

public record CentreDTO(
        String nom,
        String adresse,
        List<EtudiantDTO> listEtudiants
) {
}

