package org.dshubs.odc.workflow.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.workflow.common.constant.CacheConstants;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限获取工具类
 *
 * @author 湖南牛数商智信息科技有限公司
 */
public class SecurityUtils
{
    /**
     * 获取用户
     */
    public static String getUsername()
    {
    	if ( ServletUtils.getRequest()!=null) {
    		  String username = ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USERNAME);
    		  return ServletUtils.urlDecode(username);
		}

    	else return "9999";
    }

    /**
     * 获取昵称
     */
    public static String getNickName()
    {
        String nickName = ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_NICKNAME);
        if(StringUtils.isEmpty(nickName)){
            return "-";
        }
        return ServletUtils.urlDecode(nickName);
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId()
    {
    	if ( ServletUtils.getRequest()!=null) {
            return Convert.toLong(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USER_ID));
		}
    	else return 1l;

    }

    /**
     * 获取用户ID
     */
    public static Long getParentDeptId()
    {
        if ( ServletUtils.getRequest()!=null) {
            return Convert.toLong(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_PARENT_DEPT_ID));
        }
        else return 1l;

    }


    /**
     * 获取请求token
     */
    public static String getToken()
    {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request)
    {
        String token = ServletUtils.getRequest().getHeader(CacheConstants.HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX))
        {
            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
