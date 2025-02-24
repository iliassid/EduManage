<%@ page import="model.Etudiant" %>
<%@ page import="model.Cour" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Modifier Étudiant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%= request.getContextPath() %>?action=listEtudiants" class="nav-link"> Etudiants</a></li>
            <li><a href="<%= request.getContextPath() %>/" class="nav-link">Home</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/etudiant?action=update&idEtudiant=<%= request.getParameter("idEtudiant") %>" method="post">
                <fieldset class="form-group">
                    <label>Nom</label>
                    <input type="text" class="form-control" name="nomEtudiant" value="<%= request.getAttribute("etudiant") != null ? ((Etudiant) request.getAttribute("etudiant")).getNom() : "" %>" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Prenom</label>
                    <input type="text" class="form-control" name="prenom" value="<%= request.getAttribute("etudiant") != null ? ((Etudiant) request.getAttribute("etudiant")).getPrenom() : "" %>" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" value="<%= request.getAttribute("etudiant") != null ? ((Etudiant) request.getAttribute("etudiant")).getEmail() : "" %>" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Date de naissance</label>
                    <input type="date" class="form-control" name="dateNaissance" value="<%= request.getAttribute("etudiant") != null ? ((Etudiant) request.getAttribute("etudiant")).getNaissance() : "" %>" required>
                </fieldset>
                <fieldset class="form-group">
                    <label>Choisir un ou plusieurs cours</label>
                    <select class="form-select selectField" name="idCour[]" multiple>
                        <%
                            List<Cour> coursList = (List<Cour>) request.getAttribute("coursList");
                            if (coursList != null) {
                                Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
                                List<Cour> studentCours = etudiant.getCours();
                                for (Cour cour : coursList) {
                                    boolean selected = false;
                                    for (Cour studentCour : studentCours) {
                                        if (cour.getId() == studentCour.getId()) {
                                            selected = true;
                                            break;
                                        }
                                    }
                        %>
                        <option value="<%= cour.getId() %>" <%= selected ? "selected" : "" %>><%= cour.getNomCour() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </fieldset>
                <button type="submit" class="btn btn-success">Mettre à jour</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

