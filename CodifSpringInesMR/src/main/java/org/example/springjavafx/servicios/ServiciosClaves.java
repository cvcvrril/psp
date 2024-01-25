package org.example.springjavafx.servicios;

import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.KeyStoreException;

@Service
public class ServiciosClaves {

    //TODO: MONTAR EL SERVICIO DONDE REALZIAR LA GENERACIÓN DE LAS CLAVES TANTO PRIVADAS COMO PÚBLICAS (CERTIFICADOS)
    //INFO: De esta clase se sacan los métodos que se usarán en los controladores

    private void keystoreLoad(){




    }

    private void generatePrivateKey(){
        //INFO: Método para generar la clave privada
    }

    private void generatePublicKey(){
        //INFO: Método para generar la clave pública
    }

    private void saveInCertificate(){
        //INFO: Método para guardar la clave pública en un certificado -> Este luego va al Keystore

        try {
            KeyStore keyLoad = KeyStore.getInstance("PKCS12");
            //KeyStore.PasswordProtection pp = new KeyStore.PasswordProtection("algo");
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }


    }

}
