package example.dennwatyo;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;


import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class TelActivity extends Activity {


    TableLayout tablelayout=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tel_layout);

        tablelayout=(TableLayout)findViewById(R.id.tablelayout);

        try{
            /**データ取得**/
            Cursor cur=managedQuery(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

            /**データを取得できた場合**/
            if(cur.getCount() != 0){
                /**取得したデータを表示**/
                while(cur.moveToNext()){
                    String name=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    setItems(name);
                }
            }else{
                TextView massage=new TextView(this);
                massage.setText("データが取得できませんでした");
                LinearLayout linearlayout=(LinearLayout)findViewById(R.id.laynearlayout);
                linearlayout.addView(massage);
            }

        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }

    }

    /**setItemメソッド(テーブル処理)**/
    private void setItems(String name){
        /**名前設定**/
        TableRow row=new TableRow(this);
        TextView displayName=new TextView(this);
        displayName.setText(name);
        /**テーブルレイアウトに設定**/
        tablelayout.addView(row);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tel, menu);
        return true;
    }


}
