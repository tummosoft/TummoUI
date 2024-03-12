package com.tummosoft.control.image;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageDownload {
    private ProgressBar progressBar;
    private TextView progressText;
    private ExecutorService executorService;
    private Handler handler;
    
    private void downloadFile(String fileUrl) {
        executorService.execute(() -> {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(fileUrl).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        long fileSize = body.contentLength();
                        InputStream inputStream = body.byteStream();
                       // FileOutputStream outputStream = new FileOutputStream(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/downloaded_file.zip");
                        byte[] buffer = new byte[1024];
                        long total = 0;
                        int count;

                        while ((count = inputStream.read(buffer)) != -1) {
                            total += count;
                            int progress = (int) (total * 100 / fileSize);

                            // Cập nhật giao diện người dùng trên luồng chính
                            updateUI(() -> {
                                progressBar.setProgress(progress);
                                progressText.setText(progress + "%");
                            });

                            outputStream.write(buffer, 0, count);
                        }

                        outputStream.flush();
                        outputStream.close();
                        inputStream.close();

                        // Tải thành công
                        updateUI(() -> progressText.setText("Tải thành công"));
                    } else {
                        // Lỗi trong quá trình tải
                        updateUI(() -> progressText.setText("Lỗi khi tải"));
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    // Xử lý lỗi khi thất bại
                    updateUI(() -> progressText.setText("Lỗi khi tải"));
                }
            });
        });
    }

    private void updateUI(Runnable runnable) {
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đảm bảo rằng executorService đã được shutdown khi không cần thiết nữa
        if (executorService != null) {
            executorService.shutdown();
        }
    }

}
