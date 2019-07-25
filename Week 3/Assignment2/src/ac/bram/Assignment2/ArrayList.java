package ac.bram.Assignment2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {

	private E[] data;
	private int size = 0;
	private int maxSize = 10000;

	public ArrayList() {
		data = (E[])new Object[maxSize];
	}

	@Override
	public boolean add(E e) {
		data[size++] = e;
		return true;
	}

	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		if (index >= 0 && index < size) {
			if (data[index] != null) {
				return data[index];
			}
		} else {
			//throw new IndexOutOfBoundsException();
			System.out.println("Index not found");
		}
		return null;
	}

	@Override
	public E set(int index, E element) {
		if (index >= 0 && index < size) {
			data[index] = element;
		} else {
			//throw new IndexOutOfBoundsException();
			System.out.println("Index not found");
		}
		return null;
	}

	@Override
	public void clear() {
		data = (E[])new Object[maxSize];
		this.size = 0;
	}

	@Override
	public void add(int index, E element) {
		if (index >= 0 && index < size) {
			size++;
			if(data[index] == null) {
				data[index] = element;
			} else {
				for (int i = size - 1; i > index; i--) {
					data[i] = data[i - 1];
				}
				data[index] = element;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public E remove(int index) {
		if (index >= 0 && index < size) {
			size -= 1;
			data[index] = null;
			for(int i = index; i < maxSize - 1; i++) {
				data[i] = data[i + 1];
				if(data[i] == null) {
					break;
				}
			}
		} else {
			//throw new IndexOutOfBoundsException();
			System.out.println("Index not found");
		}
		return null;
	}

	@Override
	public boolean contains(Object o) {
		for(int i = 0; i < size; i++) {
			if (data[i] != null) {
				if(data[i].equals(o)) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		if (this.contains(o)) {
			for(int i = 0; i < maxSize; i++) {
				if (data[i] != null) {
					if(data[i].equals(o)) {
						this.remove(i);
						break;
					}
				} else {
					break;
				}
			}
			return true;
		} else {
			System.out.println(o.toString() + " not found");
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E v : c) {
			if (v != null) {
				this.add(v);
			} else {
				break;
			}
		}
		return false;
	}

	@Override
	public int indexOf(Object o) {
		if (this.contains(o)) {
			for(int i = 0; i < size; i++) {
				if (data[i] != null) {
					if(data[i].equals(o)) {
						return i;
					}
				} else {
					break;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object v : c) {
			if (!(this.contains(v))) {
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object v : c) {
			if (this.contains(v)) {
				this.remove(v);
			}
		}
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index >=0 && index < size) {
			for (E v : c) {
				this.add(index, v);
				index++;
			}
		} else {
			//throw new IndexOutOfBoundsException();
			System.out.println("Wrong index");
		}
		return false;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (this.contains(o)) {
			for (int i = size - 1; i >= 0; i--) {
				if (data[i].equals(o)) return i;
			}
		}
		return -1;
	}

	@Override
	public Object[] toArray() {

		Object[]ret = new Object[size];
		for (int i = 0; i < size; i++) {
			ret[i] = data[i];
		}

		return ret;
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		
		for (int i = 0; i < size; i++) {
			a[i] = (T) data[i];
		}

		return a;
	}

	@Override
	public Iterator<E> iterator() {
		
		E[]ret = (E[])new Object[size];
		for (int i = 0; i < size; i++) {
			ret[i] = data[i];
		}
		Iterator<E> it = Arrays.stream(ret).iterator();
		
		return it;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < toIndex 
				&& fromIndex >= 0 && fromIndex < size &&
				fromIndex >= 0 && fromIndex < size) {
			List<E> ret = new java.util.ArrayList<E>();
			for (int i = fromIndex; i <= toIndex; i++) {
				ret.add(data[i]);
			}
			return ret;
		} else {
			System.out.println("Check your index");
		}
		return null;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		List<E> temp = new java.util.ArrayList<E>();
		for (int i = 0; i < size; i++) {
			if (c.contains(data[i])) {
				temp.add(data[i]);
			}
		}
		this.clear();
		for (E v : temp) {
			this.add(v);
		}
		return false;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
}