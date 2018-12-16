package model;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class MyCookieHandler {

    private File cookieFile;
    private String fileName;

    public MyCookieHandler(String filename) {
        this.cookieFile = new File(filename);
        this.fileName = filename;
    }

    public void saveCookies(ChromeDriver browser){
        try{
            cookieFile.delete();
            cookieFile.createNewFile();
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bWrite = new BufferedWriter(fileWriter);

            for (Cookie ck : browser.manage().getCookies()) {
                bWrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+
                        ";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));

                bWrite.newLine();
            }

            bWrite.close();
            fileWriter.close();

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            browser.close();
        }
    }

    //method needs to examine cookie date in file and not only check if exists
    public boolean hasExpired(){
        //return cookieFile.exists();
        return false;
    }

    public void loadCookies(ChromeDriver browser) {

        browser.get("https://google.com");
        try{

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String strLine;
            while((strLine=bufferedReader.readLine()) != null){
                StringTokenizer token = new StringTokenizer(strLine, ";");

                while(token.hasMoreTokens()){
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;


                    String val;
                    if(!(val = token.nextToken()).equals("null")){
                        //Long date = Date.parse(val);
                        //Long date = Date.parse(val);
                        //expiry = new Date(val);

                        //"EEE MMM dd HH:mm:ss zzz yyyy"

                        SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
                        try {
                            Date d = f.parse(val);
                            long milliseconds = d.getTime();
                            //System.out.println("new date: " + d);
                            expiry = new Date(milliseconds);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    Boolean isSecure = Boolean.parseBoolean(token.nextToken());
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);

                    browser.manage().addCookie(ck);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public File getCookieFile() {
        return cookieFile;
    }


}
