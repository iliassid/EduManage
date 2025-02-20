<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Etudiant" %>
<html>
<head>
    <title>Student List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%= request.getContextPath() %>/etudiant/new" class="nav-link">Add Student</a></li>
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
                <a href="<%= request.getContextPath() %>/etudiant/edit?id=<%= etudiant.getId() %>">Edit</a>
                <a href="<%= request.getContextPath() %>/etudiant/delete?id=<%= etudiant.getId() %>">Delete</a>
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
