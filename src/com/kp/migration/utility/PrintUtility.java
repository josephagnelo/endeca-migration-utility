package com.kp.migration.utility;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class PrintUtility {
	private static final char DEFAULT_SEPARATOR = ',';
	public static void writePlainLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(value);
            } else {
                sb.append(customQuote).append(value).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }
}
