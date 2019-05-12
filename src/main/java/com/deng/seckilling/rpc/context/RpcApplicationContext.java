package com.deng.seckilling.rpc.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * RPC-Spring上下文
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/10 17:16
 */
@Component
public class RpcApplicationContext implements ApplicationContextAware {

    private static org.springframework.context.ApplicationContext context = null;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        Map<String, Object> beanMap = context.getBeansWithAnnotation(EnableScheduling.class);
    }

    /**
     * 根据Bean的类型从Bean容器中获取一个Bean实例，这是一个静态方法
     *
     * @param clazz Bean类型
     * @param <T>   Bean泛型
     * @return Bean实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 根据Bean的类型从Bean容器中获取全部Bean实例，此Bean类型为接口或者父类
     *
     * @param clazz Bean类型
     * @param <T>   Bean泛型
     * @return 该Bean类型的全部Bean实例
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return context.getBeansOfType(clazz);
    }

    /**
     * 获取被给定注解类型注解的Bean实例
     *
     * @param annotationType 注解类型
     * @return 该注解类型注解的全部Bean实例
     */
    public Map<String, Object> findBeansOfAnnotation(Class<? extends Annotation> annotationType) {
        return context.getBeansWithAnnotation(annotationType);
    }

    /**
     * 根据Bean的类型从Bean容器中获取一个Bean实例，非静态方法，通过本类实例调用
     *
     * @param clazz Bean类型
     * @param <T>   Bean泛型
     * @return Bean实例
     */
    public <T> T findBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 根据Bean的类型从Bean容器中获取全部Bean实例，此Bean类型为接口或者父类，非静态方法，通过本类实例调用
     *
     * @param clazz Bean类型
     * @param <T>   Bean泛型
     * @return 该Bean类型的全部Bean实例
     */
    public <T> Map<String, T> findBeansOfType(Class<T> clazz) {
        return context.getBeansOfType(clazz);
    }

}
