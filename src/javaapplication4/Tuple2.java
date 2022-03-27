package javaapplication4;
public class Tuple2<T1,T2> {
    public T1 o1;
    public T2 o2;
    public Tuple2(T1 object1,T2 object2){
        o1=object1;
        o2=object2;
    }
    @Override
    public String toString(){
        return "("+o1+", "+o2+")";
    }
}
