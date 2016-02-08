package kms.prototype;

/**
 * Created by KMS on 2015-10-31. 2015
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class KMS_Network {
    // 전역변수를 선언한다
    private String myId, myPWord, myTitle, mySubject, myResult;

    // singleton
    private static KMS_Network instance;

    private KMS_Network(){
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
    public void HttpPostData() {
        try {

            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL("http://localhost:8080/projectE_server/RandomMenu");       // URL 설정
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
            StringBuffer buffer = new StringBuffer();
            buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
            buffer.append("pword").append("=").append(myPWord).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            buffer.append("title").append("=").append(myTitle).append("&");           // 변수 구분은 '&' 사용
            buffer.append("subject").append("=").append(mySubject);

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
            String str;

            while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
            }

            myResult = builder.toString();                       // 전송결과를 전역 변수에 저장
        } catch (MalformedURLException e) {
            //
        } catch (IOException e) {
            //
        } // try
    } // HttpPostData
    public String getJsonResult(){
        return myResult;
    }

} // Activity


