package com.raketlabs.util;

public class CRC {
    public static String CRCFFFF (String str) {
        
        str = cleanString(str);
        
        int crc = 0xFFFF;
        
        for (int c = 0; c < str.length(); c++) {
            crc ^= str.charAt(c) << 8;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0)
                    crc = (crc << 1) ^ 0x1021;
                else
                    crc = crc << 1;
            }
        }
        return String.format("%04X", (crc & 0xFFFF));
    }
    
    
    
    
    private static String cleanString (String str) {
        
        return str;
    }
}
