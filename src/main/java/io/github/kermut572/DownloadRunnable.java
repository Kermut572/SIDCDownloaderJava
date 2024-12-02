package io.github.kermut572;

public class DownloadRunnable implements Runnable {
    private int startYear;
    private int endYear;

    public DownloadRunnable(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    @Override
    public void run() {
        try {
            DownloadUtils.downloadFiles(startYear, endYear);
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
