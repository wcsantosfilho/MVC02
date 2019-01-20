/*
 * Controle de Exceções da Aplicação como um todo
 */
package mvc02.model.bo;

/**
 * Classe MinhaAplicacaoException 
 * @author wcsan_000
 */
public class MinhaAplicacaoException extends RuntimeException {
    public MinhaAplicacaoException() {
        super();
    }
    public MinhaAplicacaoException(String mensagem) {
        super(mensagem);
    }
    public MinhaAplicacaoException (Throwable causa) {
        super (causa);
    }
    public MinhaAplicacaoException (String mensagem, Throwable causa) {
        super (mensagem, causa);
    }
}
