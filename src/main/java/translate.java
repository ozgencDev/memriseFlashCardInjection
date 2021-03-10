import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class translate {
    public static WebDriver driver = null;

    public ArrayList<ArrayList<String>> findGitMethod(String directory) {
        //"C:\\Users\\AliDev\\Desktop\\GitKomut.txt"
        File fl = new File(directory);
        BufferedReader reader = null;
        ArrayList<String> foreignLang = new ArrayList<>();  ArrayList<String> nativeLang = new ArrayList<>();
        ArrayList<ArrayList<String>> flashCardHolderList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fl));
            Scanner scanner = new Scanner(reader);

            int sayaç = 0;int sayaç2 = 1;int bölen = 2;
            while (scanner != null) {
                String s = scanner.nextLine();
                if (sayaç == 0) {
                    foreignLang.add(s);
                } else if (sayaç % 3 == 0) {
                    foreignLang.add(s);
                } else if (sayaç2 % bölen == 0) {
                    if(s == ""){

                    }else {
                        nativeLang.add(s);
                        bölen+=3;
                    }

                }
                sayaç++;sayaç2++;


            }
        } catch (Exception e) {
            flashCardHolderList.add(foreignLang);
            flashCardHolderList.add(nativeLang);
        }
        return flashCardHolderList;//liste
    }
    public void memriseElements(String chromeDrivePath,String directory,String fileName,String fileName2,String sourceFile,int option){
        //chromeDrivePath = "C:\\Users\\AliDev\\Desktop\\JavaKütüphane\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromeDrivePath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.memrise.com/signin");
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("ozgencdev@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Java11111000110");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/form/div[3]/div[1]/button")).click();
        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }catch (Exception E){

        }
        driver.findElement(By.xpath("/html/body/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"promotion-modal\"]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"course-5963550\"]/div/div/div[2]/div/div/div[1]/p/a")).click();
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }catch (Exception e){

        }
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/a/span[2]")).click();//Kursu Düzenle butonu
        if(option == 1) {
            BufferedReader bufferedReader = null;
            BufferedReader bufferedReaderntv = null;
            Scanner scanner = null;
            Scanner scannerNtv = null;
            fileCreatorAndWriter(directory,fileName,fileName2,sourceFile);
            try{
                File foreignFile = new File(directory + "\\" + fileName);
                File nativeFile = new File(directory + "\\" + fileName2);
                bufferedReader = new BufferedReader(new FileReader(foreignFile));
                bufferedReaderntv = new BufferedReader(new FileReader(nativeFile));
                scanner = new Scanner(bufferedReader);
                scannerNtv = new Scanner(bufferedReaderntv);

                while(scanner != null && scannerNtv != null){
                    String addForeign = scanner.nextLine();
                    String addNative = scannerNtv.nextLine();
                    try {
                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        Thread.sleep(325);
                        driver.findElement(By.xpath("//*[@id=\"l_13392957\"]/div[3]/table/tbody[2]/tr[2]/td[2]/input")).sendKeys(addForeign);

                        driver.findElement(By.xpath("//*[@id=\"l_13392957\"]/div[3]/table/tbody[2]/tr[2]/td[3]/input")).sendKeys(addNative);
                        Thread.sleep(100);
                        driver.findElement(By.xpath("//*[@id=\"l_13392957\"]/div[3]/table/tbody[2]/tr[2]/td[1]/i")).click();
                        Thread.sleep(100);
                    }catch (Exception E){

                    }



                }
            }catch (Exception e){
                driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/a")).click();

            }finally {
                try{
                    bufferedReader.close();
                    bufferedReaderntv.close();
                    scanner.close();
                    scannerNtv.close();
                }catch (Exception e){

                }
            }
        }
        if(option == 2){


            String sil = "//*[@id=\"l_13392759\"]/div[3]/table/tbody[1]/tr[1]/td[1]/div/i[2]";//tr değiştir

            while (true){
                try {
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    driver.findElement(By.xpath(sil)).click();
                    driver.findElement(By.xpath("//*[@id=\"modal-yesno\"]/div/div/div[3]/a[2]")).click();
                }catch (Exception E){

                }
                //if(driver.findElement(By.xpath(sil)).isDisplayed() == false){
                  //  System.out.println("Selam gg");
                    //break;
                //}

            }
        }





    }
    public void fileCreatorAndWriter(String directory,String fileName,String fileName2,String sourceFile){
        File frgn = new File(directory + "\\" + fileName);
        File ntv = new File(directory + "\\" + fileName2);
        ArrayList<ArrayList<String>> flashCardPart = findGitMethod(directory + "\\" + sourceFile);
        try{
            if(frgn.createNewFile() && ntv.createNewFile()){
                PrintWriter writer = new PrintWriter(directory + "\\" + fileName, "UTF-8");
                System.out.println("Yaratıldı");
                for(String s : flashCardPart.get(0)){
                    writer.println(s);
                }
                writer.close();
                PrintWriter writer2 = new PrintWriter(directory + "\\" + fileName2, "UTF-8");
                for(String s : flashCardPart.get(1)){
                    writer2.println(s);
                }
                writer2.close();
            }else{
                System.out.println("Yaratılmış");
            }
        }catch (Exception e){

        }

    }


    public static void main(String[] args) {
        String chromeDrivePath = "C:\\Users\\AliDev\\Desktop\\JavaKütüphane\\chromedriver.exe";
        String directory = "C:\\Users\\AliDev\\Desktop\\Yeni";
        translate tr = new translate();
        tr.memriseElements(chromeDrivePath,directory,"foreign.txt","native.txt","GitKomut.txt",1);
        //tr.findGitMethod();
        //tr.fileCreatorAndWriter("C:\\Users\\AliDev\\Desktop\\Yeni","yeni2.txt","yeni3.txt","GitKomut.txt");


    }
}
