package example.imageapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);

        //イメージビューオブジェクト作成
        ImageView iv =new ImageView(this);
        iv.setImageResource(R.drawable.kyupy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image, menu);
        return true;
    }
    
}
