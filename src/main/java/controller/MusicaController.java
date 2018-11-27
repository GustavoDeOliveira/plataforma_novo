/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import database.EtiquetaDAO;
import database.MusicaDAO;
import database.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Inject;
import model.Musica;
import model.Usuario;
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
        result.include("musicas", new MusicaDAO().listar(id));
        
    }
    
    @Path("/musica/adicionar")
    @Get
    public void adicionar(){
        
    }
    
    
    @Path("/musica/adicionar")
    @Post
    public void adicionar(Musica musica, String etiquetasCSV) throws SQLException, ClassNotFoundException {
        musica.setEtiquetas(new EtiquetaDAO().salvar(etiquetasCSV));
        
        ArrayList<Usuario> ulist = new ArrayList<>();
        Usuario u = new Usuario();
        u.setId(new UsuarioDAO().carregar(usuarioSessao.getUsuario(), usuarioSessao.getSenha()));
        ulist.add(u);
        musica.setAutores(ulist);
        
        new MusicaDAO().adicionar(musica);
        result.redirectTo(MusicaController.class).listar();        
    }
    
  
    
    
    
}
