package com.easycall.project.employee.login;


import com.easycall.project.employee.EmployeeRepository;
import com.easycall.project.employee.Role;
import com.easycall.project.employee.Employee;
import com.easycall.project.views.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AuthEmployeeService {
    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public class AuthException extends Exception {

    }

    private final EmployeeRepository employeeRepository;

    public AuthEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        Employee employee = employeeRepository.getByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            VaadinSession.getCurrent().setAttribute(Employee.class, employee);
            createRoutes(employee.getRol());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role rol) {
        RouteConfiguration routeConfiguration = RouteConfiguration.forSessionScope();
        getAuthorizedRoutes(rol).forEach(route -> {
            // Verificar si la clase de vista ya está registrada
            boolean isRouteRegistered = routeConfiguration.getAvailableRoutes().stream()
                    .anyMatch(registeredRoute -> registeredRoute.getNavigationTarget().equals(route.view));

            // Si la vista no está registrada, configurar la ruta
            if (!isRouteRegistered) {
                routeConfiguration.setRoute(route.route, route.view, MainnView.class);
            }
        });
    }




    public List<AuthorizedRoute> getAuthorizedRoutes(Role rol) {
        var routes = new ArrayList<AuthorizedRoute>();

        if (rol.equals(Role.MARKETING)) {
            routes.add(new AuthorizedRoute("EmployeeMainView", "EmployeeMainView", EmployeeMainView.class));
            routes.add(new AuthorizedRoute("EmployeeServiceView", "EmployeeServiceView", EmployeeServiceView.class));
            routes.add(new AuthorizedRoute("EmployeeDataView", "EmployeeDataView", EmployeeDataView.class));
            routes.add(new AuthorizedRoute("EmployeeManageServiceView", "EmployeeManageServiceView", EmployeeManageServiceView.class));
        }
        if (rol.equals(Role.CUSTOME_SPP)) {
            routes.add(new AuthorizedRoute("EmployeeMainView", "EmployeeMainView", EmployeeMainView.class));
            routes.add(new AuthorizedRoute("EmployeeComplaintsView","EmployeeComplaintsView", EmployeeComplaintsView.class));
            routes.add(new AuthorizedRoute("EmployeeDataView", "EmployeeDataView", EmployeeDataView.class));
            routes.add(new AuthorizedRoute("EmployeeDarBajaView", "EmployeeDarBajaView", EmployeeDarBajaView.class));
        }
        if (rol.equals(Role.FINANCE)) {
            routes.add(new AuthorizedRoute("EmployeeMainView", "EmployeeMainView", EmployeeMainView.class));
            routes.add(new AuthorizedRoute("EmployeeEmiteFacturaView", "EmployeeEmiteFacturaView", EmployeeEmiteFacturaView.class));
            routes.add(new AuthorizedRoute("EmployeeDataView", "EmployeeDataView", EmployeeDataView.class));
            routes.add(new AuthorizedRoute("EmployeeDarBajaView", "EmployeeDarBajaView", EmployeeDarBajaView.class));
        }

        return routes;
    }
}

