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
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import database.musicaDAO;
import database.GaleriaDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.inject.Inject;
import model.Foto;
import org.apache.commons.io.IOUtils;
import sessao.UsuarioSessao;

@Controller
public class FotoController {
    
    @Inject
    private Result result;
    @Inject
    private UsuarioSessao usuarioSessao;
    
    @Get
    @Path("/foto/tela_adicionar")
    public void tela_adicionar() throws SQLException, ClassNotFoundException{
          result.include("galerias", new GaleriaDAO().listar());

    }
    
    @Post
    @Path("/foto/adicionar")
    public void adicionar(UploadedFile arquivo, Foto foto ) throws IOException, SQLException, ClassNotFoundException{
        if (arquivo != null){            
            if (arquivo.getContentType().trim().equals("image/png") || arquivo.getContentType().trim().equals("image/jpeg")){
                
           InputStream is = arquivo.getFile();
            
            byte[] bytes = IOUtils.toByteArray(is);
            
                foto.setImagem(bytes);
                new musicaDAO().adicionar(foto);
                this.result.redirectTo(GaleriaController.class).listar();
            } else {
                this.result.redirectTo(GaleriaController.class).tela_adicionar();
            }            
        } else {
            this.result.redirectTo(GaleriaController.class).listar();
        }
        
    }
    
    @Get
    @Path("/foto/visualizar/{cod}")
    public byte[] visualizar(int cod) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
        byte[] bytes = new musicaDAO().get(cod).getImagem();
        return bytes;
    }
   
    @Get
    @Path("/foto/excluir/{cod}")
    public void excluir(int cod) throws SQLException, ClassNotFoundException{
        new musicaDAO().excluir(cod);
        this.result.redirectTo(GaleriaController.class).listar();


    }
}
