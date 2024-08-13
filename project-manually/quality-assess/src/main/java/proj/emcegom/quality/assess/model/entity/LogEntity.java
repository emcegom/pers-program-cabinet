package proj.emcegom.quality.assess.model.entity;


import lombok.*;
import proj.emcegom.quality.assess.enums.BusinessType;
import proj.emcegom.quality.assess.utils.JacksonUtil;

import java.lang.reflect.Method;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LogEntity {
    String method;
    Long cost;
    BusinessType businessType;
    String title;
    Object inputParam;
    Object outputParam;

    public String toString() {
        return JacksonUtil.toJSONString(this);
    }
}
