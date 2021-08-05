package DataDriving;

import java.io.IOException;
import java.util.ArrayList;

public class DummyClass {

	public static void main(String[] args) throws IOException {

		ReadingFromExcel read = new ReadingFromExcel();
		ArrayList<String> list = read.getData("Checkout");
		list.forEach((data) -> System.out.print(data + " "));

	}
}