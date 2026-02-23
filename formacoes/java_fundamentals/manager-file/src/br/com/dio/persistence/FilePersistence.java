package br.com.dio.persistence;

import java.io.FileNotFoundException;

/**
 * Interface que define o contrato para operações de persistência de arquivos.
 * Fornece métodos para criar, ler, atualizar e deletar dados em arquivos.
 * 
 * @author Anderson Carlos
 * @version 1.0
 * @since 2026
 */
public interface FilePersistence {

    /**
     * Escreve uma nova linha de dados no arquivo.
     * 
     * @param data Dados a serem escritos no arquivo
     * @return Os dados que foram escritos
     */
    String write(final String data);

    /**
     * Remove todas as linhas que contenham a sentença especificada.
     * 
     * @param sentence Texto a ser procurado para remoção
     * @return true se alguma linha foi removida, false caso contrário
     */
    boolean remove(final String sentence);

    /**
     * Substitui todas as ocorrências do conteúdo antigo pelo novo conteúdo.
     * 
     * @param oldContent Conteúdo a ser substituído
     * @param newContent Novo conteúdo
     * @return O conteúdo completo do arquivo após as substituições
     */
    String replace(final String oldContent, final String newContent);

    /**
     * Retorna todo o conteúdo do arquivo.
     * 
     * @return String contendo todo o conteúdo do arquivo
     * @throws FileNotFoundException Se o arquivo não existir
     */
    String findAll() throws FileNotFoundException;

    /**
     * Busca e retorna a primeira linha que contenha a sentença especificada.
     * 
     * @param sentence Texto a ser procurado
     * @return A primeira linha encontrada ou string vazia se não encontrada
     */
    String findBy(final String sentence);
}
