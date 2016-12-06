package com.tecapro.utils.convert;

import java.util.ArrayList;
import java.util.List;

public class FontType {
	static final List<FontType> SPECIAL_FONTS = new ArrayList<FontType>();

	private String name = "UNKNOW";
	private String[] symbols = null;
	// Use to find font
	private final List<String> fontCharaters = new ArrayList<String>();
	private int maxChar = 1;

	public static final FontType UNKNOW;

	public static final FontType ACSII;

	public static final FontType UNICODE;

	public static final FontType UNICODETH;

	public static final FontType VNI_WINDOWS;

	public static final FontType TCVN3;

	static {
		UNKNOW = new FontType();

		ACSII = new FontType("ACSII", new String[] { "﻿a", "a", "a", "a", "a",
				"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
				"a", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o",
				"o", "o", "o", "o", "o", "o", "o", "e", "e", "e", "e", "e",
				"e", "e", "e", "e", "e", "e", "e", "i", "i", "i", "i", "i",
				"i", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u",
				"u", "y", "y", "y", "y", "y", "y", "d", "A", "A", "A", "A",
				"A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
				"A", "A", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O",
				"O", "O", "O", "O", "O", "O", "O", "O", "E", "E", "E", "E",
				"E", "E", "E", "E", "E", "E", "E", "E", "I", "I", "I", "I",
				"I", "I", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U",
				"U", "U", "Y", "Y", "Y", "Y", "Y", "Y", "D" });

		UNICODE = new FontType("UNICODE", new String[] { "﻿a", "á", "à", "ả",
				"ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ", "â", "ấ", "ầ", "ẩ",
				"ẫ", "ậ", "o", "ó", "ò", "ỏ", "õ", "ọ", "ô", "ố", "ồ", "ổ",
				"ỗ", "ộ", "ơ", "ớ", "ờ", "ở", "ỡ", "ợ", "e", "é", "è", "ẻ",
				"ẽ", "ẹ", "ê", "ế", "ề", "ể", "ễ", "ệ", "i", "í", "ì", "ỉ",
				"ĩ", "ị", "u", "ú", "ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử",
				"ữ", "ự", "y", "ý", "ỳ", "ỷ", "ỹ", "ỵ", "đ", "A", "Á", "À",
				"Ả", "Ã", "Ạ", "Ă", "Ắ", "Ằ", "Ẳ", "Ẵ", "Ặ", "Â", "Ấ", "Ầ",
				"Ẩ", "Ẫ", "Ậ", "O", "Ó", "Ò", "Ỏ", "Õ", "Ọ", "Ô", "Ố", "Ồ",
				"Ổ", "Ỗ", "Ộ", "Ơ", "Ớ", "Ờ", "Ở", "Ỡ", "Ợ", "E", "É", "È",
				"Ẻ", "Ẽ", "Ẹ", "Ê", "Ế", "Ề", "Ể", "Ễ", "Ệ", "I", "Í", "Ì",
				"Ỉ", "Ĩ", "Ị", "U", "Ú", "Ù", "Ủ", "Ũ", "Ụ", "Ư", "Ứ", "Ừ",
				"Ử", "Ữ", "Ự", "Y", "Ý", "Ỳ", "Ỷ", "Ỹ", "Ỵ", "Đ" });

		UNICODETH = toSpecialFont("UNICODETH", 2, new String[] { "﻿a", "á",
				"à", "ả", "ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ", "â",
				"ấ", "ầ", "ẩ", "ẫ", "ậ", "o", "ó", "ò", "ỏ", "õ",
				"ọ", "ô", "ố", "ồ", "ổ", "ỗ", "ộ", "ơ", "ớ", "ờ", "ở",
				"ỡ", "ợ", "e", "é", "è", "ẻ", "ẽ", "ẹ", "ê", "ế", "ề",
				"ể", "ễ", "ệ", "i", "í", "ì", "ỉ", "ĩ", "ị", "u", "ú",
				"ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử", "ữ", "ự", "y",
				"ý", "ỳ", "ỷ", "ỹ", "ỵ", "đ", "﻿A", "Á", "À", "Ả",
				"Ã", "Ạ", "Ă", "Ắ", "Ằ", "Ẳ", "Ẵ", "Ặ", "Â", "Ấ", "Ầ",
				"Ẩ", "Ẫ", "Ậ", "O", "Ó", "Ò", "Ỏ", "Õ", "Ọ", "Ô", "Ố",
				"Ồ", "Ổ", "Ỗ", "Ộ", "Ơ", "Ớ", "Ờ", "Ở", "Ỡ", "Ợ", "E",
				"É", "È", "Ẻ", "Ẽ", "Ẹ", "Ê", "Ế", "Ề", "Ể", "Ễ",
				"Ệ", "I", "Í", "Ì", "Ỉ", "Ĩ", "Ị", "U", "Ú", "Ù", "Ủ",
				"Ũ", "Ụ", "Ư", "Ứ", "Ừ", "Ử", "Ữ", "Ự", "Y", "Ý", "Ỳ",
				"Ỷ", "Ỹ", "Ỵ", "Đ" });

		VNI_WINDOWS = toSpecialFont("VNI_WINDOWS", 2, new String[] { "﻿a",
				"aù", "aø", "aû", "aõ", "aï", "aê", "aé", "aè", "aú", "aü",
				"aë", "aâ", "aá", "aà", "aå", "aã", "aä", "o", "où", "oø",
				"oû", "oõ", "oï", "oâ", "oá", "oà", "oå", "oã", "oä", "ô",
				"ôù", "ôø", "ôû", "ôõ", "ôï", "e", "eù", "eø", "eû", "eõ",
				"eï", "eâ", "eá", "eà", "eå", "eã", "eä", "i", "í", "ì", "æ",
				"ó", "ò", "u", "uù", "uø", "uû", "uõ", "uï", "ö", "öù", "öø",
				"öû", "öõ", "öï", "y", "yù", "yø", "yû", "yõ", "î", "ñ", "A",
				"AÙ", "AØ", "AÛ", "AÕ", "AÏ", "AÊ", "AÉ", "AÈ", "AÚ", "AÜ",
				"AË", "AÂ", "AÁ", "AÀ", "AÅ", "AÃ", "AÄ", "O", "OÙ", "OØ",
				"OÛ", "OÕ", "OÏ", "OÂ", "OÁ", "OÀ", "OÅ", "OÃ", "OÄ", "Ô",
				"ÔÙ", "ÔØ", "ÔÛ", "ÔÕ", "ÔÏ", "E", "EÙ", "EØ", "EÛ", "EÕ",
				"EÏ", "EÂ", "EÁ", "EÀ", "EÅ", "EÃ", "EÄ", "I", "Í", "Ì", "Æ",
				"Ó", "Ò", "U", "UÙ", "UØ", "UÛ", "UÕ", "UÏ", "Ö", "ÖÙ", "ÖØ",
				"ÖÛ", "ÖÕ", "ÖÏ", "Y", "YÙ", "YØ", "YÛ", "YÕ", "Î", "Ñ" });

		TCVN3 = toSpecialFont("TCVN3", 1, new String[] { "﻿a", "¸", "µ", "¶",
				"·", "¹", "¨", "¾", "»", "¼", "½", "Æ", "©", "Ê", "Ç", "È",
				"É", "Ë", "o", "ã", "ß", "á", "â", "ä", "«", "è", "å", "æ",
				"ç", "é", "¬", "í", "ê", "ë", "ì", "î", "e", "Ð", "Ì", "Î",
				"Ï", "Ñ", "ª", "Õ", "Ò", "Ó", "Ô", "Ö", "i", "Ý", "×", "Ø",
				"Ü", "Þ", "u", "ó", "ï", "ñ", "ò", "ô", "­", "ø", "õ", "ö",
				"÷", "ù", "y", "ý", "ú", "û", "ü", "þ", "®", "A", "¸", "µ",
				"¶", "·", "¹", "¡", "¾", "»", "¼", "½", "Æ", "¢", "Ê", "Ç",
				"È", "É", "Ë", "O", "ã", "ß", "á", "â", "ä", "¤", "è", "å",
				"æ", "ç", "é", "¥", "í", "ê", "ë", "ì", "î", "E", "Ð", "Ì",
				"Î", "Ï", "Ñ", "£", "Õ", "Ò", "Ó", "Ô", "Ö", "I", "Ý", "×",
				"Ø", "Ü", "Þ", "U", "ó", "ï", "ñ", "ò", "ô", "¦", "ø", "õ",
				"ö", "÷", "ù", "Y", "ý", "ú", "û", "ü", "þ", "§" });

		SPECIAL_FONTS.add(FontType.UNICODETH);
		SPECIAL_FONTS.add(FontType.VNI_WINDOWS);
		SPECIAL_FONTS.add(FontType.TCVN3);

	}

	public FontType() {
		super();
	}

	public FontType(String name, String[] symbols) {
		super();
		this.name = name;
		this.symbols = symbols;
	}

	private static FontType toSpecialFont(String name, int maxChar,
			String[] symbols) {
		FontType type = new FontType(name, symbols);
		type.maxChar = maxChar;

		String[] symbolUnicodes = FontType.UNICODE.symbols;

		for (String str : symbols) {
			boolean ex = false;
			for (String strU : symbolUnicodes) {
				if (strU.equals(str)) {
					ex = true;
					break;
				}
			}
			if (ex) {
				continue;
			}

			type.fontCharaters.add(str);
		}
		return type;
	}

	/**
	 * Max characters in a String compared
	 *
	 * @return
	 */
	protected int getMaxChar() {
		return maxChar;
	}

	public String[] getSymbols() {
		return symbols;
	}

	/**
	 * Use to find font
	 *
	 * @return
	 */
	public List<String> getFontCharaters() {
		return fontCharaters;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FontType other = (FontType) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
