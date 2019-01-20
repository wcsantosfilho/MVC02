/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.controller;

import java.io.File;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import mvc02.model.vo.Projeto;
import mvc02.model.dao.ProjetoDAO;
import mvc02.model.vo.Abertura;

import mvc02.model.vo.Perfil;
import mvc02.model.vo.Painel;
import mvc02.model.vo.PlantaBaixa;
import mvc02.view.DialogInputAbertura;
import mvc02.view.DialogInputPainel;
import mvc02.view.DialogInputPerfil;
import mvc02.view.DialogInputPlantaBaixa;
import mvc02.view.DialogInputProjeto;
import mvc02.view.ScenePrincipal;

/**
 *
 * @author wcsan_000
 */
public class ControllerPrincipal {

    private ScenePrincipal scenePrincipal;
    private DialogInputProjeto inputProjeto;
    private DialogInputPlantaBaixa inputPlantaBaixa;
    private DialogInputPainel inputPainel;
    private DialogInputAbertura inputAbertura;
    private DialogInputPerfil inputPerfil;
    private Projeto projeto;
    private PlantaBaixa plantaBaixa;
    private Painel painel;
    private Abertura abertura;
    private Perfil perfil;
    public ProjetoDAO projetoDAO;

    /**
     * Construtor recebe o objeto da ScenePrincipal para 'observer' seu
     * comportamento, tratar os eventos e redirecionar para a model.
     *
     * @param scenePrincipal
     */
    public ControllerPrincipal(ScenePrincipal scenePrincipal) {
        this.scenePrincipal = scenePrincipal;
        this.projeto = scenePrincipal.getProjeto();

        // Associa item de menu da View a subclasse AbrirArquivo, que irá tratar o evento
        scenePrincipal.actionEventAbrir(new AbrirArquivo());
        // Associa item de menu da View a subclasse SalvarArquivos, que irá tratar o evento
        scenePrincipal.actionEventSalvar(new SalvarArquivo());
        // Associa item de menu da View a subclasse EncerrarAplicacao, que irá tratar o evento
        scenePrincipal.actionEventSair(new EncerrarAplicacao());
        // Associa item de menu da View a subclasse TrataNovoProjeto, que irá tratar o evento
        scenePrincipal.actionEventNovoProjeto(new TrataNovoProjeto());
        // Associa item de menu da View a subclasse TrataEditaProjeto, que irá tratar o evento
        scenePrincipal.actionEventEditaProjeto(new TrataEditaProjeto());
        // Associa item de menu da View a subclasse TrataNovoProjeto, que irá tratar o evento
        scenePrincipal.actionEventNovaPlantaBaixa(new TrataNovaPlantaBaixa());
        // Associa item de menu da View a subclasse TrataEditaProjeto, que irá tratar o evento
        scenePrincipal.actionEventEditaPlantaBaixa(new TrataEditaPlantaBaixa());
        // Associa item de menu da View a subclasse TrataNovoPainel, que irá tratar o evento
        scenePrincipal.actionEventNovoPainel(new TrataNovoPainel());
        // Associa item de menu da View a subclasse TrataEditaPainel, que irá tratar o evento
        scenePrincipal.actionEventEditaPainel(new TrataEditaPainel());
        // Associa item de menu da View a subclasse TrataNovaAbertura, que irá tratar o evento
        scenePrincipal.actionEventNovaAbertura(new TrataNovaAbertura());
        // Associa item de menu da View a subclasse TrataEditaAbertura, que irá tratar o evento
        scenePrincipal.actionEventEditaAbertura(new TrataEditaAbertura());
        // Associa objeto da TreeView da View a subclasse TrataNovoPerfil, que irá tratar o evento
        scenePrincipal.actionEventNovoPerfil(new TrataNovoPerfil());
        // Associa item de menu da View a subclasse TrataEditaPerfil, que irá tratar o evento
        scenePrincipal.actionEventEditaPerfil(new TrataEditaPerfil());
        // Associa objeto da TreeView da View a subclasse SelecionaObjeto, que irá tratar o evento
        scenePrincipal.mouseEventArvoreObjetos(new TrataSelecaoObjeto());
    }

    // Subclasse que fará o tratamento do item de menu AbrirArquivo
    class AbrirArquivo implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: AbrirArquivo
             */
            if (evento.getSource() == scenePrincipal.getMenuAbrir()) {
                System.out.println("Menu Abrir Arquivo");
                // Buca no Paramview o ultimo diretório usado
                String lastPath = scenePrincipal.paramView.getLastFolder();
                // Usar o FileChooser do JavaFx para selecionar o arquivo
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(lastPath));

                File file = fileChooser.showOpenDialog(scenePrincipal.stage);
                if (projetoDAO == null) {
                    projetoDAO = new ProjetoDAO();
                }
                if (file != null) {
                    Projeto projetoAux = projetoDAO.getFromArquivo(file);
                    // Salva o Diretorio Utilizado
                    scenePrincipal.lastFolderOpened = file.toPath().getParent().toString();
                    /*
                     * Copia do projetoAux lido do XML para o objeto projeto que está
                     * sempre instanciado. Não deve ser a melhor forma de fazer
                     * mas serve para não armazenar os listeners...
                     */
                    projeto.setPrjCodigo(projetoAux.getPrjCodigo());
                    projeto.setPrjDescricao(projetoAux.getPrjDescricao());
                    projeto.setAllPlantaBaixa(projetoAux.getAllPlantaBaixa());
                    projeto.disparaAlteracaoStatusProjeto();
                }

            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Salvar Arquivo
    class SalvarArquivo implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: Salvar Arquivo
             */
            if (evento.getSource() == scenePrincipal.getMenuSalvar()) {
                System.out.println("Menu SalvarArquivo");
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showSaveDialog(scenePrincipal.stage);

                if (projetoDAO == null) {
                    projetoDAO = new ProjetoDAO();
                }
                projetoDAO.saveToArquivo(projeto, file);
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Sair
    class EncerrarAplicacao implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: Sair
             */
            if (evento.getSource() == scenePrincipal.getMenuSair()) {
                System.out.println("Menu Sair");
                scenePrincipal.paramView.setPainelPrincipalHeight(scenePrincipal.getScene().getHeight());
                scenePrincipal.paramView.setPainelPrincipalWidth(scenePrincipal.getScene().getWidth());
                scenePrincipal.paramView.setPainelSplitDivider(scenePrincipal.getPainelSplit().getDividerPositions()[0]);
                scenePrincipal.paramView.setLastFolder(scenePrincipal.lastFolderOpened);

                scenePrincipal.paramView.saveToArquivo();
                scenePrincipal.stage.close();
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Novo Projeto
    class TrataNovoProjeto implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: NovoProjeto
             */

            // Valida se origem do evento foi o Menu Novo Projeto
            if (evento.getSource() == scenePrincipal.getMenuNovoProjeto()) {
                System.out.println("Controler Principal: Novo Projeto");
                if (projeto == null) {
                    projeto = new Projeto();
                }
                inputProjeto = new DialogInputProjeto(projeto);
                projeto.disparaAlteracaoStatusProjeto();
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Edita Projeto
    class TrataEditaProjeto implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: EditaProjeto
             */

            // Valida se origem do evento foi o Menu Edita Projeto
            if (evento.getSource() == scenePrincipal.getMenuEditaProjeto()) {
                System.out.println("Controler Principal: Edita Projeto");
                inputProjeto = new DialogInputProjeto(projeto);
                projeto.disparaAlteracaoStatusProjeto();
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Nova Planta Baixa
    class TrataNovaPlantaBaixa implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: NovaPlantaBaixa
             */

            // Valida se origem do evento foi o Menu Nova Planta Baixa
            if (evento.getSource() == scenePrincipal.getMenuNovaPlantaBaixa()) {
                System.out.println("Controler Principal: Nova Planta Baixa");
                if (projeto == null) {
                    System.out.println("Erro: Não pode criar planta baixa sem projeto");
                } else {
                    plantaBaixa = new PlantaBaixa();
                    inputPlantaBaixa = new DialogInputPlantaBaixa(projeto, plantaBaixa, "I");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Edita Planta Baixa
    class TrataEditaPlantaBaixa implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: EditaPlantaBaixa
             */

            // Valida se origem do evento foi o Menu Edita Planta Baixa
            if (evento.getSource() == scenePrincipal.getMenuEditaPlantaBaixa()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof PlantaBaixa) {
                plantaBaixa = (PlantaBaixa) scenePrincipal.getObjetoSelecionadoNaArvore();
                System.out.println("Controler Principal: Edita Planta Baixa");
                if (projeto == null) {
                    System.out.println("Erro: Não pode criar planta baixa sem projeto");
                } else {
                    inputPlantaBaixa = new DialogInputPlantaBaixa(projeto, plantaBaixa, "E");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Novo Painel
    class TrataNovoPainel implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: NovoPainel
             */

            // Valida se origem do evento foi o Menu NovoPainel com uma Planta Baixa selecionada
            if (evento.getSource() == scenePrincipal.getMenuNovoPainel()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof PlantaBaixa) {
                System.out.println("Controler Principal: Novo Painel");
                if (projeto == null) {
                    System.out.println("Erro: Não pode criar painel sem projeto");
                } else {
                    painel = new Painel();
                    plantaBaixa = (PlantaBaixa) scenePrincipal.getObjetoSelecionadoNaArvore();
                    inputPainel = new DialogInputPainel(plantaBaixa, painel, "I");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Edita Painel
    class TrataEditaPainel implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: EditaPainel
             */

            // Valida se origem do evento foi o Menu Edita Painel
            if (evento.getSource() == scenePrincipal.getMenuEditaPainel()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Painel) {
                plantaBaixa = (PlantaBaixa) scenePrincipal.getPaiObjetoSelecionadoNaArvore();
                painel = (Painel) scenePrincipal.getObjetoSelecionadoNaArvore();
                System.out.println("Controler Principal: Edita Painel");
                if (projeto == null) {
                    System.out.println("Erro: Não pode editar painel sem projeto");
                } else {
                    inputPainel = new DialogInputPainel(plantaBaixa, painel, "E");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Nova Abertura
    class TrataNovaAbertura implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: NovaAbertura
             */

            // Valida se origem do evento foi o Menu NovaAbertura com um Painel selecionada
            if (evento.getSource() == scenePrincipal.getMenuNovaAbertura()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Painel) {
                System.out.println("Controler Principal: Nova Abertura");
                if (projeto == null) {
                    System.out.println("Erro: Não pode criar abertura sem projeto");
                } else {
                    abertura = new Abertura();
                    painel = (Painel) scenePrincipal.getObjetoSelecionadoNaArvore();
                    inputAbertura = new DialogInputAbertura(painel, abertura, "I");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Edita Abertura
    class TrataEditaAbertura implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: EditaAbertura
             */

            // Valida se origem do evento foi o Menu Edita Abertura
            if (evento.getSource() == scenePrincipal.getMenuEditaAbertura()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Abertura) {
                painel = (Painel) scenePrincipal.getPaiObjetoSelecionadoNaArvore();
                abertura = (Abertura) scenePrincipal.getObjetoSelecionadoNaArvore();
                System.out.println("Controler Principal: Edita Abertura");
                if (projeto == null) {
                    System.out.println("Erro: Não pode editar abertura sem projeto");
                } else {
                    inputAbertura = new DialogInputAbertura(painel, abertura, "E");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Novo Perfil
    class TrataNovoPerfil implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: NovoPerfil
             */

            // Valida se origem do evento foi o Menu NovoPerfil com um Painel selecionado
            if (evento.getSource() == scenePrincipal.getMenuNovoPerfil()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Painel) {
                System.out.println("Controler Principal: Novo Perfil");
                if (projeto == null) {
                    System.out.println("Erro: Não pode criar perfil sem painel");
                } else {
                    perfil = new Perfil();
                    painel = (Painel) scenePrincipal.getObjetoSelecionadoNaArvore();
                    inputPerfil = new DialogInputPerfil(painel, perfil, "I");
                    projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento do item de menu Edita Perfil
    class TrataEditaPerfil implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent evento) {
            /* 
             * Tratamento para o MenuItem: EditaPerfil
             */

            // Valida se origem do evento foi o Menu Edita Perfil
            if (evento.getSource() == scenePrincipal.getMenuEditaPerfil()
                    && scenePrincipal.getObjetoSelecionadoNaArvore() != null
                    && scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Perfil) {
                painel = (Painel) scenePrincipal.getPaiObjetoSelecionadoNaArvore();
                perfil = (Perfil) scenePrincipal.getObjetoSelecionadoNaArvore();
                System.out.println("Controler Principal: Edita Perfil");
                if (projeto == null) {
                    System.out.println("Erro: Não pode editar perfil sem projeto");
                } else {
                    inputPerfil = new DialogInputPerfil(painel, perfil, "E");
                    //projeto.disparaAlteracaoStatusProjeto();
                }
            }
        }
    }

    // Subclasse que fará o tratamento da seleção de objetos no TreeView
    class TrataSelecaoObjeto implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent evento) {
            /* 
             * Tratamento para a TreeView: Seleção pelo Mouse
             */
            Node node = evento.getPickResult().getIntersectedNode();
            // Valida se origem do evento foi o Menu Edita Planta Baixa
            if (evento.getSource() == scenePrincipal.getArvoreObjetos()) {
                // Accept clicks only on node cells, and not on empty spaces of the TreeView
                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    scenePrincipal.setObjetoSelecionadoNaArvore(scenePrincipal.getArvoreObjetos().getSelectionModel().getSelectedItem().getValue());
                    scenePrincipal.setPaiObjetoSelecionadoNaArvore(scenePrincipal.getArvoreObjetos().getSelectionModel().getSelectedItem().getParent().getValue());
                    // Projeto Selecionado
                    if (scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Projeto
                            && scenePrincipal.getObjetoSelecionadoNaArvore() != null) {
                        Projeto tmpProjeto = (Projeto) scenePrincipal.getObjetoSelecionadoNaArvore();
                        scenePrincipal.setPainelDeTrabalho(scenePrincipal.painelTrabalho, tmpProjeto);
                    }
                    // Planta Baixa Selecionada
                    if (scenePrincipal.getObjetoSelecionadoNaArvore() instanceof PlantaBaixa
                            && scenePrincipal.getObjetoSelecionadoNaArvore() != null) {
                        PlantaBaixa tempPb = (PlantaBaixa) scenePrincipal.getObjetoSelecionadoNaArvore();
                        scenePrincipal.setPainelDeTrabalho(scenePrincipal.painelTrabalho, tempPb);
                    }
                    // Painel Selecionado
                    if (scenePrincipal.getObjetoSelecionadoNaArvore() instanceof Painel
                            && scenePrincipal.getObjetoSelecionadoNaArvore() != null) {
                        Painel tempPn = (Painel) scenePrincipal.getObjetoSelecionadoNaArvore();
                        scenePrincipal.setPainelDeTrabalho(scenePrincipal.painelTrabalho, tempPn);
                    }
                }
            }
        }

    }
}
