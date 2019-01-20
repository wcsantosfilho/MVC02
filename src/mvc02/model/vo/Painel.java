/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.vo;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mvc02.view.GrupoVisualiz;

/**
 *
 * @author wcsan_000
 */
public class Painel implements GrupoVisualiz {

    private Integer pnCodigo;       // Codigo do Painel (ID)
    private Integer pnEspessura; // Espessura do Painel (em mm)
    private Integer pnAltura;     // Altura do Painel (em mm)
    private Integer pbCodigo;       // Codigo da planta baixa a qual pertence o painel
    private Coordenada pnPos1; // Coordenada 1
    private Coordenada pnPos2; // Coordenada 2
    private Coordenada pnPos3; // Coordenada 3
    private Coordenada pnPos4; // Coordenada 4 - As 4 coordenadas definirão um polígono que representa o painel na planta baixa
    private ArrayList<Abertura> pnAbertura; // composição entre Abertura(0..*) e o Painel
    private ArrayList<Perfil> pnPerfil; // composição entre Perfil(0..*) e o Painel
    // private ArrayList<Trelica> pnTrelica; // composição entre Treliça(0..*) e o Painel
    private Byte pnTokenSequencia;   // Indica sequencia de inclusão dos elementos no painel
    // 0: Inicio
    // 1: Treliças Incluídas
    // 2: Abertura Incluídas
    // 3: Perfil Incluídos

    public Painel() {
    }

    public Painel(Integer pnCodigo, Integer pnEspessura, Integer pnAltura, Integer pbCodigo, Coordenada pnPos1, Coordenada pnPos2, Coordenada pnPos3, Coordenada pnPos4) {
        this.pnCodigo = pnCodigo;
        this.pnEspessura = pnEspessura;
        this.pnAltura = pnAltura;
        this.pbCodigo = pbCodigo;
        this.pnPos1 = pnPos1;
        this.pnPos2 = pnPos2;
        this.pnPos3 = pnPos3;
        this.pnPos4 = pnPos4;
    }

    public Integer getPnCodigo() {
        return pnCodigo;
    }

    public void setPnCodigo(Integer pnCodigo) {
        this.pnCodigo = pnCodigo;
    }

    public Integer getPnEspessura() {
        return pnEspessura;
    }

    public void setPnEspessura(Integer pnEspessura) {
        this.pnEspessura = pnEspessura;
    }

    public Integer getPnAltura() {
        return pnAltura;
    }

    public void setPnAltura(Integer pnAltura) {
        this.pnAltura = pnAltura;
    }

    public Integer getPbCodigo() {
        return pbCodigo;
    }

    public void setPbCodigo(Integer pbCodigo) {
        this.pbCodigo = pbCodigo;
    }

    public Byte getPnTokenSequencia() {
        return pnTokenSequencia;
    }

    /**
     *
     * getters and setters
     */
    public void setPnTokenSequencia(Byte pnTokenSequencia) {
        this.pnTokenSequencia = pnTokenSequencia;
    }

    public Coordenada getPnPos1() {
        return pnPos1;
    }

    public void setPnPos1(Coordenada pnPos1) {
        this.pnPos1 = pnPos1;
    }

    public Coordenada getPnPos2() {
        return pnPos2;
    }

    public void setPnPos2(Coordenada pnPos2) {
        this.pnPos2 = pnPos2;
    }

    public Coordenada getPnPos3() {
        return pnPos3;
    }

    public void setPnPos3(Coordenada pnPos3) {
        this.pnPos3 = pnPos3;
    }

    public Coordenada getPnPos4() {
        return pnPos4;
    }

    public void setPnPos4(Coordenada pnPos4) {
        this.pnPos4 = pnPos4;
    }

    public ArrayList<Abertura> getPnAbertura() {
        return pnAbertura;
    }

    public void setPnAbertura(ArrayList<Abertura> pnAbertura) {
        this.pnAbertura = pnAbertura;
    }

    public ArrayList<Perfil> getPnPerfil() {
        return pnPerfil;
    }

    public void setPnPerfil(ArrayList<Perfil> pnPerfil) {
        this.pnPerfil = pnPerfil;
    }


    /*
     * Adiciona um nova Abertura a um Painel
     */
    public void addAbertura(Abertura paramAbertura) {
        if (this.pnAbertura == null) {
            this.pnAbertura = new ArrayList<>();
        }
        this.pnAbertura.add(paramAbertura);
    }

    /*
     * Localiza a Abertura no ArrayList de paineis do projeto
     */
    public int findAbertura(Abertura paramAbertura) {
        int indexAB = this.pnAbertura.indexOf(paramAbertura);
        return indexAB;
    }

    /*
     * Remove uma Abertura de um Painel
     */
    public void delAbertura(Abertura paramAbertura) {
        int indexAB = this.findAbertura(paramAbertura);
        this.pnAbertura.remove(indexAB);
    }

    /*
     * Retorna a primeira Abertura de um Painel
     */
    public Abertura getFirstAbertura() {
        if (pnAbertura != null) {
            return this.pnAbertura.get(0);
        } else {
            return null;
        }
    }

    /*
     * Retorna ArrayList de Abertura do Painel
     */
    public ArrayList<Abertura> getAllAbertura() {
        if (pnAbertura != null) {
            return this.pnAbertura;
        } else {
            return null;
        }
    }

    /*
     * Adiciona um novo Perfil a um Painel
     */
    public void addPerfil(Perfil paramPerfil) {
        if (this.pnPerfil == null) {
            this.pnPerfil = new ArrayList<>();
        }
        this.pnPerfil.add(paramPerfil);
    }

    /*
     * Localiza o Perfil no ArrayList de paineis do projeto
     */
    public int findPerfil(Perfil paramPerfil) {
        int indexPN = this.pnPerfil.indexOf(paramPerfil);
        return indexPN;
    }

    /*
     * Remove um Perfil de um Painel
     */
    public void delPerfil(Perfil paramPerfil) {
        int indexPB = this.findPerfil(paramPerfil);
        this.pnPerfil.remove(indexPB);
    }

    /*
     * Retorna o primeiro Perfil de um Painel
     */
    public Perfil getFirstPerfil() {
        if (pnPerfil != null) {
            return this.pnPerfil.get(0);
        } else {
            return null;
        }
    }

    /*
     * Retorna ArrayList de Perfil do Painel
     */
    public ArrayList<Perfil> getAllPerfil() {
        if (pnPerfil != null) {
            return this.pnPerfil;
        } else {
            return null;
        }
    }

    public Integer getPnMaiorLargura() {
        Integer painelLargura;

        Integer X1 = this.getPnPos1().getX();
        Integer Y1 = this.getPnPos1().getY();
        Integer X2 = this.getPnPos2().getX();
        Integer Y2 = this.getPnPos2().getY();
        Integer X3 = this.getPnPos3().getX();
        Integer Y3 = this.getPnPos3().getY();
        Integer X4 = this.getPnPos4().getX();
        Integer Y4 = this.getPnPos4().getY();
        Integer eixo1a2 = X2 - X1;
        Integer eixo2a3 = Y3 - Y2;
        Integer eixo3a4 = X3 - X4;
        Integer eixo4a1 = Y4 - Y1;
        if (eixo1a2 > eixo2a3) {
            painelLargura = eixo1a2;
        } else {
            painelLargura = eixo2a3;
        }
        return painelLargura;
    }

    @Override
    public Group geraGrupoVisualizacao() {
        Group grpVis = new Group();

        // Limpa o grupo com a Painel para carregar novamente
        grpVis.getChildren().clear();
        // Desenha o Painel (poligono com a ALtura e Largura do Painel)
        Polygon polyPainel = new Polygon();
        polyPainel.setFill(Color.LIGHTYELLOW);
        polyPainel.setStrokeType(StrokeType.INSIDE);
        polyPainel.getStrokeDashArray().addAll(3.0, 3.0, 1.0, 7.0);
        polyPainel.setStroke(Color.RED);
        polyPainel.setStrokeWidth(1.5);
        polyPainel.setOpacity(1.0);
        double painelLargura = this.getPnMaiorLargura();
        double painelAltura = this.getPnAltura();
        polyPainel.getPoints().addAll(new Double[]{
            0.0, 0.0,
            painelLargura, 0.0,
            painelLargura, painelAltura,
            0.0, painelAltura
        }
        );
        grpVis.getChildren().add(polyPainel);

//        // Cria Label para identificar o elemento
//        Label lblPainel = new Label();
//        lblPainel.setLabelFor(polyPainel);
//        lblPainel.setText("Pn: " + this.getPnCodigo().toString());
//        Integer tamFonte = this.getPnAltura();
//        if (tamFonte > 20) {
//            tamFonte = 20;
//        } else {
//            tamFonte = Math.round(tamFonte * 0.8f);
//        }
//        lblPainel.setFont(Font.font("Calibri", FontWeight.BOLD, tamFonte));
//        lblPainel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
//        lblPainel.setTextAlignment(TextAlignment.CENTER);
//        lblPainel.setTextOverrun(OverrunStyle.CLIP);
//        lblPainel.setTranslateX(this.getPnAltura() / 2); // Centraliza o Label do Painel
//        lblPainel.setTranslateY(this.getPnMaiorLargura() / 2);
//        grpVis.getChildren().add(lblPainel);
        // Verifica se existem Aberturas associadas ao Painel
        if (this.getPnAbertura() != null) {
            /*            
             * Desenha no grupo as Aberturas que pertencem ao Painel 
             * Lembrete: Aberturas contem um vão livre + painéis (normalmente no entorno)
             */
            for (Abertura tempAb : this.getAllAbertura()) {
                Polygon polyAbertura = new Polygon();
                polyAbertura.setStrokeType(StrokeType.INSIDE);
                polyAbertura.setStroke(Color.BLACK);
                polyAbertura.setStrokeWidth(1.0);
                polyAbertura.setFill(Color.LIGHTGOLDENRODYELLOW);
                polyAbertura.setOpacity(0.55);
                double pontoX1 = tempAb.getAbPos().getX().doubleValue();
                double pontoY1 = tempAb.getAbPos().getY().doubleValue();
                double pontoX2 = pontoX1 + tempAb.getAbLargura();
                double pontoY2 = pontoY1;
                double pontoX3 = pontoX2;
                double pontoY3 = pontoY1 + tempAb.getAbAltura();
                double pontoX4 = pontoX1;
                double pontoY4 = pontoY3;
                polyAbertura.getPoints().addAll(new Double[]{
                    pontoX1, pontoY1,
                    pontoX2, pontoY2,
                    pontoX3, pontoY3,
                    pontoX4, pontoY4
                });
                grpVis.getChildren().add(polyAbertura);

                // Cria Label para identificar o elemento
                Label lblAbertura = new Label();
                lblAbertura.setLabelFor(polyPainel);
                lblAbertura.setText("Ab: " + tempAb.getAbCodigo().toString());
                Integer tamFonteAb = this.getPnAltura();
                if (tamFonteAb > 20) {
                    tamFonteAb = 20;
                } else {
                    tamFonteAb = Math.round(tamFonteAb * 0.8f);
                }
                lblAbertura.setFont(Font.font("Calibri", FontWeight.BOLD, tamFonteAb));
                lblAbertura.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                lblAbertura.setTextAlignment(TextAlignment.CENTER);
                lblAbertura.setTextOverrun(OverrunStyle.CLIP);
                lblAbertura.setTranslateX(pontoX1 + tempAb.getAbLargura() / 2); // Centraliza o Label da Abertura
                lblAbertura.setTranslateY(pontoY1 + tempAb.getAbAltura() / 2);
                grpVis.getChildren().add(lblAbertura);

                // Verifica Perfis associado a cada Abertura
                if (tempAb.getAbPerfil() != null) {
                    // Desenha no grupo os Perfis que pertencem ao Painel
                    for (Perfil tempPf : tempAb.getAllPerfil()) {
                        Polygon polyPerfil = new Polygon();
                        polyPerfil.setStrokeType(StrokeType.INSIDE);
                        polyPerfil.setStroke(Color.BLACK);
                        polyPerfil.setStrokeWidth(1.0);
                        polyPerfil.setFill(Color.LIGHTPINK);
                        polyPerfil.setOpacity(0.55);
                        Double pontoPfX1;
                        Double pontoPfX2;
                        Double pontoPfX3;
                        Double pontoPfX4;
                        Double pontoPfY1;
                        Double pontoPfY2;
                        Double pontoPfY3;
                        Double pontoPfY4;
                        if (tempPf.getPfOrientacao() == 90) {
                            pontoPfX1 = tempPf.getPfPosIni().getX().doubleValue() + tempAb.getAbPos().getX().doubleValue();
                            pontoPfY1 = tempPf.getPfPosIni().getY().doubleValue() + tempAb.getAbPos().getY().doubleValue();
                            pontoPfX2 = pontoPfX1 + tempPf.getPfFace();
                            pontoPfY2 = pontoPfY1;
                            pontoPfX3 = pontoPfX2;
                            pontoPfY3 = pontoPfY1 + tempPf.getPfDimensao();
                            pontoPfX4 = pontoPfX1;
                            pontoPfY4 = pontoPfY3;
                        } else {
                            pontoPfX1 = tempPf.getPfPosIni().getX().doubleValue() + tempAb.getAbPos().getX().doubleValue();
                            pontoPfY1 = tempPf.getPfPosIni().getY().doubleValue() + tempAb.getAbPos().getY().doubleValue();
                            pontoPfX2 = pontoPfX1 + tempPf.getPfDimensao();
                            pontoPfY2 = pontoPfY1;
                            pontoPfX3 = pontoPfX2;
                            pontoPfY3 = pontoPfY1 + tempPf.getPfFace();
                            pontoPfX4 = pontoPfX1;
                            pontoPfY4 = pontoPfY3;
                        }

                        polyPerfil.getPoints().addAll(new Double[]{
                            pontoPfX1, pontoPfY1,
                            pontoPfX2, pontoPfY2,
                            pontoPfX3, pontoPfY3,
                            pontoPfX4, pontoPfY4
                        });

                        grpVis.getChildren().add(polyPerfil);

                        // Cria Label para identificar o elemento
                        Label lblPerfil = new Label();
                        lblPerfil.setLabelFor(polyPainel);
                        lblPerfil.setText("Pf: " + tempPf.getPfCodigo().toString());
                        Integer tamFontePf = tempPf.getPfDimensao();
                        if (tamFontePf > 20) {
                            tamFontePf = 20;
                        } else {
                            tamFontePf = Math.round(tamFontePf * 0.8f);
                        }
                        lblPerfil.setFont(Font.font("Calibri", FontWeight.BOLD, tamFontePf));
                        lblPerfil.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        lblPerfil.setTextAlignment(TextAlignment.CENTER);
                        lblPerfil.setTextOverrun(OverrunStyle.CLIP);
                        lblPerfil.setTranslateX(pontoPfX1 + 3.0);
                        lblPerfil.setTranslateY(pontoPfY1 + 3.0);
                        grpVis.getChildren().add(lblPerfil);

                    }
                }
            }

        }
        // Verifica se existem Perfis associados ao Painel
        if (this.getPnPerfil() != null) {
            // Desenha no grupo os Perfis que pertencem ao Painel
            for (Perfil tempPf : this.getAllPerfil()) {
                Polygon polyPerfil = new Polygon();
                polyPerfil.setStrokeType(StrokeType.INSIDE);
                polyPerfil.setStroke(Color.BLACK);
                polyPerfil.setStrokeWidth(1.0);
                polyPerfil.setFill(Color.LIGHTGREEN);
                polyPerfil.setOpacity(0.55);
                Double pontoX1;
                Double pontoX2;
                Double pontoX3;
                Double pontoX4;
                Double pontoY1;
                Double pontoY2;
                Double pontoY3;
                Double pontoY4;
                if (tempPf.getPfOrientacao() == 90) {
                    pontoX1 = tempPf.getPfPosIni().getX().doubleValue();
                    pontoY1 = tempPf.getPfPosIni().getY().doubleValue();
                    pontoX2 = pontoX1 + tempPf.getPfFace();
                    pontoY2 = pontoY1;
                    pontoX3 = pontoX2;
                    pontoY3 = pontoY1 + tempPf.getPfDimensao();
                    pontoX4 = pontoX1;
                    pontoY4 = pontoY3;
                } else {
                    pontoX1 = tempPf.getPfPosIni().getX().doubleValue();
                    pontoY1 = tempPf.getPfPosIni().getY().doubleValue();
                    pontoX2 = pontoX1 + tempPf.getPfDimensao();
                    pontoY2 = pontoY1;
                    pontoX3 = pontoX2;
                    pontoY3 = pontoY1 + tempPf.getPfFace();
                    pontoX4 = pontoX1;
                    pontoY4 = pontoY3;
                }
                polyPerfil.getPoints().addAll(new Double[]{
                    pontoX1, pontoY1,
                    pontoX2, pontoY2,
                    pontoX3, pontoY3,
                    pontoX4, pontoY4
                });
                grpVis.getChildren().add(polyPerfil);

                // Cria Label para identificar o elemento
                Label lblPerfil = new Label();
                lblPerfil.setLabelFor(polyPainel);
                lblPerfil.setText("Pf: " + tempPf.getPfCodigo().toString());
                Integer tamFontePf = tempPf.getPfDimensao();
                if (tamFontePf > 20) {
                    tamFontePf = 20;
                } else {
                    tamFontePf = Math.round(tamFontePf * 0.8f);
                }
                lblPerfil.setFont(Font.font("Calibri", FontWeight.BOLD, tamFontePf));
                lblPerfil.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                lblPerfil.setTextAlignment(TextAlignment.CENTER);
                lblPerfil.setTextOverrun(OverrunStyle.CLIP);
                lblPerfil.setTranslateX(pontoX1 + 3.0);
                lblPerfil.setTranslateY(pontoY1 + 3.0);
                grpVis.getChildren().add(lblPerfil);

            }

            /*
             * Adiciona retangulo no fundo, preenchido com cor para ajudar no drap & drop
             */
            Color corRectFundo = Color.MINTCREAM;
            Rectangle rectFundoGrupo = new Rectangle(grpVis.getBoundsInLocal().getMaxX(), grpVis.getBoundsInLocal().getMaxY(), corRectFundo);
            grpVis.getChildren().add(0, rectFundoGrupo); // Adiciona no node '0' para ficar no fundo.
        } else {
            Text textoAviso = new Text(10, 10, "Este Painel não tem Perfis ou Aberturas definidos. Verifique!");
            textoAviso.setTextAlignment(TextAlignment.LEFT);
            textoAviso.setTextOrigin(VPos.TOP);
            textoAviso.setFont(new Font("Arial", 8));
            Color corRectFundo = Color.MINTCREAM;
            Rectangle rectFundoGrupo = new Rectangle(grpVis.getBoundsInLocal().getMaxX(), grpVis.getBoundsInLocal().getMaxY(), corRectFundo);
            grpVis.getChildren().addAll(rectFundoGrupo, textoAviso);
        }
        return grpVis;
    }

}
