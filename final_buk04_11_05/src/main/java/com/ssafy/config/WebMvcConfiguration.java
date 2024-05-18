package com.ssafy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = { "com.ssafy.enjoytrip.*.dao" })
public class WebMvcConfiguration implements WebMvcConfigurer {

	//	private final List<String> patterns = Arrays.asList("/board/*","/trip/*");
	//	
	//	@Autowired
	//	private SessionInterceptor sessionInterceptor;
	//	
	//	@Override
	//	public void addInterceptors(InterceptorRegistry registry) {
	//		registry.addInterceptor(sessionInterceptor).addPathPatterns(patterns);
	//	}
	@Override
	public void  addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // --1
		.addResourceLocations("file:src/main/webapp/upload/240518/"); //--2
	}
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index");
//	}
	//	@Override
	//	public void addCorsMappings(CorsRegistry registry) {
	//		registry.addMapping("/**")  // 모든 요청 주소를 허용
	//			.allowedOrigins("*")  // 화이트리스트 (요청을 허용할 서버 주소)
	//			.allowedMethods(  // 허용 할 HTTP Method
	//					HttpMethod.GET.name(),
	//					HttpMethod.POST.name(),
	//					HttpMethod.PUT.name(),
	//					HttpMethod.PATCH.name(),  // 부분 수정 요청 시
	//					HttpMethod.DELETE.name(),
	//					HttpMethod.HEAD.name(),  // preflight
	//					HttpMethod.OPTIONS.name())  // preflight
	//			.maxAge(1800);  // 1800초 동안 preflight 결과를 캐시에 저장
	//	}
}
