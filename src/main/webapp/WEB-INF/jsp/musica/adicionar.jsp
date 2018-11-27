<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Plataforma de Músicas - adicionar música</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body>

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Plataforma de Músicas</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="/your-artifactid/musica/listar">Explorar Plataforma</a></li>
                    <li><a href="/your-artifactid/musica/adicionar">Adicionar Música</a></li>
                </ul>

                <ul class="nav navbar-nav" style="float:right">
                    <li style="float:right"><a href="/your-artifactid/index/destruir">sair</a></li>
                </ul>
            </div>
        </nav>    

        <div class="container">
            <div class="jumbotron">
                <h2>
                    Enviar Música
                </h2>
                <p>Compartilha o teu som com a rapaziada daqui.</p>
            </div>
        </div>

        <div class="container">
            <div class="form-group">
                <form action="./adicionar" method="POST" enctype="multipart/form-data">
                    Nome: 
                    <input class="form-control" type="text" name="musica.nome"> <br>
                    Descricao: 
                    <textarea class="form-control" name="musica.descricao" cols="10" rows="10">
                    </textarea> <br>
                    Arquivo:
                    <input class="form-control" type="file" accept="audio/*" disabled="disabled" />
                    Adicionar Etiquetas (lista separada por vírgula, as primeiras eiquetas terão um peso maior):
                    <input class="form-control" type="text" name="etiquetasCSV" />
                    <br><br>
                    <input type="submit" class="btn btn-primary" value="Enviar">
                </form>
            </div>
        </div>
    </body>
</html>           

