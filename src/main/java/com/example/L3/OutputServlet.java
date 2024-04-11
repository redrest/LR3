package com.example.L3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.*;
import com.google.gson.*;
import POJO.*;
import Model.*;
import com.opencsv.exceptions.CsvException;

@WebServlet("/OutputServlet")
public class OutputServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/JSP/output.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String filePath = "C:\\Users\\esapr\\IdeaProjects\\OOP\\LR3\\src\\main\\webapp\\csv\\data.csv";

        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(rows);
        System.out.println(json);

        PrintWriter out = response.getWriter();
        out.println(json);
    }
}