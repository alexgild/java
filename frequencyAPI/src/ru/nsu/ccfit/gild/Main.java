package ru.nsu.ccfit.gild;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) throws IOException {

        AtomicInteger i = new AtomicInteger();
        try (Stream<String> str = Files.lines(Paths.get("C:\\Users\\hiltchen\\repos\\oop_gild_16207\\Java\\frequencyAPI\\src\\ru\\nsu\\ccfit\\gild\\file.txt"))) {
            str.map(line -> line.split("\\s*[^a-zA-Z0-9]+"))
                    .flatMap(Arrays::stream)
                    .filter(word -> word.length() > 0)
                    .peek(e -> i.addAndGet(1))
                    .collect(groupingBy(identity(), counting())).entrySet()
                    .stream().sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .forEach(e ->System.out.format("%s, %d, %.2f\n", e.getKey(), e.getValue(), ((float)e.getValue() / i.get()) * 100));
        }
    }
}