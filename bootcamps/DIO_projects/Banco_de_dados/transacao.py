import sqlite3

conexao = sqlite3.connect("meu_banco.db")
cursor = conexao.cursor()

try:
    cursor.execute("INSERT INTO clientes (nome, email) VALUES (?, ?)",
                   ('teste1', 'teste@exemple.com'))
    cursor.execute("INSERT INTO clientes (id, nome, email) VALUES (?, ?, ?)",
                   (1, 'teste1', 'teste@exemple.com'))
    conexao.commit()
except Exception as exc:
    print(f"Ocorreu um erro {exc}")
    conexao.rollback()
