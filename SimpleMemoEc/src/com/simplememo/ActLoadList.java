package com.simplememo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by USER on 2016-12-02.
 */
public class ActLoadList extends Activity {

	ListView lvLoadList;
	ArrayList<String> mainArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_list);
		lvLoadList = (ListView) findViewById(R.id.lvLoadList);
		reloadMainList();
		//
		lvLoadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				final int selPos = position;
				// final String dbSelData = memoDataList.get(selPos);
				// final String dbSelMemoData = dbSelData.split(" / ")[2];				// 
				// Log.d("d", dbSelData);
				
				final String dbSelMemoData = mainArrayList.get(selPos).split(":", 2)[1];
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ActLoadList.this);
				builder
						.setMessage("어떻게 할까요?\n→ " + dbSelMemoData.split(MemoData.TAG_MEMO_END)[0].replaceAll(MemoData.TAG_MEMO_SPLITER, "\n") + " ←")
						.setCancelable(false)
						.setPositiveButton("추가하기",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										memoData.addMemoData(dbSelMemoData);
										Toast.makeText(ActLoadList.this, "추가 : " + dbSelMemoData, Toast.LENGTH_SHORT).show();
									}
								})
						.setNegativeButton("덮어쓰기",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										FileMgr.saveFileText(memoData.saveFileFullPath, dbSelMemoData, FileMgr.ENC_UTF8, false);
										memoData.loadByFile();
										finish();
										return;
									}
								})
						.setNeutralButton("취소",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										return;
									}
								});
						
				builder.setCancelable(true);
				builder.create().show();

			}
		});

		lvLoadList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				final int selPos = position;
				final String getFullData = mainArrayList.get(selPos);
				String[] dbIdAndMemo = getFullData.split(":", 2);
				final String getId = dbIdAndMemo[0];
				final String getMemoData = dbIdAndMemo[1];

				AlertDialog.Builder builder = new AlertDialog.Builder(ActLoadList.this);
				builder
						.setMessage("삭제합니까?\n(삭제시 복구불가)→ " + getMemoData.split(MemoData.TAG_MEMO_END)[0].replaceAll(MemoData.TAG_MEMO_SPLITER, "\n") + " ←")
						.setCancelable(false)
						.setPositiveButton("삭제함",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										memoData.dbDeleteMemoData(getId);
										reloadMainList();
									}
								})
						.setNegativeButton("취소",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										return;
									}
								});
				builder.setCancelable(true);
				builder.create().show();
				return false;
			}
		});

	}

	MemoData memoData = MemoData.getInstance();

	private ArrayList<String> loadDbIdAndMemoDataList() {
		final ArrayList<String> dataList = new ArrayList<String>();
		String[][] dbAllDataList = memoData.dbSelectAllArrayCharToStr();
		for (int i = 0; i < dbAllDataList.length; i++) {
			dataList.add(dbAllDataList[i][0] + ":" + dbAllDataList[i][2]);
		}
		return dataList;
	}


	private void reloadMainList() {
		mainArrayList = loadDbIdAndMemoDataList();
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_element, mainArrayList);
		lvLoadList.setAdapter(arrayAdapter);
		arrayAdapter.notifyDataSetChanged();
	}
}
