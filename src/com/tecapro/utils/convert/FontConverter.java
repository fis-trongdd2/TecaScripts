package com.tecapro.utils.convert;

import java.util.HashMap;
import java.util.Map;


public class FontConverter {
	static Map<String, ConvertType> CACHE_TYPES = new HashMap<String, ConvertType>();

	public static FontType findFontType(String str) {
		FontType current = null;
		int count = 0;
		FontDetect fd = new FontDetect();
		for (FontType temp : FontType.SPECIAL_FONTS) {
			findFontType(temp, fd, str);
			if (fd.isSpecial()) {
				return temp;
			}
			if (fd.getCount() > count) {
				count = fd.getCount();
				current = temp;
			}
		}

		if (current == null) {
			return FontType.UNICODE;
		}
		return current;
	}

	/**
	 * @return number of match
	 */
	private static void findFontType(FontType type, FontDetect fd, String str) {
		char[] chs = str.toCharArray();

		for (int i = 0; i < chs.length;) {
			int max = type.getMaxChar();
			String ch = String.valueOf(chs[i]);
			if (max == 1) {
				if (type.getFontCharaters().contains(ch)) {
					fd.next();
				}
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(ch);
				for (int j = 1; j < max; j++) {
					if (i + j < chs.length) {
						sb.append(chs[i + j]);
					}
				}
				if (type.getFontCharaters().contains(sb.toString())) {
					fd.setSpecial(true);
					break;
				} else if (type.getFontCharaters().contains(ch)) {
					fd.next();
				}
			}
			i++;
		}
	}

	public static String toASCII(String str) {
		return convert(FontType.UNKNOW, FontType.ACSII, str);
	}

	public static String toUnicode(String str) {
		return convert(FontType.UNKNOW, FontType.UNICODE, str);
	}

	/**
	 *
	 * @param fromType
	 *            if it is UNKNOW will automatically search on source string
	 * @param toType
	 * @param str
	 * @return
	 */
	public static String convert(FontType fromType, FontType toType, String str) {
		if (str == null) {
			return "";
		}

		// Find font if unknow
		if (FontType.UNKNOW.equals(fromType)) {
			fromType = findFontType(str);
		}

		if (fromType.equals(toType)) {
			return str;
		}

		// Cache it
		String key = fromType.getName() + '-' + toType.getName();
		ConvertType ct = CACHE_TYPES.get(key);
		if (ct == null) {
			ct = new ConvertType(fromType, toType);
			CACHE_TYPES.put(key, ct);
		}

		char[] chs = str.toCharArray();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chs.length;) {
			int max = fromType.getMaxChar();
			String ch = String.valueOf(chs[i]);

			if (max == 1) {
				if (!ct.convert(ch, sb)) {
					sb.append(ch);
				}
			} else {
				StringBuilder s2 = new StringBuilder();
				s2.append(ch);
				for (int j = 1; j < max; j++) {
					if (i + j < chs.length) {
						s2.append(chs[i + j]);
					}
				}
				if (ct.convert(s2.toString(), sb)) {
					i += max - 1;
				} else if (!ct.convert(ch, sb)) {
					sb.append(ch);
				}
			}
			i++;
		}
		return sb.toString();
	}
}
