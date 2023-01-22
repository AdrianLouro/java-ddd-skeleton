package alouro.shared;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class ThrownMatcher extends TypeSafeMatcher<Runnable> {

    private final Class<? extends Throwable> expectedThrowableClass;
    private Class<? extends Throwable> thrownThrowableClass;


    public ThrownMatcher(final Class<? extends Throwable> expectedThrowableClass) {
        this.expectedThrowableClass = expectedThrowableClass;
    }

    public static Matcher<Runnable> thrown(final Class<? extends Throwable> expectedThrowableClass) {
        return new ThrownMatcher(expectedThrowableClass);
    }

    @Override
    protected boolean matchesSafely(final Runnable action) {
        try {
            action.run();

            return false;
        } catch (Throwable throwable) {
            this.thrownThrowableClass = throwable.getClass();

            return this.thrownThrowableClass.equals(expectedThrowableClass);
        }
    }

    @Override
    public void describeTo(final Description description) {
        final var thrownMessage = this.thrownThrowableClass != null
                ? this.thrownThrowableClass.getCanonicalName()
                : "nothing";

        description.appendText(
                "Should have thrown " +
                        this.expectedThrowableClass.getCanonicalName() +
                        " but threw " +
                        thrownMessage
        );
    }
}
