package com.mmm.repository;

import com.mmm.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{


}
// Jpa repository salva, lista, busca id deleta , não precisa programar SQL
