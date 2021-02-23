package com.devsdg.digipos.GestionUtilisateurs.Repositories;

import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import com.devsdg.digipos.GestionUtilisateurs.Models.ClientPOS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientPOS, Long> {
    Page<ClientPOS> findAllByAtiveuserIsTrue(Pageable pageable);
    List<ClientPOS> findAllByUsernameLike(String username);
    //@Query("select u from AppUser u where lower(u.username) like :login or lower(u.email) like :login or u.phone like  : login")
    //Page<ClientPOS> findByUsernameOrEmailOrPhone(String login,Pageable pageable);
}
