package com.easycall.project.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Employee Complaints")
public class EmployeeComplaintsView extends VerticalLayout {

    public EmployeeComplaintsView() {
        // Crear un encabezado y añadir el texto deseado
        H1 header = new H1("customesppbaby");

        // Añadir el encabezado al layout de la vista
        add(header);

        // Configurar el estilo del layout si es necesario
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
    }
}

