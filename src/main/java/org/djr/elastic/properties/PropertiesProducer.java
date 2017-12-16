package org.djr.elastic.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(PropertiesProducer.class);

    @Produces
    @ElasticProperties(name = "elastic.properties")
    public Properties loadConfigProperties(InjectionPoint injectionPoint)
    throws IOException {
        ElasticProperties elasticProperties = injectionPoint.getAnnotated().getAnnotation(ElasticProperties.class);
        String propertyFileName = "elastic.properties";
        Properties prop;
        String isElasticPropertiesInSystemEnvironment = System.getenv("elasticPropertiesInSystemEnvironment");
        log.debug("loadConfigProperties() elasticPropertiesInSystemEnvironment:{}", System.getenv("elasticPropertiesInSystemEnvironment"));
        if (null != isElasticPropertiesInSystemEnvironment && Boolean.parseBoolean(isElasticPropertiesInSystemEnvironment)) {
            prop = new Properties();
            log.debug("loadConfigProperties() elasticHosts:{}", System.getenv("elasticHosts"));
            log.debug("loadConfigProperties() elasticPorts:{}", System.getenv("elasticPorts"));
            log.debug("loadConfigProperties() elasticSchemes:{}", System.getenv("elasticSchemes"));
            log.debug("loadConfigProperties() delineator:{}", System.getenv("delineator"));
            prop.put("elasticHosts", System.getenv("elasticHosts"));
            prop.put("elasticPorts", System.getenv("elasticPorts"));
            prop.put("elasticSchemes", System.getenv("elasticSchemes"));
            prop.put("delineator", System.getenv("delineator"));
        } else {
            log.debug("loadConfigProperties() looking for elastic.properties in resources");
            if (!"".equals(elasticProperties.name().trim())) {
                propertyFileName = elasticProperties.name();
            }
            prop = new Properties();
            InputStream in = getClass().getClassLoader().getResourceAsStream(propertyFileName);
            prop.load(in);
            in.close();
        }
        return prop;
    }
}
