package com.devsdg.digipos.Configurations.Services;

import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import com.devsdg.digipos.GestionUtilisateurs.Services.AppUserSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;

@Service("serviceuserdetails")
public class ServiceUserDetails implements UserDetailsService {

    @Autowired
    AppUserSercice appUserSercice;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AppUser user = appUserSercice.getUserByLogin(login);

        if (user==null)throw new UsernameNotFoundException(login);
        Collection<GrantedAuthority> authorities=new ArrayList<>();//on a une collection de role
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRolename()));
        });
        return new User(user.getPhone(),user.getPassword(),authorities);
    }
}
