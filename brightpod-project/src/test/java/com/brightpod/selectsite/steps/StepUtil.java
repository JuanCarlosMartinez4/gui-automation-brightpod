package com.brightpod.selectsite.steps;

import brightpod.SearchPod;

public class StepUtil {

    public static void searchElement(String element) {
        SearchPod searchPod = new SearchPod();
        searchPod.searchElementByName(element);
    }
}
