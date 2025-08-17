package app.netlify.rotaract.Web_Structure.dbConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class Serializer<T> {

    public T deserialize(final List<Object> row, final Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T instance = tClass.getDeclaredConstructor().newInstance();
        Field[] fields = tClass.getDeclaredFields();

        if (row.size() != fields.length) {
            throw new IllegalArgumentException("Row size must be equal to fields size");
        }

        int i = 0;
        for (var field : fields){
            field.setAccessible(true);
            Object value = row.get(i);

            if (value == null) {
                continue;
            }
            if("null".equals(value)){
                value = "";
            }

            var type = field.getType();

            try {
                field.set(instance, switch (type) {
                    case Class<?> t when t.equals(int.class) || t.equals(Integer.class)  -> Integer.parseInt(value.toString());
                    case Class<?> t when t.equals(double.class) || t.equals(Double.class) -> Double.parseDouble(value.toString());
                    case Class<?> t when t.equals(boolean.class) || t.equals(Boolean.class) -> Boolean.parseBoolean(value.toString());
                    case Class<?> t when t.equals(String.class) -> value;
                    default -> throw new UnsupportedOperationException("Type not supported: " + type.getName());
                });
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("Invalid number format: " + value);
            }
            i++;
        }
        return instance;
    }
    public List<Object> serialize(final T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        List<Object> converter = new ArrayList<>();
        for (var field : fields){
            field.setAccessible(true);
            converter.add(field.get(t));
        }

        return converter;
    }
}
