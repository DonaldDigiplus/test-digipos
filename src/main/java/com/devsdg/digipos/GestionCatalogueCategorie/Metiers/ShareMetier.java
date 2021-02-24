package com.devsdg.digipos.GestionCatalogueCategorie.Metiers;

import org.springframework.web.multipart.MultipartFile;

public interface ShareMetier {
    boolean uploadXLSFile(MultipartFile multipartFile);
}
