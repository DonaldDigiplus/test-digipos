package com.devsdg.digipos.Cloudinary.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.devsdg.digipos.Cloudinary.Models.Cloudinarie;
import com.devsdg.digipos.Cloudinary.Repository.CloudinaryRepository;
import com.devsdg.digipos.Configurations.Properties.ManageURL;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Transactional
@Service
public class ServiceCloudinary {
    @Autowired
    CloudinaryRepository cloudinaryRepository;

    private Cloudinary cloudinary;
    private Cloudinarie cloudinarie;

    public String saveObjectOnCloudinary(String imagestring,
                                         String categorie,
                                         String nomBoutique,
                                         String secteurActivite,
                                         String objectName) {
        cloudinarie=new Cloudinarie();
        cloudinary=cloudinaryConfig();
        try {

        // Get extension on string
        String ext = imagestring.substring(imagestring.indexOf("/")+1, imagestring.indexOf(";")-1);
        System.out.println(">>>>>>>>" + imagestring);
        System.out.println(">>>>>>>>" + ext);

        // Get base image string
        String image = imagestring.substring(imagestring.indexOf(",")+1);
        System.out.println(">>>>>>>>" + imagestring);
        System.out.println(">>>>>>>>" + image);

        //Suppression du fichier s'il existe deja
        this.deleteFile("temp." + ext);

        // decode the string and write to file
        byte[] decodedBytes = Base64
                .getDecoder()
                .decode(image);
        File file = new File(System.getProperty("user.dir") + "/temp." + ext);
        FileUtils.writeByteArrayToFile(file, decodedBytes);


        //Get Source of image
        String src="";

        if(categorie.equalsIgnoreCase("boutiques")){
            src = categorie + "/" + secteurActivite + "/" + nomBoutique + "/" + objectName;
        } else if(categorie.equalsIgnoreCase("produits")){
            src = categorie + "/" + secteurActivite + "/" + nomBoutique + "/produits/" + objectName;
        } else if(categorie.equalsIgnoreCase("catalogues")){
            src = categorie + "/" + secteurActivite + "/" + objectName;
        } else if(categorie.equalsIgnoreCase("publicites")){
            src = categorie + "/" + nomBoutique + "/" + objectName;
        }

        //Upload de l'image
        Map result=cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", src));
        //Map result=cloudinary.uploader().upload(file,ObjectUtils.emptyMap());
        cloudinarie.setSignature((String) result.get("signature"));
        cloudinarie.setFormat((String) result.get("format"));
        cloudinarie.setSecure_url(((String) result.get("secure_url")));
        cloudinarie.setUrl(result.get("url").toString());
        cloudinarie.setPublic_id(result.get("public_id").toString());
        cloudinarie.setType(result.get("type").toString());
        cloudinarie.setOriginal_filname(result.get("original_filname").toString());
        cloudinarie = cloudinaryRepository.save(cloudinarie);
        boolean b = file.delete();
        if(!b)
            this.deleteFile("temp." + ext);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cloudinarie.getUrl();
    }

    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", ManageURL.CLOUDINARY_CLOUD_NAME);
        config.put("api_key", ManageURL.CLOUDINARY_API_KEY);
        config.put("api_secret", ManageURL.CLOUDINARY_API_SECRET);
        cloudinary = new Cloudinary(config);
        System.out.println(config);
        return cloudinary;
    }

    void deleteFile(String imagename) {
        String current = System.getProperty("user.dir");
        try {
            File file = new File(current + "/" +imagename);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
