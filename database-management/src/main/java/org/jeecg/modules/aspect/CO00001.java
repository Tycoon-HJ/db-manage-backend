package org.jeecg.modules.aspect;

/**
 * @author mr.ahai
 */

public enum CO00001 {

    YES("Y", "是"),
    NO("N", "否");

    private final String value;

    private final String label;

    CO00001(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return label;
    }

}
