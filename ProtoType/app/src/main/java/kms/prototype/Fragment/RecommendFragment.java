package kms.prototype.Fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kms.prototype.Recommend_Adapter;
import kms.prototype.Model.KMS_MenuComment;
import kms.prototype.R;

/**
 * Created by KMS on 2015-10-31. 2015
 */
public class RecommendFragment extends CommonFragment{

    private ImageView m_recommendImage;
    private ArrayList<Bitmap> m_bitmapContainer;
    private Recommend_Adapter m_adapter;
    private int m_recommendIndex;
    private int m_maxIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 네트워크 연결부 클라도 자바인데 직렬화해서 개체를 보낼수 있지 않을까
        //String resultObj = KMS_Network.getInstance().getJsonResult();

        m_recommendIndex = 0;
        m_maxIndex = 4;
        m_bitmapContainer = new ArrayList<>();
        m_adapter = new Recommend_Adapter();

        View rootView = inflater.inflate(R.layout.fragment_recommend, container, false);
        HttpConnectAsyncTask asyncTask = new HttpConnectAsyncTask();

        m_recommendImage = (ImageView) rootView.findViewById(R.id.recommendMenuImageView);

        // 무작위 추천 메뉴 객체
        String[] recommendMenuStr = new String[5];
        recommendMenuStr[0] = "http://cfile5.uf.tistory.com/image/127456594E2312BD335A09";
        recommendMenuStr[1] = "http://cfile202.uf.daum.net/image/1171C9464DBF4D9135B703";
        recommendMenuStr[2] = "http://cfile218.uf.daum.net/image/196EB1345032B3BC0C6662";
        recommendMenuStr[3] = "http://cfile234.uf.daum.net/image/020E103A50F2B2D00FCDBC";
        recommendMenuStr[4] = "http://cfile225.uf.daum.net/image/1908003550A20A371DAEC3";

        // image
        m_recommendImage.setScaleType(ImageView.ScaleType.FIT_XY);
        m_recommendImage.setOnClickListener(click_image());
        asyncTask.execute(recommendMenuStr);

        // hateButton 리스너 연결
        ImageButton hateButton = (ImageButton) rootView.findViewById(R.id.hateButton);
        hateButton.setOnClickListener(click_hateButton());

        // likeButton 리스너 연결
        ImageButton likeButton = (ImageButton) rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(click_likeButton());

        KMS_MenuComment comment2 = new KMS_MenuComment("동글",3,"신촌맛집왕","이래저래 맛있다.",23,13, null, null);
        KMS_MenuComment comment1 = new KMS_MenuComment("검정고양이",13,"신촌맛집대왕","장문 테스트, 떡볶이 먹고 싶다. 떡볶이면 역시 떡튀순이지.",396,130,null,null);
        m_adapter.add(comment2);
        m_adapter.add(comment1);
        m_adapter.add(comment2);
        m_adapter.add(comment1);
        m_adapter.add(comment2);
        m_adapter.add(comment1);
        m_adapter.add(comment2);
        m_adapter.add(comment1);

        // 댓글 리스트
        // ListView 에 아이템 추가
        LinearLayout commentContainer = (LinearLayout) rootView.findViewById(R.id.replyContainer);
        for (int i=0; i<m_adapter.getCount(); i++) {
            View commentView = m_adapter.getView(i,null,container);
            commentContainer.addView(commentView);
        }

        return rootView;
    }

    // image 클릭 리스너
    public View.OnClickListener click_image() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("imageView", "IMAGE!");
            }
        };
    }

    // hateButton 클릭 리스너
    public View.OnClickListener click_hateButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hate button", "HATE!");
                m_recommendIndex += 1;
                if (m_recommendIndex > m_maxIndex){
                    m_recommendIndex = m_maxIndex;
                }
                m_recommendImage.setImageBitmap(m_bitmapContainer.get(m_recommendIndex));
            }
        };
    }

    // likeButton 클릭 리스너
    public View.OnClickListener click_likeButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hate button", "HATE!");
                m_recommendIndex -= 1;
                if (m_recommendIndex < 0){
                    m_recommendIndex = 0;
                }
                m_recommendImage.setImageBitmap(m_bitmapContainer.get(m_recommendIndex));
            }
        };
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
                bitmap = loadBitmap(path);
                m_bitmapContainer.add(bitmap);
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

            m_recommendImage.setImageBitmap(m_bitmapContainer.get(m_recommendIndex));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
