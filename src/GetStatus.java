import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetStatus {

    private static final String[] hostList = {
            "http://crunchify.com",
            "http://yahoo.com",
            "http://www.ebay.com",
            "http://google.com",
            "http://www.example.co",
            "https://paypal.com",
            "http://bing.com/",
            "http://techcrunch.com/",
            "http://mashable.com/",
            "http://thenextweb.com/",
            "http://wordpress.com/",
            "http://wordpress.org/",
            "http://example.com/",
            "http://sjsu.edu/",
            "http://ebay.co.uk/",
            "http://google.co.uk/",
            "http://www.wikipedia.org/",
            "http://en.wikipedia.org/wiki/Main_Page"
    };

    private static final ExecutorService es = Executors.newFixedThreadPool(12);

    public static void main(String[] args) {

        IntWrapper[] codes = new IntWrapper[hostList.length];

        for (int i = 0; i < hostList.length; ++i) {
            IntWrapper code = new IntWrapper();
            codes[i] = code;
            es.execute(new WebVerif(hostList[i], code));
        }

        es.shutdown();
    }

}
