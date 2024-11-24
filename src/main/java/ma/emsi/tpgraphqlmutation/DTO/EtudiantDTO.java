package ma.emsi.tpgraphqlmutation.DTO;


import ma.emsi.tpgraphqlmutation.Entities.Genre;

public record EtudiantDTO (
        String nom,
        String prenom,
        Genre genre,
        Long centreId
){ }