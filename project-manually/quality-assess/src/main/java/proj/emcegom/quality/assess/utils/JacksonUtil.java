package proj.emcegom.quality.assess.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import proj.emcegom.quality.assess.common.Constant;
import proj.emcegom.quality.assess.exception.JsonCastException;

import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;


public class JacksonUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(Constant.DATE_FORMAT));
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static <T> T parseObject(String str, Class<T> clazz) {
        return attemptCast(() -> OBJECT_MAPPER.readValue(str, clazz));
    }

    public static <T> T parseObject(String str, TypeReference<T> reference) {
        return attemptCast(() -> OBJECT_MAPPER.readValue(str, reference));
    }

    public static JsonNode parseJSONObject(String str) {
        return attemptCast(() -> OBJECT_MAPPER.readTree(str));
    }

    public static JsonNode parseJSONObject(Object obj) {
        return attemptCast(() -> OBJECT_MAPPER.valueToTree(obj));
    }


    public static String toJSONString(Object object) {
        return attemptCast(() -> OBJECT_MAPPER.writeValueAsString(object));
    }

    public static String getString(JsonNode json, String key) {
        return attemptCast(() -> json.get(key).asText());
    }

    public static int getInteger(JsonNode json, String key) {
        return attemptCast(() -> json.get(key).asInt());
    }

    public static boolean getBoolean(JsonNode json, String key) {
        return attemptCast(() -> json.get(key).asBoolean());
    }

    public static JsonNode getJSONObject(JsonNode json, String key) {
        return attemptCast(() -> json.get(key));
    }

    public static ObjectNode buildJSONObject() {
        return attemptCast(OBJECT_MAPPER::createObjectNode);
    }

    public static ArrayNode buildJSONArray() {
        return attemptCast(OBJECT_MAPPER::createArrayNode);
    }

    public static <T> T attemptCast(Callable<T> parser, Class<? extends Exception> check) {
        try {
            return parser.call();
        } catch (Exception ex) {
            if (check.isAssignableFrom(ex.getClass())) {
                throw new JsonCastException("Json process exception", ex);
            }
            throw new IllegalStateException(ex);
        }
    }

    public static <T> T attemptCast(Callable<T> parser) {
        return attemptCast(parser, JacksonException.class);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

}
