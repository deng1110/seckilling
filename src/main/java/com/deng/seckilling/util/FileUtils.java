package com.deng.seckilling.util;

import java.io.*;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:09
 */
public class FileUtils {
    public static void main(String[] args) {

        writeFileContext("d:/deng.txt", "utf-8", true, true, "hhhhh");
        System.out.println("----");
        System.out.println(readFileContext("d:/deng.txt", "gbk"));
        System.out.println("----");
    }

    /**
     * 从文件中读取内容
     *
     * @param fileName 带读取的文件完整路径，例：d:/data.txt，如果没有该文件则会提示文件找不到
     * @param encoding 字符编码名称，例：gbk,utf-8
     * @return StringBuilder 线程不安全，但是速度快。返回文件中读到的内容
     */
    public static StringBuilder readFileContext(String fileName, String encoding) {
        StringBuilder context = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), encoding));
            String line = "";
            while (null != (line = in.readLine())) {
                context.append(line);
                context.append("\r\n");
            }
            context.deleteCharAt(context.length() - 1);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Invalid character set name");
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found");
        } catch (IOException e) {
            System.err.println("ReadFile Exception");
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("ReadFile Exception");
                }
            }
        }
        return context;
    }

    /**
     * 向文件中写入数据
     *
     * @param fileName   目标文件完整路径，例：d:/data.txt,如果无此文件则默认创建该文件
     * @param encoding   字符编码名称，例：gbk,utf-8
     * @param isAppend   是否追加，true：在该文件末尾追加，false：直接覆盖文件内容
     * @param isNextLine 是否换行
     * @param context    待写入的字符，String类型
     */
    public static void writeFileContext(String fileName, String encoding, boolean isAppend, boolean isNextLine, String context) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName), isAppend), encoding));
            if (isNextLine) {
                out.write("\r\n");
            }
            out.write(context);
            System.out.println("已向" + fileName + "中写入成功");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Invalid character set name");
        } catch (IOException e) {
            System.err.println("WriteFile Exception");
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println("WriteFile Exception");
                }
            }
        }
    }
}
