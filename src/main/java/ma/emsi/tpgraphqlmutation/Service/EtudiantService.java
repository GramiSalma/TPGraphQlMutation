package ma.emsi.tpgraphqlmutation.Service;

import ma.emsi.tpgraphqlmutation.DTO.EtudiantDTO;
import ma.emsi.tpgraphqlmutation.Entities.Centre;
import ma.emsi.tpgraphqlmutation.Entities.Etudiant;
import ma.emsi.tpgraphqlmutation.Repository.CentreRepository;
import ma.emsi.tpgraphqlmutation.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    CentreRepository centreRepository;
    //couler

    public List<Etudiant> getStudents() {
        return etudiantRepository.findAll();
    }
    public Etudiant addEtudiant(EtudiantDTO etudiant) {
        Centre centre=centreRepository.findById(etudiant.centreId()).orElse(null);
        if(centre!=null){
            Etudiant et=new Etudiant();
            et.setNom(etudiant.nom());
            et.setPrenom(etudiant.prenom());
            et.setGenre(etudiant.genre());
            et.setCentre(centre);

            etudiantRepository.save(et);
            return et;
        }
        return null;
    }

public Etudiant updateEtudiant(Long id, EtudiantDTO etudiant){
    Centre centre=centreRepository.findById(etudiant.centreId()).orElse(null);
    if(centre!=null)
        if(etudiantRepository.findById(id).isPresent()){
            Etudiant et=etudiantRepository.findById(id).get();
            et.setNom(etudiant.nom());
            et.setPrenom(etudiant.prenom());
            et.setGenre(etudiant.genre());
            et.setCentre(centre);
            return etudiantRepository.save(et);
        }
    return null;
}

public String deleteEtudiant(Long id){
    if(etudiantRepository.findById(id).isPresent()){
        etudiantRepository.deleteById(id);
        return String.format("l'étudiant %s est bien supprimé !",id);
    }
    return String.format("l'étudiant %s n'existe pas !",id);
}


public Etudiant getEtudiant(Long id){
    return etudiantRepository.findById(id).orElse(null);}

}