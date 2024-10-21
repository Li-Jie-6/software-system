package project2.Demo.demo2;

public class demo2 {
    public static void main(String[] args) {
        Input input = new Input();
        input.input("C:\\Users\\ASUS\\Desktop\\demo\\input.txt");
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("C:\\Users\\ASUS\\Desktop\\demo\\output.txt");

    }
}
