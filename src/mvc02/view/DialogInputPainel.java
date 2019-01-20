/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.view;

import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mvc02.model.bo.Geometria;
import mvc02.model.vo.Coordenada;
import mvc02.model.vo.Painel;
import mvc02.model.vo.PlantaBaixa;

/**
 *
 * @author wcsan_000
 */
public class DialogInputPainel {

    private Label lblCodigo;
    private Label lblEspessura;
    private Label lblAltura;
    private Label lblPbCodigo;
    private Label lblPos1;
    private Label lblPos2;
    private Label lblPos3;
    private Label lblPos4;
    private Label lblOrientacao;
    private TextField fldCodigo;
    private TextField fldEspessura;
    private TextField fldAltura;
    private TextField fldPbCodigo;
    private TextField fldPos1X;
    private TextField fldPos1Y;
    private TextField fldPos2X;
    private TextField fldPos2Y;
    private TextField fldPos3X;
    private TextField fldPos3Y;
    private TextField fldPos4X;
    private TextField fldPos4Y;
    private Button btnCalc;

    private TitledPane gridTitledPane;
    private TextInputDialog dialog;
    private Text actionStatus;

    private String orientacao;

    // Construtor
    public DialogInputPainel(PlantaBaixa plantaBaixa, Painel painel, String acaoDialogo) {

        // Cria o diálogo que retornará o objeto "Painel"
        Dialog<Painel> dialog = new Dialog<>();
        dialog.setTitle("Painel");
        dialog.setHeaderText("Dados do Painel");
        dialog.setResizable(true);

        // Cria os labels e campos para entrada de dados
        Label lblCodigo = new Label("Codigo: ");
        TextField fldCodigo = new TextField();
        fldCodigo.setPromptText("Codigo do Painel");

        Label lblPbCodigo = new Label("Planta Baixa: ");
        TextField fldPbCodigo = new TextField();
        fldPbCodigo.setText(String.valueOf(plantaBaixa.getPbCodigo()));
        fldPbCodigo.setDisable(true);

        Label lblEspessura = new Label("Espessura(mm): ");
        TextField fldEspessura = new TextField();
        fldEspessura.setPromptText("Espessura");
        if (!"I".equalsIgnoreCase(acaoDialogo)) {
            fldEspessura.setDisable(true);
        }

        Label lblAltura = new Label("Altura(mm): ");
        TextField fldAltura = new TextField();
        fldEspessura.setPromptText("Altura");
        fldAltura.setText("3000");

        Label lblOrientacao = new Label("Orientação (Vert/Horiz): ");
        final ToggleGroup rButtonGroup = new ToggleGroup();

        RadioButton rButtonVert = new RadioButton("Vertical");
        rButtonVert.setUserData("Vertical");
        rButtonVert.setToggleGroup(rButtonGroup);
        if (!"I".equalsIgnoreCase(acaoDialogo)) {
            rButtonVert.setDisable(true);
        }

        RadioButton rButtonHorz = new RadioButton("Horizontal");
        rButtonHorz.setUserData("Horizontal");
        rButtonHorz.setToggleGroup(rButtonGroup);
        if (!"I".equalsIgnoreCase(acaoDialogo)) {
            rButtonHorz.setDisable(true);
        }

        RadioButton rButtonOutro = new RadioButton("Outro");
        rButtonOutro.setUserData("Outro");
        rButtonOutro.setToggleGroup(rButtonGroup);
        if (!"I".equalsIgnoreCase(acaoDialogo)) {
            rButtonOutro.setDisable(true);
        }

        rButtonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (rButtonGroup.getSelectedToggle() != null) {
                    orientacao = rButtonGroup.getSelectedToggle().getUserData().toString();
                }
            }
        });

        Label lblComprimento = new Label("Comprimento (mm):");
        TextField fldComprimento = new TextField();
        fldComprimento.setPromptText("Comprimento");
        if (!"I".equalsIgnoreCase(acaoDialogo) || "outro".equalsIgnoreCase(orientacao)) {
            fldComprimento.setDisable(true);
        }

        Label lblPos1 = new Label("Coord(1) X,Y (mm): ");
        TextField fldPos1X = new TextField();
        TextField fldPos1Y = new TextField();
        fldPos1X.setPromptText("X");
        fldPos1Y.setPromptText("Y");

        Button btnCalc = new Button("Calcula");
        if (!"I".equalsIgnoreCase(acaoDialogo) || "outro".equalsIgnoreCase(orientacao)) {
            btnCalc.setDisable(true);
        }

        Label lblPos2 = new Label("Coord(2) X,Y (mm): ");
        TextField fldPos2X = new TextField();
        TextField fldPos2Y = new TextField();
        fldPos2X.setPromptText("X");
        fldPos2Y.setPromptText("Y");

        Label lblPos3 = new Label("Coord(3) X,Y (mm): ");
        TextField fldPos3X = new TextField();
        TextField fldPos3Y = new TextField();
        fldPos3X.setPromptText("X");
        fldPos3Y.setPromptText("Y");

        Label lblPos4 = new Label("Coord(4) X,Y (mm): ");
        TextField fldPos4X = new TextField();
        TextField fldPos4Y = new TextField();
        fldPos4X.setPromptText("X");
        fldPos4Y.setPromptText("Y");

        // Se o Painel já estiver instanciado, carrega os atributos nos campos do diálogo.
        if (painel != null && acaoDialogo.equals("E")) {
            fldCodigo.setText(String.valueOf(painel.getPnCodigo()));
            fldEspessura.setText(String.valueOf(painel.getPnEspessura()));
            fldAltura.setText(String.valueOf(painel.getPnAltura()));
            fldPbCodigo.setText(String.valueOf(painel.getPbCodigo()));
            fldPos1X.setText(String.valueOf(painel.getPnPos1().getX()));
            fldPos1Y.setText(String.valueOf(painel.getPnPos1().getY()));
            fldPos2X.setText(String.valueOf(painel.getPnPos2().getX()));
            fldPos2Y.setText(String.valueOf(painel.getPnPos2().getY()));
            fldPos3X.setText(String.valueOf(painel.getPnPos3().getX()));
            fldPos3Y.setText(String.valueOf(painel.getPnPos3().getY()));
            fldPos4X.setText(String.valueOf(painel.getPnPos4().getX()));
            fldPos4Y.setText(String.valueOf(painel.getPnPos4().getY()));
            fldComprimento.setText(String.valueOf(0.0));
        }

        // Limpa o Label  com as mensagens de erro!
        Label msgStatus = new Label("");

        // Define o GridPane dentro do diálogo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Adiciona labels e campos no grid
        grid.add(lblCodigo, 1, 1);
        grid.add(fldCodigo, 2, 1);
        grid.add(lblPbCodigo, 1, 2);
        grid.add(fldPbCodigo, 2, 2);
        grid.add(lblEspessura, 1, 4);
        grid.add(fldEspessura, 2, 4);
        grid.add(lblAltura, 3, 4);
        grid.add(fldAltura, 4, 4);
        grid.add(lblOrientacao, 1, 5);
        grid.add(rButtonVert, 2, 5);
        grid.add(rButtonHorz, 3, 5);
        grid.add(rButtonOutro, 4, 5);
        grid.add(lblComprimento, 1, 6);
        grid.add(fldComprimento, 2, 6);
        grid.add(lblPos1, 1, 8);
        grid.add(fldPos1X, 2, 8);
        grid.add(fldPos1Y, 3, 8);
        grid.add(btnCalc, 4, 8);
        grid.add(lblPos2, 1, 9);
        grid.add(fldPos2X, 2, 9);
        grid.add(fldPos2Y, 3, 9);
        grid.add(lblPos3, 1, 10);
        grid.add(fldPos3X, 2, 10);
        grid.add(fldPos3Y, 3, 10);
        grid.add(lblPos4, 1, 11);
        grid.add(fldPos4X, 2, 11);
        grid.add(fldPos4Y, 3, 11);

        grid.add(msgStatus, 1, 13, 8, 1);
        msgStatus.setStyle("-fx-text-inner-color: red;");

        dialog.getDialogPane().setContent(grid);

        // adiciona os botões no diálogo
        ButtonType buttonConfirma = new ButtonType("Confirma", ButtonData.OK_DONE);
        ButtonType buttonCancela = new ButtonType("Cancela", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonConfirma);
        dialog.getDialogPane().getButtonTypes().add(buttonCancela);

        // Mantém o foco no campo Codigo
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fldCodigo.requestFocus();
            }
        });

        /*
         * Apos digitar o comprimento, calcula a posição final baseado na posicao inicial e orientacao
         * se esta for vertical ou horizontal
         * 
         */
        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // testa se os campos Posicao Inicial, Espessura e Comprimento foram inseridos
                    // para calcular as posições 2, 3 e 4 do polígono no sentido horário
                    if (fldPos1X.getText() != null && !fldPos1X.getText().isEmpty()
                            && fldPos1Y.getText() != null && !fldPos1Y.getText().isEmpty()
                            && !fldComprimento.getText().isEmpty()
                            && !fldAltura.getText().isEmpty()
                            && !fldEspessura.getText().isEmpty()) {
                        int tmpEsp = Integer.parseInt(fldEspessura.getText());
                        int tmpAlt = Integer.parseInt(fldAltura.getText());
                        int tmpComp = Integer.parseInt(fldComprimento.getText());
                        int tmpPos1X = Integer.parseInt(fldPos1X.getText());
                        int tmpPos1Y = Integer.parseInt(fldPos1Y.getText());
                        int tmpPos2X;
                        int tmpPos2Y;
                        int tmpPos3X;
                        int tmpPos3Y;
                        int tmpPos4X;
                        int tmpPos4Y;
                        if (Double.parseDouble(fldEspessura.getText()) > 0
                                && Double.parseDouble(fldComprimento.getText()) > 0) {
                            if (orientacao.equalsIgnoreCase("Horizontal")) {
                                tmpPos2X = tmpPos1X + tmpComp;  // X + comprimento
                                tmpPos2Y = tmpPos1Y;            // mesmo Y
                                tmpPos3X = tmpPos2X;            // mesmo X da Pos2
                                tmpPos3Y = tmpPos1Y + tmpEsp;   // Y + espessura
                                tmpPos4X = tmpPos1X;             // mesmo X da Pos1
                                tmpPos4Y = tmpPos3Y;            // mesmo Y da Pos3
                                fldPos2X.setText(String.valueOf(tmpPos2X)); // Atualiza campos do diálogo
                                fldPos2Y.setText(String.valueOf(tmpPos2Y));
                                fldPos3X.setText(String.valueOf(tmpPos3X));
                                fldPos3Y.setText(String.valueOf(tmpPos3Y));
                                fldPos4X.setText(String.valueOf(tmpPos4X));
                                fldPos4Y.setText(String.valueOf(tmpPos4Y));
                            } else if (orientacao.equalsIgnoreCase("Vertical")) {
                                tmpPos2X = tmpPos1X + tmpEsp;   // X + espessura
                                tmpPos2Y = tmpPos1Y;            // mesmo Y
                                tmpPos3X = tmpPos2X;            // mesmo X da Pos2
                                tmpPos3Y = tmpPos1Y + tmpComp;  // Y + comprimento
                                tmpPos4X = tmpPos1X;            // mesmo X da Pos1
                                tmpPos4Y = tmpPos3Y;            // mesmo Y da Pos3
                                fldPos2X.setText(String.valueOf(tmpPos2X)); // Atualiza campos do diálogo
                                fldPos2Y.setText(String.valueOf(tmpPos2Y));
                                fldPos3X.setText(String.valueOf(tmpPos3X));
                                fldPos3Y.setText(String.valueOf(tmpPos3Y));
                                fldPos4X.setText(String.valueOf(tmpPos4X));
                                fldPos4Y.setText(String.valueOf(tmpPos4Y));
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro :" + e.toString());
                }
            }
        });

        /* Validação da entrada de dados, interceptando o evento do bottão confirma (buttonConfirma)
         * Campos com erro ficam na cor vermelha. Mensagem de erro é apresentada na caixa de diálogo.
         o método .consume() limpa a lista de eventos para que o diálogo não reconheça a ação, enquanto houver erro
         */
        dialog.getDialogPane().lookupButton(buttonConfirma).addEventFilter(ActionEvent.ACTION, event -> {
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldCodigo.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldCodigo.getText().matches("[0-9]{1,}+") || fldCodigo.getText() == null) {
                fldCodigo.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Codigo deve ser numérico.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldEspessura.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldEspessura.getText().matches("[0-9]+") || fldEspessura.getText() == null) {
                fldEspessura.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Espessura deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldAltura.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldAltura.getText().matches("[0-9]+") || fldAltura.getText() == null) {
                fldAltura.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Altura deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos1X.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos1X.getText().matches("[0-9]+") || fldPos1X.getText() == null) {
                fldPos1X.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 1(X) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos1Y.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos1Y.getText().matches("[0-9]+") || fldPos1Y.getText() == null) {
                fldPos1Y.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 1(Y) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos2X.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos2X.getText().matches("[0-9]+") || fldPos2X.getText() == null) {
                fldPos2X.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 2(X) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos2Y.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos2Y.getText().matches("[0-9]+") || fldPos2Y.getText() == null) {
                fldPos2Y.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 2(Y) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos3X.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos3X.getText().matches("[0-9]+") || fldPos3X.getText() == null) {
                fldPos3X.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 3(X) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos3Y.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos3Y.getText().matches("[0-9]+") || fldPos3Y.getText() == null) {
                fldPos3Y.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 3(Y) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos4X.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos4X.getText().matches("[0-9]+") || fldPos4X.getText() == null) {
                fldPos4X.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 4(X) deve ser numérico e inteiro.");
                event.consume();
            }
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos4Y.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPos4Y.getText().matches("[0-9]+") || fldPos4Y.getText() == null) {
                fldPos4Y.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada 4(Y) deve ser numérico e inteiro.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos2X.setStyle("-fx-text-inner-color: black");
            // Teste se Ponto2 está mais à direita que ponto 1
            if (Integer.parseInt(fldPos2X.getText()) < Integer.parseInt(fldPos1X.getText())) {
                fldPos2X.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Ponto2X deve ser maior ou igual a Ponto1X.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos3Y.setStyle("-fx-text-inner-color: black");
            // Teste se Ponto3 está acima do Ponto2
            if (Integer.parseInt(fldPos3Y.getText()) < Integer.parseInt(fldPos2Y.getText())) {
                fldPos3Y.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Ponto3Y deve ser maior ou igual a Ponto2Y.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPos4X.setStyle("-fx-text-inner-color: black");
            // Teste se Ponto4 está mais a direita que Ponto31
            if (Integer.parseInt(fldPos4X.getText()) > Integer.parseInt(fldPos3X.getText())) {
                fldPos4X.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Ponto4X deve ser menor ou igual a Ponto3X.");
                event.consume();
            }

            
            /*
             * Vou Comentar as consistëncias abaixo até achar um código limpo para fazer
             */
//            Geometria geoMet = new Geometria();
//            // Teste interseção entre linhas (L1: Coord(1)-Coord(2) vs L2: Coord(3)-Coord(4)
//            Double p0_x = Double.parseDouble(fldPos1X.getText());
//            Double p0_y = Double.parseDouble(fldPos1Y.getText());
//            Double p1_x = Double.parseDouble(fldPos2X.getText());
//            Double p1_y = Double.parseDouble(fldPos2Y.getText());
//            Double p2_x = Double.parseDouble(fldPos3X.getText());
//            Double p2_y = Double.parseDouble(fldPos3Y.getText());
//            Double p3_x = Double.parseDouble(fldPos4X.getText());
//            Double p3_y = Double.parseDouble(fldPos4Y.getText());
//            boolean testeIntersect = geoMet.validaIntersecaoLinhas(p0_x, p0_y, p1_x, p1_y, p2_x, p2_y, p3_x, p3_y);
//            
//            // Valida Intersecção entre linhas (para evitar sutiã do poligono)
//            if (testeIntersect) {
//                msgStatus.setText("Interseção não permitida: L1: Coord(1)-Coord(2) vs L2: Coord(3)-Coord(4)");
//                event.consume();
//            }
//
//            // Teste interseção entre linhas (L1: Coord(1)-Coord(4) vs L2: Coord(2)-Coord(3))
//            Geometria geoMet2 = new Geometria();
//            p0_x = Double.parseDouble(fldPos1X.getText());
//            p0_y = Double.parseDouble(fldPos1Y.getText());
//            p1_x = Double.parseDouble(fldPos4X.getText());
//            p1_y = Double.parseDouble(fldPos4Y.getText());
//            p2_x = Double.parseDouble(fldPos2X.getText());
//            p2_y = Double.parseDouble(fldPos2Y.getText());
//            p3_x = Double.parseDouble(fldPos3X.getText());
//            p3_y = Double.parseDouble(fldPos3Y.getText());
//            boolean testeIntersect2 = geoMet2.validaIntersecaoLinhas(p0_x, p0_y, p1_x, p1_y, p2_x, p2_y, p3_x, p3_y);
//            
//
//            // Valida Intersecção entre linhas (para evitar sutiã do poligono)
//            if (testeIntersect2) {
//                msgStatus.setText("Interseção não permitida: L1: Coord(1)-Coord(4) vs L2: Coord(2)-Coord(3)");
//                event.consume();
//            }
        });

        // Conversão do resultado do diálogo
        dialog.setResultConverter(new Callback<ButtonType, Painel>() {
            @Override
            public Painel call(ButtonType b) {
                if (b == buttonConfirma) {
                    Coordenada coord1 = new Coordenada(Integer.parseInt(fldPos1X.getText()), Integer.parseInt(fldPos1Y.getText()));
                    Coordenada coord2 = new Coordenada(Integer.parseInt(fldPos2X.getText()), Integer.parseInt(fldPos2Y.getText()));
                    Coordenada coord3 = new Coordenada(Integer.parseInt(fldPos3X.getText()), Integer.parseInt(fldPos3Y.getText()));
                    Coordenada coord4 = new Coordenada(Integer.parseInt(fldPos4X.getText()), Integer.parseInt(fldPos4Y.getText()));
                    return new Painel(Integer.parseInt(fldCodigo.getText()),
                            Integer.parseInt(fldEspessura.getText()),
                            Integer.parseInt(fldAltura.getText()),
                            Integer.parseInt(fldPbCodigo.getText()),
                            coord1,
                            coord2,
                            coord3,
                            coord4
                    ); // chama construtor do Painel
                }
                return null;
            }
        });

        // Tratamento caso o projeto retorne nulo
        Optional<Painel> result = dialog.showAndWait();

        // Tratamento se o resultado tiver conteudo.
        if (result.isPresent()) {
            Coordenada coord1 = new Coordenada(Integer.parseInt(fldPos1X.getText()), Integer.parseInt(fldPos1Y.getText()));
            Coordenada coord2 = new Coordenada(Integer.parseInt(fldPos2X.getText()), Integer.parseInt(fldPos2Y.getText()));
            Coordenada coord3 = new Coordenada(Integer.parseInt(fldPos3X.getText()), Integer.parseInt(fldPos3Y.getText()));
            Coordenada coord4 = new Coordenada(Integer.parseInt(fldPos4X.getText()), Integer.parseInt(fldPos4Y.getText()));

            painel.setPnCodigo(result.get().getPnCodigo());
            painel.setPnEspessura(result.get().getPnEspessura());
            painel.setPnAltura(result.get().getPnAltura());
            painel.setPbCodigo(result.get().getPbCodigo());
            painel.setPnPos1(coord1);
            painel.setPnPos2(coord2);
            painel.setPnPos3(coord3);
            painel.setPnPos4(coord4);
            if (acaoDialogo.equalsIgnoreCase("I")) {
                plantaBaixa.addPainel(painel);
            }
        }
    }

    public TextField getFldCodigo() {
        return fldCodigo;
    }

    public void setFldCodigo(TextField fldCodigo) {
        this.fldCodigo = fldCodigo;
    }

    public TextField getFldEspessura() {
        return fldEspessura;
    }

    public void setFldEspessura(TextField fldEspessura) {
        this.fldEspessura = fldEspessura;
    }

    public TextField getFldAltura() {
        return fldAltura;
    }

    public void setFldAltura(TextField fldAltura) {
        this.fldAltura = fldAltura;
    }

    public TextField getFldPbCodigo() {
        return fldPbCodigo;
    }

    public void setFldPbCodigo(TextField fldPbCodigo) {
        this.fldPbCodigo = fldPbCodigo;
    }

    public TextField getFldPos1X() {
        return fldPos1X;
    }

    public void setFldPos1X(TextField fldPos1X) {
        this.fldPos1X = fldPos1X;
    }

    public TextField getFldPos1Y() {
        return fldPos1Y;
    }

    public void setFldPos1Y(TextField fldPos1Y) {
        this.fldPos1Y = fldPos1Y;
    }

    public TextField getFldPos2X() {
        return fldPos2X;
    }

    public void setFldPos2X(TextField fldPos2X) {
        this.fldPos2X = fldPos2X;
    }

    public TextField getFldPos2Y() {
        return fldPos2Y;
    }

    public void setFldPos2Y(TextField fldPos2Y) {
        this.fldPos2Y = fldPos2Y;
    }

    public TextField getFldPos3X() {
        return fldPos3X;
    }

    public void setFldPos3X(TextField fldPos3X) {
        this.fldPos3X = fldPos3X;
    }

    public TextField getFldPos3Y() {
        return fldPos3Y;
    }

    public void setFldPos3Y(TextField fldPos3Y) {
        this.fldPos3Y = fldPos3Y;
    }

    public TextField getFldPos4X() {
        return fldPos4X;
    }

    public void setFldPos4X(TextField fldPos4X) {
        this.fldPos4X = fldPos4X;
    }

    public TextField getFldPos4Y() {
        return fldPos4Y;
    }

    public void setFldPos4Y(TextField fldPos4Y) {
        this.fldPos4Y = fldPos4Y;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

}
