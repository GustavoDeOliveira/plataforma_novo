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

        
        
        
        
        
            <div class="container">
                <div class="jumbotron">
                    <h1>
                        Listar Galerias
                    </h1>
                </div>
            </div>

        
             <div class="container">
                <div class="panel-group" id="accordion">
                    <h4><b>Galerias</b></h4>
              <c:forEach items="${galerias}" var="galeria">
              
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                          <table class="table table-striped" style="width:100%"> 
                           <tr>
                            <td style="width:20%"> <a data-toggle="collapse" data-parent="#accordion"  href="#collapse${galeria.cod}"> ${galeria.titulo} </a> </td>
                            <td style="width:70%">Descricao: ${galeria.descricao} </td>
                            <td style="width:10%"> <a  href="./excluir/${galeria.cod}"  class="btn btn-danger" role="button" >Excluir</a> </td>
                           </tr>  
                          </table>
                        </h4>
                    </div>
                    <div id="collapse${galeria.cod}" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <table class="table table-striped"  style="width:100%">
                                <tr><h4><b>fotos</b></h4></tr>
                                
                            <c:forEach items="${fotos}" var="foto">
                              <c:choose>
                                <c:when test="${galeria.cod eq foto.galeria.cod}">
                                <tr>                                    
                                    <td style="width:60%"> <img height="500px" width="500px" src="/your-artifactid/foto/visualizar/${foto.cod}"> </td>
                                    <td style="width:30%">Legenda: ${foto.legenda}</td>
                                    <td style="width:10%"><a href="/your-artifactid/foto/excluir/${foto.cod}"  class="btn btn-danger" role="button">Excluir</a></td>
                                </tr>
                                </c:when>
                              </c:choose> 
                            </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>        
              </c:forEach>
            </div>
        </div>    
        
        
    </body>
    <br><br><br><br><br><br><br><br>
</html>
