package com.codegym.controller;

import javafx.print.Printer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

@WebServlet(urlPatterns = "/images/*")
public class ResourceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        String[] paths = req.getRequestURI().split("/");
        System.out.println(Arrays.toString(paths));
        PrintWriter printWriter = resp.getWriter();

        FileInputStream fis = new FileInputStream(UploadServlet.urlImages + paths[2]);
        int result;
        while ((result = fis.read()) != -1)
            printWriter.write(result);
    }
}
