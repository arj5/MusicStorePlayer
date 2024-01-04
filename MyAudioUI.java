// Arjun Sharma
// Student Number: 501170334

import java.util.ArrayList;
import java.util.Scanner;
// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI {
	public static void main(String[] args) {
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your
		// mylibrary
		AudioContentStore store = new AudioContentStore();

		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		// try{
			while (scanner.hasNextLine()) {
				String action = scanner.nextLine();

				if (action == null || action.equals("")) {
					System.out.print("\n>");
					continue;
				} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;

				else if (action.equalsIgnoreCase("STORE")) // List all songs
				{
					store.listAll();
				} else if (action.equalsIgnoreCase("SONGS")) // List all songs
				{
					mylibrary.listAllSongs();
				} else if (action.equalsIgnoreCase("BOOKS")) // List all songs
				{
					mylibrary.listAllAudioBooks();
				} else if (action.equalsIgnoreCase("PODCASTS")) // List all songs
				{
					mylibrary.listAllPodcasts();
				} else if (action.equalsIgnoreCase("ARTISTS")) // List all songs
				{
					mylibrary.listAllArtists();
				} else if (action.equalsIgnoreCase("PLAYLISTS")) // List all play lists
				{
					mylibrary.listAllPlaylists();
				}
				// Download audiocontent (song/audiobook/podcast) from the store
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) {
					int index1 = 0;
					int index2 = 0;
					// get first index
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt()) {
						index1 = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					// get second index
					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt()) {
						index2 = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					// AudioContent content = store.getContent(index);
					// if (content == null)
					// System.out.println("Content Not Found in Store");

					// loop to download content from index1 to index2
						for (int i = index1; i <= index2; i++) {
						// try catch for if content already downloaded to library

							try {
								// grabbing content and downloading
								AudioContent content = store.getContent(i);
								mylibrary.download(content);
								// print statement for action
								System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
							} // catch for already downloaded song
							catch (SongAlreadyDownloaded e) {
								System.out.println(e.getMessage());
							} // catch for already downloaded audiobook
							catch (AudiobookAlreadyDownloaded e) {
								System.out.println(e.getMessage());
							}
							catch (contentDNE e) {
								System.out.println(e.getMessage());
								break;
							}

						}
				

				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song
				else if (action.equalsIgnoreCase("PLAYSONG")) {
					// Print error message if the song doesn't exist in the library

					int index = 0;// store index of song

					System.out.print("Song Number: ");// prompt
					if (scanner.hasNextInt()) {// checks for int inputs
						index = scanner.nextInt();// stores index
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// calls the method to play song and also if it does not exist (false) it will
					// give error message
					try {
						mylibrary.playSong(index);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) {
					// Print error message if the book doesn't exist in the library
					int index = 0;// to store audiobook index

					System.out.print("Audio Book Number: ");// prompt
					if (scanner.hasNextInt()) {// checks for int input
						index = scanner.nextInt();// stores index
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// calls the method to print chapter titles and also if it does not exist
					// (false) it will give error message
					try {
						mylibrary.printAudioBookTOC(index);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) {

					int index = 0;// store index of book
					int chapter = 0;// store chapter num

					System.out.print("Audio Book Number: ");// prompt
					if (scanner.hasNextInt()) {// if input is given
						index = scanner.nextInt();// save input into index
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Chapter: ");// same as above but for the chapter
					if (scanner.hasNextInt()) {
						chapter = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// calls the method to print chapter and also if does not exist (false) it will
					// give error message
					try {
						mylibrary.playAudioBook(index, chapter);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts,
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) {

				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts,
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) {

				}
				// Specify a playlist title (string)
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) {
					System.out.print("Playlist Title: "); // prompt
					String title = "";// to store playlist title
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					} // calls the method to play all in the playlist and also if it does not exists
						// (false) it will give error message

					try {
						mylibrary.playPlaylist(title);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Specify a playlist title (string)
				// Read the index of a song/audiobook/podcast in the playist from the keyboard
				// Play all the audio content
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) {
					System.out.print("Playlist Title: "); // prompt
					String title = "";// to store playlist title
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					}

					System.out.print("Content Number: "); // prompt
					int index = 0;// to store index for the playlist
					if (scanner.hasNextLine()) {// checks for input
						index = scanner.nextInt();// store the int
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					}

					// calls the method to play specific content in playlist and checks for valid
					// inputs - it will give error message

					try {
						mylibrary.playPlaylist(title, index);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Delete a song from the list of songs in mylibrary and any play lists it
				// belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) {

					int index = 0; // store index num

					System.out.print("Library Song #: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						index = scanner.nextInt();// store the int
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					} // calls the method to delete or error if invalid input is given
					try {
						mylibrary.deleteSong(index);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) {
					System.out.print("Playlist Title: "); // prompt
					String title = "";// to store playlist title
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					} // calls the method to make a playlist and also if it already existsit will give
						// error message

					try {
						mylibrary.makePlaylist(title);
					} catch (PlaylistExists e) {
						System.out.println(e.getMessage());
					}

				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
				{
					String title = "";
					System.out.print("Playlist Title: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// calls the method to print playlist or catches error if invalid input is given

					try {
						mylibrary.printPlaylist(title);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a
				// playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from
				// the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) {

					String title = "";// to store playlist title
					String type = "";// store content type
					int index = 0; // store index num

					System.out.print("Playlist Title: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Content Type [SONG, AUDIOBOOK]: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						type = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Library Content #: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						index = scanner.nextInt();// store the int
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					} // calls the method to add content to playlist or error if invalid input is given

					try {
						mylibrary.addContentToPlaylist(type, index, title);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) {
					String title = "";// to store playlist title

					int index = 0; // store index num

					System.out.print("Playlist Title: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					System.out.print("Playlist Content #: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						index = scanner.nextInt();// store the int
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

					} // calls the method to delete content to playlist or error if invalid input is
						// given
					try {
						mylibrary.delContentFromPlaylist(index, title);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}

				}

				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				} else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}

				// A2 CONTINUATION...
				else if (action.equalsIgnoreCase("SEARCH")) // search for audio content by title
				{
					String title = "";// to store title

					System.out.print("Title: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.nextLine();// store the string
					}
					// calling method
					try {
						store.printContentTitle(title);
					// catch fot non existing content
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
					
				} else if (action.equalsIgnoreCase("SEARCHA")) // search for audio content by artist
				{
					String name = "";// to store name

					System.out.print("Artist: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						name = scanner.nextLine();// store the string
					}
					// calling method
					try{
						store.printArtistSongs(name);
					// catch fot non existing content
					}catch(contentDNE e){
						System.out.println(e.getMessage());
					}
				} else if (action.equalsIgnoreCase("SEARCHG")) // search for audio content by genre
				{
					String genre = "";// to store genre

					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						genre = scanner.nextLine();// store the string
					}
					// calling method
					
					try{
						store.printGenreSongs(genre);
					// catch fot non existing content
					}catch(contentDNE e){
						System.out.println(e.getMessage());
					}
				} else if (action.equalsIgnoreCase("DOWNLOADA")) // download audio content by artists
				{
					String name = "";// to store name

					System.out.print("Artist Name: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						name = scanner.nextLine();// store the string
					}
					
					// get arraylist of songs by artist
					ArrayList<Integer> a = new ArrayList<Integer>();
					try{
						a = store.getArtistIndex(name);
					}catch (contentDNE e){
						System.out.println(e.getMessage());
					}
					// loop through array downloading songs
					for (int i = 0; i < a.size(); i++) {
						// try catch for if content already downloaded to library
						try {
							// grabbing content and downloading
							AudioContent content = store.getContent(a.get(i));
							mylibrary.download(content);
							// print statement for action
							System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
						} // catch for already downloaded song
						catch (SongAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						} // catch for already downloaded audiobook
						catch (AudiobookAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						}
						// catch fot non existing content
						catch (contentDNE e){
							System.out.println(e.getMessage());
						}

					}
				

				}

				else if (action.equalsIgnoreCase("DOWNLOADG")) // download audio content by genere
				{
					String genre = "";// to store genre

					System.out.print("Genre: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						genre = scanner.nextLine();// store the string
					}
					// get arraylist of songs from genre
					ArrayList<Integer> g = new ArrayList<Integer>();
					try{
						g = store.getGenreIndex(genre);
					}catch (contentDNE e){
						System.out.println(e.getMessage());
					}
					
					// loop through array downloading songs
					for (int i = 0; i < g.size(); i++) {
						// try catch for if content already downloaded to library
						try {
							// grabbing content and downloading
							AudioContent content = store.getContent(g.get(i));
							mylibrary.download(content);
							// print statement for action
							System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
						} // catch for already downloaded song
						catch (SongAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						} // catch for already downloaded audiobook
						catch (AudiobookAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						}
						// catch fot non existing content
						catch (contentDNE e){
							System.out.println(e.getMessage());
						}

					}
				
				}

				System.out.print("\n>");
			}
	
		scanner.close();

		
	}
}
