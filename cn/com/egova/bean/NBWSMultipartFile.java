package cn.com.egova.bean;


import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class NBWSMultipartFile implements MultipartFile {
    private DataHandler dataHandler;

    private String fileName;

    public NBWSMultipartFile(DataHandler dataHandler, String fileName) {
        this.dataHandler = dataHandler;
        this.fileName = fileName;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return null;
    }

    @Override
    public String getContentType() {
        return this.dataHandler.getContentType();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return dataHandler.getInputStream();
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void transferTo(File arg0) throws IOException,
            IllegalStateException {

    }
}
