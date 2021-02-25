package com.devsdg.digipos.GestionProduits.Repository;

import com.devsdg.digipos.GestionProduits.Models.ImageProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageProduit, Long> {
}
