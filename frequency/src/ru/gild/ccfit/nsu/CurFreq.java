package ru.gild.ccfit.nsu;

public class CurFreq {
    private int freq;
    private float freqInPercent;
    CurFreq(int newFreq, float newFreqInPercent) {
        freq = newFreq;
        freqInPercent = newFreqInPercent;
    }

    int getFreq() {
        return freq;
    }

    float getFreqInPercent() {
        return freqInPercent;
    }

    void setFreqInPercent(float newFreqInPercent) {
        freqInPercent = newFreqInPercent;
    }

    public CurFreq plus(CurFreq other) {
        freq += other.freq;
        return this;
    }
}