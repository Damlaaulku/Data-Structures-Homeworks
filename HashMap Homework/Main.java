import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
//		long startTime = System.currentTimeMillis();
		HashMap<String, ArrayList<Node<String, Integer>>> map = new HashMap<String, ArrayList<Node<String, Integer>>>(
				128);
		String DELIMITERS = "[-+=" + " " + // space
				"\r\n " + // carriage return line fit
				"1234567890" + // numbers
				"’'\"" + // apostrophe
				"(){}<>\\[\\]" + // brackets
				":" + // colon
				"," + // comma
				"‒–—―" + // dashes
				"…" + // ellipsis
				"!" + // exclamation mark
				"." + // full stop/period
				"«»" + // guillemets
				"-‐" + // hyphen
				"?" + // question mark
				"‘’“”" + // quotation marks
				";" + // semicolon
				"/" + // slash/stroke
				"⁄" + // solidus
				"␠" + // space?
				"·" + // interpunct
				"&" + // ampersand
				"@" + // at sign
				"*" + // asterisk
				"\\" + // backslash
				"•" + // bullet
				"^" + // caret
				"¤¢$€£¥₩₪" + // currency
				"†‡" + // dagger
				"°" + // degree
				"¡" + // inverted exclamation point
				"¿" + // inverted question mark
				"¬" + // negation
				"#" + // number sign (hashtag)
				"№" + // numero sign ()
				"%‰‱" + // percent and related signs
				"¶" + // pilcrow
				"′" + // prime
				"§" + // section sign
				"~" + // tilde/swung dash
				"¨" + // umlaut/diaeresis
				"_" + // underscore/understrike
				"|¦" + // vertical/pipe/broken bar
				"⁂" + // asterism
				"☞" + // index/fist
				"∴" + // therefore sign
				"‽" + // interrobang
				"※" + // reference mark
				"]";

		ArrayList<String> stopwords = FolderOperations
				.readLines(System.getProperty("user.dir") + "\\stop_words_en.txt");

		File dir = new File(System.getProperty("user.dir") + "\\bbc");

		for (int i = 0; i < dir.list().length; i++) {
			// System.out.println(f);

			String f = dir.list()[i];
			File dir2 = new File(System.getProperty("user.dir") + "\\bbc\\" + f);

			for (int j = 0; j < dir2.list().length; j++) {
				String f2 = dir2.list()[j];
				String content = FolderOperations.readFile(System.getProperty("user.dir") + "\\bbc\\" + f + "\\" + f2);

				String[] words = content.split(DELIMITERS);

				String filename=f+" "+f2;
				for (int k = 0; k < words.length; k++) {
					String word = words[k].toLowerCase();
					if (stopwords.indexOf(word) == -1 && word.length() > 0) {
						if (map.get(word) == null) {

							ArrayList<Node<String, Integer>> value = new ArrayList<Node<String, Integer>>();
							Node<String, Integer> node = new Node<String, Integer>(filename, 1);
							value.add(node);
							map.put(word, value);
						} else {
							ArrayList<Node<String, Integer>> list = map.get(word);
							boolean found = false;
							for (int l = 0; l < list.size(); l++) {
								Node<String, Integer> node = list.get(l);
								if (node.getKey().equals(filename)) {
									node.setValue(node.getValue() + 1);
									found = true;
									break;
								}
							}
							if (found == false) {
								Node<String, Integer> node = new Node<String, Integer>(filename, 1);
								map.get(word).add(node);
							}
						}
					}
				}

			}

		}
		
//		long endTime = System.currentTimeMillis();
//		long estimatedTime = endTime - startTime;
//		System.out.println(estimatedTime);

	// Search
		ArrayList<String> search = FolderOperations
				.readLines(System.getProperty("user.dir") + "\\1000.txt");
		
		for(int i=0; i<search.size(); i++) {
			
			String word=search.get(i);
			ArrayList<Node<String, Integer>> a = map.get(word);
			
			if(a!=null) {
				int docNum = a.size();
				System.out.println();
				System.out.println(word+" found in "+docNum + " documents!");
				
				for (int v = 0; v < a.size(); v++) {
					Node<String, Integer> node = a.get(v);
					System.out.println(node.getKey() + ":" + node.getValue());
				}
			}
			else {
				System.out.println();
				System.out.println(word+" not found");
			}
			
		}

		Scanner scn = new Scanner(System.in);

		while (true) {
			System.out.print("Please enter a word to search: ");
			String input = scn.next();
			

			ArrayList<Node<String, Integer>> a = map.get(input);
			if(a!=null) {
			int docNum = a.size();
			System.out.println(docNum + " " + "documents found!");
			
			for (int i = 0; i < a.size(); i++) {
				Node<String, Integer> node = a.get(i);
				System.out.println(node.getKey() + ":" + node.getValue());
			}
			}
			else {
				System.out.println();
				System.out.println(input+" not found!");
			}
			
		}

	}

}
