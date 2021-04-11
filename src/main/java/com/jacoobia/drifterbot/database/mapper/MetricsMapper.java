package com.jacoobia.drifterbot.database.mapper;

import com.jacoobia.drifterbot.database.entity.Metrics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MetricsMapper
{

    @Select("select * from drifter_metrics where name = #{name}")
    Metrics findByName(String name);

    @Select("select * from drifter_metrics where id = #{id}")
    Metrics findById(Integer id);

    @Select("select * from drifter_metrics")
    List<Metrics> findAll();

    @Update("update drifter_metrics set name=#{name}, value=#{value} where id=#{id}")
    int update(Metrics metric);
}
