package ma.emsi.tpgraphqlmutation.Controller;

import ma.emsi.tpgraphqlmutation.DTO.EtudiantDTO;
import ma.emsi.tpgraphqlmutation.Entities.Centre;
import ma.emsi.tpgraphqlmutation.Entities.Etudiant;
import ma.emsi.tpgraphqlmutation.Repository.CentreRepository;
import ma.emsi.tpgraphqlmutation.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;



@Controller
public class EtudiantController {
    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    // Queries
    @QueryMapping
    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    @QueryMapping
    public List<Etudiant> listEtudiants() {
        return etudiantRepository.findAll();
    }

    @QueryMapping
    public Centre getCentre(@Argument Long id) {
        return centreRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Le centre %s n'existe pas !", id))
        );
    }

    @QueryMapping
    public Etudiant getEtudiant(@Argument Long id) {
        return etudiantRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("L'étudiant %s n'existe pas !", id))
        );
    }

    // Mutations
    @MutationMapping
    public String deleteEtudiant(@Argument Long id) {
        if (etudiantRepository.findById(id).isPresent()) {
            etudiantRepository.deleteById(id);
            return String.format("L'étudiant %s a été supprimé avec succès !", id);
        }
        return String.format("L'étudiant %s n'existe pas !", id);
    }

    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant) {
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);
        if (centre != null) {
            Etudiant et = new Etudiant();
            et.setNom(etudiant.nom());
            et.setPrenom(etudiant.prenom());
            et.setGenre(etudiant.genre());
            et.setCentre(centre);
            return etudiantRepository.save(et);
        }
        return null;
    }

    @MutationMapping
    public Centre addCentre(@Argument String nom, @Argument String adresse) {
        Centre centre = new Centre();
        centre.setNom(nom);
        centre.setAdresse(adresse);
        return centreRepository.save(centre);
    }

    @MutationMapping
    public Centre updateCentre(@Argument Long id, @Argument String nom, @Argument String adresse) {
        Centre centre = centreRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Le centre %s n'existe pas !", id))
        );
        centre.setNom(nom);
        centre.setAdresse(adresse);
        return centreRepository.save(centre);
    }

    @MutationMapping
    public String deleteCentre(@Argument Long id) {
        if (centreRepository.existsById(id)) {
            centreRepository.deleteById(id);
            return String.format("Le centre %s a été supprimé avec succès !", id);
        }
        return String.format("Le centre %s n'existe pas !", id);
    }
}
