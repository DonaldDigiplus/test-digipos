package com.devsdg.digipos.GestionCatalogueCategorie.Metiers;

import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CatalogueProduitDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ShareMetier {
    boolean uploadXLSFile(MultipartFile multipartFile);
    CatalogueProduitDTO uploadImageFile(MultipartFile multipartFile);
}
