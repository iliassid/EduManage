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
                    insertUser(request,response);
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
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        Long id = Long.parseLong(request.getParameter("id"));
//        Cour existingUser = eduManageDAO.selectUser(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("creationCour.jsp");
//        request.setAttribute("cour", existingUser);
//        dispatcher.forward(request, response);
//
//    }
private void listCour(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    List<Cour> listCour = eduManageDAO.selectAllCours();
    System.out.println("Nombre de cours récupérés : " + listCour.size()); // Debug// Récupération des utilisateurs
    request.setAttribute("listCour", listCour);
    RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
    dispatcher.forward(request, response);
}
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nomCour = request.getParameter("nomCour");
        String descriprion = request.getParameter("descriprion");
        Cour newCour = new Cour(nomCour, descriprion);
        eduManageDAO.insertCour(newCour);
        response.sendRedirect(request.getContextPath() + "/list");

    }











    public void destroy() {
    }
}