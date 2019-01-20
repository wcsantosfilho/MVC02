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
import mvc02.model.vo.PlantaBaixa;

/**
 *
 * @author wcsan_000
 */
public class DialogInputPlantaBaixa {

    private Label lblCodigo;
    private Label lblSeqPiso;
    private Label lblPrjCodigo;
    private Label lblPosXYZ;
    private Label lblAreaPlantaBaixa;
    private TextField fldCodigo;
    private TextField fldSeqPiso;
    private TextField fldPrjCodigo;
    private TextField fldPosX;
    private TextField fldPosY;
    private TextField fldPosZ;
    private TextField fldAreaPlantaBaixa;

    
    private TitledPane gridTitledPane;
    private TextInputDialog dialog;
    private Text actionStatus;

    // Construtor
    public DialogInputPlantaBaixa(Projeto projeto, PlantaBaixa plantaBaixa, String acaoDialogo) {

        // Cria o diálogo que retornará o objeto "PlantaBaixa"
        Dialog<PlantaBaixa> dialog = new Dialog<>();
        dialog.setTitle("Planta Baixa");
        dialog.setHeaderText("Dados da Planta Baixa");
        dialog.setResizable(true);

        // Cria os labels e campos para entrada de dados
        Label lblCodigo = new Label("Codigo: ");
        TextField fldCodigo = new TextField();
        fldCodigo.setPromptText("Codigo da Planta Baixa");

        Label lblSeqPiso = new Label("Seq Piso: ");
        TextField fldSeqPiso = new TextField();
        fldSeqPiso.setPromptText("Sequencia de Piso");

        Label lblPrjCodigo = new Label("Projeto: ");
        TextField fldPrjCodigo = new TextField();
        fldPrjCodigo.setText(String.valueOf(projeto.getPrjCodigo()));
        fldPrjCodigo.setDisable(true);

        Label lblPosXYZ = new Label("Posição X,Y,Z: ");
        TextField fldPosX = new TextField();
        TextField fldPosY = new TextField();
        TextField fldPosZ = new TextField();
        fldPosX.setPromptText("X");
        fldPosY.setPromptText("Y");
        fldPosZ.setPromptText("Y");

        Label lblAreaPlantaBaixa = new Label("Area Planta Baixa (m2): ");
        TextField fldAreaPlantaBaixa = new TextField();
        fldAreaPlantaBaixa.setPromptText("Area Planta Baixa");

        // Se Projeto já estiver instanciado, carrega os atributos nos campos do diálogo.
        if (plantaBaixa != null && acaoDialogo.equals("E")) {
            fldCodigo.setText(String.valueOf(plantaBaixa.getPbCodigo()));
            fldSeqPiso.setText(String.valueOf(plantaBaixa.getPbSeqPiso()));
            fldPrjCodigo.setText(String.valueOf(plantaBaixa.getPrjCodigo()));
            fldPosX.setText(String.valueOf(plantaBaixa.getPbPosX()));
            fldPosY.setText(String.valueOf(plantaBaixa.getPbPosY()));
            fldPosZ.setText(String.valueOf(plantaBaixa.getPbPosZ()));
            fldAreaPlantaBaixa.setText(String.valueOf(plantaBaixa.getPbAreaPlantaBaixa()));
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
        grid.add(lblSeqPiso, 1, 2);
        grid.add(fldSeqPiso, 2, 2);
        grid.add(lblPrjCodigo, 1, 3);
        grid.add(fldPrjCodigo, 2, 3);
        grid.add(lblPosXYZ, 1, 4);
        grid.add(fldPosX, 2, 4);
        grid.add(fldPosY, 3, 4);
        grid.add(fldPosZ, 4, 4);
        grid.add(lblAreaPlantaBaixa, 1, 5);
        grid.add(fldAreaPlantaBaixa, 2, 5);

        grid.add(msgStatus, 1, 7, 8, 1);
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
            // Valida formato de entrada do campo Codigo
            if (!fldCodigo.getText().matches("[0-9]{2,}+")) {
                fldCodigo.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Codigo deve ser numérico com 2 ou mais posições.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro)
            fldSeqPiso.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo SeqPiso
            if (!fldSeqPiso.getText().matches("[-]*[0-9]+")) {
                fldSeqPiso.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Sequencia de Piso deve ser numérico. -1 para 1o subsolo, 0 para térreo, 1 para 1o andar.");
                event.consume();
            }
            
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro)
            fldPosX.setStyle("-fx-text-inner-color: black");
            fldPosY.setStyle("-fx-text-inner-color: black");
            fldPosZ.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo SeqPiso
            if (!fldPosX.getText().matches("[0-9]+") || !fldPosY.getText().matches("[0-9]+") || !fldPosZ.getText().matches("[0-9]+") ) {
                fldPosX.setStyle("-fx-text-inner-color: red;");
                fldPosY.setStyle("-fx-text-inner-color: red;");
                fldPosZ.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("X, Y e Z devem ser numéricos.");
                event.consume();
            }
            
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro)
            fldAreaPlantaBaixa.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo SeqPiso
            if (!fldAreaPlantaBaixa.getText().matches("[0-9]+") ) {
                fldAreaPlantaBaixa.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Area da Planta Baixa deve ser numérico.");
                event.consume();
            }
        

            
        });

        // Conversão do resultado do diálogo
        dialog.setResultConverter(new Callback<ButtonType, PlantaBaixa>() {
            @Override
            public PlantaBaixa call(ButtonType b) {
                if (b == buttonConfirma) {
                    return new PlantaBaixa(Integer.parseInt(fldCodigo.getText()), 
                            Byte.parseByte(fldSeqPiso.getText()),
                            Integer.parseInt(fldPrjCodigo.getText()),
                            Integer.parseInt(fldPosX.getText()),
                            Integer.parseInt(fldPosY.getText()),
                            Integer.parseInt(fldPosZ.getText()),
                            Integer.parseInt(fldAreaPlantaBaixa.getText())); // chama construtor da Planta Baixa
                }
                return null;
            }
        });

        // Tratamento caso o projeto retorne nulo
        Optional<PlantaBaixa> result = dialog.showAndWait();

        // Tratamento se o resultado tiver conteudo.
        if (result.isPresent()) {
            plantaBaixa.setPbCodigo(result.get().getPbCodigo());
            plantaBaixa.setPbSeqPiso(result.get().getPbSeqPiso());
            plantaBaixa.setPrjCodigo(result.get().getPrjCodigo());
            plantaBaixa.setPbPosX(result.get().getPbPosX());
            plantaBaixa.setPbPosY(result.get().getPbPosY());
            plantaBaixa.setPbPosZ(result.get().getPbPosZ());
            plantaBaixa.setPbAreaPlantaBaixa(result.get().getPbAreaPlantaBaixa());
            /*
             * PRECISA VERIFICAR SE A PLANTA BAIXA JÁ EXISTE.
             * ONDE FAZER? Aqui? No addPlantaBaixa? em um Business Object(BO)?
            */
            if (acaoDialogo.equalsIgnoreCase("I")) {
                projeto.addPlantaBaixa(plantaBaixa);
            } 
        }
    }

    public TextField getFldCodigo() {
        return fldCodigo;
    }

    public TextField getFldSeqPiso() {
        return fldSeqPiso;
    }

    public TextField getFldPrjCodigo() {
        return fldPrjCodigo;
    }

    public TextField getFldPosX() {
        return fldPosX;
    }

    public TextField getFldPosY() {
        return fldPosY;
    }

    public TextField getFldPosZ() {
        return fldPosZ;
    }

    public TextField getFldAreaPlantaBaixa() {
        return fldAreaPlantaBaixa;
    }


}
