package com.easycall.project.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Facturas de los Clientes")
public class EmployeeInvoicesView extends VerticalLayout {

    public EmployeeInvoicesView() {
        // Crear un encabezado con el mensaje deseado
        H1 header = new H1("Facturas de los Clientes");

        // Añadir el encabezado al layout de la vista
        add(header);

        // Configuraciones adicionales del layout, si son necesarias
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
    }
}
