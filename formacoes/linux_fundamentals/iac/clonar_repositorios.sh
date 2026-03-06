#!/bin/bash

echo "Buscando a lista dos seus repositórios no GitHub..."

# Pega apenas os nomes dos repositórios e guarda na variável REPOSITORIOS
REPOSITORIOS=(
"embedded_systems"
"dio_trainings"
"research_development_sequence"
"private_projects"
"java_development"
"c_development"
"python_development"
"introduction_robotics"
"computer_engineering"
)

echo "Iniciando o download..."
echo "--------------------------"

# O "${REPOSITORIOS[@]}" garante que o for passe por todos os itens da lista
for REPO in "${REPOSITORIOS[@]}"; do
    echo "Baixando: $REPO"
    gh repo clone "$REPO"
done

echo "--------------------------"
echo "Sucesso! Todos os repositórios foram clonados na pasta atual."
