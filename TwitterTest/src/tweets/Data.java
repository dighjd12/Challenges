package tweets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import twitter4j.Status;

/**
 * The Class Data.
 */
public class Data {

	/** The tweets. */
	List<Status> tweets;
	
	/** The keyword frequency map. */
	Map<String,Integer> keywordFrequencyMap;
	
	/**
	 * Wrapper object for the tweet statuses
	 * Also does pre-calculation for analysis.
	 *
	 * @param tweets the tweets
	 */
	public Data(List<Status> tweets){
		this.tweets = tweets;
		keywordFrequencyMap = new HashMap<String,Integer>();
		map();
		
	}
	
	
	/**
	 * A Map<String,Integer> from a keyword to its frequency
	 * in all the different posts.
	 *
	 * @return a keyword frequency map
	 */
	public Map<String,Integer> getKeywordFreqMap(){
		return keywordFrequencyMap;
	}
	
	
	/**
	 * Gets the excluding words from the lib file
	 *
	 * @return the excluding words list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException the file not found exception
	 */
	private List<String> getExcludingWords() throws IOException, FileNotFoundException{
		BufferedReader br = null;
		List<String> excludingWords = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(
					"lib/excludingWords.txt"));
			String word = br.readLine();
			while (word != null) {
				excludingWords.add(word);
				word = br.readLine();
			}
		} finally {
			br.close();
		}
		
		return excludingWords;
	}
	
	/**
	 * Map.
	 */
	private void map(){
		
		List<String> excludingWords = new ArrayList<String>();
		try{
			excludingWords = getExcludingWords();
		} catch (Exception e){
			// do nothing if cannot read file
			System.out.println("couldn't read the file!");
		}
		
		//count each word
		for(Status p: tweets){
			String postBody = p.getText();
			String[] keywords = postBody.split("\\s+");
			
			for(String keyword: keywords){
				
				if(!excludingWords.contains(keyword.toLowerCase())){
					if(keywordFrequencyMap.containsKey(keyword)){
						int m=keywordFrequencyMap.get(keyword).intValue()+1;
						keywordFrequencyMap.replace(keyword, new Integer(m));
					}else{
						keywordFrequencyMap.put(keyword, new Integer(1));
					}
				}
				
			}
		}
		
		//sort by value: frequency of the keywords
		Map<String,Integer> tempKeywordMap = sortByComparator(keywordFrequencyMap);
		keywordFrequencyMap = tempKeywordMap;
		
	}
	
	/**
	 * Sort by the map by values, such that the greatest number comes first.
	 *
	 * @param unsortMap the unsorted map
	 * @return the sorted map
	 */
	private Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {
		 
		List<Map.Entry<String, Integer>> list = 
			new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
 
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
                                           Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
 
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
}
