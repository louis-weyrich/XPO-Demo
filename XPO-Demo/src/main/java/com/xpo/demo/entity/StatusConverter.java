package com.xpo.demo.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * 
 * @author lweyri
 *
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String>
{

	@Override
	public String convertToDatabaseColumn(Status attribute)
	{
		return attribute.name();
	}

	@Override
	public Status convertToEntityAttribute(String dbData)
	{
		return Status.valueOf(dbData);
	}

	

}
