package com.routify;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.routify.resources.TaskResources;

public class App extends Application<AppConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "TodoApp";
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final AppConfiguration configuration,
                    final Environment environment) {
    	LOGGER.info("Registering REST resources");
    	environment.jersey().register(new TaskResources(environment.getValidator()));
    	
    }

}
