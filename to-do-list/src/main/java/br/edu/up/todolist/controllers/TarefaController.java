package br.edu.up.todolist.controllers;

import br.edu.up.todolist.daos.TarefaDaos;
import br.edu.up.todolist.models.Tarefa;

public abstract class TarefaController {
    public static void cadastrar(Tarefa tarefa){
        TarefaDaos.salvar(tarefa);
    }
}
