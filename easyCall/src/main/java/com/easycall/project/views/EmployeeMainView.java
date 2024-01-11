package com.easycall.project.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;



@PageTitle("EmployeeMainView")
public class EmployeeMainView extends VerticalLayout {

    public EmployeeMainView() {

        H1 title = new H1("Por Dónde Quieres Empezar");
        add(title);


        Button buttonToComplaints = new Button("Ver Quejas", event -> {
            UI.getCurrent().navigate(EmployeeComplaintsView.class);
        });

        Button button1 = new Button("Ver Servicios", event -> {
            UI.getCurrent().navigate(EmployeeServiceView.class);
        });

        Button button2 = new Button("Logout", event -> {
            UI.getCurrent().navigate(EmployeeLogoutView.class);
        });

        Button button3 = new Button("Facturas", event -> {
            UI.getCurrent().navigate(EmployeeEmiteFacturaView.class);
        });

        Button button4 = new Button("Datos Personales", event -> {
            UI.getCurrent().navigate(EmployeeDataView.class);
        });

        Button button5 = new Button("Dar baja", event -> {
            UI.getCurrent().navigate(EmployeeDarBajaView.class);
        });

        Button button6 = new Button("Gestionar Servicios", event -> {
            UI.getCurrent().navigate(EmployeeManageServiceView.class);
        });

        add(buttonToComplaints, button1, button3, button4, button5,button6,button2);
    }
}