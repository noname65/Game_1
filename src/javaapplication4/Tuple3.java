package javaapplication4;
public class Tuple3<T1,T2,T3> extends Tuple2<T1,T2>{
    public T3 o3;
    public Tuple3(T1 object1,T2 object2,T3 object3){
        super(object1,object2);
        o3=object3;
    }
    @Override
    public String toString(){
        return "("+o1+", "+o2+", "+o3+")";
    }
}
