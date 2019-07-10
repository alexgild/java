package ru.nsu.ccfit.gild.factory;
import java.util.UUID;

public interface Detail {
    UUID detID = null;
    String name = "detail";
    String getName();
    default UUID getID() {return detID;}
}
