package br.com.caelum.stella.validation.ie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.Validator;
import br.com.caelum.stella.validation.error.IEError;

public class IEBahiaValidatorTest {

    /*
     * 612345-57
     * 
     * 123456-63
     */

    private static final String wrongCheckDigitUnformattedNewString = "61234559";

    private static final String validUnformattedNewString = "61234557";

    private static final String validFormattedNewString = "612345-57";

    private static final String[] validValues = { validFormattedNewString, "123456-63" };

    private Validator<String> newValidator() {
        return new IEBahiaValidator();
    }

    @Test
    public void shouldHaveDefaultConstructorThatUsesSimpleMessageProducerAndAssumesThatStringIsFormatted() {
        newValidator().assertValid(validFormattedNewString);

        try {
            newValidator().assertValid(validFormattedNewString);
        } catch (RuntimeException e) {
            if (e instanceof InvalidStateException) {
                InvalidStateException invalidStateException = (InvalidStateException) e;
                String expected = "IEError : INVALID CHECK DIGITS";
                assertEquals(expected, invalidStateException.getInvalidMessages().get(0).getMessage());
            } else {
                fail();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotValidateIEWithInvalidCharacter() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);

        mockery.checking(new Expectations() {
            {
                exactly(1).of(messageProducer).getMessage(IEError.INVALID_DIGITS);
            }
        });
        Validator validator = new IEBahiaValidator(messageProducer, false);
        try {
            validator.assertValid(validUnformattedNewString.replaceFirst(".", "&"));
            fail();
        } catch (InvalidStateException e) {
            assertTrue(e.getInvalidMessages().size() == 1);
        }

        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotValidateIEWithLessDigitsThanAllowed() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);

        mockery.checking(new Expectations() {
            {
                exactly(1).of(messageProducer).getMessage(IEError.INVALID_DIGITS);
            }
        });
        Validator validator = new IEBahiaValidator(messageProducer, false);
        try {
            validator.assertValid(validUnformattedNewString.replaceFirst(".", ""));
            fail();
        } catch (InvalidStateException e) {
            assertTrue(e.getInvalidMessages().size() == 1);
        }

        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotValidateIEWithMoreDigitsThanAlowed() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);

        mockery.checking(new Expectations() {
            {
                exactly(1).of(messageProducer).getMessage(IEError.INVALID_DIGITS);
            }
        });
        Validator validator = new IEBahiaValidator(messageProducer, false);

        String value = validUnformattedNewString + "5";
        try {
            validator.assertValid(value);
            fail();
        } catch (InvalidStateException e) {
            assertTrue(e.getInvalidMessages().size() == 1);
        }

        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotValidateIEsWithCheckDigitWrong() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);

        mockery.checking(new Expectations() {
            {
                exactly(1).of(messageProducer).getMessage(IEError.INVALID_CHECK_DIGITS);
            }
        });
        Validator validator = new IEBahiaValidator(messageProducer, false);

        String value = wrongCheckDigitUnformattedNewString;
        try {
            validator.assertValid(value);
            fail();
        } catch (InvalidStateException e) {
            assertTrue(e.getInvalidMessages().size() == 1);
        }

        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldValidateValidIE() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);
        mockery.checking(new Expectations());
        Validator validator = new IEBahiaValidator(messageProducer, true);

        List<ValidationMessage> errors;

        for (String validValue : validValues) {
            try {
                validator.assertValid(validValue);
            } catch (InvalidStateException e) {
                fail();
            }
            errors = validator.invalidMessagesFor(validValue);
            assertTrue(errors.isEmpty());
        }
        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldValidateValidFormattedNovaIE() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);
        mockery.checking(new Expectations());
        Validator validator = new IEBahiaValidator(messageProducer, true);

        List<ValidationMessage> errors;

        String[] validValues = { validFormattedNewString };
        for (String validValue : validValues) {
            try {
                validator.assertValid(validValue);
            } catch (InvalidStateException e) {
                fail();
            }
            errors = validator.invalidMessagesFor(validValue);
            assertTrue(errors.isEmpty());
        }
        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldValidateNullIE() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);
        mockery.checking(new Expectations());
        Validator validator = new IEBahiaValidator(messageProducer, false);

        List<ValidationMessage> errors;
        String value = null;
        try {
            validator.assertValid(value);
        } catch (InvalidStateException e) {
            fail();
        }
        errors = validator.invalidMessagesFor(value);
        assertTrue(errors.isEmpty());

        mockery.assertIsSatisfied();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotValidateValidUnformattedIE() {
        Mockery mockery = new Mockery();
        final MessageProducer messageProducer = mockery.mock(MessageProducer.class);

        mockery.checking(new Expectations() {
            {
                exactly(1).of(messageProducer).getMessage(IEError.INVALID_FORMAT);
            }
        });
        Validator validator = new IEBahiaValidator(messageProducer, true);

        String value = validFormattedNewString.replace('-', ':');
        try {
            validator.assertValid(value);
            fail();
        } catch (InvalidStateException e) {
            assertTrue(e.getInvalidMessages().size() == 1);
        }

        mockery.assertIsSatisfied();
    }

}
