package ru.gild.ccfit.nsu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.*;
import static java.lang.Character.*;
class FrequencyCounter {
    static Map<String, CurFreq> getFrequency(String name) {
        Map<String, CurFreq> frequency = new HashMap<>();
        try (Reader reader = new InputStreamReader(new FileInputStream(name))){
                StringBuilder currentWord = new StringBuilder();
                //read the data
                int ch = -1;
                int wordCount = 0;

                while((ch = reader.read()) != -1) {
                    char currentChar = (char) ch;

                    if(isLetterOrDigit(currentChar)) {
                        currentWord.append(currentChar);
                        continue;
                    }

                    if(currentWord.toString().equals("")) {
                        continue;
                    }

                    CurFreq firstEntrance = new CurFreq(1,0);
                    frequency.merge(currentWord.toString(), firstEntrance, CurFreq::plus);
                    ++wordCount;
                    currentWord = new StringBuilder();
                }

                 if(!frequency.isEmpty()) {

                    for (Map.Entry<String, CurFreq> entry : frequency.entrySet()) {
                        entry.getValue().setFreqInPercent((float) entry.getValue().getFreq() / wordCount);
                        frequency.put(entry.getKey(), entry.getValue());
                    }
                } else {
                    System.err.println("Error! The file " + name + "is empty.\n");
                }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }

        return frequency;
    }

    static TreeSet<Map.Entry<String, CurFreq>> sortFreq(Map<String, CurFreq> frequency) {
       TreeSet<Map.Entry<String, CurFreq>> sortFrequency = new TreeSet<>(
               (o1, o2) -> {
                   if(o2.getValue().getFreq() != o1.getValue().getFreq()) {
                        return Integer.compare(o2.getValue().getFreq(), o1.getValue().getFreq());
                   } else {
                       return o1.getKey().compareTo(o2.getKey());
                   }
               }
       );
       sortFrequency.addAll(frequency.entrySet());
       return sortFrequency;
    }
}