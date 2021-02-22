package com.devsdg.digipos.GestionEmail.Format;

public class EmailModel {

    public static String resetPassword(String username, Long userId, String token){
        String mainBodyMail = "<p align=\"left\">\n" +
                "Cher(e) <b>" + username + "</b>,\n" +
                "</p>\n" +
                "<p align=\"left\">\n" +
                "Une requete de reinitialisation de votre mot de passe a été initiée. Si cela n'est pas votre initiative, veuillez contacter le service client. Mais dans le cas contraire, cliquez sur le lien ci-après pour définir votre nouveau mot de passe" +
                "</p>" +
                "<p> " +
               "<a href='"+ "http://localhost:4200" +"/confirm-password/"+userId+"/"+token+"'>Cliquer ici pour réinitialiser votre mot de passe</a>" +
                "</p>" +
                "<hr>\n";
        return HeadBodyMail + mainBodyMail + FooterBodyMail;
    }


    public static String firstConnexion(String username, String password){
        String mainBodyMail = "<p align=\"left\">\n" +
                "Cher(e) <b>" + username + "</b>,\n" +
                "</p>\n" +
                "<p align=\"left\">\n" +
                "Nous vous remercions de continuer le processus de création de votre compte. Bien vouloir vous connecter avec les paramètres suivants: login="+username+"mot de passe="+password +
                "</p>" +
                "<p> " +
                "<a href='"+ "http://localhost:4200/login'>Cliquer ici pour continuer</a>" +
                "</p>" +
                "<hr>\n";
        return HeadBodyMail + mainBodyMail + FooterBodyMail;
    }

    public static String passwordChange(String username, String newPassword){
        String mainBodyMail = "<p align=\"left\">\n" +
                "Cher(e) <b>" + username + "</b>,\n" +
                "</p>\n" +
                "<p align=\"left\">\n" +
                "Votre mot de passe a été reinitialisé. Le nouveau mot de passe est : " + newPassword +
                "</p>" +
                "<p> " +
                "Merci pour la confiance" +
                "</p>" +
                "<hr>\n";
        return HeadBodyMail + mainBodyMail + FooterBodyMail;
    }

    private final static String HeadBodyMail = "<html>\n" +
            "\t<head>\n" +
            "\t\t<title> Email </title>\n" +
            "\t</head>\n" +
            "\t\n" +
            "\t<body>\n" +
            "\t\t<table style=\"vertical-align:middle; border:1px solid lightgrey\" width=\"600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
            "            <tbody>\n" +
            "\t\t\t\t<tr bgcolor=\"#ffffff\">\n" +
            "                    <td style=\"max-width:100%;text-align:center;vertical-align:middle\" align=\"center\">\n" +
            "                        <div style=\"height:100%;width:100%;vertical-align:middle;display:table;text-align:center\" valign=\"center\">\n" +
            "                                    <span style=\"display:table-cell;vertical-align:middle;text-align:center\"> <a href=\"http://www.digishop.cm\" target=\"_blank\" data-saferedirecturl=\"http://www.digishop.cm\">                                         \n" +
            "                                    <img width=\"150\" height=\"150\"  valign=\"center\" src=\"https://res.cloudinary.com/hemris11/image/upload/v1597765216/favicon_tnxzf6.png\">\n" +
            "                                </a>\n" +
            "                            </span>\n" +
            "                        </div>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody>\n" +
            "\t\t</table>\n" +
            "\t\t\n" +
            "\t\t<div style=\"width:100%;vertical-align:middle;display:table;text-align:center\" valign=\"center\">\n" +
            "                    <span style=\"display:table-cell;vertical-align:middle;text-align:center\">                                       \n" +
            "\t\t\t\t\t<img src=\"https://res.cloudinary.com/hemris11/image/upload/v1597765085/line_v2oq3e.png\" width=\"500\" height=\"10\" alt=\"Banner\" valign=\"center\">\n" +
            "\t\t\t</span>\n" +
            "        </div>\n" +
            "\t\t\n" +
            "\t\t<table style=\"vertical-align:middle; border:1px solid lightgrey; font-size:13px; padding-top:2px; font-family:'Times New Roman'\" width=\"600px\" cellspacing=\"0\" align=\"center\">\n" +
            "                                    <tbody><tr>\n" +
            "                                        <td style=\"background-color:#ffffff;color:#565656;padding:15px 15px 15px 15px\" width=\"100%\" align=\"center\">\n" +
            "                                            <p align=\"left\">" +
            "";

    private final static String FooterBodyMail = "<p>Cordialement,</p>\n" +
            "                                                    <p>L'équipe <b>DIGISHOP</b></p>\n" +
            "                                                    <p style=\"font-weight:bold; font-size:12px\">Vous avez des questions?</p>\n" +
            "                                                    <ul style=\"padding-left:0px; font-size:11px\">\n" +
            "                                                        <li style=\"margin-left:1.2em; font-size:12px\">Visitez notre site <a href=\"http://www.digishop.cm\" style=\"color:#0000ee\" target=\"_blank\" data-saferedirecturl=\"http://www.digishop.cm\">digishop</a> pour découvrir la richesse de l'afrique et d'ailleurs.\n" +
            "                                                        </li>\n" +
            "                                                        <li style=\"margin-left:1.2em; font-size:12px\">Le service client est joignable à travers: <a href=\"#\" style=\"color:#0000ee\" target=\"_blank\" data-saferedirecturl=\"#\">ce formulaire web</a> ou whatsapp au + 237 6 72 68 72 14 ou appel téléphonique au 2 33 47 72 77</li>\n" +
            "                                                        <li style=\"margin-left:1.2em; font-size:12px\">Nous sommes à votre disposition: Lun-Ven 08h00-20h00; Sam 09h00-17h00</li>\n" +
            "                                                    </ul>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </tbody>\n" +
            "\t\t\t\t\t\t\t\t</table>\n" +
            "\t\t\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\t<table style=\"margin-bottom:15px;padding:0 0 0 4%!important; font-family:'Times New Roman'\" width=\"600px\" cellspacing=\"0\" align=\"center\">\n" +
            "                                    <tbody><tr style=\"display:block\">\n" +
            "                                        <td style=\"width:50%\">\n" +
            "                                            <table>\n" +
            "                                                <tbody><tr>\n" +
            "                                                    <td>&nbsp;</td>\n" +
            "                                                </tr>\n" +
            "                                                <tr>\n" +
            "                                                    <td>\n" +
            "                                                        <a href=\"#\" target=\"_blank\" data-saferedirecturl=\"#\"><img src=\"https://res.cloudinary.com/hemris11/image/upload/v1597765085/get-on-google-play_rgfonz.png\" style=\"width:150px\" class=\"CToWUd\"></a>\n" +
            "                                                    </td>\n" +
            "                                                </tr>\n" +
            "                                            </tbody></table>\n" +
            "                                        </td>\n" +
            "                                        <td style=\"width:25%;text-align:center;padding-left:5%\">\n" +
            "                                            <table style=\"width:100%\">\n" +
            "                                                <tbody><tr><td colspan=\"12\" style=\"column-span:6;text-align:center;font-weight:bold;width:100%; font-size:13px\">suivez nous partout</td></tr>\n" +
            "                                                <tr>\n" +
            "                                                    <td align=\"center\">\n" +
            "                                                        <a href=\"#\" target=\"_blank\" data-saferedirecturl=\"#\"><img src=\"https://res.cloudinary.com/hemris11/image/upload/v1597765085/linkedin_dz2vj1.png\" padding-top=\"-5px\" style=\"width:80%\" alt=\"linkedin\" class=\"CToWUd\"></a>\n" +
            "                                                    </td>\n" +
            "                                                    <td align=\"center\">\n" +
            "                                                        <a href=\"#\" target=\"_blank\" data-saferedirecturl=\"#\"><img src=\"https://res.cloudinary.com/hemris11/image/upload/v1597765085/facebook_lpgfdd.png\" style=\"width:100%\" alt=\"facebook\" class=\"CToWUd\"></a>\n" +
            "                                                    </td>\n" +
            "                                                    <td align=\"center\">\n" +
            "                                                        <a href=\"#\" target=\"_blank\" data-saferedirecturl=\"#\"><img src=\"https://res.cloudinary.com/hemris11/image/upload/v1597765085/twitter_syqrbw.png\" style=\"width:100%\" alt=\"twitter\" class=\"CToWUd\"></a>\n" +
            "                                                    </td>\n" +
            "                                                    \n" +
            "                                                </tr>\n" +
            "                                            </tbody></table>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </tbody></table>\n" +
            "\t</body>\n" +
            "</html>";

}
