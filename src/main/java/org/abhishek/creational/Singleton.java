package org.abhishek.creational;


import java.io.*;

class DemoSingleton implements Serializable {

    /*
    When you serialize an object, Java includes the serialVersionUID in the serialized data.
    When you later deserialize the object:
    If the class’s serialVersionUID matches the one in the serialized data → Deserialization proceeds.
    If they don’t match → InvalidClassException is thrown.
     */
    private static final long serialVersionUID =1L;

    private String value  = "value";

    private DemoSingleton() {
        //so that outside classes can't call to instantiate
    }

    //This is thread-safe without requiring synchronized blocks.
    //The instance is created only when getInstance() is called, thanks to class loading behavior.
    private static class LazyHolder {
        private static final DemoSingleton INSTANCE = new DemoSingleton();
    }

    //Ensures that during deserialization, the singleton instance is maintained
    // (prevents breaking the pattern by creating a new object).
    protected Object readResolve() {
        return getInstance();
    }

    public static DemoSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

public class Singleton {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DemoSingleton instance = DemoSingleton.getInstance();
        instance.setValue("serialized");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/singleton.ser"));
        out.writeObject(instance);
        out.close();

        System.out.println("Object serialized.");

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/singleton.ser"));
        DemoSingleton deserializedObject = (DemoSingleton) in.readObject();
        in.close();
        System.out.println(deserializedObject.getValue());
    }

}
