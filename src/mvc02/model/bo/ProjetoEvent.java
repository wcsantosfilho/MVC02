/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.bo;

import java.util.ArrayList;
import java.util.EventObject;
import mvc02.model.vo.PlantaBaixa;
import mvc02.model.vo.Projeto;

/**
 *
 * @author wcsan_000
 */
public class ProjetoEvent extends EventObject {
    
    public ProjetoEvent(Projeto source) {
        super(source);
    }
    
    /*
     * Retorna o codigo do projeto
     * @return int prjCodigo
    */
    public int getCodigoProjeto() {
        return ((Projeto)this.source).getPrjCodigo();
    }
    
    /* 
     * Retorna a descricao do projeto
     * @return String prjDescricao
    */
    public String getDescricaoProjeto() {
        return ((Projeto)this.source).getPrjDescricao();
    }

    /* 
     * Retorna a lista de plantas baixas
     * @return String prjDescricao
    */
    public ArrayList<PlantaBaixa> getAllPlantaBaixa() {
        return ((Projeto)this.source).getAllPlantaBaixa();
    }

    public PlantaBaixa getFirstPlantaBaixa() {
        return ((Projeto)this.source).getFirstPlantaBaixa();
    }
}
