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
    private EduManageDAO eduManageDAO;

    public void init() {
        etudiantDao = new EtudiantDao();
        eduManageDAO = new EduManageDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        System.out.println(action);
        System.out.println("it is working");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insertEtudiant":
                    insertEtudiant(request, response);
                    break;
                case "delete":
                    deleteEtudiant(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateEtudiant(request, response);
                    break;
                case "list":
                    listEtudiants(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/etudiant?action=list");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cour> coursList = eduManageDAO.selectAllCours(); // Fetch all courses
        request.setAttribute("coursList", coursList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("createEtudiant.jsp");
        dispatcher.forward(request, response);
    }

    private void listEtudiants(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Etudiant> etudiants = etudiantDao.getAllEtudiants();
        request.setAttribute("etudiants", etudiants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListEtudiant.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Retrieve student details
        String nom = request.getParameter("nomEtudiant");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String naissance = request.getParameter("dateNaissance");

        // Retrieve selected course IDs
        String[] selectedCourses = request.getParameterValues("idCour[]");
        int[] courseIds = null;
        if (selectedCourses != null) {
            courseIds = new int[selectedCourses.length];
            for (int i = 0; i < selectedCourses.length; i++) {
                courseIds[i] = Integer.parseInt(selectedCourses[i]);
            }
        }

        // Create the student object
        Etudiant newEtudiant = new Etudiant(nom, prenom, email, naissance, new ArrayList<>());

        // Insert the student and associate them with selected courses
        etudiantDao.insertEtudiant(newEtudiant, courseIds);

        // Redirect to the student list
        response.sendRedirect(request.getContextPath() + "/etudiant?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = etudiantDao.selectEtudiant(id);
        List<Cour> coursList = eduManageDAO.selectAllCours();

        request.setAttribute("etudiant", etudiant);
        request.setAttribute("coursList", coursList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editEtudiant.jsp");
        dispatcher.forward(request, response);
    }

    private void updateEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Retrieve student details
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nomEtudiant");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String naissance = request.getParameter("dateNaissance");

        // Retrieve selected course IDs
        String[] selectedCourses = request.getParameterValues("idCour[]");
        int[] courseIds = null;
        if (selectedCourses != null) {
            courseIds = new int[selectedCourses.length];
            for (int i = 0; i < selectedCourses.length; i++) {
                courseIds[i] = Integer.parseInt(selectedCourses[i]);
            }
        }

        // Create the student object
        Etudiant etudiant = new Etudiant(nom, prenom, email, naissance, new ArrayList<>());
        etudiant.setId(id);

        // Update the student and their course associations
        etudiantDao.updateEtudiant(etudiant, courseIds);

        // Redirect to the student list
        response.sendRedirect(request.getContextPath() + "/etudiant?action=list");
    }

    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantDao.deleteEtudiant(id);
        response.sendRedirect(request.getContextPath() + "/etudiant?action=list");
    }
}