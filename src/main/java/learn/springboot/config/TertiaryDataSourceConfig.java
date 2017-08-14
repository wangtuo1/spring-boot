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
        entityManagerFactoryRef="tertiaryEntityManagerFactory",
        transactionManagerRef="tertiaryTransactionManager",
        basePackages= { "learn.springboot.repository.tertiary" }) //设置Repository所在位置
public class TertiaryDataSourceConfig {
    @Bean(name = "tertiaryDataSource")
    @Qualifier("tertiaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.tertiary")
    public DataSource tertiaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Autowired @Qualifier("tertiaryDataSource")
    private DataSource tertiaryDataSource;
    
    @Bean(name = "tertiaryEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return tertiaryEntityManagerFactory(builder).getObject().createEntityManager();
    }
    @Bean(name = "tertiaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tertiaryEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(tertiaryDataSource)
                .properties(getVendorProperties(tertiaryDataSource))
                .packages("learn.springboot.domain") //设置实体类所在位置
                .persistenceUnit("tertiaryPersistenceUnit")
                .build();
    }
    @Autowired
    private JpaProperties jpaProperties;
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
    @Bean(name = "tertiaryTransactionManager")
    PlatformTransactionManager tertiaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(tertiaryEntityManagerFactory(builder).getObject());
    }
}
