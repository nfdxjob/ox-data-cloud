package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dshubs.odc.domain.entity.UserEmployee;


/**
 * 用户员工数据访问层
 *
 * @author wangxian 2022-03-04
 */
@Mapper
public interface UserEmployeeMapper extends BaseMapper<UserEmployee> {

    /**
     * 根据员工ID查询是否存在用户
     *
     * @param employeeId 员工ID
     * @return UserEmployee
     */
    UserEmployee selectByEmployeeId(@Param("employeeId") Long employeeId);


    /**
     * 根据用户ID查询是否存在员工
     *
     * @param userId 用户ID
     * @return UserEmployee
     */
    UserEmployee selectByUserId(@Param("userId") Long userId);
}
