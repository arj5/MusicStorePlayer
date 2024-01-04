
// Arjun Sharma
// Student Number: 501170334
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	private ArrayList<AudioContent> contents;
	// create three maps for searching title, artists and genre

	private HashMap<String, Integer> titles;
	private HashMap<String, ArrayList<Integer>> artists;
	private HashMap<String, ArrayList<Integer>> genres;

	public AudioContentStore() {

		// A2 section

		// create three maps for searching title, artists and genre
		titles = new HashMap<String, Integer>();
		artists = new HashMap<String, ArrayList<Integer>>();
		genres = new HashMap<String, ArrayList<Integer>>();
		contents = new ArrayList<AudioContent>();
		// calling methods
		fillContents("store.txt");
		fillArtists();
		fillGenres();
		fillTitles();

	}

	private void fillContents(String filename) {

		// try and catch for file io expction
		try {
			// creating scanner and file with taking in param filename
			Scanner in = new Scanner(new File(filename));
			// while loop to go through file
			while (in.hasNextLine()) {
				// storting general information for audiocontents
				String contentType = in.nextLine();
				String id = in.nextLine();
				String title = in.nextLine();

				int year = in.nextInt();
				int length = in.nextInt();
				// using nextline after using nextInt
				in.nextLine();

				String artist = in.nextLine();
				String composer = in.nextLine();
				// creating information for song type audio content
				if (contentType.equalsIgnoreCase("SONG")) {
					// genre variables
					String genre = in.nextLine();
					Song.Genre realGenre = null;
					// checking for genre
					switch (genre) {
						case "POP":
							realGenre = Song.Genre.POP;
							break;

						case "ROCK":
							realGenre = Song.Genre.ROCK;
							break;

						case "JAZZ":
							realGenre = Song.Genre.JAZZ;
							break;
						case "CLASSICAL":
							realGenre = Song.Genre.CLASSICAL;
							break;
						case "HIPHOP":
							realGenre = Song.Genre.HIPHOP;
							break;
						case "RAP":
							realGenre = Song.Genre.RAP;
							break;
					}
					// brabbing lyrics of song through for loop
					int lines = in.nextInt();
					String music = "";

					for (int i = 0; i <= lines; i++) {
						music += in.nextLine() + "\n";
					}
					// adding song content and creating song object
					contents.add(
							new Song(title, year, id, contentType, music, length, artist, composer, realGenre, music));
				}
				// audiobook audiocontent type
				else if (contentType.equalsIgnoreCase("AUDIOBOOK")) {
					// var for num of titles
					int numTitles = in.nextInt();
					in.nextLine();
					// creating arraylist for titles and chapter text
					ArrayList<String> chapters = new ArrayList<String>();
					ArrayList<String> chaptersTitles = new ArrayList<String>();
					// adding chapter titles
					for (int i = 0; i < numTitles; i++) {
						chaptersTitles.add(in.nextLine());

					}
					String contentText = "";

					// adding chapter text to list chapters
					for (int i = 0; i < numTitles; i++) {
						int numLines = in.nextInt();
						in.nextLine();

						for (int j = 0; j < numLines; j++) {

							contentText += in.nextLine();

						}
						chapters.add(contentText);
						contentText = "";

					}
					// adding audiobook object in content list
					contents.add(new AudioBook(title, year, id, contentType, "", length, artist, composer,
							chaptersTitles, chapters));

				}

			}
			// closing scanner
			in.close();

			// catch for excpetion error
		} catch (FileNotFoundException e) {
			System.out.println(filename + " Not Found");
		}

	}

	private void fillArtists() {

		// creating temp variables which will add to the hashmap for artists
		ArrayList<Integer> numArtists = new ArrayList<Integer>();
		String name = "";
		// loops through contents
		for (int i = 0; i < contents.size(); i++) {
			// check for song or audiobook
			if (contents.get(i).getType().equalsIgnoreCase("SONG")) {
				// cast content to song
				Song s = (Song) contents.get(i);
				// save name
				name = s.getArtist();
				// add song num index
				numArtists.add(i + 1);

			}
			// follows same as above but for audiobook
			else if (contents.get(i).getType().equalsIgnoreCase("AUDIOBOOK")) {
				AudioBook a = (AudioBook) contents.get(i);
				name = a.getAuthor();
				numArtists.add(i + 1);
			}
			// checks if the artist name is already in hashmap
			if (!artists.containsKey(name)) {
				// puts name and song indexes into hashmap
				artists.put(name, numArtists);
			} else {
				// adding song num if artist already in hashmap
				numArtists = artists.get(name);
				numArtists.add(i + 1);
				artists.put(name, numArtists);

			}
			// reset index nums list
			numArtists = new ArrayList<Integer>();

		}

	}

	// follows same as above but for genres as the key
	public void fillGenres() {
		ArrayList<Integer> numGenre = new ArrayList<Integer>();
		String genre = "";
		for (int i = 0; i < contents.size(); i++) {
			// only songs have genres
			if (contents.get(i).getType().equalsIgnoreCase("SONG")) {
				Song s = (Song) contents.get(i);
				genre = s.getGenre().toString();
				numGenre.add(i + 1);

				if (!genres.containsKey(genre)) {
					genres.put(genre, numGenre);
				} else {
					numGenre = genres.get(genre);
					numGenre.add(i + 1);
					genres.put(genre, numGenre);

				}

			}

			numGenre = new ArrayList<Integer>();

		}

	}

	// same as fill arists but just uses a single integer rather than an integer
	// ArrayList
	private void fillTitles() {

		String title = "";
		int num = 0;
		for (int i = 0; i < contents.size(); i++) {
			// check for song
			if (contents.get(i).getType().equalsIgnoreCase("SONG")) {
				// creates song, gets title and index
				Song s = (Song) contents.get(i);
				title = s.getTitle();
				num = i + 1;
			}
			// same as above but for audiobook
			else if (contents.get(i).getType().equalsIgnoreCase("AUDIOBOOK")) {
				AudioBook a = (AudioBook) contents.get(i);
				title = a.getTitle();
				num = i + 1;
			}
			// checks if the title is not already downloaded, and adds to the hashmap
			if (!titles.containsKey(title)) {
				titles.put(title, num);
			}

		}

	}

	// prints out songs based on given genre
	public void printGenreSongs(String genre) {
		//if content does not exist
		if (!genres.containsKey(genre)) {
			throw new contentDNE(genre);
		}
		// gets int list for index of songs from genre
		ArrayList<Integer> numGenre = genres.get(genre);
		// loops and prints song info
		for (int i = 0; i < numGenre.size(); i++) {

			System.out.print((numGenre.get(i)) + ". ");
			contents.get(numGenre.get(i) - 1).printInfo();
			System.out.println();

		}

	}

	// prints out songs based on given artist
	public void printArtistSongs(String name) {
		//if content does not exist
		if (!artists.containsKey(name)) {
			throw new contentDNE(name);
		}

		// gets int list for index of songs from artist
		ArrayList<Integer> numSongs = artists.get(name);
		// loops and prints song info
		for (int i = 0; i < numSongs.size(); i++) {

			System.out.print((numSongs.get(i)) + ". ");
			contents.get(numSongs.get(i) - 1).printInfo();
			System.out.println();

		}

	}

	// prints out song based on given title
	public void printContentTitle(String title) {
		//if content does not exist
		if (!titles.containsKey(title)) {
			throw new contentDNE(title);
		}
		// gets song index from titles
		int numContent = titles.get(title);
		// prints song info
		System.out.print(numContent + ". ");
		contents.get(numContent - 1).printInfo();

	}

	// getter method that returns value of artist hashmap
	public ArrayList<Integer> getArtistIndex(String name) {
		// if content does not exist
		if (!artists.containsKey(name)) {
			throw new contentDNE(name);
		}
		return artists.get(name);

	}

	// getter method that returns value of genre hashmap
	public ArrayList<Integer> getGenreIndex(String genre) {
		// if content does not exist
		if (!genres.containsKey(genre)) {
			throw new contentDNE(genre);
		}
		return genres.get(genre);

	}

	public AudioContent getContent(int index) {
		if (index < 1 || index > contents.size()) {
			return null;
		}
		return contents.get(index - 1);
	}

	public void listAll() {
		for (int i = 0; i < contents.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

}
