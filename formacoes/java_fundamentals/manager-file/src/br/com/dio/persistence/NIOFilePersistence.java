package br.com.dio.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIOFilePersistence implements FilePersistence{
    /** Diretório atual do sistema onde a aplicação está sendo executada */
    private final String currentDir = System.getProperty("user.dir");
    /** Diretório relativo onde os arquivos gerenciados serão armazenados */
    private final String storedDir = "managedFiles/IO";

    /** Caminho completo do arquivo gerenciado */
    private final Path filePath;

    /**
     * Construtor da classe IOFilePersistence.
     * Inicializa o gerenciador de persistência criando o diretório necessário
     * e preparando o arquivo para operações de leitura/escrita.
     *
     * @param fileName Nome do arquivo a ser gerenciado (ex: "user.csv")
     * @throws IOException Se houver erro ao criar os diretórios necessários
     */
    public NIOFilePersistence(String fileName) throws IOException{
        var dirPath = Paths.get(currentDir, storedDir);
        Files.createDirectories(dirPath);

        this.filePath = dirPath.resolve(fileName);
        if (!Files.exists(this.filePath)) {
            Files.createFile(this.filePath);
        }

        clearFile();
    }

    /**
     * Escreve uma nova linha de dados no arquivo.
     * O conteúdo é adicionado ao final do arquivo (modo append).
     *
     * @param data String contendo os dados a serem escritos (ex: "nome;email;data")
     * @return A string que foi escrita no arquivo
     */
    @Override
    public String write(String data) {
        try {
            Files.writeString(filePath, data + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    /**
     * Remove todas as linhas do arquivo que contenham a sentença especificada.
     * O método lê todo o conteúdo, filtra as linhas que não contêm a sentença,
     * limpa o arquivo e reescreve apenas as linhas que devem permanecer.
     *
     * @param sentence Texto a ser procurado nas linhas para remoção
     * @return true se alguma linha foi removida, false caso contrário
     */
    @Override
    public boolean remove(String sentence) {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8).stream()
                    .filter(l -> !l.isEmpty())
                    .collect(Collectors.toList());

            boolean anyMatch = lines.stream().anyMatch(l -> l.contains(sentence));
            if (!anyMatch) return false;

            List<String> filtered = lines.stream()
                    .filter(l -> !l.contains(sentence))
                    .collect(Collectors.toList());

            Files.write(filePath, filtered, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Substitui todas as ocorrências de um conteúdo antigo por um novo conteúdo no arquivo.
     * Lê todo o arquivo, realiza a substituição de texto e reescreve o arquivo atualizado.
     *
     * @param oldContent Conteúdo a ser substituído
     * @param newContent Novo conteúdo que substituirá o antigo
     * @return O novo conteúdo completo do arquivo após as substituições
     */
    @Override
    public String replace(String oldContent, String newContent) {
        try {
            String content = findAll();
            String updatedContent = content.replace(oldContent, newContent);

            Files.writeString(filePath, updatedContent, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            return updatedContent;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * Retorna todo o conteúdo do arquivo como uma única string.
     * Cada linha do arquivo é separada pelo separador de linha do sistema.
     *
     * @return String contendo todo o conteúdo do arquivo
     * @throws FileNotFoundException Se o arquivo não existir (declarado na interface)
     */
    @Override
    public String findAll() {
        try {
            return Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Busca e retorna a primeira linha do arquivo que contenha a sentença especificada.
     * A busca é case-sensitive e retorna apenas a primeira ocorrência encontrada.
     *
     * @param sentence Texto a ser procurado nas linhas do arquivo
     * @return A primeira linha que contém a sentença, ou uma string vazia se não encontrada
     */
    @Override
    public String findBy(String sentence) {
        try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)){
            return stream.filter(l -> l.contains(sentence)).findFirst().orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Limpa todo o conteúdo do arquivo, deixando-o vazio.
     * Utiliza try-with-resources para garantir o fechamento correto do stream.
     * Este método é privado e usado internamente para operações de remoção e substituição.
     */
    private void clearFile(){
        try {
            Files.writeString(filePath, "", StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
