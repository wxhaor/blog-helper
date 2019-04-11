package com.wxhao.blog.server.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxhao.blog.server.boot.component.SnowflakeIdWorker;
import com.wxhao.blog.server.boot.helper.ApplicationContextHelper;
import com.wxhao.blog.server.mapper.base.HaoBaseMapper;
import com.wxhao.blog.server.po.base.BaseCreateTimePO;
import com.wxhao.blog.server.po.base.BaseIdPO;
import com.wxhao.blog.server.po.base.BaseUpdateTimePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author wxhao
 * @date 2018/12/11
 */
public class HaoBaseService<M extends HaoBaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T> {

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Override
    @Transactional
    public boolean save(T entity) {
        initEntity(entity);
        return super.save(entity);
    }

    @Transactional
    public T saveGet(T entity) {
        HaoBaseService<M, T> service = ApplicationContextHelper.getBean(this.getClass());
        T initEntity = initEntity(entity);
        boolean b = service.save(entity);
        if (b) {
            return initEntity;
        }
        return null;
    }

    private T initEntity(T entity) {
        long id = idWorker.nextId();
        if (entity instanceof BaseUpdateTimePO) {
            ((BaseUpdateTimePO) entity).setCreateTime(new Date());
            ((BaseUpdateTimePO) entity).setUpdateTime(new Date());
            ((BaseUpdateTimePO) entity).setId(id);
        } else if (entity instanceof BaseCreateTimePO) {
            ((BaseCreateTimePO) entity).setCreateTime(new Date());
            ((BaseCreateTimePO) entity).setId(id);
        } else if (entity instanceof BaseIdPO) {
            ((BaseIdPO) entity).setId(id);
        }
        return entity;
    }

}
