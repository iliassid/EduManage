<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Etudiant</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .form-control {
            border-radius: 4px;
        }
        .form-control:focus {
            box-shadow: 0 0 0 0.2rem rgba(13,110,253,0.15);
        }
        .btn-primary {
            background-color: #0d6efd;
        }
        .page-title {
            color: #212529;
        }
        .date-input {
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .date-separator {
            font-weight: bold;
            color: #212529;
        }
        .date-part {
            width: 60px;
            text-align: center;
        }
        .year-part {
            width: 80px;
        }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body p-4">
                    <h2 class="text-center mb-4 page-title">Ajouter un Etudiant</h2>

                    <form action="CreateStudentServlet" method="POST">
                        <div class="mb-3">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>

                        <div class="mb-3">
                            <label for="prenom" class="form-label">Prenom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Date de Naissance</label>
                            <div class="date-input">
                                <input type="text" class="form-control date-part" name="jour" placeholder="JJ" maxlength="2" required>
                                <span class="date-separator">/</span>
                                <input type="text" class="form-control date-part" name="mois" placeholder="MM" maxlength="2" required>
                                <span class="date-separator">/</span>
                                <input type="text" class="form-control date-part year-part" name="annee" placeholder="AAAA" maxlength="4" required>
                            </div>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Enregistrer</button>
                            <a href="index.jsp" class="btn btn-light">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>