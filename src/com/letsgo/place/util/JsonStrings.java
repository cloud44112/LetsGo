package com.letsgo.place.util;

public final class JsonStrings {

	private JsonStrings() {
	}

	public static String quotedValue(String s) {
		if (s == null) {
			return "\"\"";
		}
		StringBuilder out = new StringBuilder(s.length() + 8);
		out.append('"');
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\\':
				out.append("\\\\");
				break;
			case '"':
				out.append("\\\"");
				break;
			case '\n':
				out.append("\\n");
				break;
			case '\r':
				break;
			case '\t':
				out.append("\\t");
				break;
			default:
				if (c < 0x20) {
					out.append(String.format("\\u%04x", (int) c));
				} else {
					out.append(c);
				}
			}
		}
		out.append('"');
		return out.toString();
	}
}
