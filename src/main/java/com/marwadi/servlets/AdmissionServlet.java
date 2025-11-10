package com.marwadi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling admission form submissions
 */
public class AdmissionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Get form parameters
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String program = request.getParameter("program");
            String qualification = request.getParameter("qualification");
            String percentage = request.getParameter("percentage");
            String address = request.getParameter("address");
            String message = request.getParameter("message");
            
            // Basic validation
            if (fullName == null || fullName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                program == null || program.trim().isEmpty() ||
                qualification == null || qualification.trim().isEmpty() ||
                percentage == null || percentage.trim().isEmpty()) {
                
                sendErrorResponse(out, "All required fields must be filled!");
                return;
            }
            
            // Validate email format
            if (!isValidEmail(email)) {
                sendErrorResponse(out, "Invalid email format!");
                return;
            }
            
            // Log the admission application (in production, save to database)
            logAdmissionApplication(fullName, email, phone, program, qualification, 
                                   percentage, address, message);
            
            // Send success response
            sendSuccessResponse(out, fullName);
            
        } catch (Exception e) {
            sendErrorResponse(out, "An error occurred: " + e.getMessage());
        } finally {
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("pages/admissions.html");
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    
    private void logAdmissionApplication(String fullName, String email, String phone,
                                        String program, String qualification, String percentage,
                                        String address, String message) {
        // In production, this would save to database
        System.out.println("=== Admission Application ===");
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Program: " + program);
        System.out.println("Qualification: " + qualification);
        System.out.println("Percentage: " + percentage);
        System.out.println("Address: " + address);
        System.out.println("Message: " + message);
        System.out.println("=============================");
    }
    
    private void sendSuccessResponse(PrintWriter out, String fullName) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Application Submitted - Marwadi University</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<section class='hero' style='min-height: 100vh; display: flex; align-items: center;'>");
        out.println("<div class='container' style='text-align: center;'>");
        out.println("<h1>Thank You, " + escapeHtml(fullName) + "!</h1>");
        out.println("<p>Your admission application has been submitted successfully.</p>");
        out.println("<p>Application ID: APP" + System.currentTimeMillis() + "</p>");
        out.println("<p>You will receive a confirmation email shortly with further instructions.</p>");
        out.println("<p>Our admissions team will contact you within 3-5 business days.</p>");
        out.println("<a href='pages/admissions.html' class='btn' style='margin-top: 20px;'>Back to Admissions</a>");
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
        out.println("<a href='pages/admissions.html' class='btn' style='margin-top: 20px;'>Try Again</a>");
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
