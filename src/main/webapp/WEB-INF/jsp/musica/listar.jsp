<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
             <a class="navbar-brand" href="#">Plataforma</a>
        </div>
        <ul class="nav navbar-nav">
        </ul>
                           
        <ul class="nav navbar-nav" style="float:right">
            <li style="float:right"><a href="/your-artifactid/index/destruir">sair</a></li>
        </ul>
    
        
        
        
</div>
    
    
       
</nav>

        
        
        
        
        
            <div class="container">
                <div class="jumbotron">
                    <h1>
                        Listar Musicas
                    </h1>
                </div>
            </div>

        
             <div class="container">
                <div class="panel-group" id="accordion">
                    <h4><b>Musicas</b></h4>
              <c:forEach items="${musicas}" var="musica">
              
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                          <table class="table table-striped" style="width:100%"> 
                           <tr>
                               <td>${musica.nome}</td>
                               <td>${musica.descricao}</td>
                           </tr>  
                          </table>
                        </h4>
                    </div>
                </div>        
              </c:forEach>
            </div>
        </div>    
        
        
    </body>
    <br><br><br><br><br><br><br><br>
</html>
