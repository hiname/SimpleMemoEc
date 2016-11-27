package smate.simplememo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ActNowMemo extends Activity{
	
	private EditText editText1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.now_memo);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText1.requestFocus();
		// keyborad show
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		// keyborad hide
		// InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		// immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		Toast.makeText(this, "처리", Toast.LENGTH_SHORT).show();
	}
}
