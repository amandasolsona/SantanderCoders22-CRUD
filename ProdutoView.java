package TesteCRUD2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    Scanner scanner = new Scanner(System.in);
    public Boolean menuPrincipal() {
        Boolean saida;
        String continua = null;
        do {
            System.out.println("+-------------------------------+");
            System.out.println("+-            MENU             -+");
            System.out.println("+-------------------------------+");
            System.out.println("+    Escolha Uma das Opções     +");
            System.out.println("+ 1. Cadastrar Produto          +");
            System.out.println("+ 2. Listar Produto             +");
            System.out.println("+ 3. Atualizar Produto por Id   +");
            System.out.println("+ 4. Remover Produto por Id     +");
            System.out.println("+ 5. Procurar Produto  por Id   +");
            System.out.println("+ 6. Procurar Produto por Nome  +");
            System.out.println("+ 7. Comprar Produto            +");
            System.out.println("+ 0. Sair                       +");
            System.out.println("+-------------------------------+");

            try {
                switch (Integer.valueOf(scanner.nextLine())) {
                    case 1:
                        continua = menuCadastrarProduto();
                        saida = true;
                        break;
                    case 2:
                        continua = menuListaProduto();
                        saida = true;
                        break;
                    case 3:
                        continua = menuAtualizarProduto();
                        saida = true;
                        break;
                    case 4:
                        continua = menuRemoverProduto();
                        saida = true;
                        break;
                    case 5:
                        continua = menuProcurarProduto();
                        saida = true;
                        break;

                    case 6: menuProcurarProdutoNome();
                        saida = true;
                        break;

                    case 7: menuComprarProduto();
                        saida = false;
                        break;

                    case 0:
                        continua = menuSair();
                        saida = false;
                        break;
                    default:
                        mensagemValorNaoEncontrado();
                        saida = true;
                        break;
                }

            }catch (Exception e) {
                System.out.println("Erro! Repita operação observando os valores digitados.");
                saida = true;
            }

        }while(saida);

        return false;

    }
    private String menuCadastrarProduto() throws Exception {
        Produto produto = new Produto();
        System.out.println("----------CADASTRAR------------");
        System.out.println("Digite o nome do Produto: ");
        String nome = scanner.nextLine().trim().toUpperCase();
        System.out.println("Digite o Quantidade do Produto: ");
        String quantidade = scanner.nextLine().trim().toUpperCase();;
        System.out.println("Digite o Valor do Produto: ");
        String preco = scanner.nextLine().trim().toUpperCase();
        Produto produtoValido = produto.validaProduto(nome, quantidade, preco);
        produto.cadastrarProduto(produtoValido);
        produto.listarProduto();
        System.out.println("Produto Cadastrado Com Sucesso!");
        return null;
    }
    private String menuListaProduto() throws Exception {
        Produto produto = new Produto();
        System.out.println("----------LISTA------------");
        produto.listarProduto();
        return null;
    }
    private String menuAtualizarProduto() throws Exception {
        Produto produto = new Produto();
        System.out.println("----------ATUALIZAR------------");
        produto.listarProduto();
        System.out.println("Digite o ID do Produto: ");
        String id = scanner.nextLine();
        Produto procuraProduto = produto.procuraProduto(id);
        if(procuraProduto.equals(null)) {
            System.out.println("Produto Não Encontrado");
        }else {
            System.out.println("Digite o novo nome do Produto: ");
            String nome = scanner.nextLine().trim().toUpperCase();
            System.out.println("Digite a nova Quantidade do Produto: ");
            String quantidade = scanner.nextLine().trim().toUpperCase();;
            System.out.println("Digite o novo Valor do Produto: ");
            String preco = scanner.nextLine().trim().toUpperCase();

            produto.atualizarProduto(procuraProduto, nome, quantidade, preco);
        }

        return null;
    }
    private String menuRemoverProduto() throws Exception {
        Produto produto = new Produto();
        System.out.println("----------REMOVER------------");
        produto.listarProduto();
        System.out.println("Digite o ID do Produto Para Remoção: ");
        String id = scanner.nextLine();
        Produto procuraProduto = produto.procuraProduto(id);
        if(procuraProduto.equals(null)) {
            System.out.println("Produto Não Encontrado");
        }else {
            System.out.println("----------ATENÇÃO----------");
            System.out.println("O produto ID: "+procuraProduto.getId()+" Nome: "+procuraProduto.getId());
            System.out.println("Será excluir do Nosso Sistema. Tem certeza dessa operação?");
            System.out.println("1.Sim");
            System.out.println("Qualquer outra tecla para cancelar!");
            switch (scanner.nextLine()) {
                case "1":
                    produto.removerProduto(procuraProduto);
                    System.out.println("Produto Excluído com Sucesso!");
                    break;
                default:
                    System.out.println("Operação Cancelada!");
                    break;
            }
        }
        return null;
    }
    private String menuProcurarProdutoNome() throws IOException {
        Produto produto = new Produto();
        System.out.println("Digite o nome do Produto ou parte dele: ");
        String nome = scanner.nextLine();
        Produto procuraProduto = produto.procuraProdutoNome(nome);
        if (procuraProduto.equals(null)) {
            System.out.println("Produto não Localizado");
        }
        return null;
    }

    private String menuProcurarProduto() throws IOException {
        Produto produto = new Produto();
        System.out.println("Digite o ID do Produto: ");
        String id = scanner.nextLine();
        Produto procuraProduto = produto.procuraProduto(id);
        if(procuraProduto.equals(null)) {
            System.out.println("Produto não Localizado");
        }
        return null;
    }
    private void menuComprarProduto() throws Exception {
        Produto produto = new Produto();
        produto.listarProduto();
        Boolean continua = true;
        List<Carrinho> listaPedido = new ArrayList<Carrinho>();
        do {
            System.out.println("Digite o ID do Produto:");
            Produto produtoSelecionado = produto.procuraProduto(scanner.nextLine());
            System.out.println("Dígite a Quantidade.");
            String quantidadeCompra = scanner.nextLine();
            Carrinho pedido = new Carrinho().verificaEstoque(produtoSelecionado, quantidadeCompra);
            listaPedido.add(pedido);
            System.out.println("Deseja adicionar mais algum item?");
            System.out.println("1.Sim");
            System.out.println("Qualquer outra tecla para finalizar a compra.");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Adicione mais um Produto.");
                    break;
                default:
                    continua = false;
                    break;
            }
        }while(continua);

        new Carrinho().somaFinal(listaPedido);

    }
    private String menuSair() {
        System.out.println("Obrigado por utilizar nosso sistema.");
        System.out.println("""
                Santander Coders 22'. Colaboradores:
                Amanda Solsona
                Ana Carolina Muratori
                Arthur Bicego
                Felipe Soares
                Rodrigo Rocha""");
        return null;
    }
    private void mensagemValorNaoEncontrado() {
        System.out.println("Valor não encontrado Repita a Operação");

    }
}