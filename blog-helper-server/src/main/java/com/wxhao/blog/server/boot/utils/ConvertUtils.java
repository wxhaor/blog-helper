package com.wxhao.blog.server.boot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * 转换工具类
 *
 * @author lile
 */
@Slf4j
public abstract class ConvertUtils {

    /**
     * 转换单个对象
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 目标对象
     */
    public static <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = newInstance(targetClass);

        return copyProperties(source, target);
    }

    /**
     * 转换对象列表
     *
     * @param source      源对象数组
     * @param targetClass 目标类型
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 目标对象列表
     */
    public static <S, T> List<T> convert(List<S> source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        if (source.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return source.stream().map(s -> convert(s, targetClass)).collect(Collectors.toList());
    }

    public static <S, T> List<T> convertAfter(List<S> source, Class<T> targetClass, BiConsumer<S, T> biConsumer) {
        if (source == null) {
            return null;
        }
        if (source.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<T> targetList = new ArrayList<>();
        for (S s : source) {
            T t = convert(s, targetClass);
            biConsumer.accept(s, t);
            targetList.add(t);
        }
        return targetList;
    }


    /**
     * 复制属性， 采用Spring BeanUtils
     * 只复制： 类型&属性名都一致的， 其他的通通忽略
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源类型
     * @param <T>    目标类型
     * @return 目标对象
     */
    public static <S, T> T copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }


    /**
     * 根据Class信息创建实例
     *
     * @param type Class类型信息
     * @param <T>  类型
     * @return 实例
     * @throws RuntimeException 构造器调用失败
     */
    public static <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
