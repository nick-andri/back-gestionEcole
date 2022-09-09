package fr.dawan.miseEnSituation;

import fr.dawan.miseEnSituation.interceptors.TokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MiseEnSituationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiseEnSituationApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer myMvcConfigurer() {
		return new WebMvcConfigurer() {
			//AJOUT D'UN FILTRE
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				/*HandlerInterceptor tokenInterceptor = new TokenInterceptor();
				registry.addInterceptor(tokenInterceptor);*/
			}

			// CROS ORIGIN
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");

			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
			}
		};
	}
}
