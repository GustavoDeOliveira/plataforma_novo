<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
       <nav class="navbar navbar-inverse">
    <div class="container-fluid">
         <div class="navbar-header">
             <a class="navbar-brand" href="#">Prova</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/your-artifactid/galeria/tela_adicionar">NovaGaleria</a></li>
            <li><a href="/your-artifactid/galeria/listar">Listar</a></li>
            <li><a href="/your-artifactid/foto/tela_adicionar">NovaFoto</a></li>
            
        </ul>
                        
        <ul class="nav navbar-nav" style="float:right">
            <li style="float:right"><a href="/your-artifactid/index/destruir">sair</a></li>
        </ul>
    
</div>
    
    
       
</nav>

        
        
    <br><br><br>
        
        
            <div class="container">
                <div class="form-group">
                    
                <form action="${pageContext.request.contextPath}/index/login" method="post">
                    login: <input input class="form-control" type="text" name="login">
                    <br>
                    senha: <input input class="form-control" type="password" name="senha">
                    <br><br>
                    <input class="btn btn-primary"  type="submit">

                </form>
                
                
            </div>
            </div>         
</body>
</html>