package kms.prototype.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kms.prototype.Network;
import kms.prototype.R;

/**
 * Created by KMS on 2015-10-31. 2015
 */
public class RecommendFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recommend, container, false);

        // hateButton 리스너 연결



        // 댓글 영역
        String Result = Network.getInstance().getJsonResult();
        ((TextView) (rootView.findViewById(R.id.recommendResult))).setText(Result);

        return rootView;
    }

    // hateButton 클릭 리스너
    public void click_hateButton(View view) {
        Log.d("hate button","HATE!");
    }
}
