package smate.simplememo;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class ActMemoList extends Activity{
	ListView listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_list);
		listView1 = (ListView) findViewById(R.id.listView1);
		// 리스트 출력
		// 선택 보기
		// 선택 삭제
		// 변경 저장
		
	}
}
