package br.edu.up.todolist.controllers;

import br.edu.up.todolist.models.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class UsuarioController {
    private static List<Usuario> usuarios = List.of(
        new Usuario(UUID.fromString("0465225a-cbc8-4a83-9040-1a8ed8ae549b"), "João"),
        new Usuario(UUID.fromString("45ed26b1-dae4-4d00-9c35-ed1e8b6e948c"), "Ana")
    );

    public static List<Usuario> listar(){
        return usuarios;
    }
    public static Usuario buscarUsuarioPorUUID(UUID uuid){

       Optional<Usuario> usuario = usuarios.stream()
                                            .filter(u -> u.getUuid().equals(uuid))
                                            .findFirst();

       return usuario.isPresent() ? usuario.get() : null;
    }
    public static Usuario buscarUsuarioPorUUID(String nome){

       Optional<Usuario> usuario = usuarios.stream()
                                           .filter(u -> u.getUuid().equals(nome))
                                           .findFirst();

       return usuario.isPresent() ? usuario.get() : null;
    }

}
