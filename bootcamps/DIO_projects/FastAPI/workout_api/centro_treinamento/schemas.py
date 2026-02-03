from typing import Annotated
from pydantic import Field
from workout_api.contrib.schemas import BaseSchema


class CentroTreinamento(BaseSchema):
    nome: Annotated[str, Field(
        description='Nome do centro de treinamento', example="CT RG", max_length=20)]
    endereco: Annotated[str, Field(
        description='Nome do endereço do ct', example="rua do novo clube", max_length=60)]
    proprietario: Annotated[str, Field(
        description='Nome do proprietário', example="Valber", max_length=30)]
