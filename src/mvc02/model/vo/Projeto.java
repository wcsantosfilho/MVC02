/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.vo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mvc02.model.bo.ProjetoEvent;
import mvc02.model.bo.ProjetoException;
import mvc02.model.bo.ProjetoListener;
import mvc02.view.GrupoVisualiz;

/**
 *
 * @author wcsan_000
 */
public class Projeto implements GrupoVisualiz {

    private Integer prjCodigo;      // Codigo do Projeto (ID)
    private String prjDescricao;    // Descricao do Projeto
    private ArrayList<PlantaBaixa> prjPlantaBaixa; // composição entre Projeto e suas Plantas Baixas

    /*
     * Objetos para tratamento do "Listeners" do objeto Projeto
     */
    private List<ProjetoListener> projetoListeners = new LinkedList();


    /*
     * Construtor 
     */
    public Projeto() {
    }

    public Projeto(Integer prjCodigo, String prjDescricao) {
        this.setPrjCodigo(prjCodigo);
        this.setPrjDescricao(prjDescricao);
    }

    /**
     * getters and setters
     */
    public Integer getPrjCodigo() {
        return prjCodigo;
    }

    public void setPrjCodigo(Integer prjCodigo) {
        this.prjCodigo = prjCodigo;
    }

    public String getPrjDescricao() {
        return prjDescricao;
    }

    public void setPrjDescricao(String prjDescricao) {
        if (prjDescricao == null) {
            throw new ProjetoException("Descrição do Projeto deve ter conteúdo.");
        }
        prjDescricao = prjDescricao.trim();
        if (prjDescricao.isEmpty()) {
            throw new ProjetoException("Descrição do Projeto não pode estar em branco.");
        }
        this.prjDescricao = prjDescricao;
    }

    /*
     * Adiciona uma nova Planta Baixa a um projeto
     */
    public void addPlantaBaixa(PlantaBaixa paramPlantaBaixa) {
        if (this.prjPlantaBaixa == null) {
            this.prjPlantaBaixa = new ArrayList<>();
        }
        this.prjPlantaBaixa.add(paramPlantaBaixa);
    }

    /*
     * Localiza a Planta Baixa no ArrayList de plantas baixas do projeto
     */
    public int findPlantaBaixa(PlantaBaixa paramPlantaBaixa) {
        int indexPB = this.prjPlantaBaixa.indexOf(paramPlantaBaixa);
        return indexPB;
    }

    /*
     * Remove uma Planta Baixa de um projeto
     */
    public void delPlantaBaixa(PlantaBaixa paramPlantaBaixa) {
        int indexPB = this.findPlantaBaixa(paramPlantaBaixa);
        this.prjPlantaBaixa.remove(indexPB);
    }

    /*
     * Retorna a primeira Planta Baixa de um projeto
     */
    public PlantaBaixa getFirstPlantaBaixa() {
        if (prjPlantaBaixa != null) {
            return this.prjPlantaBaixa.get(0);
        } else {
            return null;
        }
    }

    /*
     * Retorna ArrayList de Plantas Baixas do projeto
     */
    public ArrayList<PlantaBaixa> getAllPlantaBaixa() {
        if (prjPlantaBaixa != null) {
            return this.prjPlantaBaixa;
        } else {
            return null;
        }
    }
    /*
     * Define ArrayList de Plantas Baixas do projeto
     */

    public void setAllPlantaBaixa(ArrayList<PlantaBaixa> listPlantaBaixa) {
        if (listPlantaBaixa != null) {
            this.prjPlantaBaixa = listPlantaBaixa;
        }
    }

    // Sobrescrevendo o ToString do Objeto
    @Override
    public String toString() {
        StringBuilder stringRetorno = new StringBuilder();
        stringRetorno.append("\n==== Projeto ====");
        if (this.getPrjCodigo() == 0) {
            stringRetorno.append("\nSem Codigo!\n");
            stringRetorno.append("\nDescricao: ").append(this.prjDescricao);
        } else {
            stringRetorno.append("\nCodigo: ").append(String.valueOf(this.getPrjCodigo()));
            stringRetorno.append("\nDescricao: ").append(this.getPrjDescricao());
            if (this.prjPlantaBaixa != null) {
                for (PlantaBaixa p : prjPlantaBaixa) {
                    stringRetorno.append(p.toString());
                }
            }
        }
        stringRetorno.append("\n==================");
        return stringRetorno.toString();
    }

    @Override
    public Group geraGrupoVisualizacao() {
        Group grupoVis = new Group();
        Text t0 = new Text(5, 10, "Projeto");
        t0.setFont(new Font(40d));
        t0.setFill(Color.RED);
        Text t1 = new Text(5, 50, this.getPrjCodigo().toString());
        t1.setFont(new Font(30d));
        Text t2 = new Text(5, 90, this.getPrjDescricao());
        t2.setFont(new Font(30d));
        grupoVis.getChildren().addAll(t0, t1, t2);
        int i = 110;
        // Verifica se há Plantas Baixas no Projeto
        if (this.getAllPlantaBaixa() != null) {
            // Relaciona as plantas baixas.
            Text t3 = new Text(10, i, "Plantas Baixas");
            t3.setFill(Color.ORANGE);
            t3.setFont(new Font(30d));
            grupoVis.getChildren().add(t3);
            i = i + 12;
            for (PlantaBaixa tempPb : this.getAllPlantaBaixa()) {
                Text t = new Text(15, i, tempPb.getPbCodigo().toString());
                t.setFont(new Font(30d));
                t.setTextAlignment(TextAlignment.CENTER);
                grupoVis.getChildren().add(t);
                i = i + 32;
            }
        }
        return grupoVis;
    }

    /**
     * Adiciona um ProjetoListener, um objeto interessado em receber eventos
     * lançados pelo Projeto
     *
     * @see ProjetoListener
     * @param listener
     */
    public synchronized void addProjetoListener(ProjetoListener listener) {
        if (projetoListeners.contains(listener)) {
            return;
        }
        this.projetoListeners.add(listener);
    }

    public synchronized void disparaAlteracaoStatusProjeto() {
        for (ProjetoListener listeners : this.projetoListeners) {
            listeners.novoStatusProjeto(new ProjetoEvent(this));
        }
    }

}
