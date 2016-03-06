package kms.prototype.Fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kms.prototype.Model.DataBox;
import kms.prototype.R;
import kms.prototype.Adpater.TimeLine_Adapter;

/**
 * Created by KMS on 2015-10-31. 2015
 */
public class TimeLineFragment extends Fragment{

    private TimeLine_Adapter m_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        m_adapter = new TimeLine_Adapter();

        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);

        DataBox comment2 = new DataBox(null, "청경관", "떡볶이스파게티", 3500, "동글", 3, "신촌맛집왕", "이래저래 맛있다.", "3.3", 23, 13);
        DataBox comment1 = new DataBox(null, "복성각", "납작짜장", 5500, "검정고양이", 13, "신촌맛집대왕", "장문 테스트, 떡볶이 먹고 싶다. 떡볶이면 역시 떡튀순이지.", "3.4", 396, 130);
        m_adapter.add(comment1);
        m_adapter.add(comment2);
        m_adapter.add(comment1);
        m_adapter.add(comment2);
        m_adapter.add(comment1);
        m_adapter.add(comment2);
        m_adapter.add(comment1);

        // 타임라인 리스트
        // ListView 에 아이템 추가
        LinearLayout commentContainer = (LinearLayout) rootView.findViewById(R.id.timeLine_list);
        for (int i=0; i<m_adapter.getCount(); i++) {
            View commentView = m_adapter.getView(i,null,container);
            commentContainer.addView(commentView);
        }

        return rootView;
    }

    // 네트워크 통신 스레드용 AsyncTask 서브클래스
    public class HttpConnectAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... urlPath) {
            Bitmap bitmap = null;
            for(String path : urlPath){
                Log.d("do in back ground", "result = " + bitmap.getWidth());
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null) {
                Log.d("on post execute", "result = " + bitmap.getWidth());
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
