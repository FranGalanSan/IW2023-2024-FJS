package com.easycall.project.employee.login;
import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserRepository;

import com.easycall.project.employee.Role;

import com.easycall.project.views.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AuthUserService {
    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public class AuthException extends Exception {

    }

    private final UserRepository userRepository;

    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String username, String password) throws AuthUserService.AuthException {
        User user = userRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            VaadinSession.getCurrent().setAttribute(User.class, user);
            createRoutes(user.getRol());
        } else {
            throw new AuthUserService.AuthException();
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

    public List<AuthUserService.AuthorizedRoute> getAuthorizedRoutes(Role rol) {
        var routes = new ArrayList<AuthUserService.AuthorizedRoute>();

        if (rol.equals(Role.USER)) {
            routes.add(new AuthUserService.AuthorizedRoute("UserSmsView", "UserSmsView", UserSmsView.class));

        }//No importa que no esten ocultas, los usuarios solo pueden ver lo correspondiente a su id, asi que como mucho veran una pagina en blanco


        return routes;
    }



}
