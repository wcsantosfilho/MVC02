/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.vo;

/**
 *
 * @author wcsan_000
 */
public class Perfil {
    private Integer pfCodigo;   // Codigo do Perfil (ID)
    private Integer pnCodigo;     // Codigo do painel ao qual pertence
    private String pfDesignacao; // Tipo do perfil (U - U simples, Ue - U enrijecido, Cr - Cartola, L - Cantoneira de lados desiguais)
    private Integer pfOrientacao; // em graus em relação ao solo (90* = vertical, 0* = horiontal, 45* = diagonal)
    private Coordenada pfPosIni; // coordenada inicial em relacão a origem do painel (canto superior esquerdo)
    private Integer pfFace;      // tamanho em mm da Face do perfil - porção visível onde se fixam os revestimentos
    private Integer pfDimensao;  // comprimento do perfil, em mm

    public Perfil() {
    }

    public Perfil(Integer pfCodigo, Integer pnCodigo, String pfDesignacao, Integer pfOrientacao, Coordenada pfPosIni, Integer pfFace, Integer pfDimensao) {
        this.pfCodigo = pfCodigo;
        this.pnCodigo = pnCodigo;
        this.pfDesignacao = pfDesignacao;
        this.pfOrientacao = pfOrientacao;
        this.pfPosIni = pfPosIni;
        this.pfFace = pfFace;
        this.pfDimensao = pfDimensao;
    }

    public Integer getPfCodigo() {
        return pfCodigo;
    }

    public void setPfCodigo(Integer pfCodigo) {
        this.pfCodigo = pfCodigo;
    }

    public Integer getPnCodigo() {
        return pnCodigo;
    }

    public void setPnCodigo(Integer pnCodigo) {
        this.pnCodigo = pnCodigo;
    }

    public String getPfDesignacao() {
        return pfDesignacao;
    }

    public void setPfDesignacao(String pfDesignacao) {
        this.pfDesignacao = pfDesignacao;
    }

    public Integer getPfOrientacao() {
        return pfOrientacao;
    }

    public void setPfOrientacao(Integer pfOrientacao) {
        this.pfOrientacao = pfOrientacao;
    }

    public Coordenada getPfPosIni() {
        return pfPosIni;
    }

    public void setPfPosIni(Coordenada pfPosIni) {
        this.pfPosIni = pfPosIni;
    }


    public Integer getPfFace() {
        return pfFace;
    }

    public void setPfFace(Integer pfFace) {
        this.pfFace = pfFace;
    }

    public Integer getPfDimensao() {
        return pfDimensao;
    }

    public void setPfDimensao(Integer pfDimensao) {
        this.pfDimensao = pfDimensao;
    }

    
}
