package com.devsdg.digipos.Configurations.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration//Definir la classe comme classe de configuration
@EnableWebSecurity//Pour activer la securite du web
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Qualifier("serviceuserdetails")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;//il faut maitenant l'instantier et pour notre cas c'est dans la classe de demarage

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //cette methode permet de montrer à spring securite comment il va chercher les utilisateurs et les roles
        /*on va utiliser un systeme d'authentification basé sur les services et pour cella on va utiliser userdetailService
        par la suite on cree une classe qui va l'implementer
         */
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);//quand  on va hacher le  mot de pass saisi par utilisateur on va le comparer avec celui de la bd grace a cette fonction
    }

    protected void configure(HttpSecurity http) throws Exception {
        //ici on va definir les droits acces et les filtres

        http.csrf().disable();//Desactiver les failles csrf
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/login/**","/**",
                                                        "/v2/api-docs", "/swagger-resources/**",
                                                        "/configuration/ui","/configuration/security",
                                                        "/swagger-ui.html/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();//pour indiquer à spring que toutes les ressource doivent etre authentifier
        http.addFilter(new JWTauthentificationFilter(authenticationManager()));
        http.addFilterBefore(new JWTauthorizationFilter(),  UsernamePasswordAuthenticationFilter.class);

        http.logout().logoutUrl("/logout");
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
        http.formLogin().failureForwardUrl("/login?error");
    }

}
