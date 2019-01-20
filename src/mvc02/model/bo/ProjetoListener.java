/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.bo;

import java.util.EventListener;

/**
 *
 * @author wcsan_000
 */
public interface ProjetoListener extends EventListener {
    /*
     * Invocado quando uma alteração no projeto é executada
     * @param event Evento gerado pelo Projeto
    */
    public void novoStatusProjeto(ProjetoEvent event);
}
