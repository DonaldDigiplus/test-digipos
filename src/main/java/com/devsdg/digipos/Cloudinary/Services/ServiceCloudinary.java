package com.devsdg.digipos.Cloudinary.Services;

import com.cloudinary.Cloudinary;
import com.devsdg.digipos.Cloudinary.Models.Cloudinarie;
import com.devsdg.digipos.Configurations.Properties.ManageURL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class ServiceCloudinary {
    private Cloudinary cloudinary;
    public Cloudinarie cloudinarie;

    public String saveObjectOnCloudinary(){


        return null;
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
}
