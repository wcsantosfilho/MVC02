/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.vo;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
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
public class PlantaBaixa implements GrupoVisualiz {

    private Integer pbCodigo;   // Codigo da Planta Baixa (ID)
    private Byte pbSeqPiso;  // Sequencia de Pisos no Projeto (0=Terreo, +1=1oandar, +2=2oandar, -1=1o subsolo)
    private Integer prjCodigo;  // Codigo do projeto a qual a planta baixa pertence
    private Integer pbPosX;  // Posicao X,Y,Z do canto superior esquerdo do piso no projeto
    private Integer pbPosY;
    private Integer pbPosZ;  // Z = altura desta planta baixa sobre o terreno(?) ou piso anterior(?)
    private Integer pbAreaPlantaBaixa; // aream em mm2 da planta baixa
    private ArrayList<Painel> pbPainel; // composição entre Painéis e Plantas Baixas

    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
    double orgTranslateY;
    double origemX;
    double origemY;
    int pointMinorDist = 0;     // Ponto do polygono selecionado para ser movido
    int modoOperacaoCursor = 0; // Modo do Cursor: [0=Move objetos e painel/1=Move Pontos Polygonos/2=Move Arestas]

    // Construtores
    public PlantaBaixa() {
    }

    ;
    public PlantaBaixa(Integer pbCodigo, Byte pbSeqPiso, Integer prjCodigo, Integer pbPosX, Integer pbPosY, Integer pbPosZ, Integer pbAreaPlantaBaixa) {
        this.pbCodigo = pbCodigo;
        this.pbSeqPiso = pbSeqPiso;
        this.prjCodigo = prjCodigo;
        this.pbPosX = pbPosX;
        this.pbPosY = pbPosY;
        this.pbPosZ = pbPosZ;
        this.pbAreaPlantaBaixa = pbAreaPlantaBaixa;
    }

    public Integer getPbCodigo() {
        return pbCodigo;
    }

    public void setPbCodigo(Integer pbCodigo) {
        this.pbCodigo = pbCodigo;
    }

    public Byte getPbSeqPiso() {
        return pbSeqPiso;
    }

    public void setPbSeqPiso(Byte pbSeqPiso) {
        this.pbSeqPiso = pbSeqPiso;
    }

    public Integer getPrjCodigo() {
        return prjCodigo;
    }

    public void setPrjCodigo(Integer prjCodigo) {
        this.prjCodigo = prjCodigo;
    }

    public Integer getPbPosX() {
        return pbPosX;
    }

    public void setPbPosX(Integer pbPosX) {
        this.pbPosX = pbPosX;
    }

    public Integer getPbPosY() {
        return pbPosY;
    }

    public void setPbPosY(Integer pbPosY) {
        this.pbPosY = pbPosY;
    }

    public Integer getPbPosZ() {
        return pbPosZ;
    }

    public void setPbPosZ(Integer pbPosZ) {
        this.pbPosZ = pbPosZ;
    }

    public Integer getPbAreaPlantaBaixa() {
        return pbAreaPlantaBaixa;
    }

    /*
     * Getters & Setters
     */
    public void setPbAreaPlantaBaixa(Integer pbAreaPlantaBaixa) {
        this.pbAreaPlantaBaixa = pbAreaPlantaBaixa;
    }

    public ArrayList<Painel> getPbPainel() {
        return pbPainel;
    }

    public void setPbPainel(ArrayList<Painel> pbPainel) {
        this.pbPainel = pbPainel;
    }

    /*
     * Adiciona um novo Painel a uma Planta Baixa
     */
    public void addPainel(Painel paramPainel) {
        if (this.pbPainel == null) {
            this.pbPainel = new ArrayList<>();
        }
        this.pbPainel.add(paramPainel);
    }

    /*
     * Localiza o Painel no ArrayList de plantas baixas do projeto
     */
    public int findPainel(Painel paramPainel) {
        int indexPN = this.pbPainel.indexOf(paramPainel);
        return indexPN;
    }

    /*
     * Localiza o Painel, pelo Codigo, no ArrayList de plantas baixas do projeto
     */
    public int findPainelByCodigo(Integer pnCodigo) {
        if (this.getPbPainel() != null) // Verifica se há painéis na Planta Baixa 
        {
            int countPainel = 0;
            for (Painel tempPn : this.getAllPainel()) {
                if (tempPn.getPnCodigo().equals(pnCodigo)) {
                    return countPainel;
                }
                countPainel++;
            }
        }
        return 0;
    }

    /*
     * Remove um Painel de uma Planta Baixa
     */
    public void delPainel(Painel paramPainel) {
        int indexPB = this.findPainel(paramPainel);
        this.pbPainel.remove(indexPB);
    }

    /*
     * Retorna o primeira Painel de uma Planta Baixa
     */
    public Painel getFirstPainel() {
        if (pbPainel != null) {
            return this.pbPainel.get(0);
        } else {
            return null;
        }
    }
    /*
     * Retorna um Painel, pela posição na lista, de uma Planta Baixa
     */
    public Painel getPainel(Integer posPainel) {
        if (pbPainel != null) {
            return this.pbPainel.get(posPainel);
        } else {
            return null;
        }
    }

    /*
     * Retorna ArrayList de Plantas Baixas do projeto
     */
    public ArrayList<Painel> getAllPainel() {
        if (pbPainel != null) {
            return this.pbPainel;
        } else {
            return null;
        }
    }

// Sobrescrevendo o ToString do Objeto
    @Override
    public String toString() {
        StringBuilder stringRetorno = new StringBuilder();
        stringRetorno.append("\n=== Planta Baixa ====");
        stringRetorno.append("\nCodigo: ").append(String.valueOf(this.pbCodigo));
        stringRetorno.append("\nSeq Piso: ").append(String.valueOf(this.pbSeqPiso));
        stringRetorno.append("\nCod Projeto: ").append(String.valueOf(this.prjCodigo));
        stringRetorno.append("\nPos X: ").append(String.valueOf(this.pbPosX));
        stringRetorno.append("\nPos Y: ").append(String.valueOf(this.pbPosY));
        stringRetorno.append("\nPos Z: ").append(String.valueOf(this.pbPosZ));
        stringRetorno.append("\n=====================");
        return stringRetorno.toString();
    }

    @Override
    public Group geraGrupoVisualizacao() {
        Group grpVis = new Group();
        PlantaBaixa estaPlantaBaixa = this;

        // Limpa o grupo com a Planta Baixa para carregar novamente
        grpVis.getChildren().clear();
        // Verifica se há painéis na Planta Baixa
        if (this.getPbPainel() != null) {
            // Desenha no grupo Planta Baixa os Paineis inseridos.
            for (Painel tempPn : this.getAllPainel()) {
                Polygon polyPainel = new Polygon();
                polyPainel.setStrokeType(StrokeType.INSIDE);
                polyPainel.setStroke(Color.BLACK);
                polyPainel.setStrokeWidth(1.0);
                polyPainel.setFill(Color.LIGHTSTEELBLUE);
                polyPainel.setOpacity(0.85);
                polyPainel.getPoints().addAll(new Double[]{
                    tempPn.getPnPos1().getX().doubleValue(), tempPn.getPnPos1().getY().doubleValue(),
                    tempPn.getPnPos2().getX().doubleValue(), tempPn.getPnPos2().getY().doubleValue(),
                    tempPn.getPnPos3().getX().doubleValue(), tempPn.getPnPos3().getY().doubleValue(),
                    tempPn.getPnPos4().getX().doubleValue(), tempPn.getPnPos4().getY().doubleValue()
                });
                grpVis.getChildren().add(polyPainel);

                // Cria Label para identificar o elemento
                Label lblPainel = new Label();
                lblPainel.setLabelFor(polyPainel);
                lblPainel.setText("Pn: " + tempPn.getPnCodigo().toString());
                polyPainel.setId(tempPn.getPnCodigo().toString());
                Integer tamFonte = tempPn.getPnPos3().getY() - tempPn.getPnPos1().getY();
                if (tamFonte > 20) {
                    tamFonte = 20;
                } else {
                    tamFonte = Math.round(tamFonte * 0.8f);
                }
                lblPainel.setFont(Font.font("Calibri", FontWeight.BOLD, tamFonte));
                lblPainel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                lblPainel.setTextAlignment(TextAlignment.CENTER);
                lblPainel.setTextOverrun(OverrunStyle.CLIP);
                lblPainel.setTranslateX(tempPn.getPnPos1().getX().doubleValue() + 3.0);
                lblPainel.setTranslateY(tempPn.getPnPos1().getY().doubleValue() + 3.0);
                grpVis.getChildren().add(lblPainel);
                /*
                 * Adicionar listener para fazer Drag&Drop do Painel na Planta Baixa
                 */
                polyPainel.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        ObservableList pointsList;
                        pointsList = ((Polygon) (t.getSource())).getPoints();
                        double distVert1;
                        double distVert2;
                        double distVert3;
                        double distVert4;
                        double pontoP1x = (double) pointsList.get(0);
                        double pontoP1y = (double) pointsList.get(1);
                        double pontoP2x = (double) pointsList.get(2);
                        double pontoP2y = (double) pointsList.get(3);
                        double pontoP3x = (double) pointsList.get(4);
                        double pontoP3y = (double) pointsList.get(5);
                        double pontoP4x = (double) pointsList.get(6);
                        double pontoP4y = (double) pointsList.get(7);

                        origemX = t.getX();
                        origemY = t.getY();
                        /*
                         * Calcula a distancia do Mouse no Polygono para os 4 Vértices do Polygono
                         */
                        distVert4 = Math.sqrt(Math.pow(((double) pointsList.get(6) - origemX), 2)
                                + Math.pow(((double) pointsList.get(7) - origemY), 2));
                        distVert3 = Math.sqrt(Math.pow(((double) pointsList.get(4) - origemX), 2)
                                + Math.pow(((double) pointsList.get(5) - origemY), 2));
                        distVert2 = Math.sqrt(Math.pow(((double) pointsList.get(2) - origemX), 2)
                                + Math.pow(((double) pointsList.get(3) - origemY), 2));
                        distVert1 = Math.sqrt(Math.pow(((double) pointsList.get(0) - origemX), 2)
                                + Math.pow(((double) pointsList.get(1) - origemY), 2));

                        /*
                         * Verifica a menor distancia e que seja menor que 10 pontos
                         */
                        pointMinorDist = 0;
                        if ((distVert1 < distVert2 && distVert1 < distVert3 && distVert1 < distVert4) && distVert1 <= 10) {
                            pointMinorDist = 1;
                        }
                        if ((distVert2 < distVert1 && distVert2 < distVert3 && distVert2 < distVert4) && distVert2 <= 10) {
                            pointMinorDist = 2;
                        }
                        if ((distVert3 < distVert2 && distVert3 < distVert1 && distVert3 < distVert4) && distVert3 <= 10) {
                            pointMinorDist = 3;
                        }
                        if ((distVert4 < distVert2 && distVert4 < distVert3 && distVert4 < distVert1) && distVert4 <= 10) {
                            pointMinorDist = 4;
                        }

                        switch (pointMinorDist) {
                            case 1:
                                ((Polygon) (t.getSource())).setCursor(Cursor.CROSSHAIR);
                                modoOperacaoCursor = 1;
                                break;
                            case 2:
                                ((Polygon) (t.getSource())).setCursor(Cursor.CROSSHAIR);
                                modoOperacaoCursor = 1;
                                break;
                            case 3:
                                ((Polygon) (t.getSource())).setCursor(Cursor.CROSSHAIR);
                                modoOperacaoCursor = 1;
                                break;
                            case 4:
                                ((Polygon) (t.getSource())).setCursor(Cursor.CROSSHAIR);
                                modoOperacaoCursor = 1;
                                break;
                            default:
                                ((Polygon) (t.getSource())).setCursor(Cursor.DEFAULT);
                                modoOperacaoCursor = 0;

                        }

                        /*
                         * Calcula se o mouse está sobre a reta entre os pontos 1 e 2 (aresta1 do polygono)
                         */
                        if ((pontoP1y == pontoP2y) && (origemY >= (pontoP1y - 10.0)) && (origemY <= (pontoP1y + 10.0))
                                && (origemX >= pontoP1x) && (origemX <= pontoP2x)
                                && (pointMinorDist == 0)) {
                            ((Polygon) (t.getSource())).setCursor(Cursor.V_RESIZE);
                            modoOperacaoCursor = 2;
                        }
                        /*
                         * Calcula se o mouse está sobre a reta entre os pontos 2 e 3 (aresta2 do polygono)
                         */
                        if ((pontoP2x == pontoP3x) && (origemX >= (pontoP2x - 10)) && (origemX <= (pontoP2x + 10))
                                && (origemY >= pontoP2y) && (origemY <= pontoP3y)
                                && (pointMinorDist == 0)) {
                            ((Polygon) (t.getSource())).setCursor(Cursor.H_RESIZE);
                            modoOperacaoCursor = 2;
                        }
                        /*
                         * Calcula se o mouse está sobre a reta entre os pontos 3 e 4 (aresta3 do polygono)
                         */
                        if ((pontoP3y == pontoP4y) && (origemY >= (pontoP3y - 10)) && (origemY <= (pontoP3y + 10))
                                && (origemX >= pontoP4x) && (origemX <= pontoP3x)
                                && (pointMinorDist == 0)) {
                            ((Polygon) (t.getSource())).setCursor(Cursor.V_RESIZE);
                            modoOperacaoCursor = 2;
                        }
                        /*
                         * Calcula se o mouse está sobre a reta entre os pontos 4 e 1 (aresta4 do polygono)
                         */
                        if ((pontoP4x == pontoP1x) && (origemX >= (pontoP4x - 10)) && (origemX <= (pontoP4x + 10))
                                && (origemY >= pontoP1y) && (origemY <= pontoP4y)
                                && (pointMinorDist == 0)) {
                            ((Polygon) (t.getSource())).setCursor(Cursor.H_RESIZE);
                            modoOperacaoCursor = 2;
                        }
                    }

                });

                /*
                 * Define handle do click para Arrastar-e-soltar o elemento
                 */
                polyPainel.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        /*
                         * Arrastar e soltar objetos = modoOperacao = 0
                         */
                        if (modoOperacaoCursor == 0) {
                            orgSceneX = t.getSceneX();
                            orgSceneY = t.getSceneY();
                            orgTranslateX = ((Polygon) (t.getSource())).getTranslateX();
                            orgTranslateY = ((Polygon) (t.getSource())).getTranslateY();
                        }
                        /*
                         * Alterar pontos do polygono = modoOperacao = 1
                         */
                        if (modoOperacaoCursor == 1) {
                            origemX = t.getX();
                            origemY = t.getY();
                        }
                        /*
                         * Mover arestas do polygono = modoOperacao = 2
                         */
                        if (modoOperacaoCursor == 2) {
                            origemX = t.getX();
                            origemY = t.getY();
                        }
                    }
                });

                /*
                 * Define handle do drag para Arrastar-e-soltar o grupo
                 */
                polyPainel.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        String polygonID;
                        Integer ixPainel;
                        Integer novoPontoX;
                        Integer novoPontoY;
                        Coordenada novaCoordenada;
                        /*
                         * Arrastar e soltar objetos = modoOperacao = 0
                         */
                        if (modoOperacaoCursor == 0) {
                            double offsetX = t.getSceneX() - orgSceneX;
                            double offsetY = t.getSceneY() - orgSceneY;
                            double newTranslateX = orgTranslateX + offsetX;
                            double newTranslateY = orgTranslateY + offsetY;

                            ((Polygon) (t.getSource())).setTranslateX(newTranslateX);
                            ((Polygon) (t.getSource())).setTranslateY(newTranslateY);
                            t.consume();
                        }
                        /*
                         * Alterar pontos (vertices) do polygono = modoOperacao = 1
                         */
                        if (modoOperacaoCursor == 1) {
                            ObservableList pointsList;
                            double offsetX = t.getX() - origemX;
                            double offsetY = t.getY() - origemY;
                            offsetX = Math.round(offsetX);

                            double newTranslateX = origemX + offsetX;
                            double newTranslateY = origemY + offsetY;
                            pointsList = ((Polygon) (t.getSource())).getPoints();
                            switch (pointMinorDist) {
                                case 1:
                                    // Define a nova posição do vértice no polygono (apenas visual)
                                    double p1X = (double) pointsList.get(0);
                                    pointsList.set(0, newTranslateX);
                                    double p1Y = (double) pointsList.get(1);
                                    pointsList.set(1, newTranslateY);
                                    // Busca o ID do polygono
                                    polygonID = ((Polygon) (t.getSource())).getId();
                                    // Busca o Painel na lista de painéis pelo ID
                                    ixPainel = estaPlantaBaixa.findPainelByCodigo(Integer.parseInt(polygonID));
                                    novoPontoX = (int) newTranslateX;
                                    novoPontoY = (int) newTranslateY;
                                    // Define a nova coordenada no vértice (no objeto)
                                    novaCoordenada = new Coordenada(novoPontoX,novoPontoY);
                                    // Altera o painel na lista da planta baixa com a nova coordenada
                                    estaPlantaBaixa.getPainel(ixPainel).setPnPos1(novaCoordenada);
                                    t.consume();
                                    break;
                                case 2:
                                    // Define a nova posição do vértice no polygono (apenas visual)
                                    double p2X = (double) pointsList.get(2);
                                    pointsList.set(2, newTranslateX);
                                    double p2Y = (double) pointsList.get(3);
                                    pointsList.set(3, newTranslateY);
                                    // Busca o ID do polygono
                                    polygonID = ((Polygon) (t.getSource())).getId();
                                    // Busca o Painel na lista de painéis pelo ID
                                    ixPainel = estaPlantaBaixa.findPainelByCodigo(Integer.parseInt(polygonID));
                                    novoPontoX = (int) newTranslateX;
                                    novoPontoY = (int) newTranslateY;
                                    // Define a nova coordenada no vértice (no objeto)
                                    novaCoordenada = new Coordenada(novoPontoX,novoPontoY);
                                    // Altera o painel na lista da planta baixa com a nova coordenada
                                    estaPlantaBaixa.getPainel(ixPainel).setPnPos2(novaCoordenada);
                                    t.consume();
                                    break;
                                case 3:
                                    double p3X = (double) pointsList.get(4);
                                    pointsList.set(4, newTranslateX);
                                    double p3Y = (double) pointsList.get(5);
                                    pointsList.set(5, newTranslateY);
                                    // Busca o ID do polygono
                                    polygonID = ((Polygon) (t.getSource())).getId();
                                    // Busca o Painel na lista de painéis pelo ID
                                    ixPainel = estaPlantaBaixa.findPainelByCodigo(Integer.parseInt(polygonID));
                                    novoPontoX = (int) newTranslateX;
                                    novoPontoY = (int) newTranslateY;
                                    // Define a nova coordenada no vértice (no objeto)
                                    novaCoordenada = new Coordenada(novoPontoX,novoPontoY);
                                    // Altera o painel na lista da planta baixa com a nova coordenada
                                    estaPlantaBaixa.getPainel(ixPainel).setPnPos3(novaCoordenada);
                                    t.consume();
                                    break;
                                case 4:
                                    double p4X = (double) pointsList.get(6);
                                    pointsList.set(6, newTranslateX);
                                    double p4Y = (double) pointsList.get(7);
                                    pointsList.set(7, newTranslateY);
                                    // Busca o ID do polygono
                                    polygonID = ((Polygon) (t.getSource())).getId();
                                    // Busca o Painel na lista de painéis pelo ID
                                    ixPainel = estaPlantaBaixa.findPainelByCodigo(Integer.parseInt(polygonID));
                                    novoPontoX = (int) newTranslateX;
                                    novoPontoY = (int) newTranslateY;
                                    // Define a nova coordenada no vértice (no objeto)
                                    novaCoordenada = new Coordenada(novoPontoX,novoPontoY);
                                    // Altera o painel na lista da planta baixa com a nova coordenada
                                    estaPlantaBaixa.getPainel(ixPainel).setPnPos4(novaCoordenada);
                                    t.consume();
                                    break;
                            }
                        }
                        /*
                         * Mover arestas do polygono = modoOperacao = 2
                         */
                        if (modoOperacaoCursor == 2) {
                        }
                    }
                });


                /*
                 * Adiciona retangulo no fundo, preenchido com cor para ajudar no drap & drop
                 */
                Color corRectFundo = Color.MINTCREAM;
                Rectangle rectFundoGrupo = new Rectangle(grpVis.getBoundsInLocal().getMaxX(), grpVis.getBoundsInLocal().getMaxY(), corRectFundo);
                grpVis.getChildren().add(0, rectFundoGrupo); // Adiciona no node '0' para ficar no fundo.
            }
        } else {
            Text textoAviso = new Text("Esta Planta Baixa não tem painéis definidos. Verifique!");
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
