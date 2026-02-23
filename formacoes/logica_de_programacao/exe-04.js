class hero{
    constructor(nome, idade, tipo){
        this.nome = nome;
        this.idade = idade;
        this.tipo = tipo;
    }

    ataque_acc(){
        switch (this.tipo){
            case "mago":
                this.ataque = "magia";
            break;

            case "guerreiro":
                this.ataque = "espada";
            break;

            case "monge":
                this.ataque = "artes marciais";
            break;

            case "ninja":
                this.ataque = "shuriken";
            break;

            default:
                this.ataque = "Pedra";
        }

        return this.ataque;
    }
}

let heroi1 = new hero("Anakron", 23, "guerreiro");

console.log(`O ${heroi1.tipo} atacou usando ${heroi1.ataque_acc()}`);