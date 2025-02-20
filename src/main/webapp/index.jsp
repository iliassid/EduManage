<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Management Dashboard</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/" class="nav-link">Home</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container text-center">
  <div class="row justify-content-center">
    <div class="col-md-5">
      <div class="card" style="cursor: pointer;" onclick="window.location.href='<%= request.getContextPath() %>/list'">
        <div class="card-body">
          <h5 class="card-title">Manage Courses</h5>
          <p class="card-text">Click here to view and manage courses.</p>
        </div>
      </div>
    </div>
    <div class="col-md-5">
      <div class="card" style="cursor: pointer;" onclick="window.location.href='<%= request.getContextPath() %>/ListEtudiant'">
        <div class="card-body">
          <h5 class="card-title">Manage Students</h5>
          <p class="card-text">Click here to view and manage students.</p>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
