package interpreter;

import java.util.HashMap;
import java.util.Vector;

import values.Value;

/**
 * This class stores information about all arrays such as name and all elements
 * 
 * @author Martin Holecek
 * 
 */
public class ArrayStructure {
	private HashMap<String, Vector<Value>> arrays = new HashMap<String, Vector<Value>>();

	/**
	 * Add new array to the hash map
	 * 
	 * @param name   - identifier of the array
	 * @param params - elements of the array
	 */
	void addArray(String name, Vector<Value> params) {
		arrays.put(name, params);
	}

	/**
	 * Find if the array already exists
	 * 
	 * @param name - identifier of the array
	 * @return - vector of the values if the array exists, otherwise, return null
	 *         object
	 */
	Vector<Value> findArray(String name) {
		return arrays.get(name);
	}
}
