package kms.prototype.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kms.prototype.Model.KMS_MenuComment;
import kms.prototype.R;
import kms.prototype.TimeLine_Adapter;

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

        KMS_MenuComment comment2 = new KMS_MenuComment("동글",3,"신촌맛집왕","이래저래 맛있다.",23,13,"청경관","모듬떡볶이");
        KMS_MenuComment comment1 = new KMS_MenuComment("검정고양이",13,"신촌맛집대왕","장문 테스트, 떡볶이 먹고 싶다. 떡볶이면 역시 떡튀순이지.",396,130,"복성각","납작짜장");
        m_adapter.add(comment2);
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

}
