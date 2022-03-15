package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dshubs.odc.domain.entity.OauthUser;


/**
* 用户信息数据访问层
*
* @author wangxian 2022-03-04
*/
@Mapper
public interface OauthUserMapper extends BaseMapper<OauthUser> {

    /**
     * select user by username or email
     * @param username username or email
     * @return OauthUser
     */
    OauthUser selectByUsernameOrEmail(@Param("username") String username);
}
