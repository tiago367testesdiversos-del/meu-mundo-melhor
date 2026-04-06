package com.mmm;

import com.mmm.model.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void deveCriarUsuario(){

    Usuario usuario = new Usuario(
            "Tiago" ,
            "tiago123@email.com" ,
            "123",
            "Diamantina") ;



        assertEquals("Tiago",usuario.getNome());

        assertEquals("tiago123@email.com",usuario.getEmail());

        assertEquals("123",usuario.getSenha());

        assertEquals("Diamantina",usuario.getCidade());
    }


}

