package com.naresh.numbertotext;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.inmobi.commons.InMobi;
import com.inmobi.monetization.IMBanner;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getName();
	EditText inputNumber;
	TextView tvEnglish;
	TextView tvTanglish;
	TextView tvTamil;

	NumberToWordConverter twc = new TamilConverter();
	NumberToWordConverter ttc = new TamilTransliterationConverter();
	NumberToWordConverter ewc = new EnglishConverter();
	Long number = null;
	private static final String DEFAULT_STRING = "1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InMobi.initialize(this, "c04b32fbd7df4485ae62c558b3314108");
		//InMobi.setLogLevel(LOG_LEVEL.DEBUG);
		inputNumber = (EditText)findViewById(R.id.etNumber);
		inputNumber.setFilters(new InputFilter[]{new NumberEditTextInputFilter()});
		
		tvEnglish = (TextView)findViewById(R.id.tvEnglish);
		tvTanglish = (TextView)findViewById(R.id.tvTanglish);
		tvTamil = (TextView)findViewById(R.id.tvTamil);
		
		IMBanner banner = (IMBanner) findViewById(R.id.banner);
		Typeface tf = Typeface.createFromAsset(getAssets(), "test/tami.ttf");
		tvTamil.setTypeface(tf);
		inputNumber.setText(DEFAULT_STRING);
		updateForValue(DEFAULT_STRING);
		banner.loadBanner();

		inputNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				updateForValue(arg0.toString());
			}
		});
	}

	/**
	 * Convert String to long and update it
	 * @param val
	 */
	private void updateForValue (String val) {
		number = 0l;
		try{
			if(val.length() >= 1) {
				number = (long) Long.parseLong(val);
			}else {
				number = null;
			}
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			number = null;
		}
		convertAndSetText(number);

	}

	private static String English ="";
	private static String Tanglish ="";
	private static String Tamil ="";
	
	private void convertAndSetText(Long number) {
		if(number == null) {
			 English= "";
			 Tanglish= "";
			 Tamil= "";
		}else {
			English = ewc.convertToWord(number);
			Tanglish  =  ttc.convertToWord(number);
			Tamil  = twc.convertToWord(number);
		}
		tvEnglish.setText(English);
		tvTanglish.setText(Tanglish);
		tvTamil.setText(Tamil);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}