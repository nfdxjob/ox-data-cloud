package org.dshubs.odc.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dshubs.odc.workflow.common.model.BaseEntity;

import javax.validation.constraints.NotNull;

@Data
@TableName("T_FLOWABLE_FORM")
public class FlowableForm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @NotNull
    private String formKey;

    @NotNull
    private String formName;

    private String formJson;
}
