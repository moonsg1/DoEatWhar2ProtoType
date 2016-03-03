package kms.prototype.Fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kms.prototype.KMS_Network;
import kms.prototype.Model.CommentBox;
import kms.prototype.Model.MenuBox;
import kms.prototype.R;
import kms.prototype.Recommend_Adapter;

/**
 * Created by KMS on 2015-10-31. 2015
 */
public class RecommendFragment extends CommonFragment{

    private ImageView m_recommendImageView;
    private LinearLayout m_commentLayout;

    private Bitmap m_bitMap;
    private Recommend_Adapter m_adapter;
    private SparseArray<MenuBox> m_menuBoxSparseArray = new SparseArray<>();

    private ViewGroup m_container;
    private int m_recommendIndex;
    private int m_maxIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        m_recommendIndex = 0;
        m_maxIndex = 4;

        m_adapter = new Recommend_Adapter();
        m_container = container;

        View rootView = inflater.inflate(R.layout.fragment_recommend, container, false);

        m_recommendImageView = (ImageView) rootView.findViewById(R.id.recommendMenuImageView);

        // MenuContainer가 비어있다면 aSyncTask를 호출해서 가져온다.
        if (m_menuBoxSparseArray.size() == 0) {
            GetMenuAsyncTask menuGetterAsyncTask = new GetMenuAsyncTask();
            menuGetterAsyncTask.execute();
        }

        // image
        m_recommendImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        m_recommendImageView.setOnClickListener(click_image());

        // hateButton 리스너 연결
        ImageButton hateButton = (ImageButton) rootView.findViewById(R.id.hateButton);
        hateButton.setOnClickListener(click_hateButton());

        // likeButton 리스너 연결
        ImageButton likeButton = (ImageButton) rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(click_likeButton());

        // mid 꺼내와서 aSynctask 호출 - 여기서 이미지 밖고 댓글 만듬
        GetCommentAsyncTask getCommentAsyncTask = new GetCommentAsyncTask();
        getCommentAsyncTask.execute();

        // 댓글 리스트
        m_commentLayout = (LinearLayout) rootView.findViewById(R.id.replyContainer);


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
            }
        };
    }

    // likeButton 클릭 리스너
    public View.OnClickListener click_likeButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("like button", "LIKE!");
                m_recommendIndex -= 1;
                if (m_recommendIndex < 0){
                    m_recommendIndex = 0;
                }
            }
        };
    }

    // 랜덤한 메뉴 가져오기
    protected class GetMenuAsyncTask extends AsyncTask<String, Integer, SparseArray<MenuBox>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected SparseArray<MenuBox> doInBackground(String... urlPath) {
            SparseArray<MenuBox> sparseArray = KMS_Network.getInstance().getMenuResult();
            m_menuBoxSparseArray = sparseArray;
            return sparseArray;
        }

        @Override
        protected void onPostExecute(SparseArray<MenuBox> sparseArray) {
            super.onPostExecute(sparseArray);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    // 랜덤한 메뉴 가져오기
    protected class GetCommentAsyncTask extends AsyncTask<Void, Integer, SparseArray<CommentBox>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected SparseArray<CommentBox> doInBackground(Void... params) {
            // 현재 인덱스에서의 메뉴박스에서 이미지 url을 가져와서 표시
            MenuBox menuBox = m_menuBoxSparseArray.get(m_recommendIndex);
            m_bitMap = loadBitmap(menuBox.getPictureUrl());

            int mid = m_menuBoxSparseArray.get(m_recommendIndex).getMid();
            SparseArray<CommentBox> sparseArray = KMS_Network.getInstance().getCommentResult(mid);
            return sparseArray;
        }

        @Override
        protected void onPostExecute(SparseArray<CommentBox> sparseArray) {
            super.onPostExecute(sparseArray);

            // setImage는 UI thread에서 해야해서 네트워크 통신 끝난후 실행
            m_recommendImageView.setImageBitmap(m_bitMap);

            // comment container에 commentBox 만들어서 넣음.
            for (int i = 0; i < sparseArray.size(); i++) {
                CommentBox commentBox = sparseArray.get(i);
                m_adapter.add(commentBox);
            }

            // 댓글 UI 생성
            for (int i = 0; i < m_adapter.getCount(); i++) {
                View commentView = m_adapter.getView(i, null, m_container);
                m_commentLayout.addView(commentView);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
