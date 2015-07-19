package me.stronglift.stronglift.model;

/**
 * Reprezentacija REST greške koju možemo dobiti od servera.
 * <p>
 * Created by Dusan Eremic.
 */
public class RestError {

    /**
     * HTTP status code
     */
    private String httpStatusCode;

    /**
     * Error code koji smo sami definisali na niovu APIja.
     */
    private String errorCode;

    /**
     * Poruka greške koja može biti prikazana korisniku.
     */
    private String errorMessage;

    /**
     * Poruka greške koja će biti prikazana programeru.
     */
    private String developerErrorMessage;

    /**
     * Link ili email koji nudi više informacija o nastaloj grešci.
     */
    private String moreInfo;

    /**
     * Konstruktor
     */
    public RestError() {
    }

    // ##### GET i SET metode #####

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

    // ##### GET i SET metode #####

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
