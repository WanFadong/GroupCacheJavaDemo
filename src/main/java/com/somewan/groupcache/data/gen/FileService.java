package com.somewan.groupcache.data.gen;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by wan on 2017/2/27.
 */
@Service
public class FileService {

    private BufferedReader reader;

    public void initFileReader(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }

}
