/*
JOGO DA VELHA EM C usando matrizes
Anderson Carlos da Silva Morais
*/
#include <stdio.h>

#define SIZE 3

char board[SIZE][SIZE];

void initializeBoard() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            board[i][j] = ' ';
        }
    }
}

void printBoard() {
    printf("\n");
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf(" %c ", board[i][j]);
            if (j < SIZE - 1) printf("|");
        }
        printf("\n");
        if (i < SIZE - 1) printf("---+---+---\n");
    }
    printf("\n");
}

int checkWin() {
    for (int i = 0; i < SIZE; i++) {
        if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return 1;
        if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return 1;
    }
    if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return 1;
    if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return 1;
    return 0;
}

int isDraw() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i][j] == ' ') return 0;
        }
    }
    return 1;
}

void playGame() {
    int row, col, player = 0;
    char symbols[2] = {'X', 'O'};
    
    while (1) {
        printBoard();
        printf("Jogador %d (%c), insira linha e coluna (0-2): ", player + 1, symbols[player]);
        scanf("%d %d", &row, &col);
        
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ') {
            board[row][col] = symbols[player];
            if (checkWin()) {
                printBoard();
                printf("Jogador %d (%c) venceu!\n", player + 1, symbols[player]);
                break;
            }
            if (isDraw()) {
                printBoard();
                printf("Empate!\n");
                break;
            }
            player = 1 - player;
        } else {
            printf("Posição inválida! Tente novamente.\n");
        }
    }
}

int main() {
    initializeBoard();
    playGame();
    return 0;
}
