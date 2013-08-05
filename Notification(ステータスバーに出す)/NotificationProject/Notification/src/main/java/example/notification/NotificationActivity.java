package example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button)
                    sendNotification();
            }



        });
    }

    private void sendNotification() {
        Notification n = new Notification(); /**Notificationの生成**/
        n.icon = R.drawable.ic_launcher; /**アイコンの設定**/
        n.tickerText = "This is a notification message..."; /**メッセージの設定**/

        Intent i = new Intent(getApplicationContext(), NotificationActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        n.setLatestEventInfo(getApplicationContext(), "TITLE", "TEXT", pi);

        /**NotificationManagerのインスタンス取得**/
        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, n); /**設定したNotificationを通知する**/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**Inflate the menu; this adds items to the action bar if it is present.**/
        getMenuInflater().inflate(R.menu.notification, menu);
        return true;
    }
    
}
