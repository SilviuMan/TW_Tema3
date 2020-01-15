package com.example.Tema_3.models;
import lombok.Data;
@Data
public class FormCommand {


    String[] multiCheckboxSelectedValues;

    String radioButtonSelectedValue;

    public String[] getMultiCheckboxSelectedValues() {
        return multiCheckboxSelectedValues;
    }

    public void setMultiCheckboxSelectedValues(String[] multiCheckboxSelectedValues) {
        this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
    }

    public String getRadioButtonSelectedValue() {
        return radioButtonSelectedValue;
    }

    public void setRadioButtonSelectedValue(String radioButtonSelectedValue) {
        this.radioButtonSelectedValue = radioButtonSelectedValue;
    }
}
