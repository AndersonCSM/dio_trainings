/*
Anderson Carlos da Silva Morais
Projeto para desenvolver uma calculadora básica com as quatro operações: +, -, *, /
Para dois números
*/
#include <stdio.h>
#include <locale.h>

int main()
{
    setlocale(LC_ALL, "Portuguese");
    int opc = 1;

    while (opc != 0)
    {
        float n1 = 0, n2 = 0, res = 0;

        printf("Programa Calculadora 1.0\n");
        printf("Operações:\n");
        printf("1 - adição (+)\n");
        printf("2 - subtração (-)\n");
        printf("3 - multiplicação (*)\n");
        printf("4 - divisão (/)\n");
        printf("0 - sair\n");

        printf("Qual operação será realizada?\n");
        scanf("%d", &opc);

        printf("Informe os dois números a serem realizados a operação\n");
        scanf("%f %f", &n1, &n2);

        switch (opc)
        {
        case 1:
            res = n1 + n2;
            break;

        case 2:
            res = n1 - n2;
            break;

        case 3:
            res = n1 * n2;
            break;

        case 4:
            res = n1 / n2;
            break;

        default:
            printf("Opção inválida! \n");
        }

        if (opc != 0){
            char opc2 = 's';
            printf("O resultado da operação é %.2f\n", res);

            while (1)
            {
                printf("Deseja realizar outra operação? [s/n]\n");
                scanf(" %c", &opc2);
                while (getchar() != '\n')
                    ; // Limpar o buffer de entrada
                if (opc2 == 's')
                {
                    break;
                }
                else if (opc2 == 'n')
                {
                    opc = 0;
                    printf("Finalizando a calculadora!\n");
                    break;
                }
                else
                {
                    printf("Opção inválida\n");
                }
            }
        }
    }
    return (0);
}