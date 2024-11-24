package ma.emsi.tpgraphqlmutation.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Centre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nom;
    String adresse;
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL)
    List<Etudiant> listEtudiants;
}