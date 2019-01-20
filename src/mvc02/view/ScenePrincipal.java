/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.view;

import java.text.DecimalFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import mvc02.model.bo.ProjetoEvent;
import mvc02.model.bo.ProjetoListener;
import mvc02.model.vo.Abertura;
import mvc02.model.vo.Perfil;
import mvc02.model.vo.Painel;
import mvc02.model.vo.PlantaBaixa;
import mvc02.model.vo.Projeto;

/**
 *
 * @author wcsan_000
 */
public class ScenePrincipal implements ProjetoListener {

    private final BorderPane painelPrincipal;
    private final MenuBar barraDeMenus;
    private final Menu menuArquivo;
    private final Menu menuEditar;
    private final Menu menuAjuda;
    private final MenuItem menuAbrir;
    private final MenuItem menuNovoProjeto;
    private final MenuItem menuEditaProjeto;
    private final MenuItem menuSalvar;
    private final MenuItem menuSair;
    private final MenuItem menuNovaPlantaBaixa;
    private final MenuItem menuEditaPlantaBaixa;
    private final MenuItem menuExcluiPlantaBaixa;
    private final MenuItem menuNovoPainel;
    private final MenuItem menuEditaPainel;
    private final MenuItem menuExcluiPainel;
    private final MenuItem menuNovaAbertura;
    private final MenuItem menuEditaAbertura;
    private final MenuItem menuExcluiAbertura;
    private final MenuItem menuNovoPerfil;
    private final MenuItem menuEditaPerfil;
    private final MenuItem menuExcluiPerfil;
    private final MenuItem menuAjudar;
    private final MenuItem menuSobre;
    private final ContextMenu menuContextoArvore;
    private TreeView<Object> arvoreObjetos;
    private TreeItem<Object> noRaiz;
    private TrvProjeto trvProjeto;
    private final SplitPane painelSplit;
    private final VBox painelArvoreObjetos;
    private final ScrollPane painelStatus;
    public final GridPane painelTrabalho;
    private final VBox painelMensagens;
    private final Scene scene;
    private Group grupoVisualizaElemento;
    public Stage stage;
    private Projeto projeto;
    public ParamView paramView;
    public String lastFolderOpened;
    Stage primaryStage;

    private static final double MAX_SCALE = 10.0d;
    private static final double MIN_SCALE = .1d;

    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
    double orgTranslateY;

    private Object objetoSelecionadoNaArvore;
    private Object paiObjetoSelecionadoNaArvore;

    // Construtor   
    public ScenePrincipal(Stage primaryStage, Projeto projeto) {
        this.projeto = projeto;
        // Define o Painel Principal como um BorderPane
        this.painelPrincipal = new BorderPane();

        // Define a Barra de Menus
        this.barraDeMenus = new MenuBar();
        // Menus: Arquivo, Editar e Ajuda
        this.menuArquivo = new Menu("Arquivo");
        this.menuEditar = new Menu("Editar");
        this.menuAjuda = new Menu("Ajuda");
        // Itens do Menu Arquivo
        this.menuAbrir = new MenuItem("Abrir arquivo");
        this.menuSalvar = new MenuItem("Salvar arquivo");
        this.menuSair = new MenuItem("Sair");
        // Itens do Menu Editar
        this.menuNovoProjeto = new MenuItem("Novo Projeto");
        this.menuEditaProjeto = new MenuItem("Editar Projeto");
        this.menuNovaPlantaBaixa = new MenuItem("Nova Planta Baixa");
        this.menuEditaPlantaBaixa = new MenuItem("Editar Planta Baixa");
        this.menuExcluiPlantaBaixa = new MenuItem("Excluir Planta Baixa");
        this.menuNovoPainel = new MenuItem("Novo Painel");
        this.menuEditaPainel = new MenuItem("Editar Painel");
        this.menuExcluiPainel = new MenuItem("Excluir Painel");
        this.menuNovaAbertura = new MenuItem("Nova Abertura");
        this.menuEditaAbertura = new MenuItem("Editar Abertura");
        this.menuExcluiAbertura = new MenuItem("Excluir Abertura");
        this.menuNovoPerfil = new MenuItem("Novo Perfil");
        this.menuEditaPerfil = new MenuItem("Editar Perfil");
        this.menuExcluiPerfil = new MenuItem("Excluir Perfil");
        // Itens do Menu Ajuda
        this.menuAjudar = new MenuItem("Ajuda");
        this.menuSobre = new MenuItem("Sobre");
        // Adiciona os itens aos menus
        this.menuArquivo.getItems().addAll(this.menuAbrir, this.menuSalvar, this.menuSair);
        this.menuEditar.getItems().addAll(this.menuNovoProjeto, this.menuEditaProjeto, new SeparatorMenuItem(),
                this.menuNovaPlantaBaixa, this.menuEditaPlantaBaixa, this.menuExcluiPlantaBaixa, new SeparatorMenuItem(),
                this.menuNovoPainel, this.menuEditaPainel, this.menuExcluiPainel, new SeparatorMenuItem(),
                this.menuNovaAbertura, this.menuEditaAbertura, this.menuExcluiAbertura, new SeparatorMenuItem(),
                this.menuNovoPerfil, this.menuEditaPerfil, this.menuExcluiPerfil
        );
        this.menuAjuda.getItems().addAll(this.menuAjudar, this.menuSobre);
        // Adiciona os menus a Barra de Menus
        this.barraDeMenus.getMenus().addAll(this.menuArquivo, this.menuEditar, this.menuAjuda);

        // Define o SplitPlane que ficará na parte CENTER do BorderPane
        this.painelSplit = new SplitPane();
        this.painelSplit.setDividerPositions(0.1f, 0.6f);
        this.painelSplit.setOrientation(Orientation.HORIZONTAL);

        // Define os AnchorPane que compõe o SplitPane
        this.painelArvoreObjetos = new VBox();

        this.painelArvoreObjetos.setMinWidth(100);
        this.painelArvoreObjetos.setMaxWidth(500);
        this.painelArvoreObjetos.setPrefWidth(200);
        this.painelArvoreObjetos.setStyle("-fx-background-color: gray");

        // No painelArvoreObjetos será montado um TreeView c/ Panes cfe projeto for montado
        this.menuContextoArvore = new ContextMenu();
        this.menuContextoArvore.getItems().addAll(this.menuEditaProjeto, new SeparatorMenuItem(), this.menuNovaPlantaBaixa, this.menuEditaPlantaBaixa, new SeparatorMenuItem(), this.menuNovoPainel, this.menuEditaPainel);
        this.arvoreObjetos = new TreeView<>();

        this.objetoSelecionadoNaArvore = new Object();
        this.painelArvoreObjetos.getChildren().add(arvoreObjetos);

        // Define o Painel de Trabalho (visualização) no lado direito do Painel Split
        this.painelTrabalho = new GridPane();

        // Carrega os paineis painelArvoreObjetos e painelTrabalho no painelSplit
        this.painelSplit.getItems().addAll(painelArvoreObjetos, painelTrabalho);

        // Define o AnchorPane do Botton do Painel Principal
        this.painelStatus = new ScrollPane();
        this.painelStatus.setPrefSize(800, 20);
        this.painelStatus.setStyle("-fx-background-color: #d0fa00;");
        this.painelMensagens = new VBox();
        this.painelMensagens.setPadding(new Insets(10, 0, 0, 10));
        this.painelMensagens.setSpacing(10);
        this.painelStatus.setContent(painelMensagens);
        this.painelStatus.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.painelStatus.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.painelStatus.hvalueProperty().setValue(1);

        // Coloca a Barra de Menus no "Top" do Painel Principal
        this.painelPrincipal.setTop(barraDeMenus);
        // Coloca o Painel de Status no "Bottom" do Painel Principal
        this.painelPrincipal.setBottom(painelStatus);
        // Coloca o Painel de Trabalho no "Center" do Painel Principal
        this.painelPrincipal.setCenter(painelSplit);

        /*
         * Esquema experimental do ParamView
         */
        this.paramView = new ParamView();
        this.paramView = paramView.getFromArquivo();
        /*
         * Fim do esquema experimental
         */
        // Carrega o Painel Principal na "Scene"
        this.scene = new Scene(painelPrincipal, paramView.getPainelPrincipalWidth(), paramView.getPainelPrincipalHeight());
        this.painelSplit.setDividerPositions(paramView.getPainelSplitDivider());

        primaryStage.setTitle("Aplicação destinada a resolver os problemas do mundo");
        primaryStage.setScene(scene);
        this.stage = primaryStage;
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventAbrir(EventHandler<ActionEvent> event) {
        menuAbrir.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventSalvar(EventHandler<ActionEvent> event) {
        menuSalvar.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventSair(EventHandler<ActionEvent> event) {
        menuSair.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventNovoProjeto(EventHandler<ActionEvent> event) {
        menuNovoProjeto.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventEditaProjeto(EventHandler<ActionEvent> event) {
        menuEditaProjeto.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventNovaPlantaBaixa(EventHandler<ActionEvent> event) {
        menuNovaPlantaBaixa.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventEditaPlantaBaixa(EventHandler<ActionEvent> event) {
        menuEditaPlantaBaixa.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventNovoPainel(EventHandler<ActionEvent> event) {
        menuNovoPainel.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventEditaPainel(EventHandler<ActionEvent> event) {
        menuEditaPainel.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventNovaAbertura(EventHandler<ActionEvent> event) {
        menuNovaAbertura.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventEditaAbertura(EventHandler<ActionEvent> event) {
        menuEditaAbertura.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventNovoPerfil(EventHandler<ActionEvent> event) {
        menuNovoPerfil.setOnAction(event);
    }

    // Metodo que associa o item de menu a uma classe (passada como parametro)
    public void actionEventEditaPerfil(EventHandler<ActionEvent> event) {
        menuEditaPerfil.setOnAction(event);
    }

    // Metodo que associa um handler para tratar um evento de mouse da arvore de objetos
    public void mouseEventArvoreObjetos(EventHandler<MouseEvent> event) {
        arvoreObjetos.setOnMouseClicked(event);

    }

    // Metodo que gera colocar uma mensagem na parte "Botton" do Painel Principal
    public void setStatusMessage(String status) {
        this.painelMensagens.getChildren().clear();
        this.painelMensagens.getChildren().add(new Label(status));
    }

    /* Metodo que mostra os atributos do projeto no Painel de Trabalho quando há alguma alteração
     * Este método é acionado pelo listener no ControllerPrincipal quando o Projeto é atualizado
     */
    @Override
    public void novoStatusProjeto(ProjetoEvent event) {
        /*
         * Constroi os objetos para visualização no painel de Trabalho
         */
        this.montaArvoreObjetos();
        /*
         * Essa parte debaixo tá meio sem função: 09/07/2016
         */
        if (this.getObjetoSelecionadoNaArvore() instanceof PlantaBaixa
                && this.getObjetoSelecionadoNaArvore() != null) {
            PlantaBaixa tempPb = (PlantaBaixa) this.getObjetoSelecionadoNaArvore();
            //this.setGrupoPlantaBaixa(tempPb);
        }
    }

    public void montaArvoreObjetos() {
        /* 
         * Constroi uma TreeView com a estrutura do Projeto e seus ramos 
         */
        // Carrega os dados do projeto no Nó Principal da Tree View
        noRaiz = new TreeItem<>(new TrvProjeto("Projeto Invisível que será o nó"));
        noRaiz.setExpanded(true);

        /* os objetos trvProjeto, trvPlantaBaixa e demais servem para "mapear" 
         * a estrutura de objetos criados e carrega-los na árvore de seleção
         */
        TreeItem<Object> item = new TreeItem<>(projeto);
        item.setExpanded(true);
        noRaiz.getChildren().add(item);

        // monta os nós filhos na árvores (Planta Baixa, Painel, Perfis, etc)
        if (this.getProjeto().getFirstPlantaBaixa() != null) {
            for (PlantaBaixa tempPb : this.getProjeto().getAllPlantaBaixa()) // loop nas PB do projeto acionado pelo evento
            {
                TreeItem<Object> pbTree = new TreeItem<>(tempPb);
                item.getChildren().add(pbTree);
                if (tempPb.getFirstPainel() != null) {
                    for (Painel tempPn : tempPb.getAllPainel()) // loop nos Paineis da PlantaBaixa
                    {
                        TreeItem<Object> pnTree = new TreeItem<>(tempPn);
                        pbTree.setExpanded(true);
                        pbTree.getChildren().add(pnTree);
                        if (tempPn.getFirstAbertura() != null) {
                            for (Abertura tempAb : tempPn.getAllAbertura()) // loop nas Aberturas do Painel
                            {
                                TreeItem<Object> abTree = new TreeItem<>(tempAb);
                                pnTree.setExpanded(false);
                                pnTree.getChildren().add(abTree);
                            }
                        }
                        if (tempPn.getFirstPerfil() != null) {
                            for (Perfil tempPf : tempPn.getAllPerfil()) // loop nos Perfis do Painel
                            {
                                TreeItem<Object> pfTree = new TreeItem<>(tempPf);
                                pnTree.setExpanded(false);
                                pnTree.getChildren().add(pfTree);
                            }
                        }
                    }
                }
            }
        }

        /* 
         * treeView irá representar a hierarquia dos objetos abaixo do Projeto
         * i.e. Plantas Baixa, Painel, Perfil
         * Um menu de contexto será chamado nos nós para adicionar, excluir ou editar
         * Um diálogo será aberto para tratar dos dados destes objetos
         */
        arvoreObjetos.setRoot(noRaiz);
        arvoreObjetos.setEditable(false);
        arvoreObjetos.setShowRoot(false);
        arvoreObjetos.setCellFactory(
                new Callback<TreeView<Object>, TreeCell<Object>>() {
                    @Override
                    public TreeCell<Object> call(TreeView<Object> p) {
                        return new TrataTreeCell();
                    }
                }
        );
    }

    /*
     * Faz o tratamento da TreeCell na TreeView para montar a texto de cada objeto na TreeView
     */
    private class TrataTreeCell extends TreeCell<Object> {

        // Construtor
        public TrataTreeCell() {
        }

        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                setText(getDisplayText(item));
            }
            setGraphic(null);
        }
    }

    private String getDisplayText(Object item) {

        String seletor = new String();
        if (item instanceof Projeto) {
            seletor = "Projeto";
        } else if (item instanceof PlantaBaixa) {
            seletor = "Planta Baixa";
        } else if (item instanceof Painel) {
            seletor = "Painel";
        } else if (item instanceof Abertura) {
            seletor = "Abertura";
        } else if (item instanceof Perfil) {
            seletor = "Perfil";
        } else {
            seletor = "Outro Item";
        }

        switch (seletor) {
            case "Projeto":
                return "PRJ :" + String.valueOf(((Projeto) item).getPrjCodigo());
            case "Planta Baixa":
                return "PB  :" + String.valueOf(((PlantaBaixa) item).getPbCodigo());
            case "Painel":
                return "Pn  :" + String.valueOf(((Painel) item).getPnCodigo());
            case "Abertura":
                return "Ab  :" + String.valueOf(((Abertura) item).getAbCodigo());
            case "Perfil":
                return "Pf  :" + String.valueOf(((Perfil) item).getPfCodigo());
            default:
                return ("Outro Item");
        }
    }

    /*
     * Monta o Painel de Tabalho para qquer Objeto
     */
    public void setPainelDeTrabalho(GridPane painelDeTrabalho, Object obj) {
        this.painelTrabalho.getChildren().clear();
        this.painelTrabalho.getColumnConstraints().clear();
        this.painelTrabalho.getRowConstraints().clear();

        this.painelTrabalho.setStyle("-fx-background-color: #C3C3C3;");

        // Torna as linhas do Grid Visíveis (ou não)!
        this.painelTrabalho.setHgap(5.0);
        this.painelTrabalho.setVgap(5.0);
        this.painelTrabalho.setGridLinesVisible(false);

        //Adiciona "Constraints" para as linhas e colunas ficarem alinhadas a esquerda e ao topo
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHalignment(HPos.LEFT);
        column0.setMinWidth(50.0);
        column0.setMaxWidth(50.0);
        column0.setPrefWidth(50.0);
        column0.setHgrow(Priority.NEVER);
        this.painelTrabalho.getColumnConstraints().add(column0);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        column1.setMinWidth(50.0);
        column1.setMaxWidth(50.0);
        column1.setPrefWidth(50.0);
        column1.setHgrow(Priority.NEVER);
        this.painelTrabalho.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        column2.setHgrow(Priority.ALWAYS);
        this.painelTrabalho.getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHalignment(HPos.RIGHT);
        column3.setMinWidth(5.0);
        column3.setMaxWidth(5.0);
        column3.setPrefWidth(5.0);
        column3.setHgrow(Priority.NEVER);
        this.painelTrabalho.getColumnConstraints().add(column3);

        RowConstraints row0 = new RowConstraints();
        row0.setValignment(VPos.TOP);
        row0.setVgrow(Priority.NEVER);
        this.painelTrabalho.getRowConstraints().add(row0);

        RowConstraints row1 = new RowConstraints();
        row1.setValignment(VPos.TOP);
        row1.setVgrow(Priority.NEVER);
        this.painelTrabalho.getRowConstraints().add(row1);

        RowConstraints row2 = new RowConstraints();
        row2.setValignment(VPos.TOP);
        row2.setMinHeight(30.0);
        row2.setMaxHeight(30.0);
        row2.setPrefHeight(30.0);
        row2.setVgrow(Priority.NEVER);
        this.painelTrabalho.getRowConstraints().add(row2);

        RowConstraints row3 = new RowConstraints();
        row3.setValignment(VPos.TOP);
        this.painelTrabalho.getRowConstraints().add(row3);

        /*
         * CRIA GRUPO DE VISUALIZACAO DE CADA OBJETO (Projeto,PlantaBaixa, Painel etc)
         * =====================================================================
         */
        // Se o obj for um Projeto
        if ("Projeto".equals(obj.getClass().getSimpleName())) {
            grupoVisualizaElemento = ((Projeto) obj).geraGrupoVisualizacao();
        }
        // Se o obj for um PlantaBaixa
        if ("PlantaBaixa".equals(obj.getClass().getSimpleName())) {
            grupoVisualizaElemento = ((PlantaBaixa) obj).geraGrupoVisualizacao();
        }
        // Se o obj for um Painel
        if ("Painel".equals(obj.getClass().getSimpleName())) {
            grupoVisualizaElemento = ((Painel) obj).geraGrupoVisualizacao();
        }
        /*
         * =====================================================================
         */

        Pane scrPane = new Pane(); // Painel que vai ficar na área maior do Grid
        scrPane.setStyle("-fx-background-color: #AAAAAA;");

        Pane reguaX = new Pane(); // Regua no eixo X
        Pane reguaY = new Pane(); // Regua no eixo Y
        // Define o recClip para a ReguaX
        Rectangle recClipReguaX = new Rectangle();

        /*
         * Define handle do click para Arrastar-e-soltar o grupo
         */
        grupoVisualizaElemento.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Group) (t.getSource())).getTranslateX();
                orgTranslateY = ((Group) (t.getSource())).getTranslateY();
            }
        });

        /*
         * Define handle do drag para Arrastar-e-soltar o grupo
         */
        grupoVisualizaElemento.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Group) (t.getSource())).setTranslateX(newTranslateX);
                ((Group) (t.getSource())).setTranslateY(newTranslateY);
                reguaX.getChildren().clear();
                reguaX.getChildren().add(setReguaX(grupoVisualizaElemento, scrPane.getWidth()));
                reguaY.getChildren().clear();
                reguaY.getChildren().add(setReguaY(grupoVisualizaElemento, scrPane.getHeight()));
            }
        });

        /*
         * Define handle para tratar o rolar do mouse para zoom. Zoom no ponto Pivô
         */
        grupoVisualizaElemento.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double scale = grupoVisualizaElemento.getScaleX(); // currently we only use Y, same value is used for X
                double oldScale = scale;
                scale *= Math.pow(1.01, event.getDeltaY());
                if (scale <= MIN_SCALE) {
                    scale = MIN_SCALE;
                } else if (scale >= MAX_SCALE) {
                    scale = MAX_SCALE;
                }
                double f = (scale / oldScale) - 1;
                double dx = (event.getSceneX() - (grupoVisualizaElemento.getBoundsInParent().getWidth() / 2 + grupoVisualizaElemento.getBoundsInParent().getMinX()));
                double dy = (event.getSceneY() - (grupoVisualizaElemento.getBoundsInParent().getHeight() / 2 + grupoVisualizaElemento.getBoundsInParent().getMinY()));
                grupoVisualizaElemento.setScaleX(scale);
                grupoVisualizaElemento.setScaleY(scale);
                grupoVisualizaElemento.setTranslateX(grupoVisualizaElemento.getTranslateX() - f * dx);
                grupoVisualizaElemento.setTranslateY(grupoVisualizaElemento.getTranslateY() - f * dy);

                reguaX.getChildren().clear();
                reguaX.getChildren().add(setReguaX(grupoVisualizaElemento, scrPane.getWidth()));
                reguaY.getChildren().clear();
                reguaY.getChildren().add(setReguaY(grupoVisualizaElemento, scrPane.getHeight()));

                event.consume();

            }
        });

        /*
         * Adiciona botão no GRID para ajustar o Zoom a 1:1
         */
        Button btnZero = new Button();
        btnZero.setText("Zoom 1:1");
        btnZero.setMinSize(70, 30);
        btnZero.setPrefSize(70, 30);
        btnZero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grupoVisualizaElemento.setScaleX(1.0);
                grupoVisualizaElemento.setScaleY(1.0);
                grupoVisualizaElemento.setTranslateX(5.0);
                grupoVisualizaElemento.setTranslateY(5.0);
                reguaX.getChildren().clear();
                reguaX.getChildren().add(setReguaX(grupoVisualizaElemento, scrPane.getWidth()));
                reguaY.getChildren().clear();
                reguaY.getChildren().add(setReguaY(grupoVisualizaElemento, scrPane.getHeight()));
            }
        });

        /*
         * Adiciona botão no GRID para ajustar o Zoom Otimizado
         */
        Button btnOtimiz = new Button();
        btnOtimiz.setText("Zoom X:Y");
        btnOtimiz.setMinSize(70, 30);
        btnOtimiz.setPrefSize(70, 30);
        btnOtimiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Double scalaY = painelTrabalho.getLayoutBounds().getHeight() / grupoVisualizaElemento.getBoundsInParent().getHeight();
                Double scalaX = painelTrabalho.getLayoutBounds().getWidth() / grupoVisualizaElemento.getBoundsInParent().getWidth();
                if (scalaY <= scalaX) {
                    grupoVisualizaElemento.setScaleX(scalaY);
                    grupoVisualizaElemento.setScaleY(scalaY);

                } else {
                    grupoVisualizaElemento.setScaleX(scalaX);
                    grupoVisualizaElemento.setScaleY(scalaX);
                }
                Double deslocXAposZoom = grupoVisualizaElemento.getTranslateX() - grupoVisualizaElemento.getBoundsInParent().getMinX();
                Double deslocYAposZoom = grupoVisualizaElemento.getTranslateY() - grupoVisualizaElemento.getBoundsInParent().getMinY();
                grupoVisualizaElemento.setTranslateX(deslocXAposZoom);
                grupoVisualizaElemento.setTranslateY(deslocYAposZoom);
                reguaX.getChildren().clear();
                reguaX.getChildren().add(setReguaX(grupoVisualizaElemento, scrPane.getWidth()));
                reguaY.getChildren().clear();
                reguaY.getChildren().add(setReguaY(grupoVisualizaElemento, scrPane.getHeight()));
            }
        });

        /*
         * Define o retângulo para o Clip do Scrpane
         */
        Rectangle recClip = new Rectangle();
        recClip.setWidth(scrPane.getWidth());
        recClip.setHeight(scrPane.getHeight());

        /*
         * Adiciona listener para alterações de largura do clip
         */
        scrPane.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                recClip.setWidth(scrPane.getWidth());
                scrPane.setClip(recClip);
                recClipReguaX.setWidth(scrPane.getWidth());
                recClipReguaX.setHeight(reguaX.getBoundsInParent().getHeight());
                reguaX.setClip(recClipReguaX);
            }
        });

        /*
         * Adiciona listener para alterações de altura do clip
         */
        scrPane.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                recClip.setHeight(scrPane.getHeight());
                scrPane.setClip(recClip);
            }
        });

        /*
         * Define o recClip quando mostrar o Pane
         */
        scrPane.setClip(recClip);
        /*
         * Adiciona as reguas e o Grupo de Visualização aos Panes: scrPane, reguaX e reguaY
         */

        scrPane.getChildren().add(grupoVisualizaElemento);
        reguaX.getChildren().add(setReguaX(grupoVisualizaElemento, scrPane.getWidth()));
        reguaY.getChildren().add(setReguaY(grupoVisualizaElemento, scrPane.getHeight()));

        // Inicializa o Clip para ReguaX com a largura do grupoVisualização
        recClipReguaX.setWidth(grupoVisualizaElemento.getBoundsInLocal().getWidth());
        recClipReguaX.setHeight(reguaX.getBoundsInParent().getHeight());
        reguaX.setClip(recClipReguaX);

        // Adiciona os botões, reguas e o Pane onde está o Grupo de Visualização
        this.painelTrabalho.add(btnZero, 0, 0);
        this.painelTrabalho.add(btnOtimiz, 0, 1);
        this.painelTrabalho.add(reguaX, 2, 2);

        this.painelTrabalho.add(reguaY, 1, 3);
        this.painelTrabalho.add(scrPane, 2, 3);
 
    }

    private Pane setReguaX(Group grupoPai, double scrPaneWidth) {
        Pane reguaPane = new Pane();
        reguaPane.getChildren().clear();
        Double viewScaleX = grupoPai.getScaleX();
        Double grupoWidth = grupoPai.getLayoutBounds().getWidth();
        Double reguaWidth = grupoPai.getBoundsInParent().getWidth();
        Double grupoScaleGap = grupoPai.getBoundsInParent().getMinX();
        Double posReguaMinX = grupoScaleGap / viewScaleX;
        Double posReguaX = 0 - posReguaMinX;
        Double posReguaXFloor;
        Integer marcaMaiorRegua = 50;
        if (viewScaleX > 2) {
            marcaMaiorRegua = 10;
        }
        if (viewScaleX > 1 && viewScaleX <= 2.0) {
            marcaMaiorRegua = 20;
        }
        if (viewScaleX >= 0.5 && viewScaleX <= 1.0) {
            marcaMaiorRegua = 50;
        }
        if (viewScaleX < 0.5 && viewScaleX >= 0.3) {
            marcaMaiorRegua = 100;
        }
        if (viewScaleX < 0.3) {
            marcaMaiorRegua = 200;
        }

        Double ultimaMarcaRegua;
        if (posReguaMinX >= 0) {
            ultimaMarcaRegua = 0d - marcaMaiorRegua;
        } else {
            ultimaMarcaRegua = 99999999d;
        }
        Double numeroRegua;
        int posViewX = 0;
        Boolean flagLinha = false;
        Boolean geraLinha = false;
        while (posReguaX <= grupoWidth) {
            posReguaXFloor = Math.floor(posReguaX);

            if ((posReguaX >= 0 && (posReguaXFloor % marcaMaiorRegua) == 0)) {
                geraLinha = true;
            }
            if (posReguaX > (ultimaMarcaRegua + marcaMaiorRegua)) {
                geraLinha = true;
            }
            if (geraLinha) {
                if (!flagLinha) {
                    DecimalFormat myFormatter = new DecimalFormat("#");
                    numeroRegua = posReguaX;
                    if (posReguaX > ultimaMarcaRegua + marcaMaiorRegua) {
                        numeroRegua = ultimaMarcaRegua + marcaMaiorRegua;
                    }
                    Text t = new Text(posViewX, 0.0, myFormatter.format(numeroRegua));
                    t.setFont(new Font(10));
                    t.setTextAlignment(TextAlignment.CENTER);
                    t.setTextOrigin(VPos.TOP);
                    t.setTranslateX(-((t.getLayoutBounds().getWidth()) / 2));
                    reguaPane.getChildren().add(t);
                    Line line50 = new Line(posViewX, 15.0, posViewX, 25.0);
                    reguaPane.getChildren().add(line50);
                    flagLinha = true;
                    ultimaMarcaRegua = numeroRegua;
                }
            }
            geraLinha = false;
            posViewX++;
            posReguaX = posReguaX + (1 / viewScaleX);
            if (posReguaX > ultimaMarcaRegua + (marcaMaiorRegua / 2)) {
                flagLinha = false;
            }
        }
        return reguaPane;
    }

    private Pane setReguaY(Group grupoPai, double scrPaneHeight) {
        Pane reguaPane = new Pane();
        reguaPane.getChildren().clear();
        Double viewScaleY = grupoPai.getScaleY();
        Double grupoHeight = grupoPai.getLayoutBounds().getHeight();
        Double reguaHeight = grupoPai.getBoundsInParent().getHeight();
        Double grupoScaleGap = grupoPai.getBoundsInParent().getMinY();
        Double posReguaMinY = grupoScaleGap / viewScaleY;
        Double posReguaY = 0 - posReguaMinY;
        Double posReguaYFloor;
        Integer marcaMaiorRegua = 50;
        if (viewScaleY > 2) {
            marcaMaiorRegua = 10;
        }
        if (viewScaleY > 1 && viewScaleY <= 2.0) {
            marcaMaiorRegua = 20;
        }
        if (viewScaleY >= 0.5 && viewScaleY <= 1.0) {
            marcaMaiorRegua = 50;
        }
        if (viewScaleY < 0.5 && viewScaleY >= 0.3) {
            marcaMaiorRegua = 100;
        }
        if (viewScaleY < 0.3) {
            marcaMaiorRegua = 200;
        }

        Double ultimaMarcaRegua;
        if (posReguaMinY >= 0) {
            ultimaMarcaRegua = 0d - marcaMaiorRegua;
        } else {
            ultimaMarcaRegua = 99999999d;
        }
        Double numeroRegua;
        int posViewY = 0;
        Boolean flagLinha = false;
        Boolean geraLinha = false;
        while (posReguaY <= grupoHeight) {
            posReguaYFloor = Math.floor(posReguaY);

            if ((posReguaY >= 0 && (posReguaYFloor % marcaMaiorRegua) == 0)) {
                geraLinha = true;
            }
            if (posReguaY > (ultimaMarcaRegua + marcaMaiorRegua)) {
                geraLinha = true;
            }
            if (geraLinha) {
                if (!flagLinha) {
                    DecimalFormat myFormatter = new DecimalFormat("#");
                    numeroRegua = posReguaY;
                    if (posReguaY > ultimaMarcaRegua + marcaMaiorRegua) {
                        numeroRegua = ultimaMarcaRegua + marcaMaiorRegua;
                    }
                    Text t = new Text(0.0, posViewY, myFormatter.format(numeroRegua));
                    t.setFont(new Font(10d));
                    t.setTextAlignment(TextAlignment.LEFT);
                    t.setTranslateY(+((t.getLayoutBounds().getHeight()) / 2));
                    reguaPane.getChildren().add(t);
                    Line line50 = new Line(25d, posViewY, 35d, posViewY);
                    reguaPane.getChildren().add(line50);
                    flagLinha = true;
                    ultimaMarcaRegua = numeroRegua;
                }
            }
            geraLinha = false;
            posViewY++;
            posReguaY = posReguaY + (1 / viewScaleY);
            if (posReguaY > ultimaMarcaRegua + (marcaMaiorRegua / 2)) {
                flagLinha = false;
            }
        }
        return reguaPane;
    }

    /*
     * Define a Regua no eixo Y
     private Pane setReguaY(Group grupoPai, double scrPaneHeight) {
     Pane reguaPane = new Pane();
     reguaPane.getChildren().clear();
     reguaPane.setMaxWidth(50d);
     reguaPane.setPrefWidth(50d);
     reguaPane.setMinWidth(50d);
     Double grupoDeslocY = grupoPai.getTranslateY();
     Double viewScaleY = grupoPai.getScaleY();
     Double grupoHeight = grupoPai.getLayoutBounds().getHeight();
     Double reguaHeight = grupoPai.getBoundsInParent().getHeight();
     Double grupoScaleGap = grupoPai.getBoundsInParent().getMinY();
     Double posReguaMinY = grupoScaleGap / viewScaleY;
     Double posReguaMaxY = posReguaMinY + reguaHeight;
     Double posReguaY = 0 - posReguaMinY;
     int auxPosReguaY = (int) Math.floor(posReguaY);
     if ((auxPosReguaY & 1) != 0) {  // Testa se o primero bit (operador &) do inteiro é 1, então o valor é impar
     ++posReguaY;
     }
     Double posViewY = 0.0;
     Boolean flagLinha = false;

     while (posReguaY <= grupoHeight) {
     if (posReguaY >= 0 && (Math.floor(posReguaY)) % 50 == 0) {
     if (!flagLinha) {
     DecimalFormat myFormatter = new DecimalFormat("#");
     Text t = new Text(0d, posViewY, myFormatter.format(posReguaY));
     t.setFont(new Font(10d));
     t.setTextAlignment(TextAlignment.LEFT);
     t.setTranslateY(+((t.getLayoutBounds().getHeight()) / 2));
     reguaPane.getChildren().add(t);
     Line line50 = new Line(25d, posViewY, 35d, posViewY);
     reguaPane.getChildren().add(line50);
     flagLinha = true;
     }
     } else if (posReguaY >= 0 && (Math.floor(posReguaY)) % 10 == 0) {
     if (!flagLinha) {
     Line line10 = new Line(25d, posViewY, 30d, posViewY);
     reguaPane.getChildren().add(line10);
     flagLinha = true;
     }
     } else {
     flagLinha = false;
     }
     posViewY++;
     posReguaY = posReguaY + (1 / viewScaleY);
     }
     return reguaPane;
     }
     */
// get and set
    public MenuItem getMenuSair() {
        return menuSair;
    }

    public MenuItem getMenuAjudar() {
        return menuAjudar;
    }

    public BorderPane getPainelPrincipal() {
        return painelPrincipal;
    }

    public MenuBar getBarraDeMenus() {
        return barraDeMenus;
    }

    public Menu getMenuArquivo() {
        return menuArquivo;
    }

    public Menu getMenuEditar() {
        return menuEditar;
    }

    public Menu getMenuAjuda() {
        return menuAjuda;
    }

    public MenuItem getMenuAbrir() {
        return menuAbrir;
    }

    public MenuItem getMenuSalvar() {
        return menuSalvar;
    }

    public MenuItem getMenuNovoProjeto() {
        return menuNovoProjeto;
    }

    public Stage getStage() {
        return stage;
    }

    public MenuItem getMenuEditaProjeto() {
        return menuEditaProjeto;
    }

    public MenuItem getMenuExcluiPlantaBaixa() {
        return menuExcluiPlantaBaixa;
    }

    public MenuItem getMenuExcluiPainel() {
        return menuExcluiPainel;
    }

    public MenuItem getMenuNovaPlantaBaixa() {
        return menuNovaPlantaBaixa;
    }

    public MenuItem getMenuNovoPainel() {
        return menuNovoPainel;
    }

    public MenuItem getMenuEditaPlantaBaixa() {
        return menuEditaPlantaBaixa;
    }

    public MenuItem getMenuEditaPainel() {
        return menuEditaPainel;
    }

    public MenuItem getMenuNovaAbertura() {
        return menuNovaAbertura;
    }

    public MenuItem getMenuEditaAbertura() {
        return menuEditaAbertura;
    }

    public MenuItem getMenuNovoPerfil() {
        return menuNovoPerfil;
    }

    public MenuItem getMenuEditaPerfil() {
        return menuEditaPerfil;
    }

    public MenuItem getMenuSobre() {
        return menuSobre;
    }

    public SplitPane getPainelSplit() {
        return painelSplit;
    }

    public ContextMenu getMenuContextoArvore() {
        return menuContextoArvore;
    }

    public TreeView<Object> getArvoreObjetos() {
        return arvoreObjetos;
    }

    public TreeItem<Object> getNoRaiz() {
        return noRaiz;
    }

    public TrvProjeto getTrvProjeto() {
        return trvProjeto;
    }

    public Object getObjetoSelecionadoNaArvore() {
        return objetoSelecionadoNaArvore;
    }

    public void setObjetoSelecionadoNaArvore(Object objetoSelecionadoNaArvore) {
        this.objetoSelecionadoNaArvore = objetoSelecionadoNaArvore;
    }

    public Object getPaiObjetoSelecionadoNaArvore() {
        return paiObjetoSelecionadoNaArvore;
    }

    public void setPaiObjetoSelecionadoNaArvore(Object paiObjetoSelecionadoNaArvore) {
        this.paiObjetoSelecionadoNaArvore = paiObjetoSelecionadoNaArvore;
    }

    public VBox getPainelArvoreObjetos() {
        return painelArvoreObjetos;
    }

    public Scene getScene() {
        return scene;
    }

    public Projeto getProjeto() {
        return projeto;
    }

}
