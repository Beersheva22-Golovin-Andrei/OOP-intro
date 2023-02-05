package mycollection;


public abstract class AbstractMap<K, V> implements Map<K,V> {

	 protected Set<Entry<K, V>> set;
	 
	    @Override
		public V put(K key, V value) {
			V res = null;
			Entry<K, V> entry = set.get(new Entry<>(key, null));
			if (entry != null) {
				res = entry.getValue();
				entry.setValue(value);
			} else {
				set.add(new Entry<>(key, value));
			}
			return res;
		}

		@Override
		public V putIfAbsent(K key, V value) {
			V res = null;
			Entry<K,V> entryForSearch = new Entry<>(key, value);
			Entry<K,V> entryResult = set.get(entryForSearch);
			if (entryResult == null) {
				set.add(entryForSearch);
			} else {
				res = entryResult.getValue();
			}
			return res;
		}

		@Override
		public V get(K key) {
			V res = null;
			Entry<K, V> entry = set.get(new Entry<>(key, null));
			if (entry != null) {
				res = entry.getValue();
			}
			return res;
		}

		@Override
		public V getOrDefault(K key, V value) {
			V res = get(key);
			return res == null ? value : res;
		}

		@Override
		public boolean containsKey(K key) {
			return set.contains(new Entry<>(key, null));
		}

		@Override
		public boolean containsValue(V value) {
			Collection <V> setOfValues = values();
			return setOfValues.contains(value);
		}

		@Override
		public Collection<V> values() {
			Collection<V> collection = new LinkedList<V>();
			set.stream().forEach(n -> collection.add(n.getValue()));
			return collection;
		}

		@Override
		public Set<K> keySet() {
			try {
				@SuppressWarnings("unchecked")
				Set<K> res = set.getClass().getConstructor().newInstance();
				set.forEach(n -> res.add(n.getKey()));
				return res;
			} catch (Exception e) {
				throw new IllegalStateException();
			} 
			
		}

		@Override
		public Set<Entry<K, V>> entrySet() {
			try {
				@SuppressWarnings("unchecked")
				Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
				set.forEach(entry -> res.add(entry));
				return res;
			} catch (Exception e) {
				throw new IllegalStateException();
			} 
		}

		@Override
		public V remove(K key) {
			V res = null;
			Entry <K,V> removableEntry = set.get(new Entry<>(key, null));
			if (removableEntry!=null) {
				set.remove(removableEntry);
				res= removableEntry.getValue();
			}
			return res;
		}
}
