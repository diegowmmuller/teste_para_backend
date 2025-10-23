package br.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import produto.Produto;

public class Main {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String HOST = "localhost";
    private static final int PORTA = 3001;

    public static void main(String[] args) {
        //testarCriarCategoria();
        //testarListarCategorias();
        //testarEncontrarCategoria();
        //testarAtualizarCategoria();
        //testarDeletarCategoria();
        
        
        //testarCriarProduto();
        testarListarProdutos();
        //testarEncontrarProduto();
        //testarAtualizarProduto();
        //testarDeletarProduto();
    }

    public static void testarCriarCategoria() {
        Categoria categoria = new Categoria(null, "Bebidas", Tamanho.MEDIO, Embalagem.PLASTICO);
        Requisicao<Categoria> requisicao = new Requisicao<>("criar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarListarCategorias() {
        Requisicao<Void> requisicao = new Requisicao<>("listar", "categoria", null);
        enviarRequisicao(requisicao);
    }

    public static void testarEncontrarCategoria() {
        Categoria categoria = new Categoria(1, null, null, null);
        Requisicao<Categoria> requisicao = new Requisicao<>("encontrar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarAtualizarCategoria() {
        Categoria categoria = new Categoria(1, "Bebidas Geladas", Tamanho.GRANDE, Embalagem.LATA);
        Requisicao<Categoria> requisicao = new Requisicao<>("atualizar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarDeletarCategoria() {
        Categoria categoria = new Categoria(2, null, null, null);
        Requisicao<Categoria> requisicao = new Requisicao<>("deletar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarCriarProduto() {
        Categoria categoria = new Categoria(1, "Bebida", Tamanho.MEDIO, Embalagem.LATA);
        Produto produto = new Produto(null, "Coca-cola", 1.2, "unidade", categoria, 20, 5, 30);
        Requisicao<Produto> requisicao = new Requisicao<>("criar", "produto", produto);
        enviarRequisicao(requisicao);
    }

    public static void testarListarProdutos() {
        Requisicao<Void> requisicao = new Requisicao<>("listar", "produto", null);
        enviarRequisicao(requisicao);
    }

    public static void testarEncontrarProduto() {
        Produto produto = new Produto(1, null, 0.0, null, null, 0, 0, 0);
        Requisicao<Produto> requisicao = new Requisicao<>("encontrar", "produto", produto);
        enviarRequisicao(requisicao);
    }

    public static void testarAtualizarProduto() {
        Categoria categoria = new Categoria(1, "Bebidas Geladas", Tamanho.GRANDE, Embalagem.LATA);
        Produto produto = new Produto(4, "Coca-cola Zero Zero", 2.5, "unidade", categoria, 20, 5, 30);
        Requisicao<Produto> requisicao = new Requisicao<>("atualizar", "produto", produto);
        enviarRequisicao(requisicao);
    }

    public static void testarDeletarProduto() {
        Produto produto = new Produto(5, null, 0.0, null, null, 0, 0, 0);
        Requisicao<Produto> requisicao = new Requisicao<>("deletar", "produto", produto);
        enviarRequisicao(requisicao);
    }

    private static <T> void enviarRequisicao(Requisicao<T> requisicao) {
        try (Socket socket = new Socket(HOST, PORTA);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String json = mapper.writeValueAsString(requisicao);
            out.println(json);
            System.out.println("→ Enviado: " + json);

            String resposta = in.readLine();
            System.out.println("← Resposta: " + resposta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
