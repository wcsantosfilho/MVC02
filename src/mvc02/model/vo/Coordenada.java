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
public class Coordenada {
    private Integer X;
    private Integer Y;

    public Coordenada(Integer X, Integer Y) {
        this.X = X;
        this.Y = Y;
    }

    public Integer getX() {
        return X;
    }

    public void setX(Integer X) {
        this.X = X;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer Y) {
        this.Y = Y;
    }
    
    // Sobrescrevendo o ToString do Objeto
    @Override
    public String toString() {
        StringBuilder stringRetorno = new StringBuilder();
        if (this.getX() != null && this.getY() != null) {
            stringRetorno.append(this.getX());
            stringRetorno.append(", ");
            stringRetorno.append(this.getY());
        }
        return stringRetorno.toString();
    }    
}
