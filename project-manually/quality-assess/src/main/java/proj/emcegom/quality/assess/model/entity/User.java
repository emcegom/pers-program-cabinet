package proj.emcegom.quality.assess.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author otis
 * @since 2024-08-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {


    @Serial
    private static final long serialVersionUID = -7276751065968371244L;
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("USERNAME")
    private String username;

    @TableField("NAME")
    private String name;

    @TableField("PASSWORD")
    private String password;

    @TableField("EMAIL")
    private String email;

    @TableField("ROLE")
    private Byte role;

    @TableField("IS_DELETED")
    private Byte isDeleted;

    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;
}
