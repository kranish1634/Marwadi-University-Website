package com.marwadi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling contact form submissions
 */
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Get form parameters
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
            
            // Basic validation
            if (name == null || name.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                subject == null || subject.trim().isEmpty() ||
                message == null || message.trim().isEmpty()) {
                
                sendErrorResponse(out, "All fields are required!");
                return;
            }
            
            // Validate email format
            if (!isValidEmail(email)) {
                sendErrorResponse(out, "Invalid email format!");
                return;
            }
            
            // Log the contact form submission (in production, save to database)
            logContactSubmission(name, email, phone, subject, message);
            
            // Send success response
            sendSuccessResponse(out, name);
            
        } catch (Exception e) {
            sendErrorResponse(out, "An error occurred: " + e.getMessage());
        } finally {
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("pages/contact.html");
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    
    private void logContactSubmission(String name, String email, String phone, 
                                     String subject, String message) {
        // In production, this would save to database
        System.out.println("=== Contact Form Submission ===");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("===============================");
    }
    
    private void sendSuccessResponse(PrintWriter out, String name) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Success - Marwadi University</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<section class='hero' style='min-height: 100vh; display: flex; align-items: center;'>");
        out.println("<div class='container' style='text-align: center;'>");
        out.println("<h1>Thank You, " + escapeHtml(name) + "!</h1>");
        out.println("<p>Your message has been received successfully.</p>");
        out.println("<p>We will get back to you within 24-48 hours.</p>");
        out.println("<a href='pages/contact.html' class='btn' style='margin-top: 20px;'>Back to Contact</a>");
        out.println("<a href='index.html' class='btn btn-secondary' style='margin-top: 20px; margin-left: 10px;'>Go to Home</a>");
        out.println("</div>");
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void sendErrorResponse(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Error - Marwadi University</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<section class='hero' style='min-height: 100vh; display: flex; align-items: center;'>");
        out.println("<div class='container' style='text-align: center;'>");
        out.println("<h1>Oops! Something went wrong</h1>");
        out.println("<p style='color: #ff6600;'>" + escapeHtml(errorMessage) + "</p>");
        out.println("<a href='pages/contact.html' class='btn' style='margin-top: 20px;'>Try Again</a>");
        out.println("</div>");
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#x27;");
    }
}
