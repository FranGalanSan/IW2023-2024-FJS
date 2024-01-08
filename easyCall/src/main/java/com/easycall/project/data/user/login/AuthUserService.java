package com.easycall.project.data.user.login;

import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserRepository;
import com.easycall.project.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void authenticate(String email, String password) throws AuthException {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            VaadinSession.getCurrent().setAttribute(User.class, user);
            createRoutes("USER");
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(String role) {
        RouteConfiguration routeConfiguration = RouteConfiguration.forSessionScope();
        getAuthorizedRoutes(role).forEach(route -> {
            // Verificar si la clase de vista ya está registrada
            boolean isRouteRegistered = routeConfiguration.getAvailableRoutes().stream()
                    .anyMatch(registeredRoute -> registeredRoute.getNavigationTarget().equals(route.view));
        });
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(String role) {
        var routes = new ArrayList<AuthorizedRoute>();

        if ("USER".equals(role)) {
            routes.add(new AuthorizedRoute("UserMainView", "UserMainView", MainView.class));

        }

        return routes;
    }
}
