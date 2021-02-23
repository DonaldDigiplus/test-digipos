package com.devsdg.digipos.GestionUtilisateurs.Repositories;

import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    /* Rechercher un utilisateur  par username, email ou phone selon le parametre entre
     *  Cette fonction est utile a la connexion */
    @Query("select u from AppUser u where lower(u.username) = :login or lower(u.email) = :login or u.phone =: login")
    AppUser findByUsernameOrEmailOrPhone(String login);

    @Query("SELECT p FROM AppUser p WHERE p.staff=true AND CONCAT(p.nomcomplet, p.username, p.email, p.phone) LIKE %?1%")
    List<AppUser> findAllStaffByKeyWord(String keyword);

    @Query("SELECT p FROM AppUser p WHERE p.client=true AND CONCAT(p.nomcomplet, p.username, p.email, p.phone) LIKE %?1%")
    List<AppUser> findAllClientByKeyWord(String keyword);

    @Query("select q.nomcomplet from Proprietaire q order by q.date desc")
    List<String> findAllProprietaireName();

    Page<AppUser> findAllByStaffIsTrue(Pageable pageable);
    List<AppUser> findAllByUsernameLikeAndStaffIsTrue(String username);
}
