package net.dpco.pdf.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DBConfig {

  @Value("${db.driver}")
  private String dbDriver;

  @Value("${db.password}")
  private String dbPass;

  @Value("${db.url}")
  private String dbUrl;

  @Value("${db.username}")
  private String dbUsername;

  @Value("${hibernate.dialect}")
  private String hibernateDialect;

  @Value("${hibernate.show_sql}")
  private String hibernateShowSql;

  @Value("${hibernate.hbm2ddl.auto}")
  private String hibernateHbm2DdlAuto;

  @Value("${hibernate.temp.use_jdbc_metadata_defaults}")
  private String hibernateUserJdbcMetadataDefaults;

  @Value("${entitymanager.packagesToScan}")
  private String entitymanagerPackagesToScan;

  @Value("${hibernate.cache.use_query_cache}")
  private String cacheUseQueryCache;

  @Value("${hibernate.cache.region.factory_class}")
  private String cacheRegionFactoryClass;

  @Value("${hibernate.c3p0.max_size}")
  private String connPoolMaxSize;

  @Value("${hibernate.c3p0.initialPoolSize}")
  private String connPoolInitialPoolSize;

  @Value("${hibernate.c3p0.min_size}")
  private String connPoolMinSize;

  @Value("${hibernate.c3p0.timeout}")
  private String connPoolTimeout;

  @Value("${hibernate.c3p0.acquireRetryAttempts}")
  private String acquireRetryAttempts;

  @Value("${hibernate.c3p0.acquireRetryDelay}")
  private String acquireRetryDelay;

  @Value("${hibernate.c3p0.acquire_increment}")
  private String connPoolAcquireIncrement;

  @Value("${hibernate.c3p0.max_statements}")
  private String connPoolMaxStatements;

  @Value("${hibernate.c3p0.idleConnectionTestPeriod}")
  private String idleConnectionTestPeriod;

  @Value("${hibernate.c3p0.validate}")
  private String connPoolValidateConnections;

  @Value("${hibernate.c3po.numHelperThreads}")
  private String connPoolNumHelperThreads;

  @Bean
  public ComboPooledDataSource dataSource() {
    // a named datasource is best practice for later jmx monitoring
    ComboPooledDataSource dataSource = new ComboPooledDataSource("Bonyad-Maskan-Project-DS");

    try {
      dataSource.setDriverClass(dbDriver);
    } catch (PropertyVetoException pve) {
      return null;
    }
    dataSource.setJdbcUrl(dbUrl);
    dataSource.setUser(dbUsername);
    dataSource.setPassword(dbPass);
    dataSource.setInitialPoolSize(Integer.parseInt(connPoolInitialPoolSize));
    dataSource.setMinPoolSize(Integer.parseInt(connPoolMinSize));
    dataSource.setMaxPoolSize(Integer.parseInt(connPoolMaxSize));
    dataSource.setNumHelperThreads(Integer.parseInt(connPoolNumHelperThreads));
    dataSource.setMaxIdleTime(Integer.parseInt(connPoolTimeout));
    dataSource.setAcquireIncrement(Integer.parseInt(connPoolAcquireIncrement));
    dataSource.setAcquireRetryAttempts(Integer.valueOf(acquireRetryAttempts));
    dataSource.setAcquireRetryDelay(Integer.parseInt(acquireRetryDelay));

    // Test connections
    dataSource.setIdleConnectionTestPeriod(Integer.valueOf(idleConnectionTestPeriod));
    dataSource.setTestConnectionOnCheckout(Boolean.valueOf(connPoolValidateConnections));

    return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource());
    sessionFactoryBean.setPackagesToScan(entitymanagerPackagesToScan);
    Properties hibernateProperties = new Properties();
    hibernateProperties.put("hibernate.dialect", hibernateDialect);
    hibernateProperties.put("hibernate.show_sql", hibernateShowSql);
    hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
    hibernateProperties.put(
        "hibernate.temp.use_jdbc_metadata_defaults", hibernateUserJdbcMetadataDefaults);

    sessionFactoryBean.setHibernateProperties(hibernateProperties);
    return sessionFactoryBean;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }
}
