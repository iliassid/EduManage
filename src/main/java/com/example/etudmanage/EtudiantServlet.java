package com.example.etudmanage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.EduManageDAO;
import DAO.EtudiantDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Etudiant;
import model.Cour;

@WebServlet("/etudiant")
public class EtudiantServlet extends HttpServlet {
    private EtudiantDao etudiantDao;

    public void init() {
        etudiantDao = new EtudiantDao();
    }
    public EtudiantServlet(){
        etudiantDao = new EtudiantDao();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insertEtudiant":
                try {
                    insertEtudiant(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/delete":

                break;
            case "/edit":

                break;
            case "/update":

                break;

            case "/list":
                try {
                    listEtudiant(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:

                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/creationEtudiant.jsp");
        dispatcher.forward(request, response);
    }

    private void listEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Etudiant> listEtudiants = etudiantDao.selectAllEtudiants();
        request.setAttribute("listEtudiants", listEtudiants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listEtudiants.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String naissance = request.getParameter("dateNaissance");

        List<Cour> cours = new ArrayList<>();
        Etudiant newEtudiant = new Etudiant(0, cours, naissance, email, prenom, nom);
        etudiantDao.insertEtudiant(newEtudiant);

        response.sendRedirect(request.getContextPath() + "/listEtudiant");
    }

    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantDao.deleteEtudiant(id);
        response.sendRedirect(request.getContextPath() + "/listEtudiant");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant existingEtudiant = etudiantDao.selectEtudiant(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editEtudiant.jsp");
        request.setAttribute("etudiant", existingEtudiant);
        dispatcher.forward(request, response);
    }

    private void updateEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String naissance = request.getParameter("dateNaissance");

        List<Cour> cours = new ArrayList<>();
        Etudiant etudiant = new Etudiant(id, cours, naissance, email, prenom, nom);
        etudiantDao.updateEtudiant(etudiant);

        response.sendRedirect(request.getContextPath() + "/etudiant/list");
    }
}