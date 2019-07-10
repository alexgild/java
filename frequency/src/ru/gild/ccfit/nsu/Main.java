package ru.gild.ccfit.nsu;

import java.util.Scanner;
import static ru.gild.ccfit.nsu.FrequencyCounter.getFrequency;
import static ru.gild.ccfit.nsu.CSVMaker.saveToCSV;
import static ru.gild.ccfit.nsu.FrequencyCounter.sortFreq;

public class Main {
    public static void main(String[] args){
        String fileName;
        Scanner in = new Scanner(System.in);

        fileName = in.next();//C:\Users\hiltchen\IdeaProjects\frequency\src\ru\gild\ccfit\nsu\text.txt
        saveToCSV(sortFreq(getFrequency(fileName)));
    }
}
