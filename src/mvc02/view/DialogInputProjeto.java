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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mvc02.model.vo.Projeto;

/**
 *
 * @author wcsan_000
 */
public class DialogInputProjeto {

    private Label lblCodigo;
    private Label lblDescricao;
    private TextField fldCodigo;
    private TextField fldDescricao;
    private TitledPane gridTitledPane;
    private TextInputDialog dialog;
    private Text actionStatus;

    // Construtor
    public DialogInputProjeto(Projeto projeto) {

        // Cria o diálogo que retornará o objeto "Projeto"
        Dialog<Projeto> dialog = new Dialog<>();
        dialog.setTitle("Projeto");
        dialog.setHeaderText("Dados do Projeto");
        dialog.setResizable(true);

        // Cria os labels e campos para entrada de dados
        Label lblCodigo = new Label("Codigo: ");
        TextField fldCodigo = new TextField();
        fldCodigo.setPromptText("Codigo do projeto");
        Label lblDescricao = new Label("Descrição: ");
        TextField fldDescricao = new TextField();
        fldDescricao.setPromptText("Descrição do Projeto");

        // Se Projeto já estiver instanciado, carrega os atributos nos campos do diálogo.
        if (projeto != null) {
            fldCodigo.setText(String.valueOf(projeto.getPrjCodigo()));
            fldDescricao.setText(projeto.getPrjDescricao());
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
        grid.add(lblDescricao, 1, 2);
        grid.add(fldDescricao, 2, 2, 3, 3);
        grid.add(msgStatus, 1, 5, 8, 1);
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

        /* Validação da entrada de dados, interceptando o evento do bottão confirma (buttonConfirma)
         * Campos com erro ficam na cor vermelha. Mensagem de erro é apresentada na caixa de diálogo.
         o método .consume() limpa a lista de eventos para que o diálogo não reconheça a ação, enquanto houver erro
         */
        dialog.getDialogPane().lookupButton(buttonConfirma).addEventFilter(ActionEvent.ACTION, event -> {
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro)
            fldCodigo.setStyle("-fx-text-inner-color: black");
            fldDescricao.setStyle("-fx-text-inner-color: black");

            // Valida formato de entrada do campo Descricao
            if (!fldDescricao.getText().matches("[a-zA-Z| ]{5,}+")) {
                fldDescricao.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Descrição do projeto deve ter ao menos 5 caracteres.");
                event.consume();
            }

            // Valida formato de entrada do campo Codigo
            if (!fldCodigo.getText().matches("[0-9]{1,}+")) {
                fldCodigo.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Codigo deve ser numérico.");
                event.consume();
            }
        });

        // Conversão do resultado do diálogo
        dialog.setResultConverter(new Callback<ButtonType, Projeto>() {
            @Override
            public Projeto call(ButtonType b) {
                if (b == buttonConfirma) {
                    return new Projeto(Integer.parseInt(fldCodigo.getText()), fldDescricao.getText()); // chama construtor do Projeto()
                }
                return null;
            }
        });

        // Tratamento caso o projeto retorne nulo
        Optional<Projeto> result = dialog.showAndWait();

        // Tratamento se o resultado tiver conteudo.
        if (result.isPresent()) {
            projeto.setPrjCodigo(result.get().getPrjCodigo());
            projeto.setPrjDescricao(result.get().getPrjDescricao());
        }
    }

    // Getters
    public Label getLblCodigo() {
        return lblCodigo;
    }

    public Label getLblDescricao() {
        return lblDescricao;
    }

    public TextField getfldCodigo() {
        return fldCodigo;
    }

    public TextField getfldDescricao() {
        return fldDescricao;
    }

    public TitledPane getGridTitledPane() {
        return gridTitledPane;
    }

}
