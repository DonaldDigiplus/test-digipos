package com.devsdg.digipos.Commons.SharedRessources.Repository;

import com.devsdg.digipos.Commons.SharedRessources.Models.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
