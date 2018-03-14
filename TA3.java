package framework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Leonela on 3/12/2018.
 */
public class TA3 {
    public static void main(String arg [])  {

        System.setProperty("webdriver.chrome.driver", "C:/Users/Leonela/Desktop/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://darksky.net");

        // select timeMachine
        driver.findElement(By.xpath("//*[@id=\"timeMachine\"]/div[2]/a")).click();
        // pick tomorrow's day from day picker

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1); // to get tomorrows day
        Date tomorrow = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        String day = sdf.format(tomorrow.getTime());


        WebElement dateWidget = driver.findElement(By.className("calendar"));
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        for (WebElement cell: columns){
           String days = cell.getText();
            //Select tomorrow Date
            if (days.equals(day)){
                cell.click();
                WebElement clickable = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[1]/div"));

                Assert.assertFalse(clickable.isSelected());
                Assert.assertEquals(clickable,driver.findElement(By.className("date")));

                System.out.println(String.format("The date is displayed correctly, Tomorrow is " + tomorrow));
                break;

            }
        }

        // I verify selected date is not clickable
        //  WebElement date = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[1]/div"));
        //  String day = date.getText(); //get tomorrow from the webpage



         driver.quit();


    }

}
