package com.example.etudmanage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    }
    public EtudiantServlet(){

        etudiantDao = new EtudiantDao();
        eduManageDAO=new EduManageDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        System.out.println(action);
        System.out.println("it is working");
        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insertEtudiant":
                try {
                    insertEtudiant(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":

                break;
            case "edit":

                break;
            case "update":

                break;

            case "list":
                try {
                    listEtudiants(request,response);
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
        List<Cour> coursList = eduManageDAO.selectAllCours(); // Récupération des cours
        request.setAttribute("coursList", coursList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("createEtudiant.jsp");

        dispatcher.forward(request, response);
    }


//    private void listEtudiant(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//
//        System.out.println("it is working");
//      List<Etudiant> listEtudiants = etudiantDao.selectAllEtudiants();
//        request.setAttribute("listEtudiants", listEtudiants);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("ListEtudiant.jsp");
//        dispatcher.forward(request, response);
//    }
private void listEtudiants(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    List<Etudiant> etudiants = etudiantDao.getAllEtudiants();
    request.setAttribute("etudiants", etudiants); // Vérification du nom correct
    RequestDispatcher dispatcher = request.getRequestDispatcher("ListEtudiant.jsp");
    dispatcher.forward(request, response);
}

    private void insertEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nomEtudiant");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String naissance = request.getParameter("dateNaissance");

        String[] selectedCourses = request.getParameterValues("idCour");
        List<Cour> coursList = new ArrayList<>();
        if (selectedCourses != null) {
            for (String id : selectedCourses) {
                coursList.add(new Cour(Integer.parseInt(id), ""));
            }
        }

        Etudiant newEtudiant = new Etudiant(nom, prenom, email, naissance, coursList);
        etudiantDao.insertEtudiant(newEtudiant);
        response.sendRedirect(request.getContextPath() + "/etudiant?action=list");
    }






}