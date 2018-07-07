package com.lzy.j2ee.client.action.testjms;

import com.alibaba.fastjson.JSON;
import com.lzy.j2ee.client.utils.EjbHelper;
import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessMessageQueueProducer;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessMessageTopicProducer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lzy on 2017/8/17.
 */
public class TestJmsAction extends ActionSupport {

    //=========================== 单文件上传 =================================
    /**
     * 上传单文件
     * 使用Struts2 文件上传功能时，该属性与页面的name 必须一致，如页面这样定义：
     * <input type="file" name="uploadFile" />
     *
     * 注意，uploadFile并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
     * 而临时目录的定义是在struts-jmsproducer.xml文件的这一行：
     * <constant name="struts.multipart.saveDir" value="D:/tempUploadFile" />
     *
     * 别忘了get set 方法
     */
    private File uploadFile;

    /**
     * 上传单文件
     * 文件MIME类型
     * 这个属性是很有讲究的，必须在File 的属性名后面加ContentType
     */
    private String uploadFileContentType;

    /**
     *
     * 上传单文件
     *
     * 这个属性也是很有讲究的，必须在File 的属性名后面加FileName
     * 如上面的File 属性不是uploadFile,而是hello,则这里就为helloFileName
     *
     * 别忘了get set 方法
     */
    private String uploadFileFileName;

    //=========================== 多文件上传 =================================
    /**
     * 上传多文件
     * 注意，上传多文件使用数组
     * 使用Struts2 文件上传功能时，该属性与页面的name 必须一致，如页面这样定义：
     * <input type="file" name="multipartFileUpload" />
     * <input type="file" name="multipartFileUpload />
     * <input type="file" name="multipartFileUpload" />
     *
     * 别忘了get set 方法
     */
    private File[] multipartFileUpload;

    /**
     * 上传单文件
     * 文件MIME类型
     * 这个属性是很有讲究的，必须在File 的属性名后面加ContentType
     */
    private String multipartFileUploadContentType;

    /**
     *
     * 上传多文件
     * 注意，上传多文件使用数组
     * 这个属性也是很有讲究的，必须在File 的属性名后面加FileName
     * 如上面的File 属性不是uploadFileFileName,而是hello,则这里就为helloFileName
     *
     * 别忘了get set 方法
     */
    private String[] multipartFileUploadFileName;

    //=========================== 单、多文件上传（实体映射） =================================
    /**
     * 如果页面定义很多不同的name,就会导致有很多属性，这时封装到一个类当中是最优雅的方式
     *
     *  别忘了定义get set
     */
    private UploadFilePojo uploadFilePojo;


    //=========================== 文件下载 =================================
    private InputStream inputStream;

    /**
     * contentType参数设置为"image/jpeg"，则将文件发送给浏览器，并让浏览器显示其内容。
     * 将contentType参数设置为"application/octet-stream"，则将以文件下载的方式发送给浏览器。
     * 换一种说法就是，若为"image/jpeg"，就是告诉浏览器这是一个jpg文件，请显示它的内容；
     * 若为"application/octet-stream"，就是告诉浏览器，问用户想不想保存这个文件
     */
    private String contentType;

    private String contentDisposition;

    /**
     * 下载文件时页面传的文件绝对路径
     */
    private String filePath;

    /**
     * 获得文件列表
     * @return
     */
    @SkipValidation
    public String loasFileList(){
        List<String> fileList = new ArrayList<String>();
        File file = new File(getUploadTargetDir());

        /**
         * 解耦的方式获得Web资源
         * 说白就是没有servlet相关的API，操作Map
         */
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        if (!file.exists()){
            request.put("msg", "没有文件可下载，是否上传");
        }

        File[] files = file.listFiles();
        for (File tempFile : files){
            fileList.add(tempFile.getAbsolutePath());
        }
        request.put("fileList", fileList);
        return SUCCESS;
    }

    /**
     * 图片下载
     * @return
     */
    @SkipValidation
    public String download(){
        try {
            inputStream = new FileInputStream(getFilePath());
            contentType = "image/jpeg";
            //attachment, 总是弹出下载提示框;
            //若不设置, 则以浏览器默认方式打开.
            String fileName = getFilePath().split("\\\\")[getFilePath().split("\\\\").length - 1];
            contentDisposition = "attachment;filename=" + fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
}
        sendMsgToTopic("成功下载文件：" + getFilePath() + "到页面!");
        return "download";
    }

    /**
     * 上传单文件Action
     * @return
     */
    public String uploadFile(){
        String targetDir = getUploadTargetDir();
        File targetFile = new File(targetDir + uploadFileFileName);
        /**
         * 注意，再次演示国际化
         */
        String result = getText("uploadSuccessResult");

        try {
            FileUtils.copyFile(uploadFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            result = getText("uploadFalidResult");
            ServletActionContext.getRequest().setAttribute("msg", result);
            sendMsgToQueue("上传图片：" + getUploadFileFileName() + "失败!");
            return SUCCESS;
        }

        /**
         * 紧耦合的方式获取web资源，项目中如果不太推荐使用，我这里是演示，下面也有松耦合的方式
         */
        ServletActionContext.getRequest().setAttribute("msg", result);
        sendMsgToQueue("成功上传图片：" + getUploadFileFileName() + "到目录：" + getUploadTargetDir());
        return SUCCESS;
    }

    private String getUploadTargetDir(){
        String targetDir = "D:/tempUploadFile" + "/uploads/";
        File file = new File(targetDir);
        if (!file.exists()){
            file.mkdirs();
        }
        return targetDir;
    }

    /**
     * 上传多文件Action
     * @return
     */
    public String uploadMultipartFile(){

        String targetDir = getUploadTargetDir();
        String result = getText("uploadSuccessResult");

        try {
            for (int i = 0; i < getMultipartFileUpload().length; i++) {
                String currentTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()).toString();
                File targetFile = new File(targetDir + currentTime + getMultipartFileUploadFileName()[i]);

                FileUtils.copyFile(getMultipartFileUpload()[i], targetFile);

            }
        }catch (Exception ex){
            ex.printStackTrace();
            result = getText("uploadFalidResult");
            ServletActionContext.getRequest().setAttribute("msg", result);
            /**
             * 某个文件失败，则删除已经上传的，类似事务回滚的概念
             */
            try {
                FileUtils.deleteDirectory(new File(targetDir));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("删除目录失败!");
            }
            sendMsgToQueue("上传图片：" + getMultipartFileUploadFileName() + "失败!");
            return SUCCESS;
        }
        ServletActionContext.getRequest().setAttribute("msg", result);
        sendMsgToQueue("成功上传图片：" + JSON.toJSONString(getMultipartFileUploadFileName()) + "到目录：" + getUploadTargetDir());
        return SUCCESS;
    }

    /**
     * 上传单文件实体映射Action
     * @return
     */
    public String uploadFileByPojo(){

        int result = uploadFilePojo.uploadFile();

        /**
         * 松耦合的方式获取web资源，项目中推荐使用
         */
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        if(result == 0){
            sendMsgToQueue("成功上传图片：" + getUploadFilePojo().getUploadFileFileName() + "到目录：" + getUploadTargetDir());
            request.put("msg", getText("uploadSuccessResult"));
        }else {
            request.put("msg", getText("uploadFalidResult"));

            /**
             * 发送消息到队列
             */
            sendMsgToQueue("上传图片：" + getUploadFilePojo().getUploadFileFileName() + "失败!");
        }
        return SUCCESS;
    }

    /**
     * 上传多文件实体映射Action
     * @return
     */
    public String uploadMultipartFileByPojo(){

        int result = uploadFilePojo.uploadMultipartFile();

        /**
         * 松耦合的方式获取web资源，项目中推荐使用
         */
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        if(result == 0){
            request.put("msg", getText("uploadSuccessResult"));
            sendMsgToQueue("成功上传图片：" + JSON.toJSONString(getUploadFilePojo().getMultipartFileUploadFileName()) + "到目录：" + getUploadTargetDir());
        }else {
            request.put("msg", getText("uploadFalidResult"));
            sendMsgToQueue("上传图片：" + getUploadFilePojo().getUploadFileFileName() + "失败!");
        }
        return SUCCESS;
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    /**
     * 在项目中，这些应该在页面做校验，不建议在后台验证，这里只是演示
     */
    @Override
    public void validate() {
        String requestUri = ServletActionContext.getRequest().getRequestURI();
        String[] tempArr = requestUri.split("/");
        String tempStr = tempArr[tempArr.length - 1];

        /**
         * 根据请求的路径来验证对应的参数映射的属性是否为空
         */
        if (("TestJmsAction_uploadFile").equals(tempStr.split("\\.")[0])){
            if (getUploadFile() == null){
                this.addActionError(getText("uploadFileNotNull"));
            }
        }else if (("TestJmsAction_uploadMultipartFile").equals(tempStr.split("\\.")[0])){
            if (getMultipartFileUpload() == null || getMultipartFileUpload().length == 0){
                this.addActionError(getText("uploadFileNotNull"));
            }
        }else if (("TestJmsAction_uploadFileByPojo").equals(tempStr.split("\\.")[0])){
            if (getUploadFilePojo() == null || getUploadFilePojo().getUploadFile() == null){
                this.addActionError(getText("uploadFileNotNull"));
            }
        }else if (("TestJmsAction_uploadMultipartFileByPojo").equals(tempStr.split("\\.")[0])){
            if (getUploadFilePojo() == null || getUploadFilePojo().getMultipartFileUpload() == null){
                this.addActionError(getText("uploadFileNotNull"));
            }
        }else{
            this.addActionError(getText("illegalRequest"));
        }
    }

    public File getUploadFile() {
        return uploadFile;
    }

    /**
     * 发送队列消息
     * @param msg
     */
    private void sendMsgToQueue(String msg){
        IStatelessMessageQueueProducer statelessMessageQueueProducer =
                (IStatelessMessageQueueProducer) EjbHelper.localByJndi(
                        EjbHelper.getJndi(IStatelessMessageQueueProducer.class, Constant.StatelessMessageQueueProducer)
                );

        String sendResult = statelessMessageQueueProducer.send(msg);
        if (Constant.SendJmsMessageSuccessCode.equals(sendResult)){
            System.out.println("+++++++++++++++++++++发送队列消息成功!+++++++++++++++++++++++");
        }else{
            System.out.println("---------------------发送队列消息失败!-----------------------");
        }
    }

    /**
     * 发送主题消息
     * @param msg
     */
    private void sendMsgToTopic(String msg){
        IStatelessMessageTopicProducer statelessMessageTopicProducer =
                (IStatelessMessageTopicProducer) EjbHelper.localByJndi(
                        EjbHelper.getJndi(IStatelessMessageTopicProducer.class, Constant.StatelessMessageTopicProducer)
                );

        String sendResult = statelessMessageTopicProducer.send(msg);
        if (Constant.SendJmsMessageSuccessCode.equals(sendResult)){
            System.out.println("+++++++++++++++++++++发送主题消息成功!+++++++++++++++++++++++");
        }else{
            System.out.println("---------------------发送主题消息失败!-----------------------");
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

    public File[] getMultipartFileUpload() {
        return multipartFileUpload;
    }

    public void setMultipartFileUpload(File[] multipartFileUpload) {
        this.multipartFileUpload = multipartFileUpload;
    }

    public String[] getMultipartFileUploadFileName() {
        return multipartFileUploadFileName;
    }

    public void setMultipartFileUploadFileName(String[] multipartFileUploadFileName) {
        this.multipartFileUploadFileName = multipartFileUploadFileName;
    }

    public UploadFilePojo getUploadFilePojo() {
        return uploadFilePojo;
    }

    public void setUploadFilePojo(UploadFilePojo uploadFilePojo) {
        this.uploadFilePojo = uploadFilePojo;
    }

    public String getUploadFileContentType() {
        return uploadFileContentType;
    }

    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
    }

    public String getMultipartFileUploadContentType() {
        return multipartFileUploadContentType;
    }

    public void setMultipartFileUploadContentType(String multipartFileUploadContentType) {
        this.multipartFileUploadContentType = multipartFileUploadContentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
}
