import java.util.ArrayList;

public abstract class AbstractMap<Key, Value> {

	private int capacity, size = 0, prime = 33, loadFac = 50;
	public int colNum = 0;

	private String alphabet = " abcdefghýijklmnopqrstuvwxyz";

	private ArrayList<Node<Key, Value>> table;

	public AbstractMap(int capacity) {
		this.capacity = capacity;
		table = new ArrayList<Node<Key, Value>>(capacity);
		for (int i = 0; i < capacity; i++) {
			table.add(null);
		}
	}

//	private int SSF(Key k) {
//
//		String key = k.toString();
//		int total = 0;
//
//		for (int i = 0; i < key.length(); i++) {
//			char c = key.charAt(i);
//			int index = alphabet.indexOf(c);
//			total += index;
//		}
//		return total % (capacity - 1);
//	}

	private int PAF(Key k) {

		String key = k.toString();
		int len = key.length() - 1;
		int total = 0;

		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			int index = alphabet.indexOf(c);
			int value = Math.abs((int) (index * Math.pow(prime, len - i)));

			total += value;
		}

		return total % (capacity - 1);
	}

//	private int DH(Key k) {
//		int code = SSF(k);
//		//int code = PAF(k);
//		int d = 13 - (code % 13);
//		int i = 1;
//		int dh = code;
//		
//		if(code>=0) {
//		Node<Key, Value> v = table.get(code);
//		while (v != null) {
//			if (!v.getKey().equals(k)) {
//				//colNum++;
//				dh = (code + i * d) % capacity;
//				v = table.get(dh);
//				break;
//			}
//			i++;
//			if (i >= this.capacity)
//				break;
//		}
//		}
//		//System.out.println(colNum);
//		return dh;
//	}

	public void put(Key K, Value V) {
		// int index = SSF(K);
		int index = PAF(K);
		// int index = DH(K);
		if (index >= 0 && capacity >= 0) {
			Node<Key, Value> addToData = new Node<Key, Value>(K, V);

			// LP
			while (table.get(index) != null) {
				if (!table.get(index).getKey().equals(K)) {
					index++;
					// colNum++;
					if (index >= capacity) {
						index = 0;
					}
				} else {
					break;
				}
			}

			table.set(index, addToData);
			size++;

			int limit = capacity * loadFac / 100;
			if (size >= limit) {
				resize();
			}
		}
		// System.out.println(colNum);
	}

	public Value get(Key K) {
		// int index = SSF(K);
		int index = PAF(K);
		// int index = DH(K);
		if (index >= 0 && capacity >= 0) {

			// LP
			while (table.get(index) != null) {
				if (!table.get(index).getKey().equals(K)) {
					index++;

					if (index >= capacity) {
						index = 0;
					}
				} else {
					break;
				}
			}

			if (table.get(index) != null)

				return table.get(index).getValue();
			else
				return null;
		} else
			return null;

	}

	public void resize() {
		ArrayList<Node<Key, Value>> temp = table;
		capacity = capacity * 2 - 1;
		table = new ArrayList<Node<Key, Value>>(capacity);
		for (int i = 0; i < capacity; i++) {
			table.add(null);
		}
		size = 0;
		for (Node<Key, Value> i : temp) {
			if (i != null)
				put(i.getKey(), i.getValue());
		}
	}

	public void remove(Key K) {
		// int index = SSF(K);
		int index = PAF(K);

		// int index = DH(K);

		// LP
		while (table.get(index) != null) {
			if (!table.get(index).getKey().equals(K)) {
				index++;

				if (index >= capacity) {
					index = 0;
				}
			} else {
				break;
			}
		}
		// table.set(DHindex, null);
		table.set(index, null);
		size--;
	}
}
