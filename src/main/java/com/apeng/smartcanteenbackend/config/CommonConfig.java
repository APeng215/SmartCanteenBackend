package com.apeng.smartcanteenbackend.config;

import com.apeng.smartcanteenbackend.entity.Canteen;
import com.apeng.smartcanteenbackend.entity.Dish;
import com.apeng.smartcanteenbackend.entity.User;
import com.apeng.smartcanteenbackend.entity.sub.Authority;
import com.apeng.smartcanteenbackend.repository.CanteenRepository;
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
import java.util.Set;

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
            repository.save(new User(CommonConfig.ADMIN_USERNAME, CommonConfig.ADMIN_PASSWORD, Set.of(new Authority(CommonConfig.ADMIN_USERNAME, "ROLE_ADMIN"), new Authority(CommonConfig.ADMIN_USERNAME, "ROLE_USER"))));
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

    @Bean
    public CommandLineRunner addDefaultCanteen(CanteenRepository repository) {
        return (args) -> {
            repository.save(new Canteen("荷园一餐厅", 5, 10, "荷园一餐厅推出季节特选菜单，带来不同凡响的美食体验，欢迎大家前来品尝！", "https://i.ibb.co/jzjTbGr/he1.jpg"));
            repository.save(new Canteen("荷园二餐厅", 8, 10, "荷园二食堂将于五月一日休息，届时将暂停营业，请各位同学提前安排好就餐时间。", "https://i.ibb.co/0fL2nrR/he2.jpg"));
            repository.save(new Canteen("聚英园餐厅", 16, 20, "聚英园餐厅新推出多种健康餐饮选择，欢迎品尝！", "https://i.ibb.co/2ZnPrCp/jyj.jpg"));
            repository.save(new Canteen("风华园餐厅", 1, 30, "风华园餐厅将于每周五推出特别菜肴，敬请期待！", "https://i.ibb.co/HBW5F7q/fhy.jpg"));
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
