package com.lyc.dao;

import com.lyc.sys.Base;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseDao<T extends Base> {

    @Resource
    public SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    public <E> List<E> query(String sqlmapper, Object obj) {
        List<E> beans = getSqlSession().selectList(sqlmapper, obj);
        return beans;
    }

    public <E> List<E> query(String sqlmapper) {
        List<E> beans = getSqlSession().selectList(sqlmapper);
        return beans;
    }

    public <E> E queryOne(String sqlmapper) {
        return getSqlSession().selectOne(sqlmapper);
    }
    public <E> E queryOne(String sqlmapper,Object object) {
        return getSqlSession().selectOne(sqlmapper,object);
    }

    public boolean add(String nameSpec, Object object) {
        return getSqlSession().insert(nameSpec, object) > 0;
    }

    public boolean add(String nameSpec) {
        return add(nameSpec,null);
    }
}