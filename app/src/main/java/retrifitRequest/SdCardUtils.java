package retrifitRequest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * Created by zhangli on 2019/1/21.
 */

public class SdCardUtils {

    public static void saveInfo(String fileName, String content) {
            try {
                // 打开一个随机访问文件流，按读写方式
                RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
                // 文件长度，字节数
                long fileLength = randomFile.length();
                // 将写文件指针移到文件尾。
                randomFile.seek(fileLength);
                randomFile.writeBytes(content);
                randomFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    public static void saveInfo1(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName,"w");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

        public static String getSaveInfo(Context context,String fileName) {
            String content = "";
            FileInputStream inputStream;

            try {
                inputStream = context.openFileInput(fileName);
                byte temp[] = new byte[1024];
                StringBuilder sb = new StringBuilder("");
                int len = 0;
                while ((len = inputStream.read(temp)) > 0){
                    sb.append(new String(temp, 0, len));
                }
                content = sb.toString();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        return content;
    }

    public static String redSavaInfo() {

        BufferedReader reader=null;

        FileInputStream fis=null;
        StringBuilder sbd=new StringBuilder();
        File root= Environment.getExternalStorageDirectory();
        try {
            fis=new FileInputStream(root+"/CegzLog.txt");
            reader= new BufferedReader(new InputStreamReader(fis));
            String row="";
            try {
                while ((row=reader.readLine())!=null){

                    sbd.append(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    fis.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  sbd.toString();
    }

    public static void bufferSave(String msg,String fileName) {
        try {
            File root= Environment.getExternalStorageDirectory();
            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName, false));
            bfw.write(msg);
            bfw.newLine();
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
