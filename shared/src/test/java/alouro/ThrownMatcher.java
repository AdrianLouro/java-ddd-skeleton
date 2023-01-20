package alouro;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

public final class ThrownMatcher extends TypeSafeMatcher<Runnable> {

    private final Class expectedThrowableClass;
    private Optional<Class> thrownThrowableClass;


    public ThrownMatcher(final Class expectedThrowableClass) {
        this.expectedThrowableClass = expectedThrowableClass;
        this.thrownThrowableClass = Optional.empty();
    }

    public static Matcher thrown(final Class<? extends Throwable> expectedThrowableClass) {
        return new ThrownMatcher(expectedThrowableClass);
    }

    @Override
    protected boolean matchesSafely(final Runnable action) {
        try {
            action.run();

            return false;
        } catch (Throwable throwable) {
            this.thrownThrowableClass = Optional.of(throwable.getClass());

            return this.thrownThrowableClass.orElseThrow().equals(expectedThrowableClass);
        }
    }

    @Override
    public void describeTo(final Description description) {
        final var thrownMessage = this.thrownThrowableClass.isPresent()
                ? this.thrownThrowableClass.get().getCanonicalName()
                : "nothing";

        description.appendText(
                "Should have thrown " +
                        this.expectedThrowableClass.getCanonicalName() +
                        " but threw " +
                        thrownMessage
        );
    }
}
