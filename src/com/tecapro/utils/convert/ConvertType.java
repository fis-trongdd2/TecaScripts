package com.tecapro.utils.convert;

import java.util.ArrayList;
import java.util.List;

class ConvertType {
	List<RegexIndex> indexs = new ArrayList<RegexIndex>();

	public ConvertType(FontType fromType, FontType toType) {
		super();
		for (int i = 0; i < fromType.getSymbols().length; i++) {
			String fromRegex = fromType.getSymbols()[i];
			String toRegex = toType.getSymbols()[i];
			if (fromRegex.equals(toRegex)) {
				continue;
			}
			RegexIndex ri = new RegexIndex(fromRegex, toRegex);
			indexs.add(ri);
		}
	}

	boolean convert(String ch, StringBuilder sb) {
		for (RegexIndex index : indexs) {
			if (index.fromRegex.equals(ch)) {
				sb.append(index.toRegex);
				return true;
			}
		}
		return false;
	}

	private class RegexIndex {
		String fromRegex;
		String toRegex;

		public RegexIndex(String fromRegex, String toRegex) {
			super();
			this.fromRegex = fromRegex;
			this.toRegex = toRegex;
		}

		@Override
		public String toString() {
			return fromRegex + '-' + toRegex;
		}
	}
}
