package com.unowhy.sqool.robotest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    //
    public static final String PROVIDER_NAME = "com.unowhy.sqoolcp.SqoolCP";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME +"/chksqool");
    // Add URI for cloud update log
    public static final Uri CONTENT_URI_CLOUD_LOG = Uri.parse("content://" + PROVIDER_NAME +"/cloudlog");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        TextView tnr = (TextView) findViewById(R.id.dbRecords);
//        int numrecsa=0;
//        int numrecs=printRecords();
//        tnr.setText(""+numrecs);
        // Read records from DB
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.textView);
                EditText editText = (EditText) findViewById(R.id.editText);
                textView.setText(String.format("Hello, %s!", editText.getText()));
                //
                TextView tnr = (TextView) findViewById(R.id.dbRecords);
                int numrecs=printRecords();
                tnr.setText(""+numrecs);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public int printRecords() {
        //
        int norecs = 0;
        ContentResolver cr = getContentResolver();
        //
        Cursor c = cr.query(CONTENT_URI, null, null, null, null);
        while (c.moveToNext()) {
            Log.i("Records", c.getString(c.getColumnIndex(CommonValues.KEY_SQOOLKEY)));
            norecs++;
       }
        c.close();
        return norecs;
    }

    public int addMyNumbers(int a, int b){

        return a+b;
    }
}
