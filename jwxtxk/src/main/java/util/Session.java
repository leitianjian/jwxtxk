package util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Session {
    private String id;
    private String pw;
    private static final String verifyAddr = "https://cas.sustech.edu.cn/cas/login";
    private static final List<Header> defaultHeaders =
            new ArrayList<Header>(){{
                add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/75.0.3770.142 Chrome/75.0.3770.142 Safari/537.36"));
            }};


    private List<Cookie> cookies;

    private boolean loadConfig (){
        Properties loginProperties = new Properties();
        try {
            loginProperties.load(new FileInputStream("loginConfig.txt"));
            id = loginProperties.getProperty("student_id");
            pw = loginProperties.getProperty("password");
            return true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    private Session() {
        try {
            // first login to get execution code
            getExCode();
            login();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private volatile static Session instance = null;

    public static Session getInstance() {
        if (instance == null){
            synchronized (Session.class){
                if (instance == null){
                    instance = new Session();
                }
            }
        }
        return instance;
    }

    // using to get the execution code in web page
    private String getExCode () throws IOException{
        Document doc = Jsoup.connect(verifyAddr).get();
        Elements elements = doc.getElementsByAttributeValue("name", "execution");
        if (elements.size() > 0){
            return elements.first().attr("value");
        }
        return null;
    }

    private boolean login () throws IOException{
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultHeaders(defaultHeaders)
                .setDefaultCookieStore(cookieStore).build();
        String exeCode;

        try {
            exeCode = getExCode();
            HttpGet httpGet = new HttpGet(verifyAddr);
            CloseableHttpResponse response1 = httpClient.execute(httpGet);

            Header[] headers = httpGet.getAllHeaders();
            System.out.println(headers.length);
            for (Header h : headers){
                System.out.println("- " + h.toString());
            }

            try {
                HttpEntity entity = response1.getEntity();
                System.out.println("Login form get: " + response1.getStatusLine());
                EntityUtils.consume(entity);

                System.out.println("Initial set of Cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()){
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); ++ i){
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response1.close();
            }

            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI(verifyAddr))
                    .addParameter("username", "11711707")
                    .addParameter("password", "986753421")
                    .addParameter("execution", exeCode)
                    .addParameter("_eventId", "submit")
                    .build();

            CloseableHttpResponse response2 = httpClient.execute(login);

            try {
                HttpEntity entity = response2.getEntity();

                System.out.println("Login form get: " + response2.getStatusLine());

                cookies = cookieStore.getCookies();
//                System.out.println(new BufferedReader(new InputStreamReader(entity.getContent())).lines().collect(Collectors.joining("\n")));

            } finally {
                response2.close();
            }

            return false;
        } catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
        finally{
            httpClient.close();
        }
        return false;
    }

    public static void main(String[] args) throws Exception{
        Session s = Session.getInstance();
    }
}
