<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Student</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/etudiant/list" class="nav-link">Students</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/etudiant/insert" method="post">
                <fieldset class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" name="nom" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>First Name</label>
                    <input type="text" class="form-control" name="prenom" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Date of Birth</label>
                    <input type="date" class="form-control" name="dateNaissance" required>
                </fieldset>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>