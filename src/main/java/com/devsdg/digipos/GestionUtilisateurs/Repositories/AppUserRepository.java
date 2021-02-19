package com.devsdg.digipos.GestionUtilisateurs.Repositories;

import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    /* Rechercher un utilisateur  par username, email ou phone selon le parametre entre
     *  Cette fonction est utile a la connexion */
    @Query("select u from AppUser u where lower(u.username) = :login or lower(u.email) = :login or u.phone =: login")
    AppUser findByUsernameOrEmailOrPhone(String login);
    Page<AppUser> findAllByStaffIsTrue(Pageable pageable);
    @Override
    Page<AppUser> findAll(Pageable pageable);
}
