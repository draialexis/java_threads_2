import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebVerif implements Runnable {
    private final String url;
    private final IntWrapper status;

    public WebVerif(String url, IntWrapper status) {
        this.status = status;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            status.setValue(connection.getResponseCode());
        } catch (MalformedURLException e) {
            status.setValue(HttpURLConnection.HTTP_BAD_REQUEST);
            e.printStackTrace();
        } catch (IOException ex) {
            status.setValue(500);
        }
    }
}
