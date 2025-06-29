package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SysUserRole;

/**
 * 用户与角色关联表 数据层
 *
 * @author ruoyi
 * @author Bruce
 */
public interface SysUserRoleMapper
{
    // ================= ruoyi 原生方法 =================

    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    int batchUserRole(List<SysUserRole> userRoleList);

    /**
     * 删除用户和角色关联信息
     *
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    int deleteUserRoleInfo(SysUserRole userRole);

    /**
     * 批量取消授权用户角色
     *
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

    /**
     * 根据账号和角色id创建用户和角色关联信息
     *
     * @param userName 账号
     * @param roleId   角色id
     * @return 结果
     */
    int insertUserRoleByUserNameAndRoleId(@Param("userName") String userName, @Param("roleId") Long roleId);

    // ================= Bruce 新增方法 =================

    /**
     * 查询用户和角色关联
     *
     * @param userId 用户和角色关联主键
     * @return 用户和角色关联
     */
    SysUserRole selectSysUserRoleByUserId(Long userId);

    /**
     * 查询用户和角色关联列表
     *
     * @param sysUserRole 用户和角色关联
     * @return 用户和角色关联集合
     */
    List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole);

    /**
     * 新增用户和角色关联
     *
     * @param sysUserRole 用户和角色关联
     * @return 结果
     */
    int insertSysUserRole(SysUserRole sysUserRole);

    /**
     * 修改用户和角色关联
     *
     * @param sysUserRole 用户和角色关联
     * @return 结果
     */
    int updateSysUserRole(SysUserRole sysUserRole);

    /**
     * 删除用户和角色关联
     *
     * @param userId 用户和角色关联主键
     * @return 结果
     */
    int deleteSysUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysUserRoleByUserIds(Long[] userIds);
}