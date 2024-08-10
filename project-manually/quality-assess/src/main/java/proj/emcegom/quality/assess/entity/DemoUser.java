package proj.emcegom.quality.assess.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author otis
 * @since 2024-08-09
 */
@Getter
@Setter
@Builder
@ToString
@TableName("demo_user")
public class DemoUser implements Serializable {


    @Serial
    private static final long serialVersionUID = 10907223255790733L;

    @TableId("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("created_at")
    private LocalDateTime createdAt;
}
