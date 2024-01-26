package org.example.springjavafx.servicios;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static org.bouncycastle.oer.its.etsi102941.CtlDelete.cert;

@Log4j2
@Service
public class ServiciosClaves {
    //INFO: De esta clase se sacan los métodos que se usarán en los controladores


    private void generatePrivateKey(){
        //INFO: Método para generar la clave privada
    }

    private void generatePublicKey(){
        //INFO: Método para generar la clave pública
    }

    private void saveInCertificate(){
        //INFO: Método para guardar la clave pública en un certificado -> Este luego va al Keystore


    }

}
