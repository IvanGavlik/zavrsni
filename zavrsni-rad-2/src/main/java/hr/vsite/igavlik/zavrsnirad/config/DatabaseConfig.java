package hr.vsite.igavlik.zavrsnirad.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DatabaseConfig {


    @Bean
    public DataSource dataSource() {

        //     String dbUri = "postgres://voimthudbsdgis:6af2ae0043b9582cfcf5d33e4f04580c00e8c30eb84a8255a9cc9d1e8f653f05@ec2-34-232-147-86.compute-1.amazonaws.com:5432/d75n4ls81kepfh";
   //      String username = dbUri.getUserInfo().split(":")[0];
   //       String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://ec2-34-232-147-86.compute-1.amazonaws.com:5432/d75n4ls81kepfh";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername("voimthudbsdgis");
        config.setPassword("6af2ae0043b9582cfcf5d33e4f04580c00e8c30eb84a8255a9cc9d1e8f653f05");
        return new HikariDataSource(config);
    }
}
