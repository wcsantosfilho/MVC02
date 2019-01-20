/*
 * Controle de Excecoes do Projeto, extendendo o controle de excecoes da aplicacao
 */
package mvc02.model.bo;

/**
 *
 * @author wcsan_000
 */
public class ProjetoException extends MinhaAplicacaoException {
    public ProjetoException(String msg) {
        super(msg);
    }
}