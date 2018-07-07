package com.lzy.j2ee.client.action.testjms;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by laizhiyuan on 2017/8/13.
 */
public class UploadFilePojo {

    /**
     * 上传单文件
     * 使用Struts2 文件上传功能时，该属性与页面的name 必须一致，如页面这样定义：
     * <input type="file" name="xxxxx.uploadFile" />
     * xxxxx是指代该类在Action中定义的引用名称，或者说是属性名称
     *
     * 别忘了get set 方法
     */
    private File uploadFile;



    /**
     *
     * 上传单文件
     *
     * 这个属性也是很有讲究的，必须在File 的属性名后面加FileName
     * 如上面的File 属性不是xxxxx.uploadFile,而是xxxxx.hello,则这里就为helloFileName
     *
     * 别忘了get set 方法
     */
    private String uploadFileFileName;

    /**
     * 上传单文件
     * 文件MIME类型
     * 这个属性是很有讲究的，必须在File 的属性名后面加ContentType
     */
    private String uploadFileContentType;

    /**
     * 上传多文件
     * 注意，上传多文件使用数组
     * 使用Struts2 文件上传功能时，该属性与页面的name 必须一致，如页面这样定义：
     * <input type="file" name="xxxxx.multipartFileUpload" />
     * <input type="file" name="xxxxx.multipartFileUpload />
     * <input type="file" name="xxxxx.multipartFileUpload" />
     *
     * xxxxx是指代该类在Action中定义的引用名称，或者说是属性名称
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
     * 如上面的File 属性不是xxxxx.uploadFileFileName,而是xxxxx.hello,则这里就为helloFileName
     *
     * 别忘了get set 方法
     */
    private String[] multipartFileUploadFileName;

    private int result = 0;

    public File getUploadFile() {
        return uploadFile;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 多文件上传
     * 这里演示用输入输出流的方式
     * @return
     */
    public int uploadMultipartFile(){
        InputStream in = null;
        OutputStream out = null;
        try {
            for (int i = 0; i < getMultipartFileUpload().length; i++) {
                in = new FileInputStream(getMultipartFileUpload()[i]);
                String currentTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()).toString();
                out = new FileOutputStream(getTargetDir() + currentTime + getMultipartFileUploadFileName()[i]);

                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf);
                }
            }
        } catch (Exception e) {
            setResult(-1);
            e.printStackTrace();
            /**
             * 某个文件失败，则删除已经上传的，类似事务回滚的概念
             */
            try {
                FileUtils.deleteDirectory(new File(getTargetDir()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally{
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return getResult();
    }

    public String getTargetDir(){
        String targetDir = "D:/tempUploadFile" + "/uploads/";
        File file = new File(targetDir);
        if (!file.exists()){
            file.mkdirs();
        }
        return targetDir;
    }

    /**
     * 单文件上传
     * 这里演示用输入输出流的方式
     * @return
     */
    public int uploadFile(){
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(getUploadFile());
            String currentTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()).toString();
            out = new FileOutputStream(getTargetDir() + currentTime + getUploadFileFileName());

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1){
                out.write(buf);
            }
        } catch (Exception e) {
            setResult(-1);
            e.printStackTrace();
            /**
             * 某个文件失败，则删除已经上传的，类似事务回滚的概念
             */
            try {
                FileUtils.deleteDirectory(new File(getTargetDir()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally{
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return getResult();
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
}
