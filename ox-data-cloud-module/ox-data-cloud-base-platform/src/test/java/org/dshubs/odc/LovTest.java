package org.dshubs.odc;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author create by wangxian 2022/4/3
 */
public class LovTest {
    @Test
    public void quickSort() {
        //快速排序
        int[] arr = {5, 3, 2, 4, 1};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }


    @Test
    public void checkEmailTest() {
        System.out.println(checkEmail("wangxian_drew@163.com"));
        System.out.println(checkEmail("wangxian_drew-163.com"));
    }

    @Test
    public void checkPhoneTest() {
        System.out.println(checkPhone("wangxian_drew@163.com"));
    }

    @Test
    public void getLocalIpTest() {
        System.out.println(getLocalIp());
    }

    //获取两个日期之间的天数
    @Test
    public void getDaysBetweenTest() {
        System.out.println(getDaysBetween("2019-01-01", "2019-01-02"));
    }

    @Test
    public void getVerifyCodeTest(){
        System.out.println(getVerifyCode());
    }



    //验证邮箱格式
    public boolean checkEmail(String email) {
        String regex = "^\\w+@\\w+(\\.\\w+)+$";
        return email.matches(regex);
    }

    //验证手机号码格式
    public boolean checkPhone(String phone) {
        String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
        return phone.matches(regex);
    }


    //生成验证码
    public String getVerifyCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }

    //获取服务器配置的CPU数量
    public int getCpuNum() {
        return Runtime.getRuntime().availableProcessors();
    }
    //获取服务器内存大小
    public long getMemorySize() {
        return Runtime.getRuntime().maxMemory();
    }


    //获取本地IP地址
    public String getLocalIp() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    //获取两个日期之间的天数
    public int getDaysBetween(String startDate, String endDate) {
        int days = 0;
        try {
            days = (int) (((new java.text.SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime() - new java.text.SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime()) / (24 * 60 * 60 * 1000)) + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    private void quickSort(int[] arr, int i, int i1) {
        if (i < i1) {
            int j = partition(arr, i, i1);
            quickSort(arr, i, j - 1);
            quickSort(arr, j + 1, i1);
        }
    }

    private int partition(int[] arr, int i, int i1) {
        int temp = arr[i];
        while (i < i1) {
            while (i < i1 && arr[i1] >= temp) {
                i1--;
            }
            arr[i] = arr[i1];
            while (i < i1 && arr[i] <= temp) {
                i++;
            }
            arr[i1] = arr[i];
        }
        arr[i] = temp;
        return i;
    }
}
