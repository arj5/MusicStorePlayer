// Arjun Sharma
// Student Number: 501170334

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library {
	private ArrayList<Song> songs;
	private ArrayList<AudioBook> audiobooks;
	private ArrayList<Playlist> playlists;

	// private ArrayList<Podcast> podcasts;

	// Public methods in this class set errorMesg string
	// Error Messages can be retrieved from main in class MyAudioUI by calling
	// getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions


	public Library() {
		songs = new ArrayList<Song>();
		audiobooks = new ArrayList<AudioBook>();
		// ;
		playlists = new ArrayList<Playlist>();
		// podcasts = new ArrayList<Podcast>(); ;
	}

	/*
	 * Download audio content from the store. Since we have decided (design
	 * decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list)
	 * then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or
	 * AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already
	 * there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false.
	 * Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) {
		// if content does not exist
		if (content == null) {
			throw new contentDNE("this index");
			
		}

		// checks for type name
		if (content.getType().equals(Song.TYPENAME)) {
			// cast content to song
			Song s = (Song) content;
			if (songs.contains(content)) {
				// set error message by throwing new exception, taking in arg as well
				// this is followed throughout the library file depending on the error
				throw new SongAlreadyDownloaded(s.getTitle());

			} else {
				// add song to list
				songs.add(s);

			}

		}
		// follow same for audiobook
		else if (content.getType().equals(AudioBook.TYPENAME)) {
			AudioBook a = (AudioBook) content;
			if (audiobooks.contains(content)) {
				throw new AudiobookAlreadyDownloaded(a.getTitle());
			}

			else {
				audiobooks.add(a);

			}
		}

	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() {
		// loops through songs and prints info
		for (int i = 0; i < songs.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() {
		// loops through audiobooks and prints info
		for (int i = 0; i < audiobooks.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts() {

	}

	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists() {
		// loops through playlists and prints titles
		for (int i = 0; i < playlists.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". " + playlists.get(i).getTitle());
			System.out.println();
		}

	}

	// Print the name of all artists.
	public void listAllArtists() {
		// First create a new (empty) array list of string
		// Go through the songs array list and add the artist name to the new arraylist
		// only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists
		// names

		ArrayList<String> arr = new ArrayList<String>();// empty arraylist
		for (int i = 0; i < songs.size(); i++) {// loop through songs
			if (!(arr.contains(songs.get(i).getArtist()))) { // check name if not already there
				arr.add(songs.get(i).getArtist()); // add name
			}

		}
		// prints out artist names
		for (int i = 0; i < arr.size(); i++) {
			System.out.println((i + 1) + ". " + arr.get(i));
		}

	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well if it
	// is part of the playlist
	public void deleteSong(int index) {
		// checks for valid index
		if (index < 1 || index > songs.size()) {
			// errorMsg = "Song Not Found";
			// return false;
			throw new contentDNE("this index");
		}

		// remove song inside dif playlists;
		Playlist p = null;
		// searches for song in different playlists
		for (int i = 0; i < playlists.size(); i++) {
			ArrayList<AudioContent> a = playlists.get(i).getContent();
			for (int j = 0; j < a.size(); j++) {
				if (a.get(j).equals(songs.get(index - 1))) {

					p = playlists.get(i);
					// deletes song playlist
					p.deleteContent(j + 1);
				}
			}

		}

		// removes song from songs list
		songs.remove(index - 1);

	}

	// Sort songs in library by year
	public void sortSongsByYear() {
		// sorts songs in list by year using collections.sort and song year comparator
		Collections.sort(songs, new SongYearComparator());

	}

	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> {
		// takes in 2 songs
		public int compare(Song s1, Song s2) {
			// returns 1 if s1 greater
			if (s1.getYear() > s2.getYear()) {
				return 1;
			}
			// returns -1 if s2 greater
			else if (s1.getYear() < s2.getYear()) {
				return -1;
			}
			// if equal
			return 0;

		}

	}

	// Sort songs by length
	public void sortSongsByLength() {
		// sorts songs in list by song length using collections.sort and song length
		// comparator
		Collections.sort(songs, new SongLengthComparator());
	}

	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song> {
		// takes in 2 songs
		public int compare(Song s1, Song s2) {
			// returns 1 if s1 longer
			if (s1.getLength() > s2.getLength()) {
				return 1;
			}
			// returns -1 if s2 longer
			else if (s1.getLength() < s2.getLength()) {
				return -1;
			}
			// returns 0 if equal
			return 0;

		}

	}

	// Sort songs by title
	public void sortSongsByName() {
		// Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		// sort songs
		Collections.sort(songs);

	}

	/*
	 * Play Content
	 */

	// Play song from songs list
	public void playSong(int index) {
		if (index < 1 || index > songs.size()) {
			// errorMsg = "Song Not Found";
			// return false;
			throw new contentDNE("this index");
		}
		songs.get(index - 1).play();

	}

	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) {
		// check for valid index of audiobooks
		if (index < 1 || index > audiobooks.size()) {
			// errorMsg = "AudioBook Not Found";
			// return false;
			throw new contentDNE("this index");
		}

		// checks valid index of chapters
		if (chapter < 1 || chapter > audiobooks.get(index - 1).getNumberOfChapters()) {
			// errorMsg = "Chapter Not Found";
			// return false;
			throw new contentDNE("this index");
		}
		// prints audiobook information by setting index and current chapters
		audiobooks.get(index - 1).selectChapter(chapter);
		audiobooks.get(index - 1).play();
		// return true;
	}

	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) {
		if (index < 1 || index > audiobooks.size()) {
			// errorMsg = "AudioBook Not Found";
			// return false;
			throw new contentDNE("this index");
		}
		audiobooks.get(index - 1).printTOC();

	}

	/*
	 * Playlist Related Methods
	 */

	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) {
		// chekcs if playlist exists
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getTitle().equals(title)) {
				// errorMsg = "Playlist "+ playlists.get(i).getTitle()+" Already Exists";
				// return false;
				throw new PlaylistExists(playlists.get(i).getTitle());

			}
		}
		// creates playlist and adds to list
		Playlist p = new Playlist(title);
		playlists.add(p);

	}

	// Print list of content information (songs, audiobooks etc) in playlist named
	// title from list of playlists
	public void printPlaylist(String title) {
		// boolean variable to check if playlist exists
		Boolean check = false;
		Playlist p = null;
		// loop through playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// if it finds the given title it exists
			if (playlists.get(i).getTitle().equals(title)) {
				// setting the found playlist
				p = playlists.get(i);
				// sets true since playlist was found
				check = true;
				// stops loop since playlist was found
				break;
			}
		}
		// if playlist does not exist, sett the error message
		if (check == false) {
			// errorMsg = "Playlist does not Exist";
			// return false;
			throw new contentDNE(title);
		}

		// print out info for each content in playlist

		p.printContents();

	}

	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) {
		// boolean variable to check if playlist exists
		Boolean check = false;
		Playlist p = null;
		// loop through playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// if it finds the given title it exists
			if (playlists.get(i).getTitle().equals(playlistTitle)) {
				// setting the found playlist
				p = playlists.get(i);
				// sets true since playlist was found
				check = true;
				// stops loop since playlist was found
				break;
			}

		} // if playlist was not found, set error message and return false
		if (check == false) {
			// errorMsg = "Playlist does not Exist";
			// return false;
			throw new contentDNE(playlistTitle);
		}
		// call playAll method
		p.playAll();

	}

	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) {
		// boolean variable to check if playlist exists
		Boolean check = false;
		Playlist p = null;
		// loop through playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// if it finds the given title it exists
			if (playlists.get(i).getTitle().equals(playlistTitle)) {
				// setting the found playlist
				p = playlists.get(i);
				// sets true since playlist was found
				check = true;
				// stops loop since playlist was found
				break;
			}

		} // if playlist was not found, set error message and return false
		if (check == false) {
			// errorMsg = "Playlist does not Exist";
			// return false;
			throw new contentDNE(playlistTitle);
		}
		// checks for valid index
		if (!p.contains(indexInPL)) {
			// errorMsg = "Invalid index";
			// return false;
			throw new contentDNE("this index");
		}
		// calls play method
		p.play(indexInPL - 1);

	}

	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) {
		// boolean variable to check if playlist exists
		Boolean check = false;
		Playlist p = null;
		// loop through playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// if it finds the given title it exists
			if (playlists.get(i).getTitle().equals(playlistTitle)) {
				// setting the found playlist
				p = playlists.get(i);
				// sets true since playlist was found
				check = true;
				// stops loop since playlist was found
				break;
			}

		} // if playlist was not found, set error message and return false
		if (check == false) {
			// errorMsg = "Playlist does not Exist";
			// return false;

			throw new contentDNE(playlistTitle);
		}
		// adding content to the playlist based on type

		if (type.equalsIgnoreCase(Song.TYPENAME)) {
			// checks for valid index input
			if (index < 1 || index > songs.size()) {
				// errorMsg = "Song Not Found";
				// return false;

				throw new contentDNE("this index");
			}
			// adds song to playlist
			p.addContent(songs.get(index - 1));
		}
		// same as above for audiobooks
		else if (type.equalsIgnoreCase(AudioBook.TYPENAME)) {
			if (index < 1 || index > audiobooks.size()) {
				// errorMsg = "AudioBook Not Found";
				// return false;

				throw new contentDNE("this index");
			}

			p.addContent(audiobooks.get(index - 1));

		} else {
			// errorMsg = "Invalid content type";
			// return false;

			throw new contentDNE(type);

		}

	}

	// Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is
	// valid
	public void delContentFromPlaylist(int index, String title) {
		// boolean variable to check if playlist exists
		Boolean check = false;
		Playlist p = null;
		// loop through playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// if it finds the given title it exists
			if (playlists.get(i).getTitle().equals(title)) {
				// setting the found playlist
				p = playlists.get(i);
				// sets true since playlist was found
				check = true;
				// stops loop since playlist was found
				break;
			}

		} // if playlist was not found, set error message and return false
		if (check == false) {
			// errorMsg = "Playlist does not Exist";
			// return false;
			throw new contentDNE(title);
		}
		// checks for valid index
		if (!p.contains(index)) {
			// errorMsg = "Invalid index";
			// return false;
			throw new contentDNE("this index");
		}
		// deletes the content by calling method in playlist
		p.deleteContent(index);

	}

}


// creating error classes while extending RuntimeException

//if song alreadydownloaded
class SongAlreadyDownloaded extends RuntimeException {
	//takes in arg for the title to be used on the error message
	public SongAlreadyDownloaded(String title) {
		//creating message with super
		super("Song " + title + " already downloaded");

	}

}
//same as above but for Audiobook
class AudiobookAlreadyDownloaded extends RuntimeException {
	public AudiobookAlreadyDownloaded(String title) {

		super("Audiobook " + title + " already downloaded");

	}

}
//when content does not exist
class contentDNE extends RuntimeException {
	// takes string to specify the issue
	public contentDNE(String str) {

		super("No matches for " + str);

	}


}
// for when playlist has already been made
class PlaylistExists extends RuntimeException {
	public PlaylistExists(String title) {

		super("Playlist " + title + " already exists");

	}

}
