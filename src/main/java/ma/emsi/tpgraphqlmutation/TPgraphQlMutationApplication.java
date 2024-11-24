package ma.emsi.tpgraphqlmutation;

import ma.emsi.tpgraphqlmutation.Entities.Centre;
import ma.emsi.tpgraphqlmutation.Entities.Etudiant;
import ma.emsi.tpgraphqlmutation.Entities.Genre;
import ma.emsi.tpgraphqlmutation.Repository.CentreRepository;
import ma.emsi.tpgraphqlmutation.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TPgraphQlMutationApplication implements CommandLineRunner {
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	CentreRepository centreRepository;

	public static void main(String[] args) {
		SpringApplication.run(TPgraphQlMutationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Création et sauvegarde des centres
		Centre centre1 = Centre.builder()
				.nom("Maarif").adresse("Biranzarane").build();
		centreRepository.save(centre1);

		Centre centre2 = Centre.builder()
				.nom("Oranges").adresse("Oulfa").build();
		centreRepository.save(centre2);

		Centre centre3 = Centre.builder()
				.nom("Gauthier").adresse("Bd Massira").build();
		centreRepository.save(centre3);

		// Création et sauvegarde des étudiants
		Etudiant et1 = Etudiant.builder()
				.nom("Adnani").prenom("Brahim").genre(Genre.Homme)
				.centre(centre1).build();
		etudiantRepository.save(et1);

		Etudiant et2 = Etudiant.builder()
				.nom("El Khomssi").prenom("Fatima").genre(Genre.Femme)
				.centre(centre1).build();
		etudiantRepository.save(et2);

		Etudiant et3 = Etudiant.builder()
				.nom("Lahlou").prenom("Omar").genre(Genre.Homme)
				.centre(centre2).build();
		etudiantRepository.save(et3);

		Etudiant et4 = Etudiant.builder()
				.nom("Bennani").prenom("Amina").genre(Genre.Femme)
				.centre(centre3).build();
		etudiantRepository.save(et4);

		Etudiant et5 = Etudiant.builder()
				.nom("Bouchaib").prenom("Youssef").genre(Genre.Homme)
				.centre(centre2).build();
		etudiantRepository.save(et5);

		System.out.println("Données initiales insérées avec succès !");
	}
}



