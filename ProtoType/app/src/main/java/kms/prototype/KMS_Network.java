package kms.prototype;

/**
 * Created by KMS on 2015-10-31. 2015
 */

import android.util.Log;
import android.util.SparseArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import kms.prototype.Model.CommentBox;
import kms.prototype.Model.DataBox;
import kms.prototype.Model.MenuBox;

public class KMS_Network {

    // singleton
    private static KMS_Network instance;
    // 싱글턴을 사용하여 한번 불러온 데이타는 계속 들고 있음..!
    private SparseArray<DataBox> m_dataBoxSparseArray = new SparseArray<>();
    private SparseArray<MenuBox> m_menuBoxSparseArray = new SparseArray<>();
    private SparseArray<CommentBox> m_commentBoxSparseArray = new SparseArray<>();
    private int TEST = 0;
    private int MENU = 1;
    private int COMMENT = 2;
    private int TIMELINE = 3;
    private int m_mid;

    private KMS_Network() {
    }
    public static KMS_Network getInstance(){
        if(instance == null){
            synchronized (KMS_Network.class){
                if(instance == null){
                    instance = new KMS_Network();
                }
            }
        }
        return instance;
    }

    //------------------------------
    //   Http Post로 주고 받기
    //------------------------------
    public void HttpPostData(int type) {
        try {
            String server_address = "http://192.168.0.107:8080/projectE_server/RandomMenu";

            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL(server_address);       // URL 설정
            HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속

            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            http.setDefaultUseCaches(false);
            http.setDoInput(true);                         // 서버에서 읽기 모드 지정
            http.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            http.setRequestMethod("POST");         // 전송 방식은 POST

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer;
            if (type == MENU) {
                buffer = getRecommendMenuBuffer();
            } else if (type == COMMENT) {
                buffer = getCommentBuffer();
            } else if (type == TIMELINE) {
                buffer = getTimeLineBuffer();
            } else if (type == TEST) {
                buffer = getTestBuffer();
            } else {
                buffer = null;
            }

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            //--------------------------
            //   서버에서 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();

            String resultStr, str;
            while ((str = reader.readLine()) != null) {   // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str);
            }

            resultStr = builder.toString();  // 전송결과를 저장
            Log.d("Network result", resultStr);
            setJsonResult(resultStr, type);

        } catch (MalformedURLException e) {
            //
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuffer getTestBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("id").append("=").append("testId").append("&");                 // php 변수에 값 대입
        buffer.append("type").append("=").append(TEST);
        return buffer;
    }

    private StringBuffer getRecommendMenuBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("uid").append("=").append("testId").append("&");                 // php 변수에 값 대입
        buffer.append("type").append("=").append(MENU);
        return buffer;
    }

    private StringBuffer getCommentBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("uid").append("=").append("testId").append("&");                 // php 변수에 값 대입
        buffer.append("mid").append("=").append(m_mid).append("&");
        buffer.append("type").append("=").append(COMMENT);
        return buffer;
    }

    private StringBuffer getTimeLineBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("id").append("=").append("testId").append("&");
        buffer.append("type").append("=").append(TIMELINE);
        return buffer;
    }

    // json을 파싱하여 Model에 맞춰 구조화. Json Array를 기준으로 한다.
    private void setJsonResult(String str, int type) {
        try {
            JSONArray jsonArray = new JSONArray(str);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                Log.d("JSON", jo.toString());
                if (type == MENU) {
                    MenuBox menuBox = new MenuBox(jo);
                    m_menuBoxSparseArray.put(i, menuBox);
                } else if (type == COMMENT) {
                    CommentBox commentBox = new CommentBox(jo);
                    m_commentBoxSparseArray.put(i, commentBox);
                } else {
                    DataBox dataBox = new DataBox(jo);
                    m_dataBoxSparseArray.put(i, dataBox);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------
    // 외부에서 서버에서 받아온 데이터 꺼낼때
    //---------------------------------------

    public SparseArray<DataBox> getResult(int type) {
        HttpPostData(type);
        return m_dataBoxSparseArray;
    }

    public SparseArray<MenuBox> getMenuResult() {
        HttpPostData(MENU);
        return m_menuBoxSparseArray;
    }

    public SparseArray<CommentBox> getCommentResult(int mid) {
        m_mid = mid;
        HttpPostData(COMMENT);
        return m_commentBoxSparseArray;
    }
}


