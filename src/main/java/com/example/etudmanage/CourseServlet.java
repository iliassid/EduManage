package com.example.etudmanage;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import DAO.EduManageDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cour;

@WebServlet("/")
public class CourseServlet extends HttpServlet {
 private EduManageDAO eduManageDAO;

    public void init() {

    }
    public CourseServlet(){
        eduManageDAO=new EduManageDAO();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        this.doGet(request,response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    // permet de récupérer la partie de l'URL spécifique au Servlet
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
                    insertCours(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/delete":
                try {
                    deleteCours(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/edit":
                try {
                    showEditForm(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/update":
                try {
                    updateCours(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/list":
                try {
                    listCour(request,response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("creationCours.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idCour"));
        Cour existingUser = eduManageDAO.selectCours(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("creationCours.jsp");
        request.setAttribute("cour", existingUser);
        dispatcher.forward(request, response);

    }
private void listCour(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Cour> listCour = eduManageDAO.selectAllCours();
    System.out.println("Nombre de cours récupérés : " + listCour.size()); // Debug// Récupération des utilisateurs
    request.setAttribute("listCour", listCour);
    RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
    dispatcher.forward(request, response);
}
    private void insertCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nomCour = request.getParameter("nomCour");
        String descriprion = request.getParameter("descriprion");
        Cour newCour = new Cour(nomCour, descriprion);
        eduManageDAO.insertCour(newCour);
        response.sendRedirect(request.getContextPath() + "/list");

    }
    private void deleteCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.valueOf(request.getParameter("idCour"));
        eduManageDAO.deleteCours(id);
        response.sendRedirect("list");

    }
    private void updateCours(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt((request.getParameter("idCour")));
        String nom = request.getParameter("nomCour");
        String description = request.getParameter("descriprion");

        Cour cour = new Cour(id, nom,description);
        eduManageDAO.updateCours(cour);
        response.sendRedirect("list");
    }










    public void destroy() {
    }
}