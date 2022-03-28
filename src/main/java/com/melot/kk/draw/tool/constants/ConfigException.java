package com.melot.kk.draw.tool.constants;

/**
 * ConfigException.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.constants-ConfigException
 * date: 2022-3-28 14:42
 * version: 1.0.0
 */
public class ConfigException extends Exception{

    public ConfigException(String msg) {
        super(msg);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }

    public ConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ConfigException() {
    }
}
