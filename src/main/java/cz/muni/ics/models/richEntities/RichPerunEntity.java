package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.List;

public interface RichPerunEntity {

	List<PerunAttribute> getAttributes();

	void setAttributes(List<PerunAttribute> attributes);

	List<PerunAttribute> getAttributesByKeys(List<String> keys);
}
