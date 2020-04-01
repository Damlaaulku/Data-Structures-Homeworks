import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FolderOperations {

	public static ArrayList<ArrayList<String>> readLines(String f)  {
		try {
		BufferedReader reader = new BufferedReader(new FileReader(f));

		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		ArrayList<String> line;
		String l;

		while ((l = reader.readLine()) != null) {
			String[] l2 =l.split(";");
			line= new ArrayList<String>();
			for (int i = 0; i < l2.length; i++) {
				line.add(l2[i]);
			}
			
			lines.add(line);
		}
		reader.close();

		return lines;
		}
		
		catch (IOException io) {
			System.out.println("Document is not found!");
			return new ArrayList<ArrayList<String>>();
		}
	}

}
