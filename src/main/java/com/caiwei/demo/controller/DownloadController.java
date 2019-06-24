package com.caiwei.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;

/**
 * @ClassName: LocalDownloadController
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/5/26 22:23
 */
@Controller
@Slf4j
public class DownloadController {

    @Autowired
    private FTPClient ftpClient;

    //从服务器本地下载
    @RequestMapping("download/local/{fileName}")
    public void localDownload(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        File file = new File("D:\\remote\\"+fileName);
        //设置响应头，把文件名字设置好
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        //解决编码问题
        response.setContentType("application/octet-stream; charset=utf-8");
        if(!file.exists()){
            log.warn("the file is not exists");
        }
        try {
            InputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024 * 1024];
            int flag;
            while ((flag = fileInputStream.read(buffer))!=-1){
                outputStream.write(buffer, 0, flag);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("can't get the response's outputStream");
            e.printStackTrace();
        }

    }

    //从文件服务器下载到前端
    @RequestMapping("download/remote/{fileName}")
    public void remoteDownload(@PathVariable("fileName") String fileName, HttpServletResponse response){
        try {
            OutputStream outputStream = response.getOutputStream();
            boolean result = ftpClient.retrieveFile("/home/caiwei/video/" + fileName, outputStream);
            if(result) {
                log.info("下载成功!");
            }else {
                log.warn("下载失败!");
            }
            //关闭文件流
            outputStream.close();

            //关闭连接
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从文件服务器下载到http服务器
    //可以从前端发送指令通过把文件服务器的输入写入到http的输出流来实现实时下载文件
    @RequestMapping("download/{fileName}")
    public void downLoadFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.connect("192.168.129.128", 21);
        ftpClient.login("caiwei", "1518290");
        int replyCode = ftpClient.getReplyCode();
        ftpClient.setDataTimeout(120000);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制文件

        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftpClient.disconnect();
            System.out.println("FTP连接失败");
        } else {
            System.out.println("FTP连接成功");
        }
        //同理，假如指定不存在的路径，会去根路径下查找
//        ftpClient.changeWorkingDirectory("test2");
        OutputStream outputStream = response.getOutputStream();
        //设置响应头，把文件名字设置好
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        //解决编码问题
        response.setContentType("application/octet-stream; charset=utf-8");
        boolean result = ftpClient.retrieveFile("/home/caiwei/video/" + fileName, outputStream);
        if (result) {
            System.out.println("下载成功!");
        } else {
            System.out.println("下载失败!");
        }
        //关闭连接
        ftpClient.logout();
        ftpClient.disconnect();
    }

    @RequestMapping("play/{fileName}")
    public void playVideoFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.connect("192.168.129.128", 21);
        ftpClient.login("caiwei", "1518290");
        int replyCode = ftpClient.getReplyCode();
        ftpClient.setDataTimeout(120000);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制文件

        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftpClient.disconnect();
            System.out.println("FTP连接失败");
        } else {
            System.out.println("FTP连接成功");
        }
        //同理，假如指定不存在的路径，会去根路径下查找
//        ftpClient.changeWorkingDirectory("test2");
        OutputStream outputStream = response.getOutputStream();
        //解决编码问题
        response.setContentType("application/octet-stream; charset=utf-8");
        boolean result = ftpClient.retrieveFile("/home/caiwei/video/" + fileName, outputStream);
        if (result) {
            System.out.println("下载成功!");
        } else {
            System.out.println("下载失败!");
        }
        //关闭连接
        ftpClient.logout();
        ftpClient.disconnect();
    }

}
