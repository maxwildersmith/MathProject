import java.util.Objects;

/**
 * Created by Max on 5/1/2017.
 */
public class Letter implements Comparable<Letter>{
    private char letter;
    private double frequency;

    public Letter(char letter, double frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    public Letter(char letter) {
        this.letter = letter;
        frequency =1;
    }

    public char getLetter() {
        return letter;
    }
    public void freqInc(){
        frequency+=1;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public void percent(int total){
        frequency = frequency/total*100;
    }
    public double getFrequency() {
        return frequency;
    }
    @Override
    public String toString(){
        return letter +": "+frequency;
    }
    @Override
    public boolean equals(Object obj) {
        return ((Letter)obj).getLetter()==letter;
    }

    @Override
    public int compareTo(Letter o) {
        return (int)(100*(o.getFrequency()-frequency));
    }
}
