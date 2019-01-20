/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.view;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mvc02.model.vo.Coordenada;
import mvc02.model.vo.Painel;
import mvc02.model.vo.Perfil;

/**
 *
 * @author wcsan_000
 */
public class DialogInputPerfil {

    private Label lblCodigo;   // Codigo do Perfil (ID)
    private Label lblPnCodigo;   // Codigo do Painel a qual pertence (ID)
    private Label lblDadosPainel; // Para informar dados do painel
    private Label lblPnLargura;  // Largura do Painel (informação para auxiliar entrada de dados)
    private Label lblPnAltura;  // Altura do Painel (informação para auxiliar entrada de dados)
    private Label lblDesignacao; // Tipo do perfil (U - U simples, Ue - U enrijecido, Cr - Cartola, L - Cantoneira de lados desiguais)
    private Label lblOrientacao; // em graus em relação ao solo (90* = vertical, 0* = horiontal, 45* = diagonal)
    private Label lblPosIni;   // posiçao em relacão a origem do painel
    private Label lblFace;      // tamanho em mm da Alma do perfil (vide manual de construcão para especificações)
    private Label lblDimensao;  // comprimento do perfil, em mm

    private TextField fldCodigo;
    private TextField fldPnCodigo;
    private TextField fldDesignacao;
    private TextField fldOrientacao;
    private TextField fldPosXIni;
    private TextField fldPosYIni;
    private TextField fldFace;
    private TextField fldDimensao;

    private TitledPane gridTitledPane;
    private TextInputDialog dialog;
    private Text actionStatus;

    // Construtor
    public DialogInputPerfil(Painel painel, Perfil perfil, String acaoDialogo) {

        // Cria o diálogo que retornará o objeto "Painel"
        Dialog<Perfil> dialog = new Dialog<>();
        dialog.setTitle("Perfil");
        dialog.setHeaderText("Dados do Perfil");
        dialog.setResizable(true);

        // Cria os labels e campos para entrada de dados
        Label lblCodigo = new Label("Codigo: ");
        TextField fldCodigo = new TextField();
        fldCodigo.setPromptText("Codigo do Perfil");

        Label lblPnCodigo = new Label("Codigo Painel: ");
        TextField fldPnCodigo = new TextField();
        fldPnCodigo.setText(String.valueOf(painel.getPnCodigo()));
        fldPnCodigo.setDisable(true);

        Label lblDadosPainel = new Label("Painel (LxH): ");
        Label lblPnLargura = new Label(Double.toString(painel.getPnMaiorLargura()));
        Label lblPnAltura = new Label(Double.toString(painel.getPnAltura()));

        Label lblDesignacao = new Label("Designação (U/Ue/Cr/L): ");
        TextField fldDesignacao = new TextField();
        fldDesignacao.setPromptText("U/Ue/Cr/L");
        fldDesignacao.setDisable(true);

        Label lblOrientacao = new Label("Orientação (graus): ");
        TextField fldOrientacao = new TextField();
        fldOrientacao.setPromptText("90/0/45");

        Label lblPosIni = new Label("Coordenada Inicial: ");
        TextField fldPosXIni = new TextField();
        fldPosXIni.setPromptText("X");
        TextField fldPosYIni = new TextField();
        fldPosYIni.setPromptText("Y");

        Label lblFace = new Label("Face (mm): ");
        TextField fldFace = new TextField();
        fldFace.setPromptText("Face");

        Label lblDimensao = new Label("Dimensao (mm): ");
        TextField fldDimensao = new TextField();
        fldDimensao.setPromptText("Dimensao");

        /*
         * ===========================================================
         * APENAS PARA FACILITAR TESTES: DELETE DEPOIS DE TESTAR
         * ===========================================================
         */
        if (perfil != null && acaoDialogo.equals("I")) {
            fldDesignacao.setText("U");
            fldOrientacao.setText("90");
            fldPosXIni.setText("0");
            fldPosYIni.setText("0");
        }
        /*
         * ===========================================================
         * APENAS PARA FACILITAR TESTES: DELETE DEPOIS DE TESTAR
         * ===========================================================
         */

        // Se o Perfil já estiver instanciado, carrega os atributos nos campos do diálogo.
        if (perfil != null && acaoDialogo.equals("E")) {
            fldCodigo.setText(String.valueOf(perfil.getPfCodigo()));
            fldPnCodigo.setText(String.valueOf(perfil.getPnCodigo()));
            fldDesignacao.setText(String.valueOf(perfil.getPfDesignacao()));
            fldOrientacao.setText(String.valueOf(perfil.getPfOrientacao()));
            fldPosXIni.setText(String.valueOf(perfil.getPfPosIni().getX()));
            fldPosYIni.setText(String.valueOf(perfil.getPfPosIni().getY()));
            fldFace.setText(String.valueOf(perfil.getPfFace()));
            fldDimensao.setText(String.valueOf(perfil.getPfDimensao()));
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
        grid.add(lblPnCodigo, 1, 2);
        grid.add(fldPnCodigo, 2, 2);
        grid.add(lblDadosPainel, 3, 2);
        grid.add(lblPnLargura, 4, 2);
        grid.add(lblPnAltura, 5, 2);
        grid.add(lblDesignacao, 1, 3);
        grid.add(fldDesignacao, 2, 3);
        grid.add(lblOrientacao, 1, 4);
        grid.add(fldOrientacao, 2, 4);
        grid.add(lblPosIni, 1, 5);
        grid.add(fldPosXIni, 2, 5);
        grid.add(fldPosYIni, 3, 5);
        grid.add(lblFace, 1, 6);
        grid.add(fldFace, 2, 6);
        grid.add(lblDimensao, 1, 7);
        grid.add(fldDimensao, 2, 7);

        grid.add(msgStatus, 1, 10, 8, 1);
        msgStatus.setStyle("-fx-text-inner-color: red;");

        dialog.getDialogPane().setContent(grid);

        // adiciona os botões no diálogo
        ButtonType buttonConfirma = new ButtonType("Confirma", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonCancela = new ButtonType("Cancela", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonConfirma);
        dialog.getDialogPane().getButtonTypes().add(buttonCancela);

        // Mantém o foco no campo Codigo
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fldCodigo.requestFocus();
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
            if (!fldCodigo.getText().matches("^[0-9]{1,}$") || fldCodigo.getText() == null) {
                fldCodigo.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Codigo deve ser numérico.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldDesignacao.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Designacao
            if (!fldDesignacao.getText().matches("U|Ue|Cr|L") || fldDesignacao.getText() == null) {
                fldDesignacao.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Designação deve ser U/Ue/Cr/L.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldOrientacao.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Orientacao
            if (!fldOrientacao.getText().matches("90|45|0") || fldOrientacao.getText() == null) {
                fldOrientacao.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Orientação deve ser 90/45/0 graus.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPosXIni.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo PosXIni
            if (!fldPosXIni.getText().matches("^[0-9]{1,}$") || fldPosXIni.getText() == null) {
                fldPosXIni.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Posição Inicial X deve ser numérico");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPosYIni.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo PosYIni
            if (!fldPosYIni.getText().matches("^[0-9]{1,}$") || fldPosYIni.getText() == null) {
                fldPosYIni.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Posição Inicial Y deve ser numérico");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldFace.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Face
            if (!fldFace.getText().matches("^[0-9]{1,}$") || fldFace.getText() == null) {
                fldFace.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Face ser numérico");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldDimensao.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Dimensao
            if (!fldDimensao.getText().matches("^[0-9]{1,}$") || fldDimensao.getText() == null) {
                fldDimensao.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Dimensão ser numérico");
                event.consume();
            }
        });

        // Conversão do resultado do diálogo
        dialog.setResultConverter(new Callback<ButtonType, Perfil>() {
            @Override
            public Perfil call(ButtonType b) {
                if (b == buttonConfirma) {
                    Coordenada coord1 = new Coordenada(Integer.parseInt(fldPosXIni.getText()), Integer.parseInt(fldPosYIni.getText()));
                    return new Perfil(Integer.parseInt(fldCodigo.getText()),
                            Integer.parseInt(fldPnCodigo.getText()),
                            fldDesignacao.getText(),
                            Integer.parseInt(fldOrientacao.getText()),
                            coord1,
                            Integer.parseInt(fldFace.getText()),
                            Integer.parseInt(fldDimensao.getText())
                    ); // chama construtor do Perfil
                }
                return null;
            }
        });

        // Tratamento caso o projeto retorne nulo
        Optional<Perfil> result = dialog.showAndWait();

        // Tratamento se o resultado tiver conteudo.
        if (result.isPresent()) {
            Coordenada coord1 = new Coordenada(Integer.parseInt(fldPosXIni.getText()), Integer.parseInt(fldPosYIni.getText()));
            perfil.setPfCodigo(result.get().getPfCodigo());
            perfil.setPnCodigo(result.get().getPnCodigo());
            perfil.setPfDesignacao(result.get().getPfDesignacao());
            perfil.setPfOrientacao(result.get().getPfOrientacao());
            perfil.setPfPosIni(coord1);
            perfil.setPfFace(result.get().getPfFace());
            perfil.setPfDimensao(result.get().getPfDimensao());
            if (acaoDialogo.equalsIgnoreCase("I")) {
                painel.addPerfil(perfil);
            }
        }

    }
}
