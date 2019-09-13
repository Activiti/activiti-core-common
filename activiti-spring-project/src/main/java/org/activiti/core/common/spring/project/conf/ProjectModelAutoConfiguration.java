package org.activiti.core.common.spring.project.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.core.common.spring.project.ProjectModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class ProjectModelAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnMissingClass(value = "org.springframework.http.converter.json.Jackson2ObjectMapperBuilder")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ProjectModelService projectModelService(@Value("${application.manifest.file.path:classpath:/}") String path,
                                                   @Value("${activiti.cloud.application.name:default-app}") String applicationName,
                                                   ObjectMapper objectMapper,
                                                   ResourcePatternResolver resourceLoader) {
        return new ProjectModelService(path,
                                       applicationName,
                                       objectMapper,
                                       resourceLoader);
    }
}
