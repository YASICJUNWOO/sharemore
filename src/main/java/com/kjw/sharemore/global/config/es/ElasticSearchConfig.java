package com.kjw.sharemore.global.config.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "org.springframework.data.elasticsearch.repository")
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Value("${aws.ec2}")
    private String ec2;

    @Override
    public ClientConfiguration clientConfiguration() {
        log.info("**************");
        log.info(ec2);
        log.info("**************");
        return ClientConfiguration.builder()
                .connectedTo(ec2+":9200")
                .build();
    }
}
