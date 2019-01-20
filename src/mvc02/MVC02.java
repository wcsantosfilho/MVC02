/*
 * Lista de Pendências do Projeto
 * Item	Pendência                                                               Feito?
 * 1	Implementar o controller para o Accordion do Projeto                    Sim
 * 2    Usar ou não Business Objects para tratar com o Controller?              Cancelado
 * 2	Implementar o mecanismo para incluir no Model                           Sim
 * 3	Implementar o mecanismo para "ler" a alteração do Model e chamar a View	Sim
 * 4    Estudar Generics                                                        Não
 * 5    Estudar Herança (extends) e Interface (implements)   (Caleum)           Não
 * 6    Estudar callback para as funçoes de dialogo                             Sim
 * 7    Estudar padrão Abstract Factory (DAO)                                   Não
 * 8    Criar Dialogo de parâmetros padrão (altura, largura, espessura, etc)    Não
 * 9    Criar arquivo de configuração do layout salvo qdo fechar a aplicação    +/-
 *      -> Posição de fechamento na tela ou maximizado
 *10    Criar mecanismo unificado de exceções da aplicação                      Não
 *11    Estudar JAXB                                                            Não
 *12    Mudar o esquema dos painéis para Pos1, Pos2, Pos3 e Pos4(x,y)           Sim
 *      -> Consistir para não fazer sutiã                                       Não
 *      -> Na edição, bloquear campos de comprimento, deixar só os absolutos    Não
 *      -> Calcular os pontos resultantes
 *      -> com base na espessura (daí teria que ter o ângulo...)                Não
 *13    Edição de painéis (problema na selação do Pai(planta baixa) no Ctrller  Sim
 *14    Atualização da árvore quando altera uma planta baixa ou painel(só atualiza por projeto) Sim
 *15    Painel de mensagens não pode sumir na alteração de tamanho da scene     Não
 *16    Codigo do Projeto com null em "Novo Projeto"                            Não
 *17    Manter toda a arvore expandida quando cria um novo painel               Sim
 *18    Eliminar o menu de contexto da TreeView                                 Sim
 *19    Mostrar apenas a planta baixa selecionada                               Sim
 *20    Mostrar os objetos na árvore quando são inseridos e não apenas na alteração do projeto  Sim
 *21    Alteração de Painel não traz comprimento ou orientação, gera erro qdo aperta "Calc"     Não
 *22    Alteração de Painel não atualiza automaticamente a visão da Planta Baixa                Não
 *23    Mecanismo de Listener para mostrar nova PlantaBaixa após alteração de Painel?
 *24    Item de menu para excluir painel e excluir planta baixa                 Não
 *25    Consistir sobreposição de painéis (x,y)                                 Não
 *26    Criar Padding para o Grupo na área de trabalho e régua                  Não
 *27    Criar "fundo" no Group para arrastar a Planta Baixa e resize cfe tamanho do Group       Não
 *28    Criar mecanismo para salvar planta XML                                  Sim
 *29    A visualização deo Group fica diminuindo a cada clique na planta baixa se estiver vazia Sim
 *30    Atualizar a view da PlantaBaixa quando insere um novo Painel            Não
 *31    Janela de seleção de arquivos para Abrir e Salvar                       Não
 *32    Eliminar usos de classes "VO" por outras classes que não sejam a BO     Não
 *33    Substutiur GrupoVisualizacao da view pela interface GrupoVisualiz       Náo
 *34    Mudar os termos no Painel:
 *      - Espessura --> Espessura do Painel
 *      - Orientação(Vert/Horiz) --> Longitudinal/Transvesal/Outro
 *      --> Colocar aviso que 
 *35    Consistir limites do Painel na inclusão de aberturas                    Não
 *36    Consistir limites do Painel na inclusão de perfil                       Não
 *37    Consistir limites da Abertura na inclusão dos perfis                    Nâo
 *37    Incluir dado de tamanho da abertura                                     Sim
 *38    Transformar TODOS os Short em Integer                                   Sim
 *39    Montar mecanismo de Grid igual LibreCad                                 Não
 *40    Montar mecanismo para redimensionar arestas                             Não
 *41    Montar mecanismo para atualizar limites dos painéis cfe alterações do mouse     Não
 *42    Melhorar Arrastar & Soltar quando move objetos em Escala                Não
 *43    Criar labels de medidas                                                 Não
 *44    Mostrar na régua apenas os limites dos painéis/objetos                  Não
 *45    Permitir arrastar vértices dos polygonos                                Sim
 */
package mvc02;

import javafx.application.Application;
import javafx.stage.Stage;
import mvc02.controller.ControllerPrincipal;
import mvc02.model.vo.Projeto;
import mvc02.view.ScenePrincipal;

/**
 *
 * @author wcsan_000
 */
public class MVC02 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Projeto projeto = new Projeto();

        ScenePrincipal scenePrincipal = new ScenePrincipal(primaryStage, projeto);
        // Adiciona um Listener para avisar a view na scenePrincipal sobre alteraçoes no projeto
        projeto.addProjetoListener(scenePrincipal);
        ControllerPrincipal controllerPrincipal = new ControllerPrincipal(scenePrincipal);

        primaryStage.show();
    }
}
