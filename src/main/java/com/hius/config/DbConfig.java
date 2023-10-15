package com.hius.config;

import com.hius.dao.db.BaseRepositoryFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.hius.repository"},
        entityManagerFactoryRef = "primeEntityManager",
        transactionManagerRef = "primeTransactionManager",
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
public class DbConfig {
    @Autowired
    Environment env;

    public DbConfig() {
    }

    @Bean
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    public DataSourceProperties primeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean()
    public DataSource primeDataSource() {
        return this.primeDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean primeEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.primeDataSource());
        em.setPackagesToScan(new String[]{"com.hius.entity"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap();
        properties.put("hibernate.hbm2ddl.auto", this.env.getProperty("app.datasource.prime.hibernate.hbm2ddl.auto"));
        properties.put("spring.jpa.hibernate.ddl-auto", this.env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", this.env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        em.setPersistenceUnitName("prime");
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager primeTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.primeEntityManager().getObject());
        return transactionManager;
    }
}
