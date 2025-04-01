package org.opendatamesh.dpds.extensions;

import org.opendatamesh.dpds.model.core.ComponentBase;

public class CustomDefinition extends ComponentBase {
    private String mySpecField1;
    private String mySpecField2;
    private String mySpecField3;

    public CustomDefinition() {
        //DO NOTHING
    }

    public String getMySpecField1() {
        return mySpecField1;
    }

    public void setMySpecField1(String mySpecField1) {
        this.mySpecField1 = mySpecField1;
    }

    public String getMySpecField2() {
        return mySpecField2;
    }

    public void setMySpecField2(String mySpecField2) {
        this.mySpecField2 = mySpecField2;
    }

    public String getMySpecField3() {
        return mySpecField3;
    }

    public void setMySpecField3(String mySpecField3) {
        this.mySpecField3 = mySpecField3;
    }
}
