package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dshubs.odc.api.vo.TableInfoGenerateQuery;
import org.dshubs.odc.domain.entity.generate.TableColumn;
import org.dshubs.odc.domain.entity.generate.TableInfo;

import java.util.List;

/**
 * 用于代码生成
 *
 * @author wangxian 2021/3/16
 */
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfo> {

    /**
     * 分页查询数据库表
     *
     * @param page          分页参数
     * @param generateQuery 查询模型
     * @return Page
     */
    Page<TableInfo> pageQuery(Page<?> page, @Param("params") TableInfoGenerateQuery generateQuery);

    /**
     * 根据表名获取table基础信息
     * @param tableName 表名
     * @return TableInfo
     */
    TableInfo getTableByName(@Param("tableName")String tableName);

    /**
     * 获取表的所有列
     * @param tableName 表名
     * @return List
     */

    List<TableColumn> listTableColumnByTableName(@Param("tableName")String tableName);


}
