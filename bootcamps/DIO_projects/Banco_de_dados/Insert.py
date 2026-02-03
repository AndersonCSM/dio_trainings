import sqlite3

conexao = sqlite3.connect("meu_banco.db")
cursor = conexao.cursor()

# Inserir Registros
# Forma insegura de fazer - permite manipulação da do bd
# nome = 'Anderson'
# email = 'anderson@exemple.com'
# cursor.execute(f'INSERT INTO clientes (nome, email) VALUES ("{nome}", "{email}")')

# Forma segura de fazer
nome = 'Anderson'
email = 'anderson@exemple.com'
data = (nome, email)
cursor.execute('INSERT INTO clientes (nome, email) VALUES (?, ?);', data)

conexao.commit() # enviar
