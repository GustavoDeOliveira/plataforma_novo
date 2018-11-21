/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import database.musicaDAO;
import database.GaleriaDAO;
import database.UsuarioDAO;
import java.sql.SQLException;
import javax.inject.Inject;
import model.Galeria;
import sessao.UsuarioSessao;

/**
 *
 * @author Israel Risso
 */
@Controller
class MusicaController {

    @Inject
    private Result result;
    @Inject
    private UsuarioSessao usuarioSessao;
  
    @Path("/musica/listar")
    public void listar() throws SQLException, ClassNotFoundException{
        int id = new UsuarioDAO().carregar(usuarioSessao.getUsuario(), usuarioSessao.getSenha());
        result.include("musicas", new musicaDAO().listar(id));
        
    }
    
    
    
    @Path("/galeria/excluir/{cod}")
    public void excluir(int cod) throws SQLException, ClassNotFoundException{
        new GaleriaDAO().excluir(cod);
        result.redirectTo(MusicaController.class).listar();
    }
    
    @Path("/galeria/tela_adicionar")
    public void tela_adicionar(){
        
    }
    
    
    @Path("/galeria/adicionar")
    @Post
    public void adicionar(Galeria galeria) throws SQLException, ClassNotFoundException{
        new GaleriaDAO().adicionar(galeria);
        result.redirectTo(MusicaController.class).listar();        
    }
    
  
    
    
    
}
