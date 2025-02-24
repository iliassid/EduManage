<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Etudiant" %>
<%@ page import="model.Cour" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Liste des Étudiants</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%= request.getContextPath() %>?action=listEtudiants" class="nav-link"> Etudiant</a></li>
            <li><a href="<%=request.getContextPath()%>/" class="nav-link">Home</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container">
    <a href="<%= request.getContextPath() %>/etudiant?action=new" class="btn btn-success">Ajouter un nouvel etudiant</a>

    <h2>Liste des étudiants</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Email</th>
            <th>Date de Naissance</th>
            <th>Cours Inscrits</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
            if (etudiants != null) {
                for (Etudiant etudiant : etudiants) {
        %>
        <tr>
            <td><%= etudiant.getId() %></td>
            <td><%= etudiant.getNom() %></td>
            <td><%= etudiant.getPrenom() %></td>
            <td><%= etudiant.getEmail() %></td>
            <td><%= etudiant.getNaissance() %></td>
            <td>
                <ul>
                    <%
                        List<Cour> cours = etudiant.getCours();
                        if (cours != null && !cours.isEmpty()) {
                            for (Cour cour : cours) {
                    %>
                    <li><%= cour.getNomCour() %></li>
                    <%
                        }
                    } else {
                    %>
                    <li>Aucun cours inscrit</li>
                    <%
                        }
                    %>
                </ul>
            </td>
            <td>
                <a href="<%= request.getContextPath() %>/etudiant?action=edit&idEtudiant=<%= etudiant.getId() %>" class="btn btn-warning btn-sm">Modifier</a>
                <a href="<%= request.getContextPath() %>/etudiant?action=delete&idEtudiant=<%= etudiant.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
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
