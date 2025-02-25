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
        .mydiv{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 80vh;
            font-size: larger;

        }
        .myform{
            display: flex;
            flex-direction: column;
            gap: 20px;
            align-items: center;
            justify-content: space-between;
            width: 700px;
        }
        div{
            display: flex;
            justify-content: space-evenly;
            gap: 10px;
            align-items: center;

        }
        input{
            height: 20px;
            width: 500px;
        }
        .mybtn{
            height: 50px;
            width: 200px;
            background-color: rgb(244, 147, 21);
            border: none;
            border-radius: 15px;
            transition: 0.3s;
        }
        .mybtn:hover{
            height: 55px;
            width: 210px;
            background-color: rgb(255, 191, 106);
            border: none;
            border-radius: 15px;
        }
        .myinput{
            width: 300px;
        }

    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="" class="nav-link">s'inscrire</a></li>
            <li><a href="" class="nav-link">se connecter</a></li>
        </ul>
    </nav>
</header>

<div class="mydiv">
    <h1>S'inscrire</h1>
    <form class="myform" action="">
        <div>
            <label for="name">votre nom:</label>
            <input class="myinput" type="text" id="name">
        </div>
        <div>
            <label for="password">mot de passe</label>
            <input class="myinput" type="password" id="password">
        </div>
        <div>
            <label for="">select votre role</label>
            <select name="" id="">
                <option value="select" disabled selected>select</option>
                <option value="1">admin</option>
                <option value="2">etudiant</option>
            </select>
        </div>


        <button class="mybtn">envoyer</button>
    </form>
</div>

</body>
</html>