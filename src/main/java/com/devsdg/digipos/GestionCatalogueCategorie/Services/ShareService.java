package com.devsdg.digipos.GestionCatalogueCategorie.Services;

import com.devsdg.digipos.Cloudinary.Services.ServiceCloudinary;
import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CatalogueProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CategorieProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.CatalogueProduitMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.CategorieProduitMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.ShareMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Row;
import java.io.*;

@Service
@Transactional
public class ShareService implements ShareMetier {
    final
    CatalogueProduitMetier catalogueProduitMetier;
    final
    CategorieProduitMetier categorieProduitMetier;
    final
    ServiceCloudinary serviceCloudinary;

    public ShareService(CatalogueProduitMetier catalogueProduitMetier, CategorieProduitMetier categorieProduitMetier, ServiceCloudinary serviceCloudinary) {
        this.catalogueProduitMetier = catalogueProduitMetier;
        this.categorieProduitMetier = categorieProduitMetier;
        this.serviceCloudinary = serviceCloudinary;
    }

    @Override
    public boolean uploadXLSFile(MultipartFile multipartFile) {
        this.deleteFile(multipartFile.getOriginalFilename());
        File file = this.writeFile(multipartFile);

        this.loadData(file);
        return true;
    }

    @Override
    public CatalogueProduitDTO uploadImageFile(MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf("."));
        CatalogueProduit catalogueProduit = catalogueProduitMetier.getCatalogueByNomIgnoreCase(name);
        if(catalogueProduit!=null){
            catalogueProduit.setImage(
                    serviceCloudinary.uploadImage(multipartFile,
                    "Catalogues",
                    null,
                    catalogueProduit.getCategorieProduit().getSecteuractivite(),
                   name));
            return CatalogueProduitService.permuteCatalogueProduitToCatalogueProduitDTO(catalogueProduit);
        } else
            return null;
    }

    public File writeFile(MultipartFile multipartFile) {
        File file = new File(System.getProperty("user.dir") + "/" + multipartFile.getOriginalFilename()+".xlsx");
        try {
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    void deleteFile(String filename) {
        String current = System.getProperty("user.dir");
        try {
            File file = new File(current + "/" +filename+".xlsx");
            if (file.exists()) {
                boolean b = file.delete();
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData(File file){
        XSSFWorkbook fichier;
        XSSFSheet feuille;
        FileInputStream fils;

        try {
            fils = new FileInputStream(file);

            fichier=new XSSFWorkbook(fils);
            int index=1;
            feuille=fichier.getSheetAt(1);
            Row row=feuille.getRow(index++);
            while (row!=null){
                if(row.getCell(0)!=null){
                    CategorieProduit cp = categorieProduitMetier.getCategorieByNomIgnoreCase(row.getCell(0).getStringCellValue());
                    if(cp == null){
                        categorieProduitMetier.saveCategorie(new CategorieProduitDTO(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue(),row.getCell(2).getStringCellValue()));
                    }
                }
                row=feuille.getRow(index++);
            }

            index = 1;
            feuille = fichier.getSheetAt(0);
            row=feuille.getRow(index++);
            while (row!=null){
                if(row.getCell(0)!=null){
                    System.out.println(row.getCell(1).getStringCellValue());
                    CategorieProduit categorieProduit = categorieProduitMetier.getCategorieByNomIgnoreCase(row.getCell(1).getStringCellValue());
                    if(categorieProduit!=null){
                        CatalogueProduit catalogueProduit = catalogueProduitMetier.getCatalogueByNomIgnoreCase(row.getCell(0).getStringCellValue());
                        if(catalogueProduit==null){
                            String nom=row.getCell(0).getStringCellValue();
                            String genre=row.getCell(3).getStringCellValue();
                            double prix=row.getCell(4).getNumericCellValue();
                            CatalogueProduitDTO catalogueProduitDTO = new CatalogueProduitDTO();
                            catalogueProduitDTO.setCategorieProduit(categorieProduit);
                            catalogueProduitDTO.setNomProduit(nom);
                            catalogueProduitDTO.setGenreClient(genre);
                            catalogueProduitDTO.setPrix(prix);
                                    //(categorieProduit, row.getCell(0).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getNumericCellValue());
                            catalogueProduitDTO.setNomcategorie(categorieProduit.getNomCategorie());
                            CatalogueProduitDTO catalogue = catalogueProduitMetier.saveCatalogue(catalogueProduitDTO);
                        }
                    }
                }
                row=feuille.getRow(index++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
