package com.dewangan.jyotirmay;

/* * * * * * * * * * * * * * * * * * * * *
 *                                       *
 *               Hello world!            *
 *                                       *
 * * * * * * * * * * * * * * * * * * * * */

import com.dewangan.jyotirmay.auth.AppAuthorizer;
import com.dewangan.jyotirmay.auth.AppBasicAuthenticator;
import com.dewangan.jyotirmay.auth.User;
import com.dewangan.jyotirmay.rest.controller.EmployeeRESTController;
import com.dewangan.jyotirmay.rest.controller.HealthCheckController;
import com.dewangan.jyotirmay.rest.controller.RESTClientController;
import com.dewangan.jyotirmay.util.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.client.Client;


public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration config, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new EmployeeRESTController(e.getValidator()));

        final Client client = new JerseyClientBuilder().build();
        e.jersey().register(new RESTClientController(client));

        e.jersey().register(new HealthCheckController(e.healthChecks()));

        e.healthChecks().register("APIHealthCheck", new AppHealthCheck(client));


        e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new AppBasicAuthenticator())
                .setAuthorizer(new AppAuthorizer())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        e.jersey().register(RolesAllowedDynamicFeature.class);
        e.jersey().register(new AuthValueFactoryProvider.Binder(User.class));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}