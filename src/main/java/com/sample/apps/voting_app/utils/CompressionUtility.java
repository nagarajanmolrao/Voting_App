package com.sample.apps.voting_app.utils;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompressionUtility {

    public static byte[] compressData(byte[] data) throws Exception {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        byte[] compressedData = new byte[data.length];
        int compressedDataLength = deflater.deflate(compressedData);
        deflater.end();

        byte[] result = new byte[compressedDataLength];
        System.arraycopy(compressedData, 0, result, 0, compressedDataLength);
        log.debug("Image was compressed successfully !!");
        return result;
    }

    public static byte[] decompressData(byte[] compressedData) throws Exception {
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);

        byte[] decompressedData = new byte[compressedData.length * 2];
        int decompressedDataLength = inflater.inflate(decompressedData);
        inflater.end();

        byte[] result = new byte[decompressedDataLength];
        System.arraycopy(decompressedData, 0, result, 0, decompressedDataLength);
        log.debug("Image was decompressed successfully !!");
        return result;
    }
}