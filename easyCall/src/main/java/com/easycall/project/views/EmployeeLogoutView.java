package com.easycall.project.views;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("EmployeeLogoutView")
@PageTitle("EmployeeLogoutView")
public class EmployeeLogoutView extends Composite<VerticalLayout> {
    public EmployeeLogoutView() {
        UI.getCurrent().getPage().setLocation("loginEmployee");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }


}