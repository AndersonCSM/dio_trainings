package main.java.set.OperacoesBasicas;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoConvidados {
    private Set<Convidado> convidadosSet;

    public ConjuntoConvidados() {
        this.convidadosSet = new HashSet<>();
    }

    public void addConvidado(String nome, int codigo){
        convidadosSet.add(new Convidado(nome, codigo));
    }

    public void removerConvidado(int codigoConvite){
        Convidado convidadoRemovido = null;
        for (Convidado c: convidadosSet){
            if (c.getCodigoConvite() == codigoConvite){
                convidadoRemovido = c;
                break;
            }
        }
        convidadosSet.remove(convidadoRemovido);
    }

    public int contarConvidados(){
        return convidadosSet.size();
    }

    public void exibirConvidados(){
        System.out.println(convidadosSet);
    }

    public static void main(String[] args){
        ConjuntoConvidados conjunto = new ConjuntoConvidados();

        conjunto.addConvidado("N1", 1123);
        conjunto.addConvidado("N1", 1123);
        conjunto.addConvidado("N2", 1133);
        conjunto.addConvidado("N3", 1134);
        conjunto.addConvidado("N4", 1135);

        System.out.println(conjunto.contarConvidados());
        conjunto.exibirConvidados();

        conjunto.removerConvidado(1134);

        conjunto.exibirConvidados();
    }
}
