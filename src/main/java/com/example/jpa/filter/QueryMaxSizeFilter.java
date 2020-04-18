package com.example.jpa.filter;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.jdbc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by wangcl on 2017/8/9.
 */
public class QueryMaxSizeFilter extends FilterAdapter {
    private ThreadLocal<Long> resultSetFetchedSize = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryMaxSizeFilter.class);
    private static final String SYSTEM_MAX_QUERY_SIZE = "system_max_query_size";
    private static final Long MAX_QUERY_NUMBER = 20000L;

    @Override
    public boolean resultSet_next(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        boolean result = super.resultSet_next(chain, resultSet);
        if (null != resultSetFetchedSize.get() /*&& isSupportQueryMaxNumberLimit()*/) {

            Long maxQuerySize = getMaxQuerySize();

            if (resultSetFetchedSize.get() > 5000 && resultSetFetchedSize.get() % 5000 == 0) {
                LOGGER.warn("QueryMaxSizeFilter find fetch size " + resultSetFetchedSize.get() + " greater than 5000 record! sql is [" + resultSet.getSql() + "]");
            }

            if (resultSetFetchedSize.get() > maxQuerySize) {
                Map<Integer, JdbcParameter> parameterMap = resultSet.getStatementProxy().getParameters();
                List<Object> parameters = new ArrayList<>();
                if (null != parameterMap && null != parameterMap.values()) {
                    parameters = parameterMap.values().stream().map(JdbcParameter::getValue).collect(Collectors.toList());
                }
                LOGGER.error("Durid.query.result.size.larger.than {}, and the sql is {}, the parameter is {}, invoke stack is: {}",
                        maxQuerySize, resultSet.getSql(), parameters.toString(),getStack());
                throw new QueryMaxException("Durid.query.results.larger.than.system.default.size", maxQuerySize.toString());
            }
            resultSetFetchedSize.set(resultSetFetchedSize.get() + 1);
        }
        return result;
    }

//    private boolean isSupportQueryMaxNumberLimit() {
//        return null == QueryMaxNumberConfigInfo.get() || !QueryMaxNumberConfigInfo.get().isIgnore();
//    }

    private Long getMaxQuerySize() {
        Long maxQuerySize = MAX_QUERY_NUMBER;
//        final BossCommonConfig configInstance = BossCommonConfig.getInstance();
//        try {
//            if (null != QueryMaxNumberConfigInfo.get() && null !=
//                    QueryMaxNumberConfigInfo.get().getMaxQueryNumber()) {
//                maxQuerySize = QueryMaxNumberConfigInfo.get().getMaxQueryNumber();
//                LOGGER.trace("Durid.the.parameter [maxQuerySize] value is :[{}] from [{}]", configInstance
//                                .getSystem_max_query_size(), "QueryMaxNumberConfigInfo");
//            } else {
//                maxQuerySize = configInstance.getSystem_max_query_size();
//                LOGGER.trace("Durid.the.parameter [maxQuerySize] value is :[{}] from [{}]", maxQuerySize, "QueryMaxNumberConfigInfo");
//            }
//        } catch (Exception e) {
//            LOGGER.error("Durid.get.maxquerySize.error", e);
//        }
        return maxQuerySize;
    }

    private String getStack() {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stringBuilder.append("\t").append(element).append("\n");
        }
        return stringBuilder.toString();
    }
    @Override
    public ResultSetProxy preparedStatement_executeQuery(FilterChain chain, PreparedStatementProxy statement) throws
            SQLException {
        resultSetFetchedSize.set(0L);
        return chain.preparedStatement_executeQuery(statement);
    }

    @Override
    public ResultSetProxy statement_executeQuery(FilterChain chain, StatementProxy statement, String sql) throws
            SQLException {
        resultSetFetchedSize.set(0L);
        return super.statement_executeQuery(chain, statement, sql);
    }

    @Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        resultSetFetchedSize.set(0L);
        return super.preparedStatement_execute(chain, statement);
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
        resultSetFetchedSize.set(0L);
        return super.statement_execute(chain, statement, sql);
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql, int[] columnIndexes)
            throws SQLException {
        resultSetFetchedSize.set(0L);
        return super.statement_execute(chain, statement, sql, columnIndexes);
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql, int autoGeneratedKeys)
            throws SQLException {
        resultSetFetchedSize.set(0L);
        return super.statement_execute(chain, statement, sql, autoGeneratedKeys);
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql, String[] columnNames)
            throws SQLException {
        resultSetFetchedSize.set(0L);
        return super.statement_execute(chain, statement, sql, columnNames);
    }

    @Override
    public void dataSource_releaseConnection(FilterChain chain, DruidPooledConnection connection) throws SQLException {
        LOGGER.trace("connection:::" + connection.toString() + ":::::returned");
        resultSetFetchedSize.set(0L);
        super.dataSource_releaseConnection(chain, connection);
    }

    @Override
    public DruidPooledConnection dataSource_getConnection(FilterChain chain, DruidDataSource dataSource, long
            maxWaitMillis) throws SQLException {
        DruidPooledConnection connection = super.dataSource_getConnection(chain, dataSource, maxWaitMillis);
        LOGGER.trace("get connection:::" + connection.toString() + ":::::success");
        resultSetFetchedSize.set(0L);
        return connection;
    }

    @Override
    public ConnectionProxy connection_connect(FilterChain chain, Properties info) throws SQLException {
        ConnectionProxy connection = super.connection_connect(chain, info);
        LOGGER.trace("create connection:::" + connection.toString() + ":::::success");
        resultSetFetchedSize.set(0L);
        return connection;
    }
}
