package kms.prototype.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kms.prototype.KMS_Network;
import kms.prototype.Model.DataBox;
import kms.prototype.R;

/**
 * Created by KMS on 2015-10-31. 2015
 */
public class MyPageFragment extends Fragment {
    private SparseArray<DataBox> m_dataBoxSparseArray;
    private TextView m_textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mypage, container, false);
        HttpConnectAsyncTask asyncTask = new HttpConnectAsyncTask();

        m_textView = (TextView) rootView.findViewById(R.id.textView);
        asyncTask.execute("0");

        return rootView;
    }

    // 네트워크 통신 스레드용 AsyncTask 서브클래스
    protected class HttpConnectAsyncTask extends AsyncTask<String, Integer, SparseArray<DataBox>> {

        @Override
        protected SparseArray<DataBox> doInBackground(String... params) {
            SparseArray<DataBox> sparseArray = KMS_Network.getInstance().getResult(0);
            m_dataBoxSparseArray = sparseArray;
            return sparseArray;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(SparseArray<DataBox> sparseArray) {
            super.onPostExecute(sparseArray);
            String testStr = m_dataBoxSparseArray.get(0).getContent();
            m_textView.setText(testStr);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}