package br.com.zupacademy.proposta.proposta.criptografia;

import org.jasypt.util.text.BasicTextEncryptor;

public class Criptografia {

    BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
    private Criptografia encryptor;
    private String password = System.getenv().get("CRIPTOGRAFIA_SECRET");

public String criptografar(String textoLimpo){
    basicTextEncryptor.setPassword(password);
    return basicTextEncryptor.encrypt(textoLimpo);
}
public String descriptografar(String textoCriptografado){
    basicTextEncryptor.setPassword(password);
    return basicTextEncryptor.decrypt(textoCriptografado);
}
}
