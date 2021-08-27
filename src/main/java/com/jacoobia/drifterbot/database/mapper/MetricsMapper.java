package com.jacoobia.drifterbot.database.mapper;

import com.jacoobia.drifterbot.database.entity.Metrics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * The mapper for our basic table operations for the {@link Metrics} table
 */
@Mapper
public interface MetricsMapper {

    @Select("select * from drifter_metrics where id = #{id}")
    Metrics findById(Integer id);

    @Select("select * from drifter_metrics")
    List<Metrics> findAll();

    @Update("update drifter_metrics set name=#{name}, value=#{value} where id=#{id}")
    void update(Metrics metric);

}
