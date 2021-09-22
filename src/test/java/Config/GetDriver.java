package Config;

import org.openqa.selenium.WebDriver;

public class GetDriver {
    public WebDriver driver;
    String webBrowser = "Chrome";

    public GetDriver() {
        switch (System.getProperty("os.name")) {
            case "Windows 10":
                switch (webBrowser) {
                    case "Chrome":
                        driver = Drivers.windowsChrome();
                        break;
                    case "Edge":
                        driver = Drivers.windowsEdge();
                        break;
                    default:
                        driver = Drivers.windowsFirefox();
                        break;
                }
                break;

            case "Mac OS X":
                switch (webBrowser) {
                    case "Chrome":
                        driver = Drivers.macOSChrome();
                        break;
                    case "Edge":
                        driver = Drivers.macOSEdge();
                        break;
                    case "Safari":
                        driver = Drivers.macOSSafari();
                        break;
                    default:
                        driver = Drivers.macOSFirefox();
                        break;
                }
                break;

            default:
                if ("Chrome".equals(webBrowser)) {
                    driver = Drivers.linuxChrome();
                } else {
                    driver = Drivers.linuxFirefox();
                }
                break;
        }
    }
}