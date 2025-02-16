/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAL.UnitsDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Units;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateUnitController", urlPatterns = {"/admin/updateUnit"})
public class UpdateUnitController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);

        UnitsDAO dbUnit = new UnitsDAO();
        Units unit = dbUnit.getUnitById(id);

        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(unit));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        UnitsDAO dbUnit = new UnitsDAO();
        Units unit = dbUnit.getUnitById(id);
        unit.setName(name);
        unit.setDescription(description);
        dbUnit.updateUnit(unit);
        response.sendRedirect("units");
    }
}
