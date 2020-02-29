package com.api.core.service;


import com.api.core.ApiMapper;
import com.api.core.ServiceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected ApiMapper<T> apiMapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T model) {
        apiMapper.insertSelective(model);
    }

    @Override
    public void save(List<T> models) {
        apiMapper.insertList(models);
    }

    @Override
    public void deleteById(Object id) {
        apiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(String ids) {
        apiMapper.deleteByIds(ids);
    }

    @Override
    public void update(T model) {
        apiMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(Object id) {
        return apiMapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return apiMapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByIds(String ids) {
        return apiMapper.selectByIds(ids);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return apiMapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return apiMapper.selectAll();
    }
}
