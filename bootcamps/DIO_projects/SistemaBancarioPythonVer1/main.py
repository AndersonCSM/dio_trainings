clear = "\n" * 2
saldo = 1000
qtd_saque = 3
valor_saque_acumulado = 0
extrato = ""
opc = 99
while opc != 0:
    opc = int(
        input(
            """
        Bem vindo ao sistema bancário ver:1.0!
        =====================================
        Operações Disponíveis:
        ----------------------
        1 - Saque
        2 - Depósito
        3 - Extrato
        0 - Sair
        ---------------------
        """
        )
    )

    # Saque
    if opc == 1:
        valor_saque = float(
            input(
                """
        ========================
        Opção saque 
        Qual valor deseja sacar?
        ========================
        """
            ).strip()
        )
        if qtd_saque == 0:
            print(
                """
        =======================================================
        Quantidade de saques diárias atingido, tente outro dia!
        =======================================================
        """
            )
            print(clear)

        if 500 < valor_saque or valor_saque <= 0:
            print(
                """
        ========================================================
        Valor inválido para sacar 
        Limite por saque de R$500,00
        ========================================================
            """
            )
            print(clear)

        elif valor_saque_acumulado == 1500:
            print(
                """
        =======================================================
        Valor máximo de saques diários atingido, tente outro dia!
        =======================================================
        """
            )
            print(clear)

        else:
            qtd_saque -= 1  # diminui a quantidade de saques disponíveis
            saldo -= valor_saque  # subtrai o saldo do valor do saque
            valor_saque_acumulado += valor_saque
            extrato += f"""
            =========================
            Operação de saque
            {'Saldo anterior:  R$ ':<15}{saldo + valor_saque:>10.2f}
            {'Valor do saque: -R$ ':<15}{valor_saque:>10.2f}
            -------------------------
            {'Saldo atual: R$ ':<15}{saldo:>10.2f}
            =========================
            """
            print(
                f"""
        ===================================
        Saque realizado com sucesso!
        valor sacado de R$ {valor_saque:.2f}
        ===================================
            """
            )  # informa o usuário da operação
            print(clear)

    # Depósito
    elif opc == 2:
        valor_deposito = float(
            input(
                """
        ========================
        Opção Depósito 
        Quanto valor deseja depositar?
        ========================
        """
            ).strip()
        )

        if valor_deposito <= 0:
            print(
                """
        ========================================================
        Valor inválido para depositar! 
        ========================================================
            """
            )
            print(clear)

        else:
            saldo += valor_deposito  # adicionar ao saldo o valor do depósito
            extrato += f"""
            =========================
            Operação de Depósito
            {'Saldo anterior:     R$ ':<15}{saldo - valor_deposito:>10.2f}
            {'Valor do depósito: +R$ ':<15}{valor_deposito:>10.2f}
            -------------------------
            {'Saldo atual: R$ ':<15}{saldo:>10.2f}
            =========================
            """
            print(
                f"""
        ===================================
        Déposito realizado com sucesso!
        valor do depósito de R$ {valor_deposito:.2f}
        ===================================
            """
            )  # informa o usuário da operação
            print(clear)

    # Extrato
    elif opc == 3:
        print(
            f"""
    ===============================
    extrato
    saldo atual: R$ {saldo:>10.2f}
    ===============================
    """
        )
        print(extrato)

print(
    """
====================
Operação finalizada!
====================
"""
)
