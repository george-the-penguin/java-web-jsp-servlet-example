/*
 *  MIT License
 *  
 *  Copyright (c) 2023 Jorge Garcia "George the Penguin"
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package dev.georgethepenguin.example.servlet;

import dev.georgethepenguin.example.model.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A servlet that serves as a controller for appointments.
 * 
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@WebServlet(name = "AppointmentsServlet", urlPatterns = {"/appointments"})
public class AppointmentsServlet extends HttpServlet {

    private final List<Appointment> appointments = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final var appointment = new Appointment();
        appointment.setName(request.getParameter("name"));
        appointment.setEmail(request.getParameter("email"));
        appointment.setPhoneNumber(request.getParameter("phone"));
        appointment.setAppointmentDate(request.getParameter("date"));
        appointment.setAppointmentTime(request.getParameter("time"));
        appointment.setLocation(request.getParameter("location"));
        appointment.setReason(request.getParameter("reason"));

        appointments.add(appointment);

        request.setAttribute("appointments", appointments);
        
        final var requestDispatcher = request.getRequestDispatcher("appointments-list.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "A servlet that serves as a controller for appointments.";
    }
}
