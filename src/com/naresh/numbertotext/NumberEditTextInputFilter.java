package com.naresh.numbertotext;

import android.text.InputFilter;
import android.text.Spanned;

class NumberEditTextInputFilter implements InputFilter{

	long max = Long.MAX_VALUE;
	long min = Long.MIN_VALUE;
	
	@Override
	public CharSequence filter(CharSequence source, int start, int end,
			Spanned dest, int dstart, int dend) {
		try{
			long input = Long.parseLong(dest.toString() + source.toString());
		}catch(NumberFormatException nfe) {
			return  "";
		}
		return null;
	}
	
}