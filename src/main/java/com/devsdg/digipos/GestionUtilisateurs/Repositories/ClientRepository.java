package com.devsdg.digipos.GestionUtilisateurs.Repositories;

import com.devsdg.digipos.GestionUtilisateurs.Models.ClientPOS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientPOS, Long> {
}
