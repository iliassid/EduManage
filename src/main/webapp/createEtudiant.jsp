<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer un étudiant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .selectField{
            width: 565px;
            height: 50px;
            border-radius: 5px;
            border-color:darkgrey;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Étudiantes</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/etudiant?action=insertEtudiant" method="post">
                <fieldset class="form-group">
                    <label>Nom</label>
                    <input type="text" class="form-control" name="nomEtudiant" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Prénom</label>
                    <input type="text" class="form-control" name="prenom" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Date de naissance</label>
                    <input type="date" class="form-control" name="dateNaissance" required>
                </fieldset>
                <fieldset class="form-group ">
                    <select class="form-select selectField" aria-label="Default select example">
                        <option selected>cour</option>
                        <option value="1"></option>
                        <option value="2"></option>
                        <option value="3"></option>
                    </select>
                </fieldset>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>