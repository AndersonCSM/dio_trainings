/*
PROJETO DIO PARA ÁRVORE BINÁRIA BALANCEADA
ANDERSON CARLOS DA SILVA MORAIS
*/
#include <iostream>
using namespace std;

struct Node {
    int value;
    Node* left;
    Node* right;
    int height; // Adicionando altura do nó
};

// Função para criar um novo nó
Node* createNode(int value) {
    Node* newNode = new Node;
    newNode->value = value;
    newNode->left = NULL;
    newNode->right = NULL;
    newNode->height = 1; // Novo nó começa com altura 1
    return newNode;
}

// Retorna a altura de um nó
int getHeight(Node* node) {
    return (node == NULL) ? 0 : node->height;
}

// Calcula o fator de balanceamento
int getBalanceFactor(Node* node) {
    return (node == NULL) ? 0 : getHeight(node->left) - getHeight(node->right);
}

// Rotação simples à direita (RR)
Node* rightRotate(Node* y) {
    Node* x = y->left;
    Node* T2 = x->right;

    // Rotação
    x->right = y;
    y->left = T2;

    // Atualiza alturas
    y->height = max(getHeight(y->left), getHeight(y->right)) + 1;
    x->height = max(getHeight(x->left), getHeight(x->right)) + 1;

    return x; // Novo nó raiz
}

// Rotação simples à esquerda (LL)
Node* leftRotate(Node* x) {
    Node* y = x->right;
    Node* T2 = y->left;

    // Rotação
    y->left = x;
    x->right = T2;

    // Atualiza alturas
    x->height = max(getHeight(x->left), getHeight(x->right)) + 1;
    y->height = max(getHeight(y->left), getHeight(y->right)) + 1;

    return y; // Novo nó raiz
}

// Rotação dupla à esquerda (RL)
Node* rightLeftRotate(Node* node) {
    node->right = rightRotate(node->right);
    return leftRotate(node);
}

// Rotação dupla à direita (LR)
Node* leftRightRotate(Node* node) {
    node->left = leftRotate(node->left);
    return rightRotate(node);
}

// Função de inserção na Árvore AVL
Node* insert(Node* root, int value) {
    if (root == NULL) 
        return createNode(value);

    if (value < root->value)
        root->left = insert(root->left, value);
    else if (value > root->value)
        root->right = insert(root->right, value);
    else 
        return root; // Valores duplicados não são permitidos

    // Atualiza a altura do nó atual
    root->height = 1 + max(getHeight(root->left), getHeight(root->right));

    // Obtém o fator de balanceamento
    int balance = getBalanceFactor(root);

    // Aplica rotações se necessário
    if (balance > 1 && value < root->left->value) // RR
        return rightRotate(root);

    if (balance < -1 && value > root->right->value) // LL
        return leftRotate(root);

    if (balance > 1 && value > root->left->value) // LR
        return leftRightRotate(root);

    if (balance < -1 && value < root->right->value) // RL
        return rightLeftRotate(root);

    return root; // Nenhuma rotação necessária
}

// Percurso em ordem
void inOrder(Node* root) {
    if (root != NULL) {
        inOrder(root->left);
        cout << root->value << " ";
        inOrder(root->right);
    }
}

int main() {
    Node* root = NULL;

    // Inserção mantendo balanceamento AVL
    root = insert(root, 50);
    root = insert(root, 30);
    root = insert(root, 20);
    root = insert(root, 40);
    root = insert(root, 70);
    root = insert(root, 60);
    root = insert(root, 80);

    cout << "Inorder traversal of the AVL tree: ";
    inOrder(root);
    return 0;
}
