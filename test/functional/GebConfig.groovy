/**
 * @author Noam Y. Tenne
 */

import org.openqa.selenium.chrome.ChromeDriver

driver = {
    System.setProperty('webdriver.chrome.driver', '/home/noam/selenium-drivers/chromedriver')
    def driverInstance = new ChromeDriver()
    driverInstance.manage().window().maximize()
    driverInstance
}