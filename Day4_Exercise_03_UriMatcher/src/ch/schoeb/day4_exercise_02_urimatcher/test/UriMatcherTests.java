package ch.schoeb.day4_exercise_02_urimatcher.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import android.content.UriMatcher;
import android.net.Uri;

public class UriMatcherTests extends TestCase {

	public void testbasic_match() {
		// Arrange
		int itemMatch = 1;
		UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI("ch.schoeb.dataprovider", "items", itemMatch);
		// TODO: Fix problem here
		
		Uri uriToMatch = Uri.parse("content://ch.schoeb.dataprovider/items");

		// Act
		int result = matcher.match(uriToMatch);

		// Assert
		assertEquals(itemMatch, result);
	}

	public void testadvanced_match() {
		int itemMatch = 1;
		UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI("ch.schoeb.dataprovider", "item/#", itemMatch);
		// TODO: Fix problem here

		List<Uri> urisToMatch = new ArrayList<Uri>();
		for (int id = 0; id < 50; id++) {
			urisToMatch.add(Uri.parse("content://ch.schoeb.dataprovider/item/" + id));
		}

		for (Uri uriToMatch : urisToMatch) {
			int result = matcher.match(uriToMatch);
			assertEquals(itemMatch, result);
		}
	}

	public void testmatch_unknownuri() {
		// Arrange
		UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI("ch.schoeb.dataprovider", "myitems", 1);
		Uri uriToMatch = Uri.parse("content://ch.schoeb.dataprovider/anything");

		// Act
		int result = matcher.match(uriToMatch);

		// Assert
		// TODO: Fix problem here
		assertEquals(UriMatcher.NO_MATCH, result);
	}
}
