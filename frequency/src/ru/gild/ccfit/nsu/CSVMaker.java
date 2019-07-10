package ru.gild.ccfit.nsu;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class CSVMaker {
    public static void saveToCSV(TreeSet<Map.Entry<String, CurFreq>> frequency){
        try (FileOutputStream out = new FileOutputStream("result.csv")) {
            for (Map.Entry<String, CurFreq> entry : frequency) {
               List<String> parameters = Arrays.asList(entry.getKey(), Integer.toString(entry.getValue().getFreq()), Double.toString(entry.getValue().getFreqInPercent()));
                out.write((String.join(", ", parameters) + Character.toString('\n')).getBytes());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}