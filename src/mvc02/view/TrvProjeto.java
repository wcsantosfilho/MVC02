/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.view;

import javafx.beans.property.SimpleStringProperty;


/*
 * Classe auxiliar de Projeto para montar a árvore de objetos 
 * refletidas na TreeView
 */
public class TrvProjeto {

    private final SimpleStringProperty etiqueta;

    public TrvProjeto(String etiqueta) {
        this.etiqueta = new SimpleStringProperty(etiqueta);
    }

    public String getEtiqueta() {
        return etiqueta.get();
    }

    public void setEtiqueta(String fEtiqueta) {
        etiqueta.set(fEtiqueta);
    }
}
