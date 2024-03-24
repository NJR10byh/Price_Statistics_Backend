package tutu.njr10byh.price_statistics.config;

/**
 * @author baoyuhao
 * @version [1.0]
 * @date 2023/2/24 10:53:30
 * @description
 */

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Configuration;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TypeHandleConfig extends BaseTypeHandler<List> {

    /**
     * 入库前执行
     * 设置不为null的参数类型转化
     *
     * @param preparedStatement
     * @param i
     * @param list
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType)
            throws SQLException {
        if (CollectionUtils.isNotEmpty(list)) {
            preparedStatement.setString(i, String.join(",", list));
        } else {
            preparedStatement.setString(i, null);
        }
    }

    /**
     * 从数据库中获取到数据时执行
     * 将获取到的参数进行类型转化
     *
     * @param resultSet
     * @param str
     * @return
     * @throws SQLException
     */
    @Override
    public List getNullableResult(ResultSet resultSet, String str)
            throws SQLException {
        String result = resultSet.getString(str);
        return result == null ? new ArrayList<>() : Arrays.asList(result.split(","));
    }

    @Override
    public List getNullableResult(ResultSet resultSet, int i)
            throws SQLException {
        String result = resultSet.getString(i);
        return result == null ? new ArrayList<>() : JSONArray.parseArray(result);
    }

    @Override
    public List getNullableResult(CallableStatement callableStatement, int i)
            throws SQLException {
        String result = callableStatement.getString(i);
        return result == null ? new ArrayList<>() : JSONArray.parseArray(result);
    }
}


