package com.example.seguridad.utils;

public class Constantes {
    public static final String KEYSTORE_PFX = "keystore.pfx";
    public static final String RSA = "RSA";
    public static final String CN_SERVER = "CN=Server";
    public static final String PKCS_12 = "PKCS12";
    public static final String SHA_256_WITH_RSA_ENCRYPTION = "SHA256WithRSAEncryption";
    public static final String SERVER = "server";
    public static final String PBKDF_2_WITH_HMAC_SHA_256 = "PBKDF2WithHmacSHA256";
    public static final String AES = "AES";
    public static final String AES_GCM_NO_PADDING = "AES/GCM/noPadding";

    private static final String[] WHITE_LIST_URL = {
            "/auth/login",
            "/auth/registro",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs/**"
    };
}
