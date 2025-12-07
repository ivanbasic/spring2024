package com.ivanbasic.learnspring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.ivanbasic.learnspring")
@EnableJpaRepositories(
        basePackages = "com.ivanbasic.learnspring.repositories.db3",
        entityManagerFactoryRef = "db3EntityManager",
        transactionManagerRef = "db3TransactionManager"
)
public class Db3AutoConfiguration {

    @Bean(name = "db3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    public DataSource db3DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db3EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db3DataSource());
        em.setPackagesToScan("com.ivanbasic.learnspring.model.db3");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public PlatformTransactionManager db3TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(db3EntityManager().getObject());
        return transactionManager;
    }

}