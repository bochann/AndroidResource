package example.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.widget.Toast;

public class NetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_layout);

        /**ネットワーク接続チェック**/
        if ( networkIsConnected(this.getApplicationContext()) ) {

            Toast.makeText(this, "オンライン", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, "オフライン", Toast.LENGTH_LONG).show();
        }


    }

    /**ネットワーク接続チェック**/
    public static boolean networkIsConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if( ni != null ){
            return cm.getActiveNetworkInfo().isConnected();
        }
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**Inflate the menu; this adds items to the action bar if it is present.**/
        getMenuInflater().inflate(R.menu.net, menu);
        return true;
    }
    
}
