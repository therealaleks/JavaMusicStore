package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    private int numEntries;
    private int numBuckets;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        if (initialCapacity <= 0) throw new IndexOutOfBoundsException("The initial capacity should be greater than 0.");
        
        this.numEntries = 0;
        this.numBuckets = initialCapacity;
    	
        this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(numBuckets);
        
        initializeValues();
        //ADD YOUR CODE ABOVE THIS
    }
    
    private void initializeValues() {
    	while (buckets.size() < numBuckets) buckets.add(new LinkedList<HashPair<K, V>>());
    }
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    	if (key == null) { 
    		
    		throw new IllegalArgumentException("Key cant be null");
    	}
    	
    	LinkedList<HashPair<K,V>> bukeet = buckets.get(hashFunction(key));
    	
    	
    	
    	for (HashPair<K, V> pair : bukeet) {
            if (pair.getKey().equals(key)) {
                V old = pair.getValue();
                pair.setValue(value);
                return old;
            }
        }

        bukeet.add(new HashPair<>(key, value));

        numEntries++;
    	
    	
    	if (((double) numEntries / numBuckets) > 0.75) {
    		rehash();
    	}
        
    	return null;
    	
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE

    	if (key != null) {
    		LinkedList<HashPair<K,V>> list = buckets.get(hashFunction(key));
    	
    		for (HashPair<K,V> bok : list) {
    		
    			if (bok.getKey().equals(key)) {
    			
    				return bok.getValue();
    			}
    		}
    	}
    	
    	return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	if (key == null) {
    		return null;
    	}
    	
    	
    	LinkedList<HashPair<K,V>> list = buckets.get(hashFunction(key));
    	V old = null;
    	
    	for (int i=0; i<list.size(); i++){
    		if (list.get(i).getKey().equals(key)) {
    			old = list.get(i).getValue();
    			list.remove(i);
    			numEntries--;
    			return old;
    		}
    	}
    	
    	return old;
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() { 
        //ADD YOUR CODE BELOW HERE
    	ArrayList<LinkedList<HashPair<K, V>>> old = buckets;

        numBuckets *= 2;
        buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new LinkedList<>());
        }

        for (LinkedList<HashPair<K, V>> bucket : old) {
            for (HashPair<K, V> pair : bucket) {
             
                buckets.get(hashFunction(pair.getKey())).add(pair);
            }
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	//MyHashIterator it = new MyHashIterator();
    	ArrayList<K> k = new ArrayList<>();
    	
    	for (LinkedList<HashPair<K, V>> bucket : buckets) {
    		
            for (HashPair<K, V> pair : bucket) {
                k.add(pair.getKey());
            }
        }

        return k;
    
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    
    public ArrayList<V> values() { 
    /**
    change to make unique keys
    */
        //ADD CODE BELOW HERE
    	MyHashTable<V,V> newList=new MyHashTable<V,V>(this.numBuckets);
    	ArrayList<V> _vals=new ArrayList<V>();
    	for (LinkedList<HashPair<K,V>> i : this.buckets){
    		for (HashPair<K,V> j : i){
    			newList.put(j.getValue(), j.getValue());
    		}
    	}
    	for (LinkedList<HashPair<V,V>> i : newList.buckets){
    		for (HashPair<V,V> j : i){
    			_vals.add(j.getValue());
    		}
    	}
            return _vals;

        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;

        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        	entries = new LinkedList<HashPair<K,V>>();
        	
        	for (LinkedList<HashPair<K,V>> bok : buckets) {
    			for (HashPair<K,V> bokeet : bok) {
    				entries.add(bokeet);
    			}
    			
        	}
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
            
        	boolean doesit = (entries.size() > 0);
        	
        	return doesit;
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE

        	
        	return entries.removeFirst();
            
        	//ADD YOUR CODE ABOVE HERE
        }
        
    }
}