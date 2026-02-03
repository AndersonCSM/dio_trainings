import sqlite3

conexao = sqlite3.connect("meu_banco.db")
cursor = conexao.cursor()


def criar_tabela(cursor):
    cursor.execute(
        'CREATE TABLE clientes (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(100), email VARCHAR(150))')


def inserir_registro(conexao, cursor, nome, email):
    data = (nome, email)
    cursor.execute('INSERT INTO clientes (nome, email) VALUES (?, ?);', data)

    conexao.commit()  # enviar


def atualizar_registro(conexao, cursor, nome, email, id):
    data = (nome, email, id)
    cursor.execute('UPDATE clientes SET nome=?, email=? WHERE id=?', data)

    conexao.commit()


def excluir_registro(conexao, cursor, id):
    data = (id,)
    cursor.execute('DELETE FROM clientes WHERE id=?', data)

    conexao.commit()


def inserir_muitos(conexao, cursor, dados):
    cursor.executemany(
        "INSERT INTO clientes (nome, email) VALUES (?, ?)", dados)

    conexao.commit()


def recuperar_cliente(cursor, id):
    cursor.execute("SELECT * FROM clientes WHERE id=?", (id,))
    return cursor.fetchone()


def listar_clientes(cursor):
    return cursor.execute("SELECT * FROM clientes ORDER BY nome;")


def recuperar_cliente2(cursor, id):
    cursor.row_factory = sqlite3.Row
    cursor.execute("SELECT * FROM clientes WHERE id=?", (id,))
    return cursor.fetchone()

# inserir_registro(conexao, cursor,"Valeria", "valecia@exemple.com")
# atualizar_registro(conexao, cursor,"Valecia", "valecia@exemple.com", 2)
# excluir_registro(conexao, cursor, 2)
# dados = [("João", "Joao@exemple.com"),
#         ("Lex", "Lex234@exemple.com"),
#         ("Calton", "Calll1@exemple.com"),
#         ("Rick", "Ricker@exemple.com"),]
# inserir_muitos(conexao, cursor, dados)
# print(recuperar_cliente(cursor, 2))


# clientes = listar_clientes(cursor)
# for cliente in clientes:
#    print(cliente)

clt = recuperar_cliente2(cursor, 1)
# print(clt)
# print(dict(clt))
# print(clt['nome'])
