/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import static javafx.scene.layout.BorderStroke.DEFAULT_WIDTHS;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mvc02.model.vo.Abertura;
import mvc02.model.vo.Coordenada;
import mvc02.model.vo.Painel;
import mvc02.model.vo.Perfil;

public class DialogInputAbertura {


    /*
     * Label e campos da Abertura
     */
    private Label lblCodigo;       // Codigo da abertura
    private Label lblPnCodigo;       // Codigo do painel a que pertence a abertura
    private Label lblPos; // Coordenada
    private Label lblLargura; // Largura do vão livre da abertura
    private Label lblAltura; // Altura do vão livre da abertura

    private TextField fldCodigo;
    private TextField fldPnCodigo;
    private TextField fldPosX;
    private TextField fldPosY;
    private TextField fldLargura;
    private TextField fldAltura;

    /*
     * Label para informar dimensões do Painel (auxílio)
     */
    private Label lblPainel;
    private Label lblPnLargura;
    private Label lblPnAltura;

    /*
     * Label e campos da entrada de dados do Perfil
     */
    private Label lblPfCodigo;
    private Label lblPfDesignacao;
    private Label lblPfOrientacao;
    private Label lblPfPos;
    private Label lblPfFace;
    private Label lblPfDimensao;

    private TextField fldPfCodigo;
    private TextField fldPfDesignacao;
    private TextField fldPfOrientacao;
    private TextField fldPfPosXIni;
    private TextField fldPfPosYIni;
    private TextField fldPfFace;
    private TextField fldPfDimensao;
    private Text msgStatusPerfil;

    private TitledPane gridTitledPane;
    private TextInputDialog dialog;
    private Text actionStatus;

    Abertura abertura;

    /* 
     * Coisarada para TableView
     */
    private TableView<Perfil> table;
    private ObservableList<Perfil> data;

    // Construtor
    public DialogInputAbertura(Painel painel, Abertura abertura, String acaoDialogo) {

        this.abertura = abertura;
        // Cria o diálogo que retornará o objeto "Abertura"
        Dialog<Abertura> dialog = new Dialog<>();
        dialog.setTitle("Abertura");
        dialog.setHeaderText("Dados da Abertura");
        dialog.setResizable(true);

        // Cria os labels e campos para entrada de dados
        lblCodigo = new Label("Codigo: ");
        fldCodigo = new TextField();
        fldCodigo.setPromptText("Codigo da Abertura");

        lblPnCodigo = new Label("Codigo Painel: ");
        fldPnCodigo = new TextField();
        fldPnCodigo.setText(painel.getPnCodigo().toString());
        fldPnCodigo.setDisable(true);

        lblPos = new Label("Coordenada X,Y (mm): ");
        fldPosX = new TextField();
        fldPosX.setPromptText("X");
        fldPosY = new TextField();
        fldPosY.setPromptText("Y");

        lblLargura = new Label("Largura Total (mm): ");
        fldLargura = new TextField();
        fldLargura.setPromptText("Largura");

        lblAltura = new Label("Altura Total (mm): ");
        fldAltura = new TextField();
        fldAltura.setPromptText("Altura");

        // Campos de informação do Painel (Largura x Altura)
        lblPainel = new Label("Dados do Painel");
        lblPainel.setTextFill(Color.DARKBLUE);
        lblPainel.setFont(Font.font("Calibri", FontWeight.BOLD, 12));
        lblPnAltura = new Label("Altura :" + painel.getPnAltura().toString());
        lblPnLargura = new Label("Largura :" + painel.getPnMaiorLargura().toString());

        // Campos para entrada de dados dos Perfis da Abertura 
        lblPfCodigo = new Label("Codigo Perfil: ");
        fldPfCodigo = new TextField();
        lblPfDesignacao = new Label("Designacao (U/Ue/Cr/L): ");
        fldPfDesignacao = new TextField();
        lblPfOrientacao = new Label("Orientacao (graus): ");
        fldPfOrientacao = new TextField();
        lblPfPos = new Label("Pos Ini (X,Y) (mm): ");
        fldPfPosXIni = new TextField();
        fldPfPosYIni = new TextField();
        lblPfFace = new Label("Face(mm): ");
        fldPfFace = new TextField();
        lblPfDimensao = new Label("Dimensao(mm): ");
        fldPfDimensao = new TextField();
        msgStatusPerfil = new Text();
        msgStatusPerfil.setFill(Color.RED);

        // Add and delete buttons
        Button addbtn = new Button("Insere");
        addbtn.setOnAction(new AddButtonListener());
        Button delbtn = new Button("Exclui");
        delbtn.setOnAction(new DeleteButtonListener());

        // Status message text
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        /*
         * TableView para entrada de dados dos perfis da abertura
         */
        // Perfil label
        Label label = new Label("Perfis");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 12));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Table view, data, columns and properties
        List<Perfil> list = new ArrayList<>();
        data = FXCollections.observableList(list);

        // Se a Abertura já estiver instanciada, carrega os atributos nos campos do diálogo.
        if (abertura != null && acaoDialogo.equals("E")) {
            fldCodigo.setText(String.valueOf(abertura.getAbCodigo()));
            fldPnCodigo.setText(String.valueOf(abertura.getPnCodigo()));
            fldPosX.setText(String.valueOf(abertura.getAbPos().getX()));
            fldPosY.setText(String.valueOf(abertura.getAbPos().getY()));
            fldLargura.setText(String.valueOf(abertura.getAbLargura()));
            fldAltura.setText(String.valueOf(abertura.getAbAltura()));

            if (abertura.getFirstPerfil() != null) {
                data = getInitialTableData();
            }

        }

        // Cria TableView para listar os Perfis
        table = new TableView<>();
        table.setItems(data);
        table.setEditable(true);

        // Colunas da TableView
        TableColumn<Perfil, Integer> codigoCol = new TableColumn("Codigo");
        codigoCol.setCellValueFactory(new PropertyValueFactory<Perfil, Integer>("pfCodigo"));

        TableColumn designacaoCol = new TableColumn("Design");
        designacaoCol.setCellValueFactory(new PropertyValueFactory<Perfil, String>("pfDesignacao"));

        TableColumn orientacaoCol = new TableColumn("Orientação");
        orientacaoCol.setCellValueFactory(new PropertyValueFactory<Perfil, Integer>("pfOrientacao"));
        
        TableColumn posIniCol = new TableColumn("Pos Ini(X,Y)");
        posIniCol.setCellValueFactory(new PropertyValueFactory<Perfil, Coordenada>("pfPosIni"));

        TableColumn faceCol = new TableColumn("Face");
        faceCol.setCellValueFactory(new PropertyValueFactory<Perfil, Integer>("pfFace"));

        TableColumn dimensaoCol = new TableColumn("Dimensão");
        dimensaoCol.setCellValueFactory(new PropertyValueFactory<Perfil, Integer>("pfDimensao"));

        table.getColumns().setAll(codigoCol, designacaoCol, orientacaoCol, posIniCol, faceCol, dimensaoCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Cria o GridPane para campos de entrada dos Perfis + TableView
        GridPane gridPerfil = new GridPane();
        gridPerfil.setHgap(10);
        gridPerfil.setVgap(10);

        gridPerfil.setPadding(new Insets(5, 5, 5, 5));

        gridPerfil.setBorder(new Border(new BorderStroke(new Color(0.66, 0.66, 0.66, 1.0), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, DEFAULT_WIDTHS)));

//        gridPerfil.setGridLinesVisible(true);
        gridPerfil.add(lblPfCodigo, 0, 1);
        gridPerfil.add(fldPfCodigo, 1, 1);
        gridPerfil.add(lblPfDesignacao, 0, 2);
        gridPerfil.add(fldPfDesignacao, 1, 2);
        gridPerfil.add(lblPfOrientacao, 0, 3);
        gridPerfil.add(fldPfOrientacao, 1, 3);
        gridPerfil.add(lblPfPos, 0, 4);
        gridPerfil.add(fldPfPosXIni, 1, 4);
        gridPerfil.add(fldPfPosYIni, 2, 4);
        gridPerfil.add(lblPfFace, 0, 5);
        gridPerfil.add(fldPfFace, 1, 5);
        gridPerfil.add(lblPfDimensao, 0, 6);
        gridPerfil.add(fldPfDimensao, 1, 6);
        gridPerfil.add(addbtn, 1, 8);
        gridPerfil.add(delbtn, 2, 8);
        gridPerfil.add(labelHb, 3, 0);
        gridPerfil.add(table, 3, 1, 5, 10);
        gridPerfil.add(msgStatusPerfil, 0, 15, 4, 1);

        // Select the first row
        table.getSelectionModel().select(0);
        Perfil perfil = table.getSelectionModel().getSelectedItem();

        // Limpa o Label  com as mensagens de erro!
        Label msgStatus = new Label("");

        // Define o GridPane dentro do diálogo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
//        grid.setGridLinesVisible(true);

        // Adiciona labels e campos da Abertura no grid
        grid.add(lblCodigo, 0, 1);
        grid.add(fldCodigo, 1, 1);
        grid.add(lblPnCodigo, 0, 2);
        grid.add(fldPnCodigo, 1, 2);
        grid.add(lblPos, 0, 3);
        grid.add(fldPosX, 1, 3);
        grid.add(fldPosY, 2, 3);
        grid.add(lblLargura, 0, 4);
        grid.add(fldLargura, 1, 4);
        grid.add(lblAltura, 0, 5);
        grid.add(fldAltura, 1, 5);
        // Adiciona label para informar dados do Painel
        grid.add(lblPainel, 3, 1);
        grid.add(lblPnAltura, 3, 2);
        grid.add(lblPnLargura, 3, 3);

        grid.add(gridPerfil, 0, 6, 30, 20);
        grid.add(msgStatus, 0, 5, 8, 1);
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
            if (!fldCodigo.getText().matches("[0-9]{1,}+") || fldCodigo.getText() == null) {
                fldCodigo.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Codigo deve ser numérico.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPosX.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo PosX
            if (!fldPosX.getText().matches("[0-9]+") || fldPosX.getText() == null) {
                fldPosX.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada (X) deve ser numérico e inteiro.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPosY.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo PosY
            if (!fldPosY.getText().matches("[0-9]+") || fldPosY.getText() == null) {
                fldPosY.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Coordenada (Y) deve ser numérico e inteiro.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldLargura.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Largura
            if (!fldLargura.getText().matches("[0-9]+") || fldLargura.getText() == null) {
                fldLargura.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Largura deve ser numérico e inteiro.");
                event.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldAltura.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Altura
            if (!fldAltura.getText().matches("[0-9]+") || fldAltura.getText() == null) {
                fldAltura.setStyle("-fx-text-inner-color: red;");
                msgStatus.setText("Campo Altura deve ser numérico e inteiro.");
                event.consume();
            }

        });

        // Conversão do resultado do diálogo
        dialog.setResultConverter(new Callback<ButtonType, Abertura>() {
            @Override
            public Abertura call(ButtonType b) {
                if (b == buttonConfirma) {
                    ArrayList<Perfil> tempListPf = new ArrayList<>();
                    if (!data.isEmpty()) {
                        for (Perfil tempPf : data.sorted()) {
                            tempListPf.add(tempPf);
                        }
                    }
                    Coordenada coord1 = new Coordenada(Integer.parseInt(fldPosX.getText()), Integer.parseInt(fldPosY.getText()));
                    return new Abertura(Integer.parseInt(fldCodigo.getText()),
                            Integer.parseInt(fldPnCodigo.getText()),
                            coord1,
                            Integer.parseInt(fldLargura.getText()),
                            Integer.parseInt(fldAltura.getText()),
                            tempListPf
                    ); // chama construtor da Abertura
                }
                return null;
            }
        });

        // Tratamento caso o projeto retorne nulo
        Optional<Abertura> result = dialog.showAndWait();

        // Tratamento se o resultado tiver conteudo.
        if (result.isPresent()) {
            ArrayList<Perfil> tempListPf = new ArrayList<>();
            if (!data.isEmpty()) {
                for (Perfil tempPf : data.sorted()) {
                    tempListPf.add(tempPf);
                }
            }

            Coordenada coord1 = new Coordenada(Integer.parseInt(fldPosX.getText()), Integer.parseInt(fldPosY.getText()));
            abertura.setAbCodigo(result.get().getAbCodigo());
            abertura.setPnCodigo(result.get().getPnCodigo());
            abertura.setAbPos(coord1);
            abertura.setAbLargura(result.get().getAbLargura());
            abertura.setAbAltura(result.get().getAbAltura());
            abertura.setAbPerfil(null);
            abertura.setAbPerfil(tempListPf);
            if (acaoDialogo.equalsIgnoreCase("I")) {
                painel.addAbertura(abertura);
            }
        }

    }

    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {

            int ix = newVal.intValue();
            if ((ix < 0) || (ix >= data.size())) {
                return; // invalid data
            }
            Perfil perfil = data.get(ix);
            actionStatus.setText(perfil.toString());
        }
    }

    private ObservableList<Perfil> getInitialTableData() {

        List<Perfil> list = new ArrayList<>();
        for (Perfil tempPf : abertura.getAllPerfil()) {
            list.add(tempPf);
        }
        ObservableList<Perfil> data = FXCollections.observableList(list);
        return data;
    }

    private class AddButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            /* Validação da entrada de dados, interceptando o evento do bottão confirma (buttonConfirma)
             * Campos com erro ficam na cor vermelha. Mensagem de erro é apresentada na caixa de diálogo.
             o método .consume() limpa a lista de eventos para que o diálogo não reconheça a ação, enquanto houver erro
             */
            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfCodigo.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Codigo
            if (!fldPfCodigo.getText().matches("[0-9]{1,}+") || fldPfCodigo.getText() == null) {
                fldPfCodigo.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Codigo deve ser numérico.");
                e.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfDesignacao.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Designcao
            if (!fldPfDesignacao.getText().matches("U|Ue|Cr|L") || fldPfDesignacao.getText() == null) {
                fldPfDesignacao.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Designacao deve ser U ou Ue ou Cr ou L");
                e.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfOrientacao.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo Orientacao
            if (!fldPfOrientacao.getText().matches("90|0") || fldPfOrientacao.getText() == null) {
                fldPfOrientacao.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Orientação, em graus, deve ser 0 ou 90");
                e.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfPosXIni.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo PosXIni
            if (!fldPfPosXIni.getText().matches("[0-9]+") || fldPfPosXIni.getText() == null) {
                fldPfPosXIni.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Coordenada Inicial (X) deve ser numérico e inteiro.");
                e.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfPosYIni.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo PosYIni
            if (!fldPfPosYIni.getText().matches("[0-9]+") || fldPfPosYIni.getText() == null) {
                fldPfPosYIni.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Coordenada Inicial (Y) deve ser numérico e inteiro.");
                e.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfFace.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo fldPfFace
            if (!fldPfFace.getText().matches("[0-9]+") || fldPfFace.getText() == null) {
                fldPfFace.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Face deve ser numérico e inteiro.");
                e.consume();
            }

            // define fonte cor preta (reset caso tenho sido colocada em vermelho por erro anterior)
            fldPfDimensao.setStyle("-fx-text-inner-color: black");
            // Valida formato de entrada do campo fldPfDimensao
            if (!fldPfDimensao.getText().matches("[0-9]+") || fldPfDimensao.getText() == null) {
                fldPfDimensao.setStyle("-fx-text-inner-color: red;");
                msgStatusPerfil.setText("Dimensao deve ser numérico e inteiro.");
                e.consume();
            }

            // Create a new row after last row
            if (!e.isConsumed()) {
                Coordenada coord1 = new Coordenada(Integer.parseInt(fldPfPosXIni.getText()), Integer.parseInt(fldPfPosYIni.getText()));
                Perfil perfil = new Perfil(Integer.parseInt(fldPfCodigo.getText()), Integer.parseInt(fldPnCodigo.getText()), fldPfDesignacao.getText(), Integer.parseInt(fldPfOrientacao.getText()), coord1, Integer.parseInt(fldPfFace.getText()), Integer.parseInt(fldPfDimensao.getText()));
                data.add(perfil);
                int row = data.size() - 1;

                // Select the new row
                table.requestFocus();
                table.getSelectionModel().select(row);
                table.getFocusModel().focus(row);

                // Limpa os campos
                fldPfCodigo.clear();
                fldPfDesignacao.clear();
                fldPfOrientacao.clear();
                fldPfPosXIni.clear();
                fldPfPosYIni.clear();
                fldPfFace.clear();
                fldPfDimensao.clear();
                msgStatusPerfil.setText("");

            }

            actionStatus.setText("Novo perfil: Entre Codigo e Designacao. <Enter>.");
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Get selected row and delete
            int ix = table.getSelectionModel().getSelectedIndex();
            Perfil perfil = (Perfil) table.getSelectionModel().getSelectedItem();
            data.remove(ix);
            actionStatus.setText("Deleted: " + perfil.toString());

            // Select a row
            if (table.getItems().size() == 0) {

                actionStatus.setText("No data in table !");
                return;
            }

            if (ix != 0) {

                ix = ix - 1;
            }

            table.requestFocus();
            table.getSelectionModel().select(ix);
            table.getFocusModel().focus(ix);
        }
    }

}
