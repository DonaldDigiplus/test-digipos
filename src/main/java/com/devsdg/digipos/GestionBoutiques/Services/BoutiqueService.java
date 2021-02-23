package com.devsdg.digipos.GestionBoutiques.Services;

import com.devsdg.digipos.GestionBoutiques.DTO.BoutiqueDTO;
import com.devsdg.digipos.GestionBoutiques.Metiers.BoutiqueMetier;
import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import com.devsdg.digipos.GestionBoutiques.Repository.BoutiqueRepository;
import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import com.devsdg.digipos.GestionUtilisateurs.Models.Proprietaire;
import com.devsdg.digipos.GestionUtilisateurs.Models.Vendeur;
import com.devsdg.digipos.GestionUtilisateurs.Repositories.ProprietaireRepository;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BoutiqueService implements BoutiqueMetier {
    @Autowired
    BoutiqueRepository boutiqueRepository;
    @Autowired
    AppUserMetier appUserMetier;

    @Override
    public BoutiqueDTO saveBoutique(BoutiqueDTO boutiqueDTO) {
        if(boutiqueRepository.findByNomBoutique(boutiqueDTO.getNomBoutique())!=null) throw new ErrorMessages("Le nom de la boutique entree existe deja", HttpStatus.NOT_FOUND);
        if(boutiqueRepository.findByTelephoneBoutique(boutiqueDTO.getTelephoneBoutique())!=null) throw new ErrorMessages("Le telephone entree existe deja", HttpStatus.NOT_FOUND);
        if(boutiqueRepository.findByEmailBoutique(boutiqueDTO.getEmailBoutique())!=null) throw new ErrorMessages("L'email entree existe deja", HttpStatus.NOT_FOUND);
        if(boutiqueRepository.findByLien(boutiqueDTO.getLien())!=null) throw new ErrorMessages("Le lien de la boutique entree existe deja", HttpStatus.NOT_FOUND);

        Boutique boutique = new Boutique();
        BeanUtils.copyProperties(boutiqueDTO, boutique);
        boutique.setDate(new Date());
        boutique.setDateLastModification(new Date());
        boutique.setLien(boutique.getNomBoutique().trim().replace(" ", "")); //Defini le lien qui permettra de definir le lien vers la page de la boutique

        boutique = boutiqueRepository.save(boutique);
        Proprietaire proprietaire = (Proprietaire) appUserMetier.getUserByLogin(boutiqueDTO.getProprietaire());
        if(!boutiqueDTO.getProprietaire().isEmpty() || boutiqueDTO.getProprietaire()!=null){
            this.addProprietaireToBoutique(boutique.getIdBoutique(), proprietaire.getId_user());
        }
        BoutiqueDTO bdto = permuteBoutiqueToBoutiqueDTO(boutique);
        bdto.setProprietaire(proprietaire.getUsername());
        return bdto;
    }

    @Override
    public BoutiqueDTO updateBoutique(BoutiqueDTO boutiqueDTO) {
        Boutique boutique = boutiqueRepository.getOne(boutiqueDTO.getIdBoutique());
        Assert.notNull(boutique);
        if(!boutiqueDTO.getEmailBoutique().isEmpty() || boutiqueDTO.getEmailBoutique()!=null){
            boutique.setEmailBoutique(boutiqueDTO.getEmailBoutique());
        }
        if(!boutiqueDTO.getTelephoneBoutique().isEmpty() || boutiqueDTO.getTelephoneBoutique()!=null){
            boutique.setTelephoneBoutique(boutiqueDTO.getTelephoneBoutique());
        }
        if(!boutiqueDTO.getEmailBoutique().isEmpty() || boutiqueDTO.getEmailBoutique()!=null){
            boutique.setEmailBoutique(boutiqueDTO.getEmailBoutique());
        }
        if(!boutiqueDTO.getPhotoboutique().isEmpty() || boutiqueDTO.getPhotoboutique()!=null){
            boutique.setPhotoboutique(boutiqueDTO.getPhotoboutique());
        }
        if(!boutiqueDTO.getDescription().isEmpty() || boutiqueDTO.getDescription()!=null){
            boutique.setDescription(boutiqueDTO.getDescription());
        }
        boutique.setDateLastModification(new Date());
        boutique.setLien(boutique.getNomBoutique().trim().replace(" ", "")); //Defini le lien qui permettra de definir le lien vers la page de la boutique

        BoutiqueDTO bdto = permuteBoutiqueToBoutiqueDTO(boutique);
        bdto.setProprietaire(boutique.getProprietaire().getUsername());
        return bdto;
    }

    @Override
    public BoutiqueDTO getBoutiqueById(Long idBoutique) {
        BoutiqueDTO bdto = permuteBoutiqueToBoutiqueDTO(boutiqueRepository.getOne(idBoutique));
        bdto.setProprietaire(boutiqueRepository.getOne(idBoutique).getProprietaire().getUsername());
        return bdto;
    }

    @Override
    public BoutiqueDTO getBoutiqueByName(String nomBoutique) {
        BoutiqueDTO bdto = permuteBoutiqueToBoutiqueDTO(boutiqueRepository.findByNomBoutique(nomBoutique));
        bdto.setProprietaire(boutiqueRepository.findByNomBoutique(nomBoutique).getProprietaire().getUsername());
        return bdto;
    }

    @Override
    public Boutique getBoutiqueByNomBoutique(String nomBoutique) {

        return boutiqueRepository.findByNomBoutique(nomBoutique);
    }

    @Override
    public BoutiqueDTO getBoutiqueByPhone(String phoneBoutique) {
        BoutiqueDTO bdto = permuteBoutiqueToBoutiqueDTO(boutiqueRepository.findByTelephoneBoutique(phoneBoutique));
        bdto.setProprietaire(boutiqueRepository.findByTelephoneBoutique(phoneBoutique).getProprietaire().getUsername());
        return bdto;    }

    @Override
    public BoutiqueDTO getBoutiqueByLien(String lienBoutique) {
        BoutiqueDTO bdto = permuteBoutiqueToBoutiqueDTO(boutiqueRepository.findByLien(lienBoutique));
        bdto.setProprietaire(boutiqueRepository.findByLien(lienBoutique).getProprietaire().getUsername());
        return bdto;
    }

    @Override
    public Page<Boutique> getAllBoutiques(Pageable pageable) {
        return boutiqueRepository.findAll(pageable);
    }

    @Override
    public Page<Boutique> getAllBoutiquesIsActive(Pageable pageable) {
        return boutiqueRepository.findAllByActiveboutiqueIsTrue(pageable);
    }

    @Override
    public List<String> getAllNomBoutique() {
        return boutiqueRepository.findAllBoutiqueName();
    }

    @Override
    public List<String> getAllNomBoutiqueIsActive() {
        return boutiqueRepository.findAllBoutiqueNameIsActive();
    }

    @Override
    public boolean active_and_desactive_boutique(Long idBoutique) {
        Boutique boutique = boutiqueRepository.getOne(idBoutique);

        Assert.notNull(boutique);
        boutique.setActiveboutique(!boutique.isActiveboutique());
        return boutique.isActiveboutique();
    }

    @Override
    public BoutiqueDTO addProprietaireToBoutique(Long idboutique, Long idProprietaire) {
        Boutique boutique = boutiqueRepository.getOne(idboutique);
        Assert.notNull(boutique);
        Proprietaire proprietaire = (Proprietaire) appUserMetier.findUserById(idProprietaire);
        Assert.notNull(proprietaire);
        boutique.setProprietaire(proprietaire);
        proprietaire.setBoutique(boutique);

        return permuteBoutiqueToBoutiqueDTO(boutique);
    }

    @Override
    public Boutique addVendeurToBoutique(Long idboutique, Long idVendeur) {
        Boutique boutique = boutiqueRepository.getOne(idboutique);
        Assert.notNull(boutique);
        Vendeur vendeur = (Vendeur) appUserMetier.findUserById(idVendeur);

        boutique.getVendeurs().add(vendeur);
        return boutique;
    }

    @Override
    public Boutique getBoutiqueByProprietaire(Long id_proprietaire) {
        Proprietaire proprietaire = (Proprietaire) appUserMetier.findUserById(id_proprietaire);
        return proprietaire.getBoutique();
    }

    @Override
    public BoutiqueDTO getBoutiqueDTOByProprietaire(Long id_proprietaire) {
        Proprietaire proprietaire = (Proprietaire) appUserMetier.findUserById(id_proprietaire);
        if(proprietaire!=null){
            BoutiqueDTO boutiqueDTO = permuteBoutiqueToBoutiqueDTO(proprietaire.getBoutique());
            boutiqueDTO.setProprietaire(proprietaire.getUsername());
            return boutiqueDTO;
        } else
            throw new ErrorMessages("Le proprietaire entree n'existe pas", HttpStatus.NOT_FOUND);
    }

    static BoutiqueDTO permuteBoutiqueToBoutiqueDTO(Boutique boutique){
        BoutiqueDTO boutiqueDTO = new BoutiqueDTO();
        BeanUtils.copyProperties(boutique, boutiqueDTO);
        return boutiqueDTO;
    }

    static List<BoutiqueDTO> permuteBoutiqueListToBoutiqueDTOList(List<Boutique> boutiques){
        List<BoutiqueDTO> boutiqueDTOList = new ArrayList<>();
        boutiques.forEach(b->{
            BoutiqueDTO boutiqueDTO = new BoutiqueDTO();
            BeanUtils.copyProperties(b, boutiqueDTO);
            boutiqueDTOList.add(boutiqueDTO);
        });
        return boutiqueDTOList;
    }
}
