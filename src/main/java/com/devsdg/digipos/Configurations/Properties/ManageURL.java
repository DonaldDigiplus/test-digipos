package com.devsdg.digipos.Configurations.Properties;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ManageURL implements EnvironmentAware {


    //Cloudinary Parametres
    /*//////Test
    public static final String CLOUDINARY_CLOUD_NAME_TEST = "hy1rkfu9u";
    public static final String CLOUDINARY_API_KEY_TEST = "427567891753675";
    public static final String CLOUDINARY_API_SECRET_TEST = "_eIVN74X5nY5mHqFuSGFWoc0ekM";
    //////Prod
    public static final String CLOUDINARY_CLOUD_NAME_PROD = "hlttt4fd6";
    public static final String CLOUDINARY_API_KEY_PROD = "793256836434385";
    public static final String CLOUDINARY_API_SECRET_PROD = "O6__2P6mgMw4pVq8fY72kvJXleU";*/
    //////Use
    public static String CLOUDINARY_CLOUD_NAME = "hemris11";
    public static String CLOUDINARY_API_KEY = "519237229147227";
    public static String CLOUDINARY_API_SECRET = "QZDB8qDQwZNBGyk94glGx1bgeiA";

    @Override
    public void setEnvironment(Environment environment) {

    }
}
