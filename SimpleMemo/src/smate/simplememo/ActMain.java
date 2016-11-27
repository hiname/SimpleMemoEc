package smate.simplememo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class ActMain extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, ActNowMemo.class));
		finish();
	}
}
