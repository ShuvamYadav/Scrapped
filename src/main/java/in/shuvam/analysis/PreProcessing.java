package in.shuvam.analysis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.io.Files;

public class PreProcessing {
	List<String> stopwords;
	List<String> allWords;
	String st="";
	public PreProcessing() {
		File file = new File("C:\\Users\\Shuvam\\Downloads\\stopwords.txt");
		 stopwords = Collections.emptyList();
		try {
			stopwords = Files.readLines(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String whenRemoveStopwords(String original) {
		original=original.replaceAll("[^a-zA-z\\s]","");
		original = Arrays.stream(original.split("\\s+")).distinct().collect(Collectors.joining(" "));
		allWords = Stream.of(original.toLowerCase().split(" "))
				.collect(Collectors.toList());
		allWords.removeAll(stopwords);
		st=allWords.stream().collect(Collectors.joining(" "));
		allWords.clear();
		return st;
	}

}
