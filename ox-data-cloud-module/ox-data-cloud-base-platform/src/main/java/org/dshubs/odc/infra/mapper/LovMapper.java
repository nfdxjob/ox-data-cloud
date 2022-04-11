package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dshubs.odc.domain.entity.Lov;


/**
 * 值集数据访问层
 *
 * @author wangxian 2022-04-07
 */
@Mapper
public interface LovMapper extends BaseMapper<Lov> {

    /**
     * 根据值集编码查询值集
     *
     * @param lovCode 值集编码
     * @return Lov
     */
    Lov selectByLovCode(@Param("lovCode") String lovCode);
}
