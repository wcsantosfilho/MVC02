/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.vo;

import java.util.ArrayList;

/**
 *
 * @author wcsan_000
 */
public class Abertura {

    private Integer abCodigo;       // Codigo da abertura
    private Integer pnCodigo;       // Codigo do painel a que pertence a abertura
    private Coordenada abPos; // Coordenada 1 do canto superior direito da abertura no painel
    private Integer abLargura; // largura total abertura
    private Integer abAltura; // altura total da abertura
    private ArrayList<Perfil> abPerfil; // composição entre Abertura(0..*) e os Perfis que a compõe

    public Abertura() {
    }

    public Abertura(Integer abCodigo, Integer pnCodigo, Coordenada abPos) {
        this.abCodigo = abCodigo;
        this.pnCodigo = pnCodigo;
        this.abPos = abPos;
    }

    public Abertura(Integer abCodigo, Integer pnCodigo, Coordenada abPos, Integer abLargura, Integer abAltura, ArrayList<Perfil> abPerfil) {
        this.abCodigo = abCodigo;
        this.pnCodigo = pnCodigo;
        this.abPos = abPos;
        this.abLargura = abLargura;
        this.abAltura = abAltura;
        this.abPerfil = abPerfil;
    }

    public Integer getAbCodigo() {
        return abCodigo;
    }

    public void setAbCodigo(Integer abCodigo) {
        this.abCodigo = abCodigo;
    }

    public Integer getPnCodigo() {
        return pnCodigo;
    }

    public void setPnCodigo(Integer pnCodigo) {
        this.pnCodigo = pnCodigo;
    }

    public Coordenada getAbPos() {
        return abPos;
    }

    public void setAbPos(Coordenada abPos) {
        this.abPos = abPos;
    }

    public Integer getAbLargura() {
        return abLargura;
    }

    public void setAbLargura(Integer abLargura) {
        this.abLargura = abLargura;
    }

    public Integer getAbAltura() {
        return abAltura;
    }

    public void setAbAltura(Integer abAltura) {
        this.abAltura = abAltura;
    }


    public ArrayList<Perfil> getAbPerfil() {
        return abPerfil;
    }

    public void setAbPerfil(ArrayList<Perfil> abPerfil) {
        this.abPerfil = abPerfil;
    }

    /*
     * Adiciona um novo Perfil a uma Abertura
     */
    public void addPerfil(Perfil paramPerfil) {
        if (this.abPerfil == null) {
            this.abPerfil = new ArrayList<>();
        }
        this.abPerfil.add(paramPerfil);
    }

    /*
     * Localiza o Perfil no ArrayList de paineis do projeto
     */
    public int findPerfil(Perfil paramPerfil) {
        int indexPN = this.abPerfil.indexOf(paramPerfil);
        return indexPN;
    }

    /*
     * Remove um Perfil de uma Abertura
     */
    public void delPerfil(Perfil paramPerfil) {
        int indexPB = this.findPerfil(paramPerfil);
        this.abPerfil.remove(indexPB);
    }

    /*
     * Retorna o primeiro Perfil de uma Abertura
     */
    public Perfil getFirstPerfil() {
        if (abPerfil != null && !abPerfil.isEmpty()) {
            return this.abPerfil.get(0);
        } else {
            return null;
        }
    }

    /*
     * Retorna ArrayList de Perfil da Abertura
     */
    public ArrayList<Perfil> getAllPerfil() {
        if (abPerfil != null) {
            return this.abPerfil;
        } else {
            return null;
        }
    }


}
