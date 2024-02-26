package com.example.ej1practicaseguridad.domain.servicios;

import com.example.ej1practicaseguridad.data.modelo.EmpleadoEntity;
import com.example.ej1practicaseguridad.data.modelo.dto.EmpleadoDTO;
import com.example.ej1practicaseguridad.data.modelo.error.ErrorObject;
import com.example.ej1practicaseguridad.data.repositorios.EmpleadoRepository;
import com.example.ej1practicaseguridad.seguridad.KeyGens;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;
    private final KeyGens keyGens;

    public EmpleadoService(EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder, KeyGens keyGens) {
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
        this.keyGens = keyGens;
    }

    public Either<ErrorObject, EmpleadoDTO> doLogin(EmpleadoEntity empleado) {
        Either<ErrorObject, EmpleadoDTO> res;
        EmpleadoEntity empleadoDB = empleadoRepository.findByUsername(empleado.getUsername());
        if (empleadoDB != null) {
            if (passwordEncoder.matches(empleado.getPassword(), empleadoDB.getPassword())) {
                EmpleadoDTO empleadoRes = new EmpleadoDTO();
                res = Either.right(empleadoRes);
            } else {
                res = Either.left(new ErrorObject("Contraseña incorrecta.", LocalDateTime.now()));
            }
        } else {
            res = Either.left(new ErrorObject("Empleado no encontrado", LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, EmpleadoDTO> doRegistro(EmpleadoEntity nuevoEmpleado) {
        Either<ErrorObject, EmpleadoDTO> res;
        try {
            nuevoEmpleado.setPassword(passwordHashed(nuevoEmpleado.getPassword()));
            empleadoRepository.save(nuevoEmpleado);
            generateCertificate(nuevoEmpleado, generateKeys());
            EmpleadoDTO nuevoEmpleadoDTO = new EmpleadoDTO();
            res = Either.right(nuevoEmpleadoDTO);
        }catch (Exception e){
            res = Either.left(new ErrorObject("No se ha podido realizar el registro", LocalDateTime.now()));
        }
        return res;
    }

    private String passwordHashed(String password) {
        return passwordEncoder.encode(password);
    }

    public KeyPair generateKeys() throws NoSuchAlgorithmException {

        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator;
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom());

        return keyPairGenerator.generateKeyPair();
    }

    private void generateCertificate(EmpleadoEntity empleado, KeyPair keyPair) throws UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        //SET CERTIFICATE VALUES
        X509V3CertificateGenerator certificateGenerator = new X509V3CertificateGenerator();
        certificateGenerator.setSerialNumber(BigInteger.valueOf(1));
        certificateGenerator.setSubjectDN(new X509Principal("CN=" + empleado.getUsername()));
        certificateGenerator.setIssuerDN(new X509Principal("CN=Server"));
        certificateGenerator.setPublicKey(keyPair.getPublic());
        certificateGenerator.setNotBefore(Date.from(LocalDate.now().plusDays(365).atStartOfDay().toInstant(ZoneOffset.UTC)));
        certificateGenerator.setNotAfter(new Date());
        certificateGenerator.setSignatureAlgorithm("SHA256WithRSAEncryption");

        //GENERATE CERTIFICATE SIGNING WITH THE SERVER'S PRIVATE KEY
        X509Certificate certificate = certificateGenerator.generate(keyGens.getServerPrivate());

        //LOAD KEYSTORE
        char[] keystorePass = "admin".toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("keystore.pfx"), keystorePass);

        //SAVE CERTIFICATE
        ks.setCertificateEntry(empleado.getUsername(), certificate);
        ks.setKeyEntry(empleado.getUsername(), keyPair.getPrivate(), empleado.getPassword().toCharArray(), new Certificate[]{certificate});
        FileOutputStream fos = new FileOutputStream("keystore.pfx");
        ks.store(fos, keystorePass);
        fos.close();
    }
}
