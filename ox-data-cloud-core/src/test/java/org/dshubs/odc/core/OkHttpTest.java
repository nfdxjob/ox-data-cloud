package org.dshubs.odc.core;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * @author create by wangxian 2021/12/4
 */
public class OkHttpTest {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "applicant_id=288707351&job_id=270153909&field_values=[{\"PropertyName\":\"ActiveOfferApplyState\",\"PropertyShortName\":\"\",\"Code\": \"\",\"Value\":\"1\"}]");
        Request request = new Request.Builder()
                .url("http://api.beisenapp.com/recruitv2/105947/applicant/updateapplystatus?format=json")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 273aea4dfb864be7b4da67b987c76942")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(Objects.requireNonNull(response.body()).string());
    }
}
