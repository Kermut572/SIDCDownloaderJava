package io.github.kermut572;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DownloadUtils {

    private static List<String> getFileUrls(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            String content = new String(inputStream.readAllBytes());
            Pattern pattern = Pattern.compile("href=\"(.*?\\.(?:png|jpg|jpeg|gif|pdf))\"");
            Matcher matcher = pattern.matcher(content);
            return matcher.results()
                    .map(matchResult -> urlString + matchResult.group(1))
                    .collect(Collectors.toList());
        }
    }

    private static void downloadSingleFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            Path filePath = Paths.get("downloads", Paths.get(url.getPath()).getFileName().toString());
            Files.createDirectories(filePath.getParent());
            Files.copy(inputStream, filePath);
        }
    }

    public static void downloadFiles(int startYear, int endYear) throws InterruptedException {
        String baseUrlString = "https://www.sidc.be/uset/data/drawings/";
        for (int year = startYear; year <= endYear; year++) {
            for (int month = 1; month <= 12; month++) {
                System.out.println("Downloading files for " + month + "/" + year);
                String dateString = String.format("%04d/%02d/", year, month);
                try {
                    List<String> fileUrls = getFileUrls(baseUrlString + dateString);
                    for (String fileUrl : fileUrls) {
                        downloadSingleFile(fileUrl);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(3000);
            }
        }
    }

}
