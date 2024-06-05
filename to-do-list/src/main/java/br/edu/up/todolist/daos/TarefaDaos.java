package br.edu.up.todolist.daos;

import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.models.Tarefa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TarefaDaos extends BaseDaos{

    private static final Logger logger = LogManager.getLogger(TarefaDaos.class);

    public static List<Tarefa> listarTarefas(String fileName){
        logger.info("Iniciando leitura dos dados de tarefas");
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            //Declaração de variáveis locais do metodo
            String linha = null;
            List<Tarefa> tarefas = new ArrayList<>();
            //Laço de repetição para leitura do arquivo de tarefas
            while ((linha = reader.readLine()) != null){
                var tarefa = parse(linha);
                tarefas.add(tarefa);
            }
            return tarefas;
        }catch (IOException e){
            logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de tarefas");
            return null;
        }
    }

    private static Tarefa parse(String linha){
        var dados = linha.split(";");
        //Gerando UUID from string
        var uuid = UUID.fromString(dados[0].toString());
        var usuarioUUID = UUID.fromString(dados[4].toString());
        var usuario = UsuarioController.buscarUsuarioPorUUID(usuarioUUID);

        var tarefa = new Tarefa(dados[1], dados[2], dados[3], usuario);
        tarefa.setUuid(uuid);

        return tarefa;
    }


}
