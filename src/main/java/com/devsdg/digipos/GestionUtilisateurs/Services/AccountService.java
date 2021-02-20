package com.devsdg.digipos.GestionUtilisateurs.Services;

import com.devsdg.digipos.GestionEmail.Format.EmailModel;
import com.devsdg.digipos.GestionEmail.Service.MyAuthentication;
import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.PasswordResetRequestModel;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AccountMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import com.devsdg.digipos.GestionUtilisateurs.Models.PasswordResetTokenEntity;
import com.devsdg.digipos.GestionUtilisateurs.Repositories.PasswordResetTokenRepository;
import com.devsdg.digipos.GestionUtilisateurs.Services.Commons.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements AccountMetier {
    @Autowired
    AppUserSercice appUserSercice;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MyAuthentication myAuthentication;

    @Override
    public AppUserDTO RegisterAccount(AppUserDTO appUserDTO) {
        AppUser appUser=appUserSercice.getUserByLogin(appUserDTO.getPhone());
        if (appUser!=null) throw new ErrorMessages("Ce numéro est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);
        appUser=appUserSercice.getUserByLogin(appUserDTO.getUsername());
        if (appUser!=null) throw new ErrorMessages("Ce nom d'utilisateur est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);
        appUser=appUserSercice.getUserByLogin(appUserDTO.getEmail());
        if (appUser!=null) throw new ErrorMessages("Ce E-mail est deja utilisée par un autre utilisateur!!!", HttpStatus.CONFLICT);

        return appUserSercice.saveUser(appUserDTO);
    }

    @Override
    public AppUserDTO ConnectAccount(String login) {
        AppUser user = appUserSercice.getUserByLogin(login);
        if(user==null)
            throw new ErrorMessages("Utilisateur introuvable", HttpStatus.NOT_FOUND);

        return AppUserSercice.permuteAppUserToAppUserDTO(user);
    }

    @Override
    public PasswordResetTokenEntity requestPasswordReset(String email) {
        AppUser userEntity = appUserSercice.getUserByLogin(email);
        System.out.println("Requete utilisateur : .................." + email);

        if (userEntity==null){
            throw new ErrorMessages("Cette email ne correspond à aucun utilisateur", HttpStatus.NOT_FOUND);
        }
        System.out.println("Récupération de l'utilisateur ..................");

        String token = Utils.generatePasswordResetToken(userEntity.getId_user().toString());

        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserdetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);
        //Envoie du mail
        myAuthentication.sendMail(userEntity.getEmail(), EmailModel.resetPassword(userEntity.getNomcomplet(), userEntity.getId_user(), token), "Réinitialisation du mot de passe");
        //send sms
        //smsApiService.smsAuthentification(userEntity.getPhone(), Detail.WebAppUrl +"/resetpassword/"+userEntity.getId_user()+"/"+token);

        return passwordResetTokenEntity;
    }

    @Override
    public boolean resetPassword(PasswordResetRequestModel passwordResetRequestModel) {
        boolean returnValue = false;
        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(passwordResetRequestModel.getToken());
        if (passwordResetTokenEntity == null) {
            return returnValue;
        }
        if (utils.hasTokenExpired(passwordResetRequestModel.getToken())) {
            return returnValue;
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(passwordResetRequestModel.getPassword());
        AppUser userEntity = appUserSercice.findUserById(passwordResetTokenEntity.getUserdetails().getId_user());

        if (userEntity==null) throw new ErrorMessages("Utilisateur introuvable, Rééssayer svp !!!", HttpStatus.NOT_FOUND);

        userEntity.setPassword(encryptedPassword);
        AppUser savedUserEntity = appUserSercice.saveAppuser(userEntity);

        if (savedUserEntity != null && encryptedPassword.equals(savedUserEntity.getPassword())) {
            returnValue = true;
        }

        passwordResetTokenRepository.delete(passwordResetTokenEntity);

        // returnValue = notificationService.sendNotification(userEntity.getEmail(), PASSWORD_RESET_HTMLBODY.replace("$newpassword", passwordResetRequestModel.getPassword()) , "Digiplus : Password Reset");
         returnValue = myAuthentication.sendMail(userEntity.getEmail(), EmailModel.passwordChange(userEntity.getNomcomplet(), passwordResetRequestModel.getPassword()) , "Mot de passe réinitialisé avec succès");

        return returnValue;
    }

    @Override
    public boolean renewPassword(Long idUser, String pass) {
        AppUser user = appUserSercice.findUserById(idUser);
        if(user==null)
            throw new ErrorMessages("L'utilisateur entrer n'existe pas.", HttpStatus.NOT_FOUND);
        if(!pass.equals("")){
            String encryptedPassword = bCryptPasswordEncoder.encode(pass);
            user.setPassword(encryptedPassword);
            return true;
        } else {
            return false;
        }
    }

}
