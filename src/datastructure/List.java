package datastructure;

//public interface List<T> {
//    int size();
//
//    default boolean isEmpty() {
//        return size() == 0;
//    }
//
//    void clear();
//
//    default void isElementsNull(T[] elements) {
//        if (elements == null) {
//            throw new IllegalArgumentException("error in Structure.List , elements is null ");
//        }
//    }
//
//    void add(int index, T element);
//
//    default void add(T element) {
//        add(size(), element);
//    }
//
//    T remove(int index);
//
//    T get(int index);
//
//    default int getIndex(T element) {
//        for (int i = 0; i < size(); i++) {
//            if (get(i).equals(element)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    void createList(T[] elements);
//
//    void reserve();
//
//    default String display() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[");
//        for (int i = 0; i < size(); i++) {
//            stringBuilder.append(get(i));
//            if (i != size() - 1) {
//                stringBuilder.append(",");
//            }
//        }
//        stringBuilder.append("]");
//        return stringBuilder.toString();
//    }
//}