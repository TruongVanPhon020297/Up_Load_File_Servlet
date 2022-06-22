package com.codegym.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;


@MultipartConfig

@WebServlet(name = "UploadServlet", urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
    public final static String urlImages = "C:\\DEV\\images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("upload.jsp");
        req.setAttribute("avatar", null);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("file") == null) {
            req.setAttribute("message", "Vui lòng chọn File");
        } else {
            String avatar = uploadImagesServer(req);
            req.setAttribute("message", "Upload File Success!");
            req.setAttribute("avatar", avatar);
            System.out.println("PATH : " + avatar);
        }
        getServletContext().getRequestDispatcher("/upload.jsp").forward(req, resp);
    }


    public static String uploadImagesServer(HttpServletRequest req) throws ServletException, IOException {
        String fileName = UUID.randomUUID().toString();
        String fileName2 = urlImages +  fileName;
        for (Part part : req.getParts()) {
            part.write(fileName2 + extractFileName(part));
            fileName += extractFileName(part);
        }
        return fileName;
    }

    private static String extractFileName(Part part) {
        String contenDisp = part.getHeader("content-disposition");
        String[] items = contenDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.lastIndexOf("."), s.length() - 1);
            }
        }
        return "";
    }
}
