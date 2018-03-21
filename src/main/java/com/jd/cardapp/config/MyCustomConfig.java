package com.jd.cardapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
@PropertySource("classpath:custom.properties")
public class MyCustomConfig {

    private String upload;

    private String cardfolder;

    private String userfolder;

    private String filefolder;

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getCardfolder() {
        return cardfolder;
    }

    public void setCardfolder(String cardfolder) {
        this.cardfolder = cardfolder;
    }

    public String getUserfolder() {
        return userfolder;
    }

    public void setUserfolder(String userfolder) {
        this.userfolder = userfolder;
    }

    public String getFilefolder() {
        return filefolder;
    }

    public void setFilefolder(String filefolder) {
        this.filefolder = filefolder;
    }
}
