import java.util.*;


class HtmlParser {
    String[] urls;
    int[][] edges;
    HtmlParser(String[] u, int[][] e) {
        urls = u;
        edges = e;
    }
    List<String> getUrls(String url) {
        for (int i = 0; i < urls.length; i++) {
            if (urls[i].equals(url)) {
                List<String> ret = new ArrayList<>();
                for (int edge : edges[i]) {
                    ret.add(urls[edge]);
                }
                return ret;
            }
        }
        return new ArrayList<>();
    }
}
public class Main {
    public static List<String> crawl(String startUrl, HtmlParser htmlParser) {
        int end = startUrl.indexOf('/', 7);
        String hostname = end == -1 ? startUrl : startUrl.substring(0, end);
        HashSet<String> visited = new HashSet<>();
        Queue<String> urlQueue = new LinkedList<>();
        List<String> ans = new ArrayList<>();
        urlQueue.add(startUrl);
        String curUrl;
        while (!urlQueue.isEmpty()) {
            curUrl = urlQueue.poll();
            ans.add(curUrl);
            visited.add(curUrl);
            List<String> nexts = htmlParser.getUrls(curUrl);
            for (String next : nexts) {
                if (!visited.contains(next) && next.startsWith(hostname)) {
                    urlQueue.add(next);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] urls = new String[]{
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us"
        };
        int[][] edges = {{4}, {}, {0,1}, {1,2},{}};
        String startUrl = "http://news.yahoo.com/news/topics/";
        List<String> ans = crawl(startUrl, new HtmlParser(urls, edges));
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
