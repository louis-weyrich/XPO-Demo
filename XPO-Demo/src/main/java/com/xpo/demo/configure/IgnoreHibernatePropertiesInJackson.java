package com.xpo.demo.configure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class IgnoreHibernatePropertiesInJackson {

	public IgnoreHibernatePropertiesInJackson() {
		// TODO Auto-generated constructor stub
	}

}
