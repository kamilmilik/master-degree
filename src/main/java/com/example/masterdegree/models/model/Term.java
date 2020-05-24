package com.example.masterdegree.models.model;

import lombok.Value;

@Value
public class Term {

    private static final String NO_TERM = "0";

    String term;

    public Term(String term) {
        this.term = term;
    }

    private boolean isNoTerm() {
        return term.equals(NO_TERM);
    }

    public boolean compareTerm(String term) {
        return isNoTerm() || this.term.equals(term);
    }
}
