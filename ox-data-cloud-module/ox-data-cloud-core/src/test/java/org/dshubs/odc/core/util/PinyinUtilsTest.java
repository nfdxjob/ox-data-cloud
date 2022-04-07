package org.dshubs.odc.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.google.common.io.Files.write;

/**
 * @author create by wangxian 2021/12/31
 */
@Slf4j
public class PinyinUtilsTest {


    @Test
    public void toPinyinTest() throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        String result = PinyinHelper.toHanYuPinyinString("汉", format, "", true);
        System.out.println(result);
        Assertions.assertEquals(result, "han4");
    }

    @Test
    public void toPinyinTest1() throws BadHanyuPinyinOutputFormatCombination {
       generateDomainAccount("王贤");
    }

    public static void generateDomainAccount(String username) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        char [] array = username.toCharArray();
        for (char a:array){
            String result = PinyinHelper.toHanYuPinyinString(a+"", format, "", true);
            System.out.println(result);

        }
    }

    @Test
    public void mapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "");
        Object result = map.get("a");
        System.out.println(result);
    }

    @Test
    public void timeTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, -1);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }

    @Test
    public void readJson() throws Exception {
        File file = new File("/Users/wangxian/downloads/shr-api-json.txt");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode att = jsonNode.get("attachmenInfo");
        System.out.println(att.isArray());
        String base64File = att.get(0).get("content").asText();
        System.out.println(base64File);
        byte[] jpg = Base64.getDecoder().decode(base64File);
        write(jpg, new File("/Users/wangxian/downloads/0119123123.jpg"));

    }

    @Test
    public void readJson1() throws Exception {
        File file = new File("/Users/wangxian/downloads/dddd.txt");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode att = jsonNode.get("attachmenInfo");
        System.out.println(att.isArray());
        String base64File = att.get(6).get("content").asText();
        System.out.println(base64File);
        byte[] jpg = Base64.getDecoder().decode(base64File);
        write(jpg, new File("/Users/wangxian/downloads/obs-test.jpg"));

    }


    @Test
    public void readJson0119() throws Exception {
        File file = new File("/Users/wangxian/downloads/response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode att = jsonNode.get("attachmenInfo");
        System.out.println(att.isArray());
        String base64File = att.get(0).get("content").asText();
        System.out.println(base64File);
        byte[] jpg = Base64.getDecoder().decode(base64File);
        write(jpg, new File("/Users/wangxian/downloads/0119test.jpg"));

    }

    @Test
    public void test5(){
        LocalDate now = LocalDate.now();
        LocalDate localDate = LocalDate.of(2022,2,16);
        System.out.println(localDate.isAfter(now));
    }

    @Test
    public void testSend() throws IOException {
        File file = new File("/Users/wangxian/downloads/response.json");
        System.out.println(file.exists());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);

        String json = objectMapper.writeValueAsString(jsonNode);
        String params = "shrAddr=https://52.80.56.185:6888/shr&serviceName=personEntryServiceOsf&params="+json;
        log.info("params:{}",params);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, params);
        Request request = new Request.Builder()
        .url("http://shrcloud.kingdee.com/tenant/api/osf/v0")
        .method("POST", body)
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build();
        Response response = client.newCall(request).execute();
        log.info("rsponse status:{}",response.code());
       log.info("result:{}",response.body().string());
//        sendBsPost("http://shrcloud.kingdee.com/tenant/api/osf/v0","","utf-8");
    }

    public static String sendBsPost(String urlParam, String params, String charset) throws RuntimeException {
        StringBuffer resultBuffer;
        // 构建请求参数
        URLConnection con;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            URL realUrl = new URL(urlParam);
            // 打开和URL之间的连接
            con = realUrl.openConnection();
            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            con.setDoOutput(true);
            con.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            osw = new OutputStreamWriter(con.getOutputStream(), charset);
            // 发送请求参数
            osw.write(params);
            // flush输出流的缓冲
            osw.flush();
            // 定义BufferedReader输入流来读取URL的响应
            resultBuffer = new StringBuffer();
//            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
//            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return resultBuffer.toString();
    }
}

