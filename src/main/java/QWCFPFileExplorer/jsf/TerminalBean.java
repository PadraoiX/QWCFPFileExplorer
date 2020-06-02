package QWCFPFileExplorer.jsf;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@SuppressWarnings("deprecation")
// @ManagedBean
@Named
@ViewScoped
public class TerminalBean implements Serializable {
    private static final long serialVersionUID = -1951219049568277333L;
    private String hostAddress;

    /**
     * @return the hostAddress
     */
    public String getHostAddress() {
        return hostAddress;
    }

    /**
     * @param hostAddress the hostAddress to set
     */
    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

}
