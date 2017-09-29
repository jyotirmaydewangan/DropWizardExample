package com.dewangan.jyotirmay.rest.controller;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.util.Map.Entry;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jyotirmay.d on 28/09/17.
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/status")
public class HealthCheckController
{
    private HealthCheckRegistry registry;

    public HealthCheckController(HealthCheckRegistry registry) {
        this.registry = registry;
    }

    @GET
    public Set<Entry<String, HealthCheck.Result>> getStatus(){
        return registry.runHealthChecks().entrySet();
    }
}
