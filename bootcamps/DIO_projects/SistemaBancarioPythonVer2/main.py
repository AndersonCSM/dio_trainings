import textwrap


def func_saque(
    saldo, valor, extrato, limite, numero_saques, limite_saques, saque_acumulado
):
    """
    numero_saques = qtd de saques realizados
    limite_saques = qtd de saques permitido
    limite = quantidade monetária passível de saque
    saque_acumulado = quantidade sacada
    """

    if numero_saques >= limite_saques:
        print(
            """
    =======================================================
    Quantidade de saques diárias atingido, tente outro dia!
    =======================================================
    """
        )
        print(f"\n" * 2)

    elif saque_acumulado >= limite:
        print(
            """
    =======================================================
    Valor máximo de saques diários atingido, tente outro dia!
    =======================================================
    """
        )
        print(f"\n" * 2)

    elif valor > 500 or valor <= 0:
        print(
            """
    ========================================================
    Valor inválido para sacar
    Limite por saque de R$500,00
    ========================================================
        """
        )
        print(f"\n" * 2)

    else:
        numero_saques += 1  # aumenta a quantidade de saques realizados
        saldo -= valor  # subtrai o valor do saque do saldo
        saque_acumulado += valor
        extrato += f"""
        =========================
        Operação de saque
        {'Saldo anterior:  R$ ':<15}{saldo + valor:>10.2f}
        {'Valor do saque: -R$ ':<15}{valor:>10.2f}
        -------------------------
        {'Saldo pos operação: R$ ':<15}{saldo:>10.2f}
        =========================
        """

        print(
            f"""
    ===================================
    Saque realizado com sucesso!
    Valor sacado de R$ {valor:.2f}
    ===================================
        """
        )
        print(f"\n" * 2)

    return {
        "saldo": saldo,
        "numero_saques": numero_saques,
        "limite_saques": limite_saques,
        "saque_acumulado": saque_acumulado,
        "extrato": extrato,
    }


def func_deposito(saldo, valor, extrato):
    if valor <= 0:
        print(
            """
    ========================================================
    Valor inválido para depositar!
    ========================================================
        """
        )
        print(f"\n" * 2)

    else:
        saldo += valor  # adicionar ao saldo o valor do depósito
        extrato += f"""
        =========================
        Operação de Depósito
        {'Saldo anterior:     R$ ':<15}{saldo - valor:>10.2f}
        {'Valor do depósito: +R$ ':<15}{valor:>10.2f}
        -------------------------
        {'Saldo pos operação: R$ ':<15}{saldo:>10.2f}
        =========================
        """
        print(
            f"""
    ===================================
    Depósito realizado com sucesso!
    Valor do depósito de R$ {valor:.2f}
    ===================================
        """
        )
        print(f"\n" * 2)

    return {"saldo": saldo, "extrato": extrato}


def func_extrato(saldo, extrato):
    print(
        f"""
    ===============================
    {"Extrato":^20}
    Saldo atual: R$ {saldo:>10.2f}
    ===============================
    """
    )
    print("Não foram realizadas movimentações." if not extrato else extrato)


def criar_usuario(template, usuarios):
    print(
        """
    ========================================================
    Criação de usuário
    ========================================================
    """
    )

    cpf = int(input("Informe o CPF (somente número): "))
    usuario = filtrar_usuario(cpf, usuarios)

    if usuario:
        print("Usuário já cadastrado")
        return

    nome = str(input("Informe o nome completo: ")).strip().lower()
    nascimento = input("Informe a data de nascimento (dd-mm-aaaa): ")
    endereco = input("Informe o endereço (logradouro, n - bairro - cidade/estado)")

    template["usuario"].update(
        {"nome": nome, "nascimento": nascimento, "cpf": cpf, "endereco": endereco}
    )

    usuarios.append(template)

    print(f"""{'='*30}\n{"Usuário criado com sucesso!":^30}\n{'='*30}""")

    return usuarios


def filtrar_usuario(cpf, usuarios):
    usuario_filtrado = [user for user in usuarios if user["usuario"]["cpf"] == cpf]

    return usuario_filtrado[0] if usuario_filtrado else None


def criar_conta(agencia, numero_conta, usuarios):
    print(
        """
    ========================================================
    Criação de conta
    ========================================================
    """
    )

    cpf = int(input("Informe o CPF do usuário: "))

    usuario = filtrar_usuario(cpf, usuarios)
    if usuario:
        print("\n=== Conta criada com sucesso! ===")
        usuario.update({"agencia": agencia, "numero_conta": numero_conta})

        return usuario

    print("Usuário não encontrado, fluxo de criação de conta encerrado!")


def listar_contas(contas):
    print(
        """
    ========================================================
    Lista de contas do sistema
    ========================================================
    """
    )
    for conta in contas:
        linha = f"""\
            Agência:\t{conta['agencia']}
            C/C:\t\t{conta['numero_conta']}
            Titular:\t{conta['usuario']['nome']}
        """
        print("=" * 100)
        print(textwrap.dedent(linha))


def main():
    conta_template = {
        "id": "",
        "numero_conta": 0,
        "agencia": "0001",
        "data": {
            "saldo": 0,
            "numero_saques": 0,
            "limite": 1500,
            "limite_saques": 3,
            "saque_acumulado": 0,
            "extrato": "",
        },
        "usuario": {"nome": "", "nascimento": "", "endereco": "", "cpf": 0},
    }

    usuarios = []
    contas = []

    opc = 99
    while opc != 0:
        opc = int(
            input(
                """
            Bem vindo ao sistema bancário ver:2.0!
            =====================================
            Operações Disponíveis:
            ----------------------
            1 - Saque
            2 - Depósito
            3 - Extrato
            4 - Nova conta
            5 - Listar contas
            6 - Novo usuário
            0 - Sair
            ---------------------
            """
            )
        )

        # Saque
        if opc == 1:
            valor = float(
                input(
                    """
            ========================
            Opção Saque
            Qual valor deseja sacar?
            ========================
            """
                ).strip()
            )

            # Usando um loop para extrair os valores e desempacotar
            saldo, extrato, limite, numero_saques, limite_saques, saque_acumulado = (
                conta["data"][chave]
                for chave in [
                    "saldo",
                    "extrato",
                    "limite",
                    "numero_saques",
                    "limite_saques",
                    "saque_acumulado",
                ]
            )

            return_op = func_saque(
                saldo=saldo,
                valor=valor,
                extrato=extrato,
                limite=limite,
                numero_saques=numero_saques,
                limite_saques=limite_saques,
                saque_acumulado=saque_acumulado,
            )

            conta["data"].update(return_op)

        # Depósito
        elif opc == 2:
            valor = float(
                input(
                    """
            ========================
            Opção Depósito
            Quanto valor deseja depositar?
            ========================
            """
                ).strip()
            )

            return_op = func_deposito(
                conta["data"]["saldo"], valor, conta["data"]["extrato"]
            )

            conta["data"].update(return_op)

        # Extrato
        elif opc == 3:
            func_extrato(conta["data"]["saldo"], extrato=conta["data"]["extrato"])

        # Criar contas
        elif opc == 4:
            numero_conta = len(contas) + 1
            conta = criar_conta(conta_template["agencia"], numero_conta, usuarios)
            if conta:
                contas.append(conta)

        # Listar Contas
        elif opc == 5:
            listar_contas(contas)

        # Criar usuário
        elif opc == 6:
            usuarios = criar_usuario(conta_template, usuarios)[::]

    print(
        """
    Operação finalizada!
    ====================
    """
    )


main()
