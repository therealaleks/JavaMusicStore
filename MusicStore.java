package assignment4;

import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
    MyHashTable<String, Song> bitle;
    MyHashTable<String, ArrayList<Song>> autist;
    MyHashTable<Integer, ArrayList<Song>> anos;
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	autist = new MyHashTable<String, ArrayList<Song>>(2*songs.size());
    	bitle =  new MyHashTable<String, Song>(2*songs.size()); 
    	anos = new MyHashTable<Integer, ArrayList<Song>>(2*songs.size());
    	
    	for (Song s: songs) {
    		this.addSong(s);
    	}
 
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    	if (s == null) return;
        if(s.getTitle() != null) bitle.put(s.getTitle(), s);
        
        if (autist.get(s.getArtist()) == null || autist.get(s.getArtist()).size() == 0) 
        {
        	ArrayList<Song> artist = new ArrayList<Song>();
        	artist.add(s);
        	autist.put(s.getArtist(), artist);
        } else 
        {
        	autist.get(s.getArtist()).add(s);
        }
        
        if (anos.get(s.getYear()) == null || anos.get(s.getYear()).size() == 0) 
        {
        	ArrayList<Song> year = new ArrayList<Song>();
        	year.add(s);
        	anos.put(s.getYear(), year);
        } else 
        {
        	anos.get(s.getYear()).add(s);
        }
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	
        return bitle.get(title);
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    	if (autist.get(artist) != null) {
    		
    		return autist.get(artist);
    	}
        return new ArrayList<Song>();
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
    	if (anos.get(year) != null) {
    		
    		return anos.get(year);
    	}
        return new ArrayList<Song>();
        //ADD CODE ABOVE HERE
        
    }
}
