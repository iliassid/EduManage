<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Application de gestion de cours</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Cours</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${Cours != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${Cours == null}">
        <form action="insert" method="post">
          </c:if>

          <h2>
            <c:if test="${Cours != null}">
              Modifier un cours
            </c:if>
            <c:if test="${cour == null}">
              Ajouter un nouveau cours
            </c:if>
          </h2>

            <c:if test="${not empty Cours}">
              <input type="hidden" name="idCour" value="<c:out value='${Cours.idCour}' />" />
            </c:if>


            <fieldset class="form-group">
            <label>Nom du cours</label>
            <input type="text" class="form-control" name="nomCour" value="<c:out value='${Cours.nomCour}' />" required>
          </fieldset>

          <fieldset class="form-group">
            <label>Description</label>
            <input type="text" class="form-control" name="descriprion" value="<c:out value='${Cours.descriprion}' />" required>
          </fieldset>

          <button type="submit" class="btn btn-success">Enregistrer</button>
        </form>
    </div>
  </div>
</div>
</body>
</html>
