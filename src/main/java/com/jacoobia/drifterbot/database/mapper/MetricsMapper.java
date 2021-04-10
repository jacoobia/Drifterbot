package com.jacoobia.drifterbot.database.mapper;

import com.jacoobia.drifterbot.database.entity.Metrics;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MetricsMapper
{

    @Select("select * from drifter_metrics where name = #{name}")
    Metrics selectMetricByName(String name);

    @Update("update drifter_metrics set name = #{name},  value = #{value}) where id = #{id}")
    void save(Metrics metrics);

}
