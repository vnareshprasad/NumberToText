package com.naresh.numbertotext;

import java.util.Map;

import com.inmobi.commons.InMobi;
import com.inmobi.commons.InMobi.LOG_LEVEL;
import com.inmobi.monetization.IMBanner;
import com.inmobi.monetization.IMBannerListener;
import com.inmobi.monetization.IMErrorCode;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getName();
	EditText inputNumber;
	TextView outputWords;

	NumberToWordConverter twc = new TamilConverter();
	NumberToWordConverter ttc = new TamilTransliterationConverter();
	NumberToWordConverter ewc = new EnglishConverter();
	Long number = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InMobi.initialize(this, "c04b32fbd7df4485ae62c558b3314108");
		InMobi.setLogLevel(LOG_LEVEL.DEBUG);
		inputNumber = (EditText)findViewById(R.id.etNumber);
		inputNumber.setFilters(new InputFilter[]{new NumberEditTextInputFilter()});
		
		outputWords = (TextView)findViewById(R.id.textviewOutputWords);
		IMBanner banner = (IMBanner) findViewById(R.id.banner);
		Typeface tf = Typeface.createFromAsset(getAssets(), "test/tami.ttf");
		outputWords.setTypeface(tf);
		banner.loadBanner();
		banner.setIMBannerListener(new IMBannerListener() {

			@Override
			public void onShowBannerScreen(IMBanner arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onShowBannerScreen: "+arg0.toString());
			}

			@Override
			public void onLeaveApplication(IMBanner arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onLeaveApplication: "+arg0.toString());
			}

			@Override
			public void onDismissBannerScreen(IMBanner arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onDismissBannerScreen: "+arg0.toString());
			}

			@Override
			public void onBannerRequestSucceeded(IMBanner arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onBannerRequestSucceeded: "+arg0.toString());
			}

			@Override
			public void onBannerRequestFailed(IMBanner arg0, IMErrorCode arg1) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onBannerRequestFailed: "+arg0.toString());
			}

			@Override
			public void onBannerInteraction(IMBanner arg0, Map<String, String> arg1) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onBannerInteraction: "+arg0.toString());
			}
		});

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
				number = 0l;
				try{
					if(arg0.toString().length() >= 1) {
						number = (long) Long.parseLong(arg0.toString());
					}else {
						number = null;
					}
				}catch(NumberFormatException nfe) {
					nfe.printStackTrace();
					number = null;
				}
				updateValue(number);
			}
		});
	}

	private static String words ="";
	private void updateValue(Long number) {
		
		if(number == null) {
			words = "";
		}else {
			words  = ewc.convertToWord(number);
			words  = words + " : " + ttc.convertToWord(number);
			words  = words + " : " + twc.convertToWord(number);	
		}
		outputWords.setText(words);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}