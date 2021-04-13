package in.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.io.Files;

public class PreProcessing {
	List<String> stopwords;

	public PreProcessing() {
		File file = new File("C:\\Users\\Shuvam\\Downloads\\stopwords.txt");
		 stopwords = Collections.emptyList();
		try {
			stopwords = Files.readLines(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String whenRemoveStopwordsUsingRemoveAll_thenSuccess(String original) {

		ArrayList<String> allWords = Stream.of(original.toLowerCase().split(" "))
				.collect(Collectors.toCollection(ArrayList<String>::new));
		allWords.removeAll(stopwords);
		return allWords.stream().collect(Collectors.joining(" "));
	}

}
