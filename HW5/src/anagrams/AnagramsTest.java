package anagrams;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	Anagrams a = new Anagrams();
	
	@Test
	void addWordTest() {
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		assertEquals(a.getMaxEntries().toString(),"[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
		assertEquals(a.getMaxEntries().get(0).getKey(), 236204078);
		assertThrows(IllegalArgumentException.class, () -> a.myHashCode(""));
		assertThrows(IllegalArgumentException.class, () -> a.addWord("alters"));
	}

}
