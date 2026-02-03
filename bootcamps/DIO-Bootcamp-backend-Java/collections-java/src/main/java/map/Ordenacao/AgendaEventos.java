package main.java.map.Ordenacao;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AgendaEventos {
    private Map<LocalDate,Evento> eventoMap;

    public AgendaEventos() {
        this.eventoMap = new HashMap<>();
    }

    public void addEvento(LocalDate data, String nome, String atracao){
        eventoMap.put(data, new Evento(nome, atracao));
    }

    public void exibirAgenda(){
        Map<LocalDate, Evento> eventosTreeMap = new TreeMap<>(eventoMap);
        System.out.println(eventosTreeMap);
    }

    public void obterProximoEvento(){
        LocalDate dataAtual = LocalDate.now();
        LocalDate proximaData = null;
        Evento proximoEvento = null;
        Map<LocalDate, Evento> eventosTreeMap = new TreeMap<>(eventoMap);
        for(Map.Entry<LocalDate, Evento> entry: eventosTreeMap.entrySet()){
            if(entry.getKey().isEqual(dataAtual) || entry.getKey().isAfter(dataAtual)){
                proximaData = entry.getKey();
                proximoEvento = entry.getValue();
                System.out.println("O próximo evento: "+ proximoEvento + "acontecerá na data: "+ proximaData);
                break;
            }
        }
    }

    public static <List> void main(String[] args){
        AgendaEventos agenda = new AgendaEventos();

        agenda.addEvento(LocalDate.of(2024, Month.JULY, 14), "Evento 1", "Atração 1");
        agenda.addEvento(LocalDate.of(2023, Month.AUGUST, 24), "Evento 2", "Atração 2");
        agenda.addEvento(LocalDate.of(2023, Month.FEBRUARY, 3), "Evento 3", "Atração 3");
        agenda.addEvento(LocalDate.of(2025, Month.JANUARY, 25), "Evento 4", "Atração 4");

        agenda.exibirAgenda();

        agenda.addEvento(LocalDate.of(2024, Month.JULY, 17), "Evento 4", "Atração 4");

        agenda.obterProximoEvento();
    }
}
