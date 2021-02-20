package com.devsdg.digipos.GestionUtilisateurs.Repositories;

import com.devsdg.digipos.GestionUtilisateurs.Models.Vendeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendeurRepository extends JpaRepository<Vendeur, Long> {
    Page<Vendeur> findAllByAtiveuserIsTrue(Pageable pageable);
}
