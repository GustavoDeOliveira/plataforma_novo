package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import database.UsuarioDAO;
import java.sql.SQLException;
import sessao.UsuarioSessao;

@Controller
public class IndexController {

	private final Result result;
        
        @Inject
        private UsuarioSessao usuarioSessao;
        

	/**
	 * @deprecated CDI eyes only
	 */
	protected IndexController() {
		this(null);
	}
	
	@Inject
	public IndexController(Result result) {
		this.result = result;
	}

        
	@Path("/")
	public void index() {
//		result.include("variable", "Download e Upload");
	}
        
        
        //        
         
        @Get
        @Path("/index/destruir")
        public void destruir(){ 
            this.usuarioSessao.setUsuario("");
            this.usuarioSessao.setSenha("");
            this.result.redirectTo(this).index();
        }
        
        @Post
        @Path("/index/login")
        public void login(String login, String senha) throws SQLException, ClassNotFoundException{
               if (new UsuarioDAO().valida(login, senha)){
                   this.usuarioSessao.setUsuario(login);
                   this.usuarioSessao.setSenha(senha);
                   this.result.redirectTo(GaleriaController.class).listar();                   
               } else {
                   this.result.include("variable", "erro no login");
                   this.result.redirectTo(this).index();
               }
        }
}