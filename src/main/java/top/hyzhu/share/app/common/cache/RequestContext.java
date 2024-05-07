package top.hyzhu.share.app.common.cache;

import io.swagger.v3.oas.models.security.SecurityScheme;
import top.hyzhu.share.app.common.constant.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhy
 * @createTime: 2024/05/07
 * @description:
 **/
public class RequestContext {
    private static final ThreadLocal<Map<Object, Object>> RESOURCES = new InheritableThreadLocalMap<>();
    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            RESOURCES.get().remove(key);
            return;
        }
        RESOURCES.get().put(key, value);
    }
    public static Integer getUserId() {
        Object result = get(Constant.USER_ID);
        if (result == null) {
            throw new IllegalArgumentException("user id cannnot be null");
        }
        return Integer.valueOf(String.valueOf(result));
    }

    public static Object get(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        return RESOURCES.get().get(key);
    }

    public static void clear(){
        RESOURCES.remove();
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object,
            Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        @Override
        protected Map<Object, Object> initialValue() {
            return new HashMap<>();
        }
        @Override
        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            if (parentValue != null) {
                return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
            }else {
                return null;
            }
        }
    }
}

