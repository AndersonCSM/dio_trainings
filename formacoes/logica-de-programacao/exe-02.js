
function saldo_vitorias(wins, loser){
    if (wins <= 10) {
        nivel = "Ferro";
    } else if (wins >= 11 && wins <= 20) {
        nivel = "Bronze";
    } else if (wins >= 21 && wins <= 50) {
        nivel = "Prata";
    } else if (wins >= 51 && wins <= 70) {
        nivel = "Ouro";
    } else if (wins >= 71 && wins <= 80) {
        nivel = "Platina";
    } else if (wins >= 81 && wins <= 90) {
        nivel = "Ascendente";
    } else if (wins >= 91 && wins <= 100) {
        nivel = "Imortal";
    } else if (wins >= 101) {
        nivel = "Radiante";
    }
    return nivel, wins - loser;
}

let nivel, saldo = saldo_vitorias(150, 20);

console.log(`O Herói tem de saldo de**${saldo}** e está no nível de **${nivel}**`)
