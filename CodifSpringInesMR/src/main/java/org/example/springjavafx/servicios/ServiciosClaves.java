package org.example.springjavafx.servicios;

import lombok.extern.log4j.Log4j2;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.example.springjavafx.Configuration;
import org.example.springjavafx.utils.Constantes;
import org.example.springjavafx.seguridad.impl.EncriptacionAES;
import org.example.springjavafx.utils.RandomBytesGenerator;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Log4j2
@Service
public class ServiciosClaves {
    //INFO: De esta clase se sacan los métodos que se usarán en los controladores

    private final Configuration configuration;
    private final EncriptacionAES aes;
    private final RandomBytesGenerator ksa;

    public ServiciosClaves(Configuration configuration, EncriptacionAES aes, RandomBytesGenerator ksa) {
        this.configuration = configuration;
        this.aes = aes;
        this.ksa = ksa;
    }

    public void generateUserPrivatePublicKey(String nombreUsuario){
        //INFO: Método para generar las claves privada y pública del usuario
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Constantes.RSA);
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivadaUser = clavesRSA.getPrivate();
            PublicKey clavePublicaUser = clavesRSA.getPublic();

            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
            PrivateKey firmaKey = privateKeyKeyStore();

            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();

            cert1.setSerialNumber(BigInteger.valueOf(1));
            cert1.setSubjectDN(new X509Principal(Constantes.CN + nombreUsuario));
            cert1.setIssuerDN(new X509Principal(Constantes.CN + nombreUsuario));
            cert1.setPublicKey(clavePublicaUser);
            cert1.setNotBefore(Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
            cert1.setNotAfter(new Date());
            cert1.setSignatureAlgorithm(Constantes.SHA_256_WITH_RSA_ENCRYPTION);

            X509Certificate cert = cert1.generate(firmaKey);

            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            FileInputStream fis = new FileInputStream(Constantes.KEYSTORE_PFX);
            ks.load(fis, keyStorePassword);

            ks.setCertificateEntry(nombreUsuario, cert);
            ks.setKeyEntry(nombreUsuario, clavePrivadaUser, nombreUsuario.toCharArray(), new Certificate[]{cert});
            FileOutputStream fos = new FileOutputStream(Constantes.KEYSTORE_PFX);
            ks.store(fos, keyStorePassword);
            fos.close();

        }catch (Exception e){
         log.error(e.getMessage(), e);
        }
    }

    public String encryptCode(String code, String secret){
        return aes.encriptar(code, secret);
    }

    public String decryptCode(String code){
        return aes.desencriptar(code, String.valueOf(ksa));
    }

    public String signCode(String code, String username){
        try {
            PublicKey publicKeyUser = publicKeyUser(username);
            PrivateKey privateKeyUser = privateKeyUser(username);
            Signature sign = Signature.getInstance("SHA256WithRSA");
            sign.initSign(privateKeyUser);
            sign.initVerify(publicKeyUser);
            sign.update("contenIdo".getBytes());
            byte[] firma = sign.sign();
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public String privateKeyUserString(String username){
        String pKString = String.valueOf(privateKeyUser(username));
        return pKString;
    }

    public String publicKeyUserString(String username){
        String puKString = publicKeyUserString(username);
        return puKString;
    }

    public PublicKey publicKeyUser(String username){
        try {
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();

            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            FileInputStream fis = new FileInputStream(Constantes.KEYSTORE_PFX);
            ks.load(fis, keyStorePassword);
            X509Certificate certLoad = (X509Certificate) ks.getCertificate(username);
            PublicKey publicKey = certLoad.getPublicKey();
            return publicKey;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public PrivateKey privateKeyUser(String username){
        try {
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();

            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            FileInputStream fis = new FileInputStream(Constantes.KEYSTORE_PFX);
            ks.load(fis, keyStorePassword);
            KeyStore.PasswordProtection protection = new KeyStore.PasswordProtection(keyStorePassword);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(Constantes.SERVER, protection);
            return privateKeyEntry.getPrivateKey();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private PrivateKey privateKeyKeyStore(){
        try {
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();

            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            FileInputStream fis = new FileInputStream(Constantes.KEYSTORE_PFX);
            ks.load(fis, keyStorePassword);
            KeyStore.PasswordProtection protection = new KeyStore.PasswordProtection(keyStorePassword);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(Constantes.SERVER, protection);
            return privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
