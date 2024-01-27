package org.example.springjavafx.servicios;

import lombok.extern.log4j.Log4j2;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.example.springjavafx.Configuration;
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

import static org.bouncycastle.oer.its.etsi102941.CtlDelete.cert;

@Log4j2
@Service
public class ServiciosClaves {
    //INFO: De esta clase se sacan los métodos que se usarán en los controladores

    private final Configuration configuration;

    public ServiciosClaves(Configuration configuration) {
        this.configuration = configuration;
    }


    public void generateUserPrivatePublicKey(String nombreUsuario){
        //INFO: Método para generar las claves privada y pública asimétricas
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivadaUser = clavesRSA.getPrivate();
            PublicKey clavePublicaUser = clavesRSA.getPublic();

            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();
            PrivateKey firmaKey = privateKeyKeyStore();

            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();

            cert1.setSerialNumber(BigInteger.valueOf(1));
            cert1.setSubjectDN(new X509Principal("CN="+ nombreUsuario));
            cert1.setIssuerDN(new X509Principal("CN=Server"));
            cert1.setPublicKey(clavePublicaUser);
            cert1.setNotBefore(Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
            cert1.setNotAfter(new Date());
            cert1.setSignatureAlgorithm("SHA256WithRSAEncryption");

            X509Certificate cert = cert1.generate(firmaKey);

            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream("keystore.pfx");
            ks.load(fis, keyStorePassword);

            ks.setCertificateEntry(nombreUsuario, cert);
            ks.setKeyEntry(nombreUsuario, clavePrivadaUser, nombreUsuario.toCharArray(), new Certificate[]{cert});
            FileOutputStream fos = new FileOutputStream("keystore.pfx");
            ks.store(fos, keyStorePassword);
            fos.close();

        }catch (Exception e){
         log.error(e.getMessage(), e);
        }
    }

    public void generateSymmetricPrivatePublicKey(){
        //INFO: Método para generar las claves privada y pública simétrica
    }

    public void saveInCertificate(){
        //INFO: Método para guardar la clave pública en un certificado -> Este luego va al Keystore


    }

    private PrivateKey privateKeyKeyStore(){
        try {
            char[] keyStorePassword = configuration.getKeyStorePassword().toCharArray();

            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream("keystore.pfx");
            ks.load(fis, keyStorePassword);
            KeyStore.PasswordProtection protection = new KeyStore.PasswordProtection(keyStorePassword);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("server", protection);

            return privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
