package br.com.dio.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.stream.Stream;

/**
 * Implementação da interface FilePersistence utilizando operações de I/O tradicionais do Java.
 * Esta classe gerencia a persistência de dados em arquivos CSV usando streams de entrada e saída.
 * 
 * @author Anderson Carlos
 * @version 1.0
 * @since 2026
 */
public class IOFilePersistence implements  FilePersistence{

    /** Diretório atual do sistema onde a aplicação está sendo executada */
    private final String currentDir = System.getProperty("user.dir");
    
    /** Diretório relativo onde os arquivos gerenciados serão armazenados */
    private final String storedDir = "/managedFiles/IO/";
    
    /** Nome do arquivo a ser gerenciado */
    private final String fileName;

    /**
     * Construtor da classe IOFilePersistence.
     * Inicializa o gerenciador de persistência criando o diretório necessário
     * e preparando o arquivo para operações de leitura/escrita.
     * 
     * @param fileName Nome do arquivo a ser gerenciado (ex: "user.csv")
     * @throws IOException Se houver erro ao criar os diretórios necessários
     */
    public IOFilePersistence(String fileName) throws IOException{
        this.fileName = fileName;
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

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
        try(var fileWriter = new FileWriter(currentDir + storedDir + fileName, true);
            var bufferedWrite = new BufferedWriter(fileWriter);
            var printWrite = new PrintWriter(bufferedWrite);)
        {
            printWrite.println(data);
        } catch (IOException ex){
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
        var content = findAll();
        
        // Se o arquivo estiver vazio ou contiver apenas linhas vazias, não há nada para remover
        if (content == null || content.trim().isEmpty()) {
            return false;
        }
        
        // Divide o conteúdo em linhas e filtra linhas vazias
        var contentList = Stream.of(content.split(System.lineSeparator()))
                .filter(line -> !line.isEmpty())
                .toList();

        // Verifica se existe alguma linha com a sentença a ser removida
        if (contentList.stream().noneMatch(c -> c.contains(sentence))) {
            return false;
        }

        // Limpa o arquivo e reescreve apenas as linhas que não contêm a sentença
        clearFile();
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);
        return true;
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
        var content = findAll();
        var updatedContent = content.replace(oldContent, newContent);
        
        clearFile();
        
        // Reescreve o arquivo com o conteúdo atualizado
        var lines = updatedContent.split(System.lineSeparator());
        for (String line : lines) {
            if (!line.isEmpty()) {
                write(line);
            }
        }
        
        return updatedContent;
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
        var content = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader( currentDir+ storedDir + fileName))){
            String line;
            do {
                line = reader.readLine();
                if ((line != null)) content.append(line)
                        .append(System.lineSeparator());

            }while (line != null);
        } catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
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
        String found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))){
            String line = reader.readLine();
            while (line != null){
                if (line.contains(sentence)){
                    found = line;
                    break;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }

    /**
     * Limpa todo o conteúdo do arquivo, deixando-o vazio.
     * Utiliza try-with-resources para garantir o fechamento correto do stream.
     * Este método é privado e usado internamente para operações de remoção e substituição.
     */
    private void clearFile(){
        try (OutputStream outputStream = new FileOutputStream(new File(currentDir + storedDir + fileName))) {
            // O arquivo é criado/limpo automaticamente ao abrir o FileOutputStream
            // O try-with-resources garante o fechamento correto do stream
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
