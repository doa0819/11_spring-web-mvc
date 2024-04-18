package com.ohgiraffers.fileupload;

public class FileDTO {

    // 네가지 데이터 받기
    private String originFileName;
    private String savedName;
    public String filePath;
    public String fileDescription;

    public FileDTO() {
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public FileDTO(String originFileName, String savedName, String filePath, String fileDescription) {
        this.originFileName = originFileName;
        this.savedName = savedName;
        this.filePath = filePath;
        this.fileDescription = fileDescription;
    }
}
