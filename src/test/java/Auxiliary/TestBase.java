package Auxiliary;

import org.openqa.selenium.WebDriver;

public class TestBase extends GeneralMethods {
    public WebDriver driver;
    String webBrowser = "Chrome";

    protected WebDriver getDriver() {
        return driver;
    }

    public TestBase() {

        System.out.println(System.getProperty("os.name"));
        switch (System.getProperty("os.name")) {
            case "Windows 10":
                System.out.println("Windows Environment");
                switch (webBrowser) {
                    case "Chrome":
                        driver = Drivers.localWindowsChrome();
                        break;
                    case "Edge":
                        driver = Drivers.localWindowsEdge();
                        break;
                    default:
                        driver = Drivers.localWindowsFirefox();
                        break;
                }
                break;
            case "Mac OS X":
                System.out.println("MacOS environemnt");
                switch (webBrowser) {
                    case "Chrome":
                        driver = Drivers.localMacOSChrome();
                        break;
                    case "Edge":
                        driver = Drivers.localMacOSEdge();
                        break;
                    case "Safari":
                        driver = Drivers.localMacOSSafari();
                        break;
                    default:
                        driver = Drivers.localMacOSFirefox();
                        break;
                }
                break;
            default:
                System.out.println("Unix/Linux settings");
                if ("Chrome".equals(webBrowser)) {
                    driver = Drivers.localLinuxChrome();
                } else {
                    driver = Drivers.locaLinuxFirefox();
                }
                break;
        }
    }

    public void close() {
        closeDriver(getDriver());
    }
}