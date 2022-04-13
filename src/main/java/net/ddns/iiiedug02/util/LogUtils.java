package net.ddns.iiiedug02.util;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
	private static final Logger logger = LogManager.getLogger(LogUtils.class);

	public enum LogType {
		Debug, Info, Error, Trace, Warn
	}

	private static final String genLogFormat(final String tag, final String message) {
		return String.format("[%1$s] %2$s", tag, message);
	}

	public static final void d(final String tag, final String message) {
		logger.debug(genLogFormat(tag, message));
	}

	public static final void i(final String tag, final String message) {
		logger.info(genLogFormat(tag, message));
	}

	public static final void e(final String tag, final String message) {
		logger.error(genLogFormat(tag, message));
	}

	public static final void t(final String tag, final String message) {
		logger.trace(genLogFormat(tag, message));
	}

	public static final void w(final String tag, final String message) {
		logger.warn(genLogFormat(tag, message));
	}

	public static final void data(@NotNull LogType type, @NotNull final String tag,
			@NotNull final Map<String, String> data) {
		for (String key : data.keySet()) {
			final String msg = String.format("key: %1$s, value: %2$s", key, data.get(key));
			switch (type) {
			case Info:
				i(tag, msg);
				break;
			case Error:
				e(tag, msg);
				break;
			case Trace:
				t(tag, msg);
				break;
			case Warn:
				w(tag, msg);
				break;
			default:
				d(tag, msg);
				break;
			}
		}
	}
}