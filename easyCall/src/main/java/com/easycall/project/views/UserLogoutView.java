

package com.easycall.project.views;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("UserLogoutView")
@PageTitle("UserLogoutView")
public class UserLogoutView extends Composite<VerticalLayout> {
    public UserLogoutView() {
        UI.getCurrent().getPage().setLocation("UserLoginView");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }


}