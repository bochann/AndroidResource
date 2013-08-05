package example.jsonapplication;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class JsonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_layout);

        HttpClient httpClient = new DefaultHttpClient();

        /**URL取得**/

        StringBuilder uri = new StringBuilder("http://api.atnd.org/events/?keyword=android&format=json");
        HttpGet request = new HttpGet(uri.toString());
        HttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            Log.d("JSONActivity", "Error Execute");
            return;
        }

        int status = httpResponse.getStatusLine().getStatusCode();

        if (HttpStatus.SC_OK == status) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(outputStream);
                String data;
                data = outputStream.toString(); /**JSONデータ**/

                /**ルートオブジェクトを取得**/
                JSONObject rootObject = new JSONObject(data);

                JSONArray eventArray = rootObject.getJSONArray("events");

                /**１つずつJSONObjectを取り出して次はタイトルの要素を取得**/
                for (int i = 0; i < eventArray.length(); i++) {
                    JSONObject jsonObject = eventArray.getJSONObject(i);
                    Log.d("JSONActivity", jsonObject.getString("title"));
                }
            } catch (Exception e) {
                Log.d("JSONSampleActivity", "Error");
            }
        } else {
            Log.d("JSONSampleActivity", "Status" + status);
            return;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**Inflate the menu; this adds items to the action bar if it is present.**/
        getMenuInflater().inflate(R.menu.json, menu);
        return true;
    }
    
}
