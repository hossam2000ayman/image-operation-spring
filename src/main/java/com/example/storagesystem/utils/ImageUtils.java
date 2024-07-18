package com.example.storagesystem.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    // here I make 2 method
    // one is to compress the image when store to database  ((for uploading))
    // and the other method is decompressed when retrieve from database  ((for downloading))

    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 2024];
        while (!deflater.finished()) {
            int size = deflater.deflate(temp);
            outputStream.write(temp, 0, size);
        }

        outputStream.close();


        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] temp = new byte[4 * 2024];
        while (!inflater.finished()) {
            int count = inflater.inflate(temp);
            outputStream.write(temp, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }
}
