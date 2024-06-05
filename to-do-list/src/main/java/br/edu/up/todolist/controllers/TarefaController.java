package br.edu.up.todolist.controllers;

import br.edu.up.todolist.daos.TarefaDaos;
import br.edu.up.todolist.models.FormatacaoEscrita;
import br.edu.up.todolist.models.Tarefa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class TarefaController {
    private static final Logger logger = LogManager.getLogger(TarefaController.class);

    private static final String TAREFA_FILE_NAME = "tarefa.txt";

    public static Tarefa buscarTarefaUUID(UUID uuid){
        //Busca as tarefas cadastradas no arquivo.txt
        var listaTarefas = listar();
        //Verificando se a tarefa existe
        Optional<Tarefa> tarefa = listaTarefas.stream().filter(t -> t.getUuid().equals(uuid)).findFirst();

        if(tarefa.isEmpty()){
            //lançar uma exceção
            return  null;
        }

        return ;
    }

    public static void cadastrar(Tarefa tarefa){
        TarefaDaos.escrever(TAREFA_FILE_NAME, List.of(tarefa), true);
    }

    public static List<Tarefa> listar(){
        var tarefas = TarefaDaos.listarTarefas(TAREFA_FILE_NAME);
        return tarefas;
    }

    public static void atualizar(UUID uuid, Tarefa tarefaAtualizado){
        //Verificar se a tarefa existe
        var tarefaUpdate = buscarTarefaUUID(uuid);

        //TODO: Modificar essa validação
        if (tarefaUpdate == null){
            return;
        }
        //Atualizar os dados da tarefa
        tarefaUpdate.atualizarDados(tarefaAtualizado);

        //Removendo a tarefa da lista e criando uma nova lista valida
        var novaListaDeTarefas = removerTarefaDaListaPorUuuid(uuid);
        //Adicionando a tarefa atualizada para a lista valida
        novaListaDeTarefas.add(tarefaUpdate);

        //Escrever arquivo de tarefa
        TarefaDaos.escrever(TAREFA_FILE_NAME, novaListaDeTarefas, false);
    }

    public static void remover(UUID uuid){

        var dados = removerTarefaDaListaPorUuuid(uuid);

        TarefaDaos.escrever(TAREFA_FILE_NAME, dados, false);

    }

    private static List<FormatacaoEscrita> removerTarefaDaListaPorUuuid(UUID uuid){
        List<FormatacaoEscrita> dados = new ArrayList<>();
        var tarefas = listar();

        tarefas.forEach(t -> {
            if (!t.getUuid().equals(uuid)){
                dados.add(t);
            }
        });

        return dados;
    }
}
