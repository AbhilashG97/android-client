package com.mifos.exceptions;

/**
 * Created by root on 13/2/18.
 */

public class FingerprintException extends Exception {

    public FingerprintException(Exception exception) {
        super(exception);
    }

}
