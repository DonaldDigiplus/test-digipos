package com.devsdg.digipos.GestionBoutiques.Repository;

import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {
    Boutique findByNomBoutique(String nomBoutique);
    Boutique findByNomBoutiqueAndActiveboutiqueIsTrue(String nomBoutique);
    Boutique findByEmailBoutique(String emailBoutique);
    Boutique findByTelephoneBoutique(String phoneBoutique);
    Boutique findByLien(String lien);
    Page<Boutique> findAllByActiveboutiqueIsTrue(Pageable pageable);

    @Query("select q.nomBoutique from Boutique q order by q.dateLastModification desc")
    List<String> findAllBoutiqueName();

    @Query("select q.nomBoutique from Boutique q where q.activeboutique = true order by q.dateLastModification desc")
    List<String> findAllBoutiqueNameIsActive();
}
