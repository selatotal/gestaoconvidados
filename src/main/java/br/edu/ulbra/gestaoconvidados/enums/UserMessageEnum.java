package br.edu.ulbra.gestaoconvidados.enums;

/**
 * Created by talesviegas on 26/06/17.
 */
public enum UserMessageEnum {

    EMPTY_EMAIL("E-mail nao informado"),
    EMPTY_PASSWORD("Senha nao informada"),
    EMPTY_PASSWORD_CONFIRM("Confirmacao de Senha nao informada"),
    PASSWORDS_MISMATCH("Senhas nao conferem"),
    EMPTY_NAME("Nome nao informado"),
    EMPTY_PROFILE("Perfil nao informado"),
    USER_NOT_FOUND("Usuario inexistente");

    private final String text;

    private UserMessageEnum(String message){
        this.text = message;
    }

    public String toString(){
        return this.text;
    }
}
