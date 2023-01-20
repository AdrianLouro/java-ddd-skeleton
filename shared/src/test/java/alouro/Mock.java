package alouro;

import org.mockito.Mockito;

import java.lang.reflect.ParameterizedType;

public abstract class Mock<T> {

    protected T mock;

    public T mock() {
        if (this.mock == null) {
            this.mock = Mockito.mock(this.typeClass());
        }

        return this.mock;
    }

    private Class<T> typeClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
