package org.example.springjavafx.seguridad;

import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.bouncycastle.oer.its.etsi102941.CtlDelete.cert;

public class MainKeyStore {

    public static void main(String[] args) {
        try {

            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); // Hace uso del provider BC
            keyGen.initialize(2048,new SecureRandom());  // tamano clave 512 bits
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();

            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();

            cert1.setSerialNumber(BigInteger.valueOf(1));   //or generate a random number
            cert1.setSubjectDN(new X509Principal("CN=Server"));  //see examples to add O,OU etc
            cert1.setIssuerDN(new X509Principal("CN=Server")); //same since it is self-signed
            cert1.setPublicKey(clavesRSA.getPublic());
            cert1.setNotBefore(
                    Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
            cert1.setNotAfter(new Date());
            cert1.setSignatureAlgorithm("SHA256WithRSAEncryption");

            X509Certificate cert =  cert1.generate(clavePrivada);
            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] password = "Obito".toCharArray();        // -> Tomar la contraseña de aquí desde el Config
            ks.load(null, null);
            ks.setCertificateEntry("server", cert);
            ks.setKeyEntry("server", clavePrivada, password, new Certificate[]{cert});
            FileOutputStream fos = new FileOutputStream("keystore.pfx");
            ks.store(fos, password);
            fos.close();

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException |
                 SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
