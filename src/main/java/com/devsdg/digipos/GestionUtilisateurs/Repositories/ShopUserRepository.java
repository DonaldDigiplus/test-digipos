package com.devsdg.digipos.GestionUtilisateurs.Repositories;

import com.devsdg.digipos.GestionUtilisateurs.Models.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {
}
