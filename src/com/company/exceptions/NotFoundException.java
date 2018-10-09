package com.company.exceptions;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:12
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
