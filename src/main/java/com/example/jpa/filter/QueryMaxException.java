package com.example.jpa.filter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Created by wangcl on 2017/8/11.
 */
public class QueryMaxException extends SQLException {

    private static final long serialVersionUID = 1L;
    private static final Map<String, String> I18N = new HashMap<>();
    static{
        I18N.put(Locale.CHINA.getLanguage(), "查询结果数量超过上限: ");
        I18N.put(Locale.ENGLISH.getLanguage(), "The results size is larger than ");
        I18N.put(Locale.FRANCE.getLanguage(), "The results size is larger than ");
    }
    private final String[] args;

    public QueryMaxException(String reason, Throwable cause, String... args) {
        super(reason, cause);
        this.args = args;
    }

    public QueryMaxException(String reason, String... args) {
        super(reason);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String getI18n(Locale locale) {
        String maxQuerySize = "20000";
        if (null == args || args.length == 0) {
            maxQuerySize = args[0];
        }
        locale = Optional.ofNullable(locale).orElse(Locale.ENGLISH);
        return Optional.ofNullable(I18N.get(locale.getLanguage()) + maxQuerySize).orElse("The results size is larger " +
                "than " + maxQuerySize);

    }

}
