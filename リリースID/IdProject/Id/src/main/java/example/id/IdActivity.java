package example.id;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class IdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_layout);

        Resources res = getResources();
        int strId = getResources().getIdentifier("rerese", "string", getPackageName());
        int resId = getResources().getIdentifier("rerese_txt", "id", getPackageName());
        TextView kagawa_txt = (TextView)findViewById(resId);
        kagawa_txt.setText(res.getString(strId));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.id, menu);
        return true;
    }
    
}
