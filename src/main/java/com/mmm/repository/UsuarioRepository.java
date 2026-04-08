package com.mmm.repository;
import java.util.Optional;

import com.mmm.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
//senha
Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
// Jpa repository salva, lista, busca id deleta , não precisa programar SQL
