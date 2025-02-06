package com.example.publicspring.windLien.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;

import java.util.List;

public class SqlUtil {
    public static boolean isSelectSqlByParse(String sql) {
        if (StrUtil.isBlank(sql)) {
            return false;
        }
        // 使用 Druid 解析 SQL
        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, DbType.mock);
        for (SQLStatement statement : statementList) {
            if (statement instanceof SQLSelectStatement) {
                return true;
            }
        }
        return false;
    }
}
