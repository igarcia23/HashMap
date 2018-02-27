import java.util.ArrayList;
import java.util.Collection;

public class IsaacHashMap<K,V> {
  
  Object []farm = new Object[20];
  int size= 0;
  
  private class chicken
  {
    K key;
    V value;
    public chicken (K kim, V vix)
    {
      key = kim;
      value = vix;
    }
    
  }
  
	public IsaacHashMap()
	{
		
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public V get(K key)
	{
		if (key == null) return null;
		int address= Math.abs(key.hashCode()%farm.length);
		chicken Ch =(chicken)farm[address];
		if ( Ch == null || ! Ch.key.equals(key) ) return null;
		return Ch.value;
	}
	
	public V remove(K key)
	{
		if (key == null) return null;
		int address= Math.abs(key.hashCode()%farm.length);
		chicken Ch =(chicken)farm[address];
		if ( Ch == null || !Ch.key.equals(key) ) return null;
		farm[address] = null;
		size--;
		return Ch.value;
	}
	
	public void put(K key, V value)
	{
	  if(key==null) return;
		int hash = Math.abs(key.hashCode()%farm.length);
		if(farm[hash]!=null){
		  if((((chicken)farm[hash]).key).equals(key)){
		    farm[hash]=new chicken(key,value);
		    return;
		  }
		  boolean done = false;
		  Object[] oldFarm= farm;
	  	while (!done){
		    boolean Fitting= true;
		    farm = new Object[farm.length*2];
		    for (Object o: oldFarm){
		      if (o!= null && Fitting){
		        int hasher= Math.abs(((chicken)o).key.hashCode()%farm.length);
		          if (farm[hasher]==null){
		            chicken item = (chicken)o;
		            farm[hasher]= item;
		        }
		        else{Fitting=false;}
		      }
		    }
		    if (Fitting) done= true;
		  }
		  put (key, value);
		  //size++;
	    return;
	  }
	  
	  farm[hash]=new chicken(key, value);
	  size++;
	  return;
	}

	
	
	public void clear()
	{
		farm= new Object[20];
		size=0;
	}
	
	public boolean containsKey(K key)
	{
	  if (key == null) return false;
		int address= Math.abs(key.hashCode()%farm.length);
		chicken Ch =(chicken)farm[address];
		if ( Ch == null || !key.equals(Ch.key) ) return false;
		return true;
	}
	
	public boolean containsValue(V value)
	{
	  for (Object thing : farm)
	  {
	    if (thing != null)
	    {
	      chicken Ch = (chicken)thing;
	      if ( (value == null && Ch.value == null ) || (Ch!=null&&Ch.value.equals(value))) return true;
	    }
	  }
		return false;
	}
	
	public Collection<V> values()
	{
	  ArrayList<V> lineup = new ArrayList<V>();
	  for ( Object thing : farm){
      if ( thing != null )
      {
        chicken c = (chicken)thing;
        lineup.add(c.value);
      }
	 }
	  
		return lineup;
	}
	
}
