package com.Assignment.shopping_carts.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final boolean seedDatabase;
    private final boolean resetDatabaseBeforeSeed;

    public DatabaseSeeder(
            DataSource dataSource,
            JdbcTemplate jdbcTemplate,
            @Value("${app.seed-database:false}") boolean seedDatabase,
            @Value("${app.reset-database-before-seed:false}") boolean resetDatabaseBeforeSeed) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.seedDatabase = seedDatabase;
        this.resetDatabaseBeforeSeed = resetDatabaseBeforeSeed;
    }

    @Override
    public void run(ApplicationArguments args) {
        Integer productCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product", Integer.class);
        boolean productTableIsEmpty = productCount == null || productCount == 0;

        if (!seedDatabase && !productTableIsEmpty) {
            System.out.println("[DatabaseSeeder] Seed disabled and product table already has data.");
            return;
        }

        if (resetDatabaseBeforeSeed) {
            resetTables();
            seedAllDemoData();
            return;
        }

        if (productTableIsEmpty) {
            seedProductsAndCategoriesOnly();
            return;
        }

        seedAllDemoData();
    }

    private void seedProductsAndCategoriesOnly() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("UTF-8");
        populator.addScripts(
                new ClassPathResource("static/mysqlScripts/productAndCategoryScripts.sql"));
        populator.execute(dataSource);

        System.out.println("[DatabaseSeeder] Product and category seed data loaded.");
    }

    private void seedAllDemoData() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("UTF-8");
        populator.addScripts(
                new ClassPathResource("static/mysqlScripts/productAndCategoryScripts.sql"),
                new ClassPathResource("static/mysqlScripts/CustomerScript.sql"),
                new ClassPathResource("static/mysqlScripts/ReviewScript.sql"));
        populator.execute(dataSource);

        System.out.println("[DatabaseSeeder] Full demo seed data loaded.");
    }

    private void resetTables() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        jdbcTemplate.execute("TRUNCATE TABLE review");
        jdbcTemplate.execute("TRUNCATE TABLE favourites");
        jdbcTemplate.execute("TRUNCATE TABLE shopping_cart_detail");
        jdbcTemplate.execute("TRUNCATE TABLE order_detail");
        jdbcTemplate.execute("TRUNCATE TABLE orders");
        jdbcTemplate.execute("TRUNCATE TABLE product");
        jdbcTemplate.execute("TRUNCATE TABLE category");
        jdbcTemplate.execute("TRUNCATE TABLE customer");
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }
}
