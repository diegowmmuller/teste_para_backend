package br.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String HOST = "localhost";
    private static final int PORTA = 3001;

    public static void main(String[] args) {
        // 🔹 Escolha qual teste executar
        //testarCriar();
        //testarListar();
        //testarEncontrar();
        //testarAtualizar();
        testarDeletar();
    }

    // ------------------- TESTES -------------------

    public static void testarCriar() {
        Categoria categoria = new Categoria(null, "Bebidas", Tamanho.MEDIO, Embalagem.PLASTICO);
        Requisicao<Categoria> requisicao = new Requisicao<>("criar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarListar() {
        Requisicao<Void> requisicao = new Requisicao<>("listar", "categoria", null);
        enviarRequisicao(requisicao);
    }

    public static void testarEncontrar() {
        Categoria categoria = new Categoria(1, null, null, null); // Buscar ID = 1
        Requisicao<Categoria> requisicao = new Requisicao<>("encontrar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarAtualizar() {
        Categoria categoria = new Categoria(1, "Bebidas Geladas", Tamanho.GRANDE, Embalagem.LATA);
        Requisicao<Categoria> requisicao = new Requisicao<>("atualizar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    public static void testarDeletar() {
        Categoria categoria = new Categoria(1, null, null, null); // Deletar ID = 1
        Requisicao<Categoria> requisicao = new Requisicao<>("deletar", "categoria", categoria);
        enviarRequisicao(requisicao);
    }

    // ------------------- MÉTODO GENÉRICO -------------------

    private static <T> void enviarRequisicao(Requisicao<T> requisicao) {
        try (Socket socket = new Socket(HOST, PORTA);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String json = mapper.writeValueAsString(requisicao);
            out.println(json); // Envia a requisição JSON
            System.out.println("→ Enviado: " + json);

            String resposta = in.readLine(); // Lê resposta
            System.out.println("← Resposta: " + resposta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
