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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="primaryEntityManagerFactory",
        transactionManagerRef="primaryTransactionManager",
        basePackages= { "learn.springboot.repository.primary" }) //设置Repository所在位置
public class PrimaryDataSourceConfig {
	@Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Autowired @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
    
    @Bean(name = "primaryEntityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return primaryEntityManagerFactory(builder).getObject().createEntityManager();
    }
    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("learn.springboot.domain") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }
    @Autowired
    private JpaProperties jpaProperties;
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }
}
