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
            <li><a href="<%= request.getContextPath() %>/listEtudiant" class="nav-link"> Student</a></li>
            <li><a href="<%=request.getContextPath()%>/" class="nav-link" >Home</a></li>

        </ul>
    </nav>
</header>
<br>
<div class="container">
    <h2>Student List</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>First Name</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Etudiant> listEtudiants = (List<Etudiant>) request.getAttribute("listEtudiant");
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
                <a href="<%= request.getContextPath() %>/etudiant/edit?idEtudiant=<%= etudiant.getId() %>">Edit</a>
                <a href="<%= request.getContextPath() %>/etudiant/delete?idEtudiant=<%= etudiant.getId() %>">Delete</a>
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
