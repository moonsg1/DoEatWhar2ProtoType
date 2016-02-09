package kms.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import kms.prototype.Model.DataBox;

/**
 * Created by KMS on 2016-02-09. 2016
 */

public class Recommend_Adapter extends BaseAdapter {

    // 문자열을 보관 할 ArrayList
    private ArrayList<DataBox> m_List;

    // 생성자
    public Recommend_Adapter() {
        m_List = new ArrayList<>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final DataBox comment = m_List.get(position);

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // UI 부터 가져온다.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reply_list_item, parent, false);

            // 유저 아이디
            TextView userId = (TextView) convertView.findViewById(R.id.uid_text);
            userId.setText(comment.getUserName());

            // 레벨
            TextView userLv = (TextView) convertView.findViewById(R.id.userLv_text);
            String LV = "Lv." + comment.getUserLv().toString();
            userLv.setText(LV);

            // 타이틀
            TextView userTitle = (TextView) convertView.findViewById(R.id.userTitle_text);
            userTitle.setText(comment.getUserTitle());

            // 코멘트 내용
            TextView content = (TextView) convertView.findViewById(R.id.comment_content);
            content.setText(comment.getContent());

            // 좋아요 갯수
            TextView likeNum = (TextView) convertView.findViewById(R.id.like_num_text);
            String likeIt = "좋아요 " + comment.getLikeCount().toString();
            likeNum.setText(likeIt);

            // 답글 갯수
            TextView replyNum = (TextView) convertView.findViewById(R.id.reply_num_text);
            replyNum.setText(String.format("%d", comment.getReplyCount()));
        }
        return convertView;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(DataBox comment) {
        m_List.add(comment);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }
}