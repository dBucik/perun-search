package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;

import java.util.ArrayList;
import java.util.List;

public class DAOUtils {

    public static List<Attribute> convertAttrsFromInput(List<InputAttribute> input) {
        List<Attribute> result = new ArrayList<>();
        for (InputAttribute inputAttribute: input) {
            Attribute filter = new Attribute(inputAttribute.getKey(), inputAttribute.getValue());
            result.add(filter);
        }

        if (result.isEmpty()) {
            return null;
        }

        return result;
    }

}
