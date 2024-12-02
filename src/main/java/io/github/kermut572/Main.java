package io.github.kermut572;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * SIDC Downloader
 * author: @kermut572
 * */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        if(args.length < 2) {
            System.out.println("Usage: java -jar sidc-downloader.jar <start_year> <end_year> [threads]");
            System.exit(1);
        }

        int startYear = Integer.parseInt(args[0]);
        int endYear = Integer.parseInt(args[1]);
        int threads = args.length > 2 ? Integer.parseInt(args[2]) : 1;

        if(startYear > endYear) {
            System.out.println("Start year must be less than or equal to end year");
            System.exit(1);
        }

        if(endYear - startYear > 8) {
            ExecutorService executor = Executors.newFixedThreadPool(threads);

            for(int i = startYear; i <= endYear; i += 8) {
                int start = i;
                int end = Math.min(i + 7, endYear);
                executor.execute(new DownloadRunnable(start, end));
            }

            executor.shutdown();
            return;
        }

        DownloadUtils.downloadFiles(startYear, endYear);
    }


}