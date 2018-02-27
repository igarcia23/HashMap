import java.util.*;

public class SeilerHashMap<K,V> {

  Object[] bank = new Object[20];
  int size = 0;
  
  private class Lock
  {
    K key;
    V value;
    
    public Lock(K kie, V vic)
    {
      key = kie;
      value = vic;
      
    }
    
  }
  

	public SeilerHashMap()
	{
		
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public V get(K key)
	{
		if ( key == null ) return null;
		int address = Math.abs(key.hashCode()%bank.length);
		Lock l = (Lock)bank[address];
		if ( l == null ) return null;
		if(l.key.equals(key))return l.value;
		return null;
	}
	
	public V remove(K key)
	{
		if ( key == null ) return null;
		int address = Math.abs(key.hashCode()%bank.length);
		Lock l = (Lock)bank[address];
		if ( l == null ) return null;
		bank[address] = null;
		size--;
		return l.value;
	}
	
	public void put(K key, V value)
	{
	  if(key==null) return;
	  int hash = Math.abs(key.hashCode()%bank.length);
	  if(bank[hash]!=null){
	    if((((Lock)bank[hash]).key).equals(key)){
	      bank[hash] = new Lock(key, value);
	      return;
	    }
	    boolean done = false;
	    Object[] oldBank = bank;
	    while(!done){
	      boolean allFit = true;
	      bank = new Object[bank.length*2];
  	    for (Object o: oldBank){
  	      if (o != null && allFit){
  	        int hash2 = Math.abs(((Lock)o).key.hashCode()%bank.length);
  	        if(bank[hash2]==null){
  	          Lock item = (Lock)o;
  	          bank[hash2] = item;
  	        }
  	        else{allFit = false;}
  	      }
  	    }
  	    if(allFit) done = true;
	    }
	    put(key, value);
	    return;
	  }
	  bank[hash] = new Lock(key, value);
	  size++;
	  return;
	}
	
	public void clear()
	{
		bank = new Object[20];
		size = 0; 
	}
	
	public boolean containsKey(K key)
	{
	  for ( Object stuff : bank )
		  {
		    if ( stuff != null)
		      {
		        Lock l = (Lock)stuff; 
		        if ( l.key.equals(key) ) return true; 
		      }
		  }
		  return false;
		//Another way?
		/*if ( key == null ) return false;
		int address = Math.abs(key.hashCode()%bank.length);
		Lock l = (Lock)bank[address];
		if(l == null||l.key.equals(key)) return false;
		return true;*/
	}
	
	public boolean containsValue(V value)
	{
		for ( Object stuff : bank )
		  {
		    if ( stuff != null)
		      {
		        Lock l = (Lock)stuff; 
		        if ( l.value.equals(value) ) return true; 
		      }
		  }
		  return false;
	}
	
	public Collection<V> values()
	{
		ArrayList<V> paper = new ArrayList<V>(); 
		for (Object o: bank){
		  if(o!=null){
		    paper.add(((Lock)o).value);
		  }
		}
		return paper;
	}
}
