package com.example.L3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import com.google.gson.*;
import POJO.*;
import Model.*;


@WebServlet("/AddingServlet")
public class AddingServlet extends HttpServlet {
    Model dataModel = new Model();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/take.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = "C:\\Users\\esapr\\IdeaProjects\\OOP\\LR3\\src\\main\\webapp\\csv\\data.csv";
        String data = request.getParameter("data");
        System.out.println(data);
        Gson gson = new Gson();
        User gfg = gson.fromJson(data, User.class);
        System.out.println(gfg.name);
        String[] row = {gfg.name, gfg.lastname, gfg.middlename, gfg.age, gfg.language};
        dataModel.addData(row);
        dataModel.writeToCSV(filePath);
    }
}
