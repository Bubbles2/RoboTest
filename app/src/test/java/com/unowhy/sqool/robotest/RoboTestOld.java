package com.unowhy.sqool.robotest;


import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class RoboTestOld {
    @Test
    public void testTrueIsTrue() throws Exception {
        assertEquals(true, true);
    }

    Activity activity,activity2;


    @Before
    public void setup() {
        //activity = Robolectric.buildActivity(DeckardActivity.class).get();
        activity = Robolectric.setupActivity(MainActivity.class);
    }
    @Test
    public void titleIsCorrect() throws Exception {
        //      activity = Robolectric.setupActivity(DeckardActivity.class);
        assertTrue(activity.getTitle().toString().equals("RoboTest"));
        //
        assertThat(activity).isNotNull();

        TextView textView = (TextView) activity.findViewById(R.id.textView);
        assertThat(textView).isNotNull();

        EditText editText = (EditText) activity.findViewById(R.id.editText);
        assertThat(editText).isNotNull();

        Button button = (Button) activity.findViewById(R.id.button);
        assertThat(button).isNotNull();

        editText.setText("Peter");
        button.performClick();

        assertThat(textView).containsText("Hello, Peter!");
    }

    @Test
    public void shouldNotBeNull() {


        assertThat(activity).isNotNull();

        TextView textView = (TextView) activity.findViewById(R.id.textView);
        assertThat(textView).isNotNull();

        Button button = (Button) activity.findViewById(R.id.button);
        assertThat(button).isNotNull();


        EditText editText = (EditText) activity.findViewById(R.id.editText);
        assertThat(editText).isNotNull();

        editText.setText("Peter");
        button.performClick();

        assertThat(textView).containsText("Hello, Peter!");
    }
    @Test
    public void Start2() {
        //
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.putExtra("myTitle","pass this value");
        activity2 = Robolectric.buildActivity(Screen2.class).withIntent(i).create().get();
        //
        assertThat(activity2).isNotNull();
        //
        assertTrue("Incorrect Title Should be Robotest",activity2.getTitle().toString().equals("Screen2"));
        //
        TextView textView = (TextView) activity2.findViewById(R.id.txt1);
        assertThat(textView).containsText("pass this value");
        //

    }


}