package com.example.ej1practicaseguridad.seguridad;

import com.example.ej1practicaseguridad.config.Configuration;
import com.example.ej1practicaseguridad.data.modelo.EmpleadoEntity;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Component
public class KeyGens {

    private static final String KEYSTORE_PASS = Configuration.getInstance().getProperty("keystoreKey");

    public PrivateKey getServerPrivate() throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {

        KeyStore ksLoad = KeyStore.getInstance("PKCS12");
        ksLoad.load(new FileInputStream("keystore.pfx"), KEYSTORE_PASS.toCharArray());
        KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(KEYSTORE_PASS.toCharArray());
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ksLoad.getEntry("Server", passwordProtection);

        return privateKeyEntry.getPrivateKey();

    }

    public PrivateKey getPrivate(EmpleadoEntity empleado) throws KeyStoreException, IOException, UnrecoverableEntryException, NoSuchAlgorithmException, CertificateException {

        KeyStore ksLoad = KeyStore.getInstance("PKCS12");
        ksLoad.load(new FileInputStream("keystore.pfx"), KEYSTORE_PASS.toCharArray());
        KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(empleado.getPassword().toCharArray());
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ksLoad.getEntry(empleado.getUsername(), passwordProtection);

        return privateKeyEntry.getPrivateKey();
    }
    public PublicKey getPublic(String username) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ksLoad = KeyStore.getInstance("PKCS12");
        ksLoad.load(new FileInputStream("keystore.pfx"), KEYSTORE_PASS.toCharArray());
        X509Certificate certificate = (X509Certificate) ksLoad.getCertificate(username);

        return certificate.getPublicKey();
    }

}
