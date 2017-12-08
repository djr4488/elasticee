package org.djr.elastic.properties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.bean.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by djr4488 on 12/8/17.
 */
@ApplicationScoped
public class PropertiesProducer {
    @Produces
    @ElasticProperties
    public Properties loadConfigProperties(InjectionPoint injectionPoint)
    throws IOException {
        ElasticProperties elasticProperties = injectionPoint.getAnnotated().getAnnotation(ElasticProperties.class);
        String propertyFileName = "elastic.properties";
        if (null != elasticProperties && !"".equals(elasticProperties.name().trim())) {
            propertyFileName = elasticProperties.name();
        }
        Properties prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        prop.load(in);
        in.close();
        return prop;
    }
}