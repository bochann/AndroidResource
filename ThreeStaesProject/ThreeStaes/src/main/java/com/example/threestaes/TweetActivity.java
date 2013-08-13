package com.example.threestaes;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;

import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;


public class TweetActivity extends Activity implements OnClickListener {

    public static final String PREF_NAME="acess_token";
    public static final String TOKEN="token";
    public static final String TOKEN_SECRET="token_secret";

    private static final int SELECT_PIC=1;
    private Uri uri=null;
    private File picPath=null;
    private RatingBar rb;
    private String token;
    private String tokenSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tewwt);

        SharedPreferences preferences=getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        token=preferences.getString(TOKEN,null);
        tokenSecret=preferences.getString(TOKEN_SECRET,null);
        if(token==null||tokenSecret==null){
            Intent intent=new Intent(this,OAuthActivity.class);
            startActivity(intent);
            finish();
        }

        rb=(RatingBar)findViewById(R.id.ratingBar1);
        rb.setNumStars(3);
        rb.setStepSize((float) 0.5);
        rb.setRating((float) 1.5);

        /**画像選択ボタン**/
        Button btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);

        /**Tweetボタン**/
        Button btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                /**画像ボタンを押したときの処理**/
                selectedPicture();
                return;
            case R.id.button2:
                /**Tweetボタン押したときの処理**/
                doTweet();
                return;

            default:
                break;
        }
    }

    public void selectedPicture(){
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PIC);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==SELECT_PIC){
                /**Callback from selectPictuer()**/
                uri=data.getData();
                ContentResolver cr = getContentResolver();
                String[]columns={MediaStore.Images.Media.DATA};
                Cursor c =cr.query(uri,columns,null,null,null);

                c.moveToFirst();
                picPath=new File(c.getString(0));
                c.close();
            }
        }
    }

    private void doTweet(){
        EditText edCusine=(EditText)findViewById(R.id.editText1);
        String tweet=edCusine.getText().toString();
        if(tweet.equals("")){
            Toast.makeText(this,"料理名を入力してください",Toast.LENGTH_SHORT).show();
            edCusine.requestFocus();
            return;
        }
        new TweetTask().execute(tweet);
    }

   public class TweetTask extends AsyncTask<String,Void,Void>{


       @Override
       protected Void doInBackground(String... params) {
           ConfigurationBuilder builder =new ConfigurationBuilder();
           builder.setOAuthConsumerKey(getString(R.string.consumer_Key));
           builder.setOAuthConsumerSecret(getString(R.string.consumer_secret));
           builder.setOAuthAccessToken(token);
           builder.setOAuthAccessTokenSecret(tokenSecret);
           /**ここでMediaProviderをTWITTERにする**/
           builder.setMediaProvider("TWITTER");

           Configuration conf =builder.build();

           ImageUpload imageUpload =new ImageUploadFactory(conf).getInstance();

           String tweet = params[0];
           tweet = tweet + " 星　" + rb.getRating() + "　です。";

           try{
               imageUpload.upload(picPath,tweet);
           }catch (TwitterException e){
               e.printStackTrace();
           }
           return null;
       }

       @Override
       protected void onPostExecute(Void result) {
           super.onPostExecute(result);
           showShortToast("つぶやきました");
       }

   }
    private void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}
