package com.brightpod.steps;

import brightpod.SearchElement;

public class StepUtil {

    public static void searchElement(String element) {
        SearchElement searchElement = new SearchElement();
        searchElement.searchElementByName(element);
    }
}
