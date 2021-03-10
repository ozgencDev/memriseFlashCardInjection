import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class translate {
    public static WebDriver driver = null;
    public void findGitMethod() {

        File fl = new File("C:\\Users\\AliDev\\Desktop\\GitKomut.txt");
        BufferedReader reader = null;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> karşılıkListesi = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fl));
            Scanner scanner = new Scanner(reader);

            int sayaç = 0;int sayaç2 = 1;
            while (scanner != null) {
                String s = scanner.nextLine();
                if (sayaç == 0) {
                    list.add(s);
                } else if (sayaç % 3 == 0) {
                    list.add(s);
                } else if (sayaç2 % 2 == 0) {
                    karşılıkListesi.add(s.replaceAll("\n\n", "\n"));
                }
                sayaç++;sayaç2++;


            }
        } catch (Exception e) {
            for (String s : list) {
                System.out.println(s);
            }
            for (String s : karşılıkListesi) {
                if (s == "") {

                } else {
                    System.out.println(s);
                }
            }
        }
    }
    public void memriseElements(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\AliDev\\Desktop\\JavaKütüphane\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://app.memrise.com/signin");

        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("ozgencdev@gmail.com");

        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Java11111000110");

        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/form/div[3]/div[1]/button")).click();
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }catch (Exception E){

        }



        driver.findElement(By.xpath("/html/body/div[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"promotion-modal\"]/div/button")).click();

        driver.findElement(By.xpath("//*[@id=\"course-5963431\"]/div/div/div[2]/div/div/div[1]/p/a")).click();

        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/a/span[2]")).click();

        driver.findElement(By.xpath("//*[@id=\"l_13391990\"]/div[3]/table/tbody[2]/tr[2]/td[2]/input")).sendKeys("Ali");

        driver.findElement(By.xpath("//*[@id=\"l_13391990\"]/div[3]/table/tbody[2]/tr[2]/td[3]/input")).sendKeys("Özgenç");

        driver.findElement(By.xpath("//*[@id=\"l_13391990\"]/div[3]/table/tbody[2]/tr[2]/td[1]/i")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/a")).click();


    }

    public static void main(String[] args) {
        translate tr = new translate();
        tr.memriseElements();
        //tr.findGitMethod();


    }
}
