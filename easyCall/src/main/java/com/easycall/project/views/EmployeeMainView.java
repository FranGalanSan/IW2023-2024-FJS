package com.easycall.project.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("employee-main-view")
@PageTitle("EmployeeMainViewW")
public class EmployeeMainView extends VerticalLayout {

    public EmployeeMainView() {
        // Crear un encabezado con el mensaje deseado
        H1 header = new H1("Estás en la vista main de empleados");

        // Añadir el encabezado al layout de la vista
        add(header);

        // Configuraciones adicionales del layout, si son necesarias
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
    }
}
