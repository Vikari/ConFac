/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conFac;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jukka
 */
public class MailServlet extends HttpServlet {

    @Resource(name = "mail/ownmail")
    private Session ownmail;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Message message = new MimeMessage(ownmail);
            String to = request.getParameter("Recipient");
            String sub = request.getParameter("Subject");
            String msg = request.getParameter("Message");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MailServlet at " + request.getContextPath() + "</h1>");
            try {
                message.setSubject(sub);
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                message.setText(msg);
                Transport.send(message);
                out.println("Message Sent SuccessFully..please check your mail");

            } catch (MessagingException ex) {
                out.println("Oops! Got an Exception");
                out.println(ex.toString());
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        MimeMessage message = new MimeMessage(ownmail);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }

}
