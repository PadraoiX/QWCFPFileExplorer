package QWCFPFileExplorer.jsf;

import java.io.File;
import java.net.URLEncoder;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import QWCFPFileExplorer.jsf.util.MessageUtil;
import QWCFPFileExplorer.jsf.util.SecurityBean;
import QWCFPFileExplorer.mp3.Mp3Meta;
import QWCFPFileExplorer.mp3.Mp3Util;
import QWCFPFileExplorer.security.AESEncryption;
import QWCFPFileExplorer.spring.AppProperties;

@SuppressWarnings("deprecation")
public abstract class BaseBean {
    final static Logger logger = LoggerFactory.getLogger(BaseBean.class);

    public static final String ENCODING_SCHEME = "UTF-8";
    // @ManagedProperty("#{securityBean}")
    @Inject
    private SecurityBean securityBean;
    // @ManagedProperty("#{appProperties}")
    @Inject
    private AppProperties appProperties;
    //@ManagedProperty("#{applicationConstants}")
    @Inject
    private ApplicationConstants applicationConstants;

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public boolean hasFacesMessages() {
        return MessageUtil.hasWarnOrHigherMessage();
    }

    public void redirectFatalErrors(String errorMessage, String errorSummary) {
        try {
            StringBuilder sbNav = new StringBuilder("/jsf/error/error.jsf");
            sbNav.append("?errorMessage=")
                    .append(errorMessage == null ? "" : URLEncoder.encode(errorMessage, ENCODING_SCHEME))
                    .append("&errorSummary=")
                    .append(errorSummary == null ? "" : URLEncoder.encode(errorSummary, ENCODING_SCHEME));

            getFacesContext().getExternalContext().redirect(
                    getFacesContext().getExternalContext().getRequestContextPath()
                    + sbNav.toString());
        } catch (Throwable e) {
            logger.error("Error redirecting to display error with \nerror message: "
                    + errorMessage + "\nerror summary: " + errorSummary, e);
        }
    }

    public void redirectPage(String url) {
        try {
            getFacesContext().getExternalContext().redirect(
                    getFacesContext().getExternalContext().getRequestContextPath() + url);
        } catch (Throwable e) {
            logger.error("Cannot redirect url: " + url, e);
        }
    }

    public HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(true);
    }

    /**
     * This should is used when doing a redirect to handle encoding issues
     *
     * @param message
     * @return
     */
    public String encryptAndEncode(String message) {
        return encryptAndEncode(message, getSecurityBean().getLoginUser());
    }

    public String encryptAndEncode(String message, String key) {
        String encodedMesg = null;
        try {
            String encryptedMesg = AESEncryption.encrypt(message, key);
            // encrypted message has special characters that will be unescaped, so needs to encode it
            encodedMesg = URLEncoder.encode(encryptedMesg, ENCODING_SCHEME);
        } catch (Throwable e) {
            logger.error("Error encryptAndEncode message: " + message + " with key: " + key, e);
            redirectFatalErrors("Encrypting Error", "Error trying to encrypt message: " + e.getMessage());
        }

        return encodedMesg;
    }

    public String encrypt(String message) {
        return encrypt(message, getSecurityBean().getLoginUser());
    }

    public String encrypt(String message, String key) {
        String encryptedMesg = null;
        try {
            encryptedMesg = AESEncryption.encrypt(message, key);
        } catch (Throwable e) {
            logger.error("Error encrypting message: " + message + " with key: " + key, e);
            redirectFatalErrors("Encrypting Error", "Error trying to encrypt message: " + e.getMessage());
        }

        return encryptedMesg;
    }

    public String decrypt(String message) {
        return decrypt(message, getSecurityBean().getLoginUser());
    }

    public String decrypt(String message, String key) {
        String decryptedMesg = null;
        try {
            decryptedMesg = AESEncryption.decrypt(message, key);
        } catch (Throwable e) {
            logger.error("Error decrypting message: " + message + " with key: " + key, e);
            redirectFatalErrors("Decrypting Error", "Error trying to decrypt message: " + e.getMessage());
        }

        return decryptedMesg;
    }

    public Mp3Meta getMp3Meta(File file) {
        return Mp3Util.getMp3Meta(file.getAbsolutePath());
    }
	
    /**
     * @return the securityBean
     */
    public SecurityBean getSecurityBean() {
        return securityBean;
    }

    /**
     * @param securityBean the securityBean to set
     */
    public void setSecurityBean(SecurityBean securityBean) {
        this.securityBean = securityBean;
    }
	
    /**
     * @return the appProperties
     */
    public AppProperties getAppProperties() {
        return appProperties;
    }

    /**
     * @param appProperties the appProperties to set
     */
    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    /**
     * @return the applicationConstants
     */
    public ApplicationConstants getApplicationConstants() {
        return applicationConstants;
    }

    /**
     * @param applicationConstants the applicationConstants to set
     */
    public void setApplicationConstants(ApplicationConstants applicationConstants) {
        this.applicationConstants = applicationConstants;
    }
}
