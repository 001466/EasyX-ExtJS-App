
package org.easy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 系统模块启动器
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
@MapperScan({"org.easy.**.mapper"})
@Controller
public class Application {
	@GetMapping("/")
	public String list() {
		return "/index";
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


