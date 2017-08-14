package learn.springboot.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="secondaryEntityManagerFactory",
        transactionManagerRef="secondaryTransactionManager",
        basePackages= { "learn.springboot.repository.secondary" }) //设置Repository所在位置
public class SecondaryDataSourceConfig {
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Autowired @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;
    
    @Bean(name = "secondaryEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return secondaryEntityManagerFactory(builder).getObject().createEntityManager();
    }
    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource)
                .properties(getVendorProperties(secondaryDataSource))
                .packages("learn.springboot.domain") //设置实体类所在位置
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }
    @Autowired
    private JpaProperties jpaProperties;
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
    @Bean(name = "secondaryTransactionManager")
    PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
    }
}
