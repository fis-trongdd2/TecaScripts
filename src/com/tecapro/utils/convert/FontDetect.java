package com.tecapro.utils.convert;

class FontDetect {
	private int count = 0;
	private boolean special = false;

	public void reset() {
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public void next() {
		this.count++;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}
}
