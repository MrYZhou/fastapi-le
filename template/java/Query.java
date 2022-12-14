import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

{% set table = config.table|capitalize %}

@Data
@EqualsAndHashCode(callSuper = false)
public class {{table}}Query extends Page<{{table}}Entity> {
    {% for item in config.list -%}
    {% set fieldName = item.columnName|replace(config.fieldPrefix, "") -%}
    {% if fieldName in  config.searchList -%}
    /**{{ item.columnComment  }}*/
    {% if item.dataType == 'datetime' -%}
    private Date {{fieldName}};
    {% elif item.dataType == 'decimal' -%}
    private BigDecimal {{fieldName}};
    {% else -%}
    private String {{fieldName}};
    {% endif -%}
    {% endif -%}
    {% endfor %}
}