import sqlite3

conexao = sqlite3.connect("meu_banco.db")
cursor = conexao.cursor()

# Criar tabela
cursor.execute('CREATE TABLE clientes (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(100), email VARCHAR(150))')
