
package org.dshubs.odc.core.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

/**
 * MVC配置
 *
 * @author wangxian
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final GlobalInterceptor globalInterceptor;

    public WebMvcConfig(GlobalInterceptor globalInterceptor) {
        this.globalInterceptor = globalInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(jackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();

        //日期格式转换
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //LocalDateTime
        SimpleModule localDateTime = new SimpleModule();
        localDateTime.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        localDateTime.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(localDateTime);
        //BigDecimal类型转String类型
        SimpleModule bigDecimal = new SimpleModule();
        bigDecimal.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        mapper.registerModule(bigDecimal);

        //Long类型转String类型
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(simpleModule);

        //Double类型转String类型
        SimpleModule doubleModule = new SimpleModule();
        doubleModule.addSerializer(Double.class, ToStringSerializer.instance);
        doubleModule.addSerializer(Double.TYPE, ToStringSerializer.instance);
        mapper.registerModule(doubleModule);

        converter.setObjectMapper(mapper);
        return converter;
    }
}
