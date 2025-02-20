
<%@ page import="java.util.List" %>
<%@ page import="model.Cour" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Studiant Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">


        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link" >Cours</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of cours</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Cours</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom Cours</th>
                <th>Déscription</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%

                List<Cour> courList = (List<Cour>) request.getAttribute("listCour");


                for (Cour cour : courList) {
            %>
            <tr>
                <td><%= cour.getId() %></td>
                <td><%= cour.getNomCour() %></td>
                <td><%= cour.getDescriprion() %></td>

                <td>
                    <a href="edit?idCour=<%= cour.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                    <a href="delete?idCour=<%= cour.getId() %>" class="btn btn-warning btn-sm">Delete</a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>


        </table>
    </div>
</div>
</body>
</html>
