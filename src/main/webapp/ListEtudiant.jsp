<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Etudiant" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Student List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%= request.getContextPath() %>?action=listEtudiants" class="nav-link"> Étudiant</a></li>
            <li><a href="<%=request.getContextPath()%>/" class="nav-link" >Home</a></li>

        </ul>
    </nav>
</header>
<br>
<div class="container">
    <a href="<%= request.getContextPath() %>/etudiant?action=new" class="btn btn-success">Ajouter un nouvel étudiant</a>

    <h2>Liste des étudiants</h2>
    <div class="container text-left">


    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Etudiant> listEtudiants = (List<Etudiant>) request.getAttribute("listEtudiants");
            if (listEtudiants != null) {
                for (Etudiant etudiant : listEtudiants) {
        %>
        <tr>
            <td><%= etudiant.getId() %></td>
            <td><%= etudiant.getNom() %></td>
            <td><%= etudiant.getPrenom() %></td>
            <td><%= etudiant.getEmail() %></td>
            <td><%= etudiant.getNaissance() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/etudiant/edit?idEtudiant=<%= etudiant.getId() %>">Modifier</a>
                <a href="<%= request.getContextPath() %>/etudiant/delete?idEtudiant=<%= etudiant.getId() %>">Supprimer</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
