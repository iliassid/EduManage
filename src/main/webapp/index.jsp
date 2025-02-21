<html>
<head>
  <title>Management Dashboard</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f5f7fa;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .navbar {
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    .cards-container {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 85vh;
      gap: 50px;
    }

    .card {
      width: 400px;
      height: 280px;
      border-radius: 12px;
      border: none;
      display: flex;
      align-items: center;
      justify-content: center;
      transition: all 0.3s ease;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
      cursor: pointer;
      background: linear-gradient(145deg, #ffffff, #f5f5f5);
      overflow: hidden;
      position: relative;
    }

    .card:after {
      content: "";
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      height: 5px;
      background: linear-gradient(90deg, #ff7e5f, #feb47b);
      transform: scaleX(0);
      transform-origin: left;
      transition: transform 0.3s ease;
    }

    .card:hover {
      transform: translateY(-15px);
      box-shadow: 0 20px 35px rgba(0, 0, 0, 0.15);
    }

    .card:hover:after {
      transform: scaleX(1);
    }

    .card-body {
      text-align: center;
      padding: 30px;
    }

    .card-title {
      font-size: 28px;
      font-weight: 700;
      margin-bottom: 25px;
      color: #2d3748;
      position: relative;
      display: inline-block;
    }

    .card-title:after {
      content: "";
      position: absolute;
      bottom: -10px;
      left: 50%;
      transform: translateX(-50%);
      width: 50px;
      height: 3px;
      background-color: #feb47b;
    }

    .card-text {
      font-size: 18px;
      color: #4a5568;
      line-height: 1.6;
    }

    /* Card icons */
    .card-icon {
      font-size: 50px;
      margin-bottom: 20px;
      color: #ff7e5f;
    }
  </style>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/" class="nav-link">Home</a></li>
    </ul>
  </nav>
</header>

<div class="cards-container">
  <div class="card" onclick="window.location.href='<%= request.getContextPath() %>/list'">
    <div class="card-body">
      <div class="card-icon">
        <i class="fas fa-book"></i>
      </div>
      <h5 class="card-title">Manage Courses</h5>
      <p class="card-text">Access and manage your course catalog, update materials, and track course performance.</p>
    </div>
  </div>

  <div class="card" onclick="window.location.href='<%= request.getContextPath() %>/etudiant?action=list'">
    <div class="card-body">
      <div class="card-icon">
        <i class="fas fa-user-graduate"></i>
      </div>
      <h5 class="card-title">Manage Students</h5>
      <p class="card-text">View student profiles, track progress, and manage enrollment and attendance records.</p>
    </div>
  </div>
</div>

</body>
</html>