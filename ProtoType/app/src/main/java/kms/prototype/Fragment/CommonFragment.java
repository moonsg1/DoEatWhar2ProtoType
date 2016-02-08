package kms.prototype.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by KMS on 2016-02-08. 2016
 */
public class CommonFragment extends Fragment {

    private BitmapFactory m_bitmapFactory = new BitmapFactory();

    // 비트맴 불러오기
    public Bitmap loadBitmap(String urlPath){
        Bitmap btm = null;
        try{
            URL imageUrl = new URL(urlPath);
            HttpURLConnection connect = (HttpURLConnection) imageUrl.openConnection();
            InputStream is = connect.getInputStream();
            btm = m_bitmapFactory.decodeStream(is);
        }catch (Exception e){
            e.printStackTrace();
        }

        return btm;
    }


}
