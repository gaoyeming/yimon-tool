package org.yimon.tool.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: ym.gao
 * @description: 基础对象类
 * @date: 2023/11/20 14:43
 */
public abstract class ABasePojo implements Serializable {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" [");

        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // 按字段名进行排序
        Arrays.sort(fields, Comparator.comparing(Field::getName));
        // 打印属性值
        for (Field field : fields) {
            if (field.getName().contains("$")) {
                // Reject field from inner class.
                continue;
            }
            if (Modifier.isTransient(field.getModifiers())) {
                // Reject transient fields.
                continue;
            }
            if (Modifier.isStatic(field.getModifiers())) {
                // Reject static fields.
                continue;
            }

            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue;
            try {
                fieldValue = field.get(this);
                if (fieldValue == null) {
                    fieldValue = "<null>";
                }
            } catch (IllegalAccessException e) {
                fieldValue = "N/A";
            }
            sb.append(fieldName).append("=").append(fieldValue).append(", ");
        }
        // 移除最后一个逗号和空格
        if (fields.length > 0) {
            sb.setLength(sb.length() - 2);
        }

        sb.append("]");
        return sb.toString();
    }
}
