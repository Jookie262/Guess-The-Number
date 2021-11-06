public class RandomNumber {
    private final int min = 1, max = 50;

    // Generate a Random Number
    public int generateNumber(){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
