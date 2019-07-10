package ru.nsu.ccfit.gild.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigFileReader {
    ArrayList<StringList> stringList = new ArrayList<>();
    ArrayList<String> fileContent = new ArrayList<>();
    private class StringList {
        String name = null;
        int count = 0;

        StringList(String newName, int newCount) {
            name = newName;
            count = newCount;
        }

        public String getString() {
            return name;
        }

        public int getCount() {
            return count;
        }
    }

    public ArrayList<StringList> getStringList() {
        return stringList;
    }

    public int getCountByName(String key) {
      //  dataAnalyze();
        for(StringList str : stringList) {
            if(str.getString().equals(key)) {
                return str.getCount();
            }
        }
        return 0;
    }

    public ArrayList<StringList> getFileContent() {
        return stringList;
    }

    private void dataAnalyze() {
        String[] nameList = {"body", "motor", "accessory", "auto", "suppliers", "workers", "dealers"};
        for(String optionsName : nameList) {
            for (String s : fileContent) {
                if (s.toLowerCase().contains(optionsName)) {
                    stringList.add(new StringList(optionsName, Integer.parseInt(s.replaceAll("[^-?0-9]+", ""))));
                    break;
                }
            }
        }
        try {
            if (stringList.size() != nameList.length) throw new ExceptionInInitializerError("Error in config file! Not enough information.");
        } catch (ExceptionInInitializerError e) {
            System.out.println(e.getMessage());
        }
    }

    public ConfigFileReader(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNextLine()) {
            fileContent.add(scan.nextLine());
        }
        scan.close();
        dataAnalyze();
    }
}
