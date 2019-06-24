package com.caiwei.demo.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: FtpConfig
 * @Description: 配置ftp客户端相关信息， 这个package主要做web服务器从ftp服务器下载文件供前端实时调用，就是把从文件服务器读取的流直接写入http的输入流
 * @auther: caiwei
 * @date: 2019/5/26 21:27
 * 注意：这么搞，不能用，暂时未研究原因，能用的在downloadController中
 */
@Configuration
@Slf4j
public class FtpConfig {

    //ftp服务器地址
    @Value("${ftp.ip}")
    private String ip;
    //ftp服务器端口号默认为21
    @Value("${ftp.port}")
    private Integer port;
    //ftp登录账号
    @Value("${ftp.user}")
    private String username;
    //ftp登录密码
    @Value("${ftp.password}")
    private String password;
    //ftp连接超时时间
    @Value("${ftp.timeout}")
    private Integer timeout;

    @Bean
    public FTPClient ftpClient() {
        //apache中的FTPClient是大写的，sun包中的FtpClient是小写的
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
        try {
            log.info("ip"+ip+"port"+port+"user"+username+"password"+password+"timeout"+timeout);
            //连接ftp服务器
            ftpClient.connect(ip);
            //登录ftp服务器
            ftpClient.login(username, password);
            ftpClient.setDataTimeout(timeout);
            //设置为二进制文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                log.warn("connect failed...ftp服务器");
            }
            log.info("connect successful...ftp服务器");
        } catch (IOException e) {
            log.warn("连接ftp服务器失败");
            e.printStackTrace();

        }
        return ftpClient;
    }
}
