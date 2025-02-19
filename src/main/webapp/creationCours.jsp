
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Management Application</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Studiant</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">

      <form action="<%= request.getContextPath() %>/insert" method="post">
        <input type="hidden" name="id" >

        <fieldset class="form-group">
          <label>Nom Cours</label>
          <input type="text" class="form-control" name="nomCour"  required>
        </fieldset>
        <fieldset class="form-group">
          <label>Description</label>
          <input type="text" class="form-control" name="descriprion"  required>
        </fieldset>


        <button type="submit" class="btn <%= "btn-warning"  %>">
        save
        </button>

      </form>
    </div>
  </div>
</div>
</body>
</html>
