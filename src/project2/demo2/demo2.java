package project2.demo2;

public class demo2 {
    public static void test(String fileName) {
        Input input = new Input();
        input.input(fileName);
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("src/project2/resouces/output.txt");

    }
}
