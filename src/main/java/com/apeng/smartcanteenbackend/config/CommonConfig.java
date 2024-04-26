package com.apeng.smartcanteenbackend.config;

import com.apeng.smartcanteenbackend.entity.Dish;
import com.apeng.smartcanteenbackend.entity.User;
import com.apeng.smartcanteenbackend.repository.DishRepository;
import com.apeng.smartcanteenbackend.repository.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class CommonConfig {

    Logger logger = LoggerFactory.getLogger(CommonConfig.class);

    @Autowired
    private DishRepository dishRepository;

    public static String ADMIN_USERNAME = "admin";
    public static String ADMIN_PASSWORD = "123456";

    @Bean
    public RestTemplate configureRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置 RestTemplateBuilder 以使用管理员认证
     *
     * @param restTemplateBuilder
     * @return
     */
    @Bean
    RestOperations configureRestOperations(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication(ADMIN_USERNAME, ADMIN_PASSWORD).build();
    }

    /**
     * 添加管理员用户
     *
     * @param repository
     * @return
     */
    @Bean
    public CommandLineRunner addAdminUser(UserRepository repository) {
        return (args) -> {
            repository.save(new User(CommonConfig.ADMIN_USERNAME, CommonConfig.ADMIN_PASSWORD));
            repository.save(new User("player", "123456"));
        };
    }

    @Bean
    public CommandLineRunner addDishesInfo(DishRepository repository) {
        return (args) -> {
            Sheet sheet = retrieveDishesSheet();
            writeSheet2DataBase(sheet);
        };
    }

    private void writeSheet2DataBase(Sheet sheet) {
        for (Row row : sheet) {
            Dish dish = generateDish(row);
            if (dish == null) continue;
            dishRepository.save(dish);
        }
    }

    private static Dish generateDish(Row row) {
        if (isHeaderRow(row)) return null;
        Dish dish = new Dish();
        for (Cell cell : row) { // Column index begins from 0
            switch (cell.getColumnIndex()) {
                case 0 -> dish.setName(cell.getStringCellValue());
                case 1 -> dish.setImage_url(cell.getStringCellValue());
                case 2 -> dish.setPrice(cell.getNumericCellValue());
                case 3 -> dish.setLocation(cell.getStringCellValue());
                case 4 -> dish.getNutrition().setProtein(cell.getNumericCellValue());
                case 5 -> dish.getNutrition().setFat(cell.getNumericCellValue());
                case 6 -> dish.getNutrition().setEnergy(cell.getNumericCellValue());
            }
        }
        return dish;
    }


    private static boolean isHeaderRow(Row row) {
        return row.getRowNum() == 0;
    }

    private static Sheet retrieveDishesSheet() throws URISyntaxException, IOException {
        FileInputStream file = new FileInputStream(new URI(CommonConfig.class.getClassLoader().getResource("static/dishes_info.xlsx").getPath()).getPath());
        Workbook workbook = new XSSFWorkbook(file);
        return workbook.getSheetAt(0);
    }
}
