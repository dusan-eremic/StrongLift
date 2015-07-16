package me.stronglift.stronglift.rest;

/**
 * Created by dusan on 16/07/15.
 */
public class RestError {

    private String httpStatusCode;
    private String errorCode;
    private String errorMessage;
    private String developerErrorMessage;
    private String moreInfo;

    public RestError() {
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDeveloperErrorMessage() {
        return developerErrorMessage;
    }

    public void setDeveloperErrorMessage(String developerErrorMessage) {
        this.developerErrorMessage = developerErrorMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "RestError{" +
                "httpStatusCode='" + httpStatusCode + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", developerErrorMessage='" + developerErrorMessage + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }
}
